package tmp.test.pkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class login extends Activity {

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

	private void SwitchToMessageList()
	{
		TextView txtUser = (TextView)this.findViewById(R.id.txtID);
		TextView txtIP = (TextView)this.findViewById(R.id.txtID);
		TextView txtRoom = (TextView)this.findViewById(R.id.txtRoomName);

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
			this.startActivityForResult(intent, 0);
		}
	}

}
