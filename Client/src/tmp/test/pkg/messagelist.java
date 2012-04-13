package tmp.test.pkg;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class messagelist extends Activity {

	//ArrayList<Locale> locales;
	//ArrayList<String> names;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    
	    //names = new ArrayList<String>();
		//for(Locale l: Locale.getAvailableLocales()) {
			//locales.add(l);
			//names.add(l.getDisplayName());
		//}
		
		
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));
	    //ListView lv = (ListView)findViewById(R.id.lvMsgsList);
	    //for (int i = 0; i < locales.size(); i++)
		//{
	    	
		//}
	    //lv.add
	    setContentView(R.layout.messagelist);
	}

}
