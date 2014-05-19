package com.example.testing.db;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.testing.Questions;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MySQLiteHelper extends SQLiteOpenHelper {

  public final static String DATABASE_NAME = "javatut.db";
  public final static String DATABASE_PATH = "/data/data/com.aditya.java1/databases/";
  private static final int DATABASE_VERSION = 1;
  //public static final String TABLE_NOSYSTEM = "nosystem";
  public static final String TABLE_QUESTIONS = "questions";
  public static final String COLUMN_ID = "_id";
  public static final String COLUMN_QUESTIONS = "questions";
  public static final String COLUMN_ANSWER = "answer";
  public static final String COLUMN_OPTA = "opta";
  public static final String COLUMN_OPTB = "optb";
  public static final String COLUMN_OPTC = "optc";
  public static final String COLUMN_OPTD = "optd";
  
  private static final String KEY_ID = "id";
  private static final String KEY_QUESTIONS = "questions";
  private static final String KEY_ANSWER = "answer";
  private static final String KEY_OPTA = "opta";
  private static final String KEY_OPTB = "optb";
  private static final String KEY_OPTC = "optc";
  private static final String KEY_OPTD = "optd";
	Context context;

	SQLiteDatabase MySQLiteHelper;
	String dbPath = DATABASE_PATH + DATABASE_NAME;


  // Database creation sql statement
  private static final String DATABASE_CREATE = "create table "
      + TABLE_QUESTIONS + "(" + COLUMN_ID
      + " integer primary key autoincrement, " + COLUMN_QUESTIONS
      + " text not null, " + COLUMN_ANSWER
      + " text not null, " + COLUMN_OPTA
      + " text not null, " + COLUMN_OPTB
      + " text not null, " + COLUMN_OPTC
      + " text not null, " + COLUMN_OPTD
      + " text not null);";

  @Override
  public void onCreate(SQLiteDatabase database) {
    database.execSQL(DATABASE_CREATE);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    Log.w(MySQLiteHelper.class.getName(),
        "Upgrading database from version " + oldVersion + " to "
            + newVersion + ", which will destroy all old data");
    db.execSQL("DROP TABLE IF EXISTS " + COLUMN_QUESTIONS);
    onCreate(db);
  }
  
  
  public MySQLiteHelper(Context context) {
	  super(context, DATABASE_NAME, null, DATABASE_VERSION);
		this.context = context;
		 
	}
  
	private void copyDataBase() throws IOException{
		InputStream is = context.getAssets().open(DATABASE_NAME);
		OutputStream os = new FileOutputStream(dbPath);
		
		byte[] buffer = new byte[1024];
		int length;
		while((length = is.read(buffer)) > 0){
			os.write(buffer, 0, length);
		}
		
		os.flush();
		os.close();
		is.close();
	}
	
	private void openDataBase() throws SQLException{
		MySQLiteHelper = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
	}
	
	private boolean checkDatabase(){
		SQLiteDatabase checkDb = null;
		
		try{
			checkDb = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
		}catch(SQLiteException e){
			Toast.makeText(context, "Error opening database!", Toast.LENGTH_SHORT).show();
		}
		
		if(checkDb != null){
			return true;
		}
		
		return false;
	}


  
  public void addQuestion(Questions question) {
      SQLiteDatabase db = this.getWritableDatabase();

      ContentValues values = new ContentValues();
      values.put(KEY_QUESTIONS, question.getQuestions()); // Contact Name
      values.put(KEY_ANSWER, question.getans()); // Contact Phone
      values.put(KEY_OPTA, question.getopta()); // Contact Phone
      values.put(KEY_OPTB, question.getoptb()); // Contact Phone
      values.put(KEY_OPTC, question.getoptc()); // Contact Phone
      values.put(KEY_OPTD, question.getoptd()); // Contact Phone
      
      // Inserting Row
      db.insert(TABLE_QUESTIONS, null, values);
      db.close(); // Closing database connection
  }

} 