package tmp.test.pkg;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class postmessage extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.postmessage);
	    
		Button btnPost = (Button)this.findViewById(R.id.btnPost);
		btnPost.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Toast t = Toast.makeText(getApplicationContext(), "you clicked btnpost!", Toast.LENGTH_LONG );
				t.show();
			}
		});
	}

}
