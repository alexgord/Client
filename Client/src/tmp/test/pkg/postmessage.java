package tmp.test.pkg;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Scanner;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class postmessage extends Activity
{

	private String message;
	private String room;
	private String IP;
	private String user;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.postmessage);
		Bundle extras = getIntent().getExtras();
		user = extras.getString("user");
		IP = extras.getString("IP");
		room = extras.getString("room");
		message = "asdfasf";
		Button btnPost = (Button)this.findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				PostMessage();
			}
		});
	}

	/************Method ToastText************
	This method takes the text in the
	textview and makes a request to the
	server to add the message to the current
	room. 
	 */
	private void PostMessage()
	{
		TextView post = (TextView)this.findViewById(R.id.ETMessage);
		message = post.getText().toString();
		SendTextFromURLTask task = new SendTextFromURLTask();
		task.execute();
		finish();
	}


	/*********************************LoadTextFromURLTask CLASS INFO*****************************************

	This class is responsible for contacting the server and sending the message.
	The server will add this message to the current room.

	 *********************************************************************************************/
	private class SendTextFromURLTask extends AsyncTask<Void, Void, String>
	{

		@Override
		protected String doInBackground(Void... params)
		{
			try
			{
				//Encode the text so they can be properly sent via GET
				room = URLEncoder.encode(room, "UTF-8");
				user = URLEncoder.encode(user, "UTF-8");
				message = URLEncoder.encode(message, "UTF-8");
				String urlStr = "http://" + IP + ":8000/" + room + "/message?user=" + user + "&content=" + message;
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
			//nothing to do here. The server does all the work
		}	
	}
}
