/***************************************ID BLOCK***********************************************

Due Date:	 April 22nd, 2012
Software Designers:	Alex Simard & Peter Johnston
Course:	 420-603 Winter 2012
Deliverable:	 Assignment #4 Part I - Client
Description:	 This Android app communicates with the server from part II.
A user logs in using the IP address of the server, an unauthenticated username,
and a room name. Once logged in, the user sees a list of all the messages
posted in that room. Users can then post new messages and refresh the list of
messages to see an updated list of all the messages on the server.

 ******************************************************************************************************/
package tmp.test.pkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity
{

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.login);

		Button login = (Button)this.findViewById(R.id.btnLogin);
		login.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				SwitchToMessageList();
			}
		});
	}

	/************Method SwitchToPost************
	This method changes the current activity
	to the messagelist activity, where users
	can view all the messages for the current room.
	 */
	private void SwitchToMessageList()
	{
		TextView txtUser = (TextView)this.findViewById(R.id.txtID);
		TextView txtIP = (TextView)this.findViewById(R.id.txtIP);
		TextView txtRoom = (TextView)this.findViewById(R.id.txtRoomName);
		
		//if any of the textviews are empty
		if (txtUser.getText().length() == 0 || txtIP.getText().length() == 0 || txtRoom.length() == 0)
		{
			Toast t = Toast.makeText(getApplicationContext(), "Sorry, you cannot leave any fields blank", Toast.LENGTH_LONG);
			t.show();
		}
		else
		{
			Intent intent = new Intent(this, messagelist.class);
			intent.putExtra("user", txtUser.getText().toString());
			intent.putExtra("IP", txtIP.getText().toString());
			intent.putExtra("room", txtRoom.getText().toString());
			this.startActivity(intent);
		}
	}
}
