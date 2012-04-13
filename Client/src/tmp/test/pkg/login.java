package tmp.test.pkg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
		Intent intent = new Intent(this, messagelist.class);
		//intent.putExtra("curr_lang", this.lang);
		this.startActivityForResult(intent, 0);
	}

}
