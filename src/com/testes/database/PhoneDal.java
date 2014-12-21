package com.testes.database;

import com.testes.data.Phone;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract.Constants;
import android.util.Log;

public class PhoneDal extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = Constants.DATA;

    private static final String BLOCKED_PHONES_TABLE = "BLOCKED_PHONES_TABLE";
    private static final String COMMENTS_TABLE = "COMMENTS_TABLE";


    private static final String KEY_ID = "id";
    private static final String KEY_PHONE = "KEY_PHONE";
    private static final String KEY_IS_BLOCKED = "KEY_IS_BLOCKED";


    public PhoneDal(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL statement to create book table

        String CREATE_BLOCKED_PHONES_TABLE = "CREATE TABLE "
                + BLOCKED_PHONES_TABLE + " ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "phone TEXT, "
                + "isBlocked BIT )";

        db.execSQL(CREATE_BLOCKED_PHONES_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (newVersion > oldVersion) {
            Log.w("MyAppTag", "Updating database from version " + oldVersion
                    + " to " + newVersion + " .Existing data will be lost.");
            // Drop older books table if existed
            db.execSQL("DROP TABLE IF EXISTS " + BLOCKED_PHONES_TABLE);

            // create fresh books table
            this.onCreate(db);
        }

    }

    public void addItem(Phone phone) {
        // 1. get reference to writable DB
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("phone", "234132");
        db.insert(BLOCKED_PHONES_TABLE, null, contentValues);
    }
}
