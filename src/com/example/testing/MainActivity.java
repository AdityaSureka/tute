package com.example.testing;


import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.view.ViewPager;
import android.util.Log;

public class MainActivity extends FragmentActivity {
	
	public MySQLiteHelper db = new MySQLiteHelper(this);

	
	// A helper class to keep a list of the Fragments and titles.
	private ViewPager pager            = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new NotifySer().execute();
}
	
private void dbToFragment(){
	final List<Fragment> flist = new ArrayList<Fragment>();
	String selectQ = "SELECT *FROM "+MySQLiteHelper.TABLE_QUESTIONS ;
	MySQLiteHelper qDb = new MySQLiteHelper(getApplicationContext());
	SQLiteDatabase sqlDb = qDb.getReadableDatabase();
	Cursor cursor = sqlDb.rawQuery(selectQ, null);
	
	if(cursor.moveToFirst()){
		do{
			String problem = cursor.getString(1);
			String opt1 = cursor.getString(3);

			
			
			flist.add(QuestionsFragment.addItem(problem, opt1));
		}while(cursor.moveToNext());
	}

	QuestionsPagerAdapter qpa;

	 qpa = new QuestionsPagerAdapter(getSupportFragmentManager(), flist);
	 ViewPager viewPager = (ViewPager)findViewById(R.id.pager);
	 viewPager.setAdapter(qpa);

}

public class NotifySer extends AsyncTask<Void, Void, Void>
{
	JSONParser jsonParser = new JSONParser();


	 public String question,ans,opta,optb,optc,optd;
	 private static final String TAG_QUESTION = "question";
	 private static final String TAG_ANSWER = "answer";
	 private static final String TAG_OPTIONA = "opta";
	 private static final String TAG_OPTIONB = "optb";
	 private static final String TAG_OPTIONC = "optc";
	 private static final String TAG_OPTIOND = "optd";


	protected Void doInBackground(Void... arg0) {
		 try
      {
		List<NameValuePair> params = new ArrayList<NameValuePair>(); 
    	//params.add(new BasicNameValuePair("flag","1"));
	  for (int i=1;i<=10;i++)
		{
	    String j= String.valueOf(i);
    	params.add(new BasicNameValuePair("msg_idd",j));
	    JSONObject json = jsonParser.makeHttpRequest("http://illusionroms.com/hello.php","POST", params);
	     question = json.getString(TAG_QUESTION);
	     ans = json.getString(TAG_ANSWER);
	     opta = json.getString(TAG_OPTIONA);
	     optb = json.getString(TAG_OPTIONB);
	     optc = json.getString(TAG_OPTIONC);
	     optd = json.getString(TAG_OPTIOND);
	    //int success = json.getInt(TAG_SUCCESS);
	    Log.d( "JSON REply1",question); 
	    Log.d( "JSON REply2",ans); 
	    Log.d( "JSON REply3",opta); 
	    Log.d( "JSON REply4",optb); 
	    Log.d( "JSON REply5",optc); 
	    Log.d( "JSON REply6",optd);
	    Log.d("Insert: ", "Inserting .."); 
        db.addQuestion(new Questions(question, ans, opta, optb, optc, optd));
	    //String arr[][]={ans,opta};
		}
	    } 
		 catch (Exception e) 
    {
		e.printStackTrace();
    }	
    return null;
}
	
	protected void onPostExecute(Void result)
	{
		dbToFragment();
        super.onPostExecute(result);
	}
}


/*
		// Add any number of items to the list of your Fragment
		pageAdapter.addItem(data, title);
		pageAdapter.addItem("https://m.bbc.co.uk", "WebView 2");
		pageAdapter.addItem("http://thorbek.net", "WebView 3");

		pager = (ViewPager)findViewById(R.id.pager);

		// This gives the number of Fragments loaded outside the view. 
		// Here set to the number of Fragments minus one, i.e., all Fragments loaded.
		// This might not be a good idea if there are many Fragments.
		pager.setOffscreenPageLimit(pageAdapter.getCount() - 1);
		pager.setAdapter(pageAdapter);
		*/
}