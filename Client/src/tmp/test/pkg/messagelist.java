package tmp.test.pkg;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class messagelist extends ListActivity {

	//ArrayList<Locale> locales;
	ArrayList<String> names;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		//locales = new ArrayList<Locale>();
		names = new ArrayList<String>();
		names.add("hai");
		names.add("lol");
		names.add("wut");
		names.add("orly");
		names.add("boo");
		names.add("text");
		names.add("foo");
		names.add("bar");
		names.add("foobar");
		names.add("I");
		names.add("pity");
		names.add("the");
		names.add("fool");
		names.add("trololol");
		names.add("bazinga");
		names.add("hai");
		names.add("lol");
		names.add("wut");
		names.add("orly");
		names.add("boo");
		names.add("text");
		names.add("foo");
		names.add("bar");
		names.add("foobar");
		names.add("I");
		names.add("pity");
		names.add("the");
		names.add("fool");
		names.add("trololol");
		names.add("bazinga");
		names.add("lol");
		names.add("wut");
		names.add("orly");
		names.add("boo");
		names.add("text");
		names.add("foo");
		names.add("bar");
		names.add("foobar");
		names.add("I");
		names.add("pity");
		names.add("the");
		names.add("fool");
		names.add("trololol");
		names.add("bazinga");
		setContentView(R.layout.messagelist);
		//for(Locale l: Locale.getAvailableLocales()) {
			//locales.add(l);
			//names.add(l.getDisplayName());
		//}

		//ListView lv ;//= (ListView)findViewById(R.id.lvMsgsList);
		//lv.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));
		//ListActivity la = new ListActivity();
		//la.setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));
		//lv = la.getListView();
		//lv.
		setListAdapter(new ArrayAdapter<String>(this, R.layout.list_item, names));
		//for (int i = 0; i < locales.size(); i++)
		//{

		//}
		//lv.add
		//setContentView(R.layout.messagelist);
	}

}
