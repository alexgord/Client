package tmp.test.pkg;

import java.io.IOException;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
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
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

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
		LoadTextFromURLTask task = new LoadTextFromURLTask();
		task.execute();

	}

	private void SwitchToPost()
	{
		Intent intent = new Intent(this, postmessage.class);
		this.startActivity(intent);
	}
	
	private void ToastText(String i)
	{
		Toast t = Toast.makeText(getApplicationContext(),
				i, Toast.LENGTH_LONG);
		t.show();
	}
	
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
			setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, handler.getMessages()));
			messages = handler.getObjMessages();
			SetClickListener();
		}
		catch(Exception e)
		{
			ToastText("setlistadapter failed");
		}
	}
	
	private void SetClickListener()
	{
		ListView lv = this.getListView();
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				Message tmp = messages.get(position);
				String toToast = tmp.getName() + " " + tmp.getTime() + " " + tmp.getMessage();
				Toast t = Toast.makeText(getApplicationContext(), toToast, Toast.LENGTH_LONG);
				t.show();
			}
			
		});
	}
	
	private class LoadTextFromURLTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{

			try {
				URL url = new URL("http://pastebin.com/raw.php?i=8rg6fMPj");
				//URL url = new URL("http://linux-cs.johnabbott.qc.ca/~ian/cs603/alice/text_" + "en" + ".txt");
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
				return e.getMessage();//"ugh IOException";
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
