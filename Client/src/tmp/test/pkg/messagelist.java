package tmp.test.pkg;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import android.view.View;

public class messagelist extends ListActivity
{
	ArrayList<Message> messages;
	ArrayList<String> strMessages;
	String user;
	String IP;
	String room;
	boolean addToMessages;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		messages = new ArrayList<Message>();
		strMessages = new ArrayList<String>();
		addToMessages = false;
		setContentView(R.layout.messagelist);
		Button post = (Button)this.findViewById(R.id.btnAdd);
		post.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SwitchToPost();
			}
		});

		Button refresh = (Button)this.findViewById(R.id.btnRefresh);
		refresh.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				addToMessages = true;
				LoadTextFromURLTask task = new LoadTextFromURLTask();
				task.execute();
			}
		});

		Bundle extras = getIntent().getExtras();

		user = extras.getString("user");
		IP = extras.getString("IP");
		room = extras.getString("room");
		LoadTextFromURLTask task = new LoadTextFromURLTask();
		task.execute();
	}

	/************Method SwitchToPost************
	This method changes the current activity
	to the postmessage activity, where users
	can post messages.
	 */
	private void SwitchToPost()
	{
		Intent intent = new Intent(this, postmessage.class);
		intent.putExtra("user",user);
		intent.putExtra("IP", IP);
		intent.putExtra("room", room);
		this.startActivity(intent);
	}

	/************Method ToastText************
	This method toasts the string sent to it.
	 */
	private void ToastText(String i)
	{
		Toast t = Toast.makeText(getApplicationContext(),
				i, Toast.LENGTH_LONG);
		t.show();
	}

	/************Method ParseText************
	This method parses the string sent to it.
	The XML within this string is parsed
	using the XMLStatHandler class. Once the
	parsing is finished, this method
	populates the messages and strMessages
	arraylists with the result.
	 */
	private void ParseText(String i)
	{
		System.setProperty("org.xml.sax.driver","org.xmlpull.v1.sax2.Driver");
		XMLReader reader = null;
		try
		{
			reader = XMLReaderFactory.createXMLReader();
		}
		catch (SAXException e)
		{
			ToastText(e.getMessage());
			System.exit(1);
		}

		XMLStatHandler handler = new XMLStatHandler();
		reader.setErrorHandler(handler);
		reader.setContentHandler(handler);
		try
		{
			InputSource is = new InputSource(new StringReader(i));
			reader.parse(is);
			if(handler.hasError())
			{
				ToastText("Fix the errors listed above...");
			}
		} 
		catch (Exception e)
		{
			ToastText(e.getMessage() + " could not be opened");
			//System.exit(1);
		}
		try
		{
			if (addToMessages)
			{
				messages.addAll(handler.getObjMessages());
				strMessages.addAll(handler.getMessages());
			}
			else
			{
				messages = handler.getObjMessages();
				strMessages = handler.getMessages();
			}
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, strMessages));
			SetClickListener();
			addToMessages = false;
		}
		catch(Exception e)
		{
			ToastText("setlistadapter failed");
		}
	}

	/************Method SetClickListener************
	This method sets the click listener
	on the listview in this activity.
	When a user clicks on an item in the
	listview, the information about
	that message is toasted to the screen.
	 */
	private void SetClickListener()
	{
		ListView lv = this.getListView();
		lv.setOnItemClickListener(new OnItemClickListener()
		{

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Message tmp = messages.get(position);
				Date d = new Date();
				long ms = Long.parseLong(tmp.getTime());
				d.setTime(ms);
				String toToast = tmp.getName() + " said:\n"  + tmp.getMessage()  + "\nat: " + d.toString();
				ToastText(toToast);
			}

		});
	}

	/*********************************LoadTextFromURLTask CLASS INFO*****************************************

	This class is responsible for contacting the server and retrieving the messages from the
	current room.  

	 *********************************************************************************************/
	private class LoadTextFromURLTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{
			try
			{
				long last = 0;
				if (messages.size() > 0)
				{
					//we need to know when the last message was written
					last = Long.parseLong(messages.get(messages.size() - 1).getTime());
				}
				String urlStr = "http://" + IP + ":8000/" + room + "/retrieve";

				if (last > 0)//If this is the first time loading the messages from the server
				{
					//make sure we only get messages since the time when the last message was written
					urlStr += "?since=" + last;
				}
				URL url = new URL(urlStr);
				URLConnection conn = url.openConnection();

				Scanner sc = new Scanner(conn.getInputStream());

				// neat trick for reading complete stream into String:
				//   http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html

				return sc.useDelimiter("\\A").next();
			} 
			catch (MalformedURLException e)
			{
				e.printStackTrace();
				return "ugh MalformedURLException";
			}
			catch (IOException e)
			{
				e.printStackTrace();
				return e.getMessage();
			}
			catch(Exception e)
			{
				return "ugh Exception";
			}
		}

		@Override
		protected void onPostExecute(String result)
		{
			ParseText(result);
		}
	}
}
