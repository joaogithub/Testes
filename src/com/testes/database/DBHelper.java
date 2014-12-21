package com.testes.database;

import java.util.ArrayList;

import com.testes.data.Cow;
import com.testes.data.Cow.Vacas;

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
	public static String VACAS_TABLE_NAME = "cows";

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
	
	public ArrayList<Cow> selectAllVacas() {
	    ArrayList<Cow> list = new ArrayList<Cow>();
	    Cursor cursor = database.query(VACAS_TABLE_NAME, 
	        null, null, null, null, null, Vacas.NOMBRE+" ASC");
	    
	    String [] columns = new String[] { "phone", "isBloacked" };
	    String phone = "21212";
	    
	    Cursor secCursor = database.query(VACAS_TABLE_NAME, 
		        null, "? = "+phone+" AND ? = 1", columns, null, null, Vacas.NOMBRE+" ASC");
	    
	    if (cursor.moveToFirst()) {
	         do {
	        	 Cow vaca = new Cow(cursor.getLong(0), cursor.getString(1),
	                  cursor.getString(2), cursor.getString(3), 
	                  cursor.getInt(4), cursor.getInt(5), cursor.getInt(6));

	            list.add(vaca);
	         } while (cursor.moveToNext());
	      }
	      if (cursor != null && !cursor.isClosed()) {
	     cursor.close();
	  }
	  return list;

	}
	
	public long insertVaca(Cow vaca) {
		  ContentValues values = new ContentValues();
		  values.put(Vacas.NOMBRE, vaca.getNombre());
		  values.put(Vacas.NUMERO_CORTO, vaca.getNumero_corto());
		  values.put(Vacas.NUMERO_COMPLETO, vaca.getNumero_completo());
		  values.put(Vacas.FECHA_NACIMIENTO, vaca.getFecha_nacimiento());
		  values.put(Vacas.PADRE, vaca.getPadre());
		  values.put(Vacas.MADRE, vaca.getMadre());
		  database = getWritableDatabase();
		  long id = database.insert(VACAS_TABLE_NAME, null, values);
		  return id;

		}
	
	public Integer deleteContact(Integer id) {
	    SQLiteDatabase db = getWritableDatabase();
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
