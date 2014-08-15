package com.testes.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

	SQLiteDatabase database;

	public static String TABLE_MESSAGES_READUSER        =   "messages";
	public static String COLUMN_MESSAGES_READ_USER      =   "useruid";
	public static String COLUMN_MESSAGE_READ_COUNT      =   "messageid";

	Context context;
	public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
			int version) {

		super(context, name, factory, version);
		database = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
//		String creatString = "CREATE TABLE IF NOT EXISTS simpletable" + 
//				"(_id INTEGER KEY AUTOINCREMENT, "
//				+ "name TEXT NOT NULL,"
//				+ "amount INTEGER NOT NULL);";
		
		final String DATABASE_MESSAGES_READUSER= "create table "
	            + TABLE_MESSAGES_READUSER 
	            + "(" 
	                + COLUMN_MESSAGES_READ_USER       + " text not null, " 
	                +COLUMN_MESSAGE_READ_COUNT       + " integer not null"
	            +");";
		

		db.execSQL(DATABASE_MESSAGES_READUSER);
	}

	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String dropString = 
				"DROP TABLE IF EXISTS simpletable;";
		db.execSQL(dropString);
		onCreate(db);

	}
	
	public String getUser(){
		
		Cursor userCursor = null;
		
		database.execSQL("SELECT * FROM " + TABLE_MESSAGES_READUSER + "  ");
		
		return "";
		
	}
	
	public Integer deleteContact(Integer id) {
	    SQLiteDatabase db = this.getWritableDatabase();
	    return db.delete(TABLE_MESSAGES_READUSER, "id = ? ",
	            new String[] { Integer.toString(id) });
	}
	
	public void createMessageReaduser(String user, int countMessage){
		ContentValues values = new ContentValues();
		values.put(  COLUMN_MESSAGES_READ_USER,user);
		values.put(  COLUMN_MESSAGE_READ_COUNT,countMessage);   
		database.insert(TABLE_MESSAGES_READUSER, null, values);
	}
	
}
