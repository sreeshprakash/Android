package com.example.cloudpress;

import com.example.cloudpress.DB.info;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBedit extends SQLiteOpenHelper {
	public static final int database_version=1;
	public String CREATE_QUERY = "CREATE TABLE "+info.TABLE_NAME +"("+info.USER_NAME+" TEXT ,"+info.PASSWORD+" TEXT ,"+info.MAIL_ID+" TEXT ,"+info.QUESTION+" TEXT ,"+info.ANSWER+" TEXT );";

	public DBedit(Context context) {
		super(context, info.DATABASE_NAME, null, database_version);
		Log.d("Processing Database Operations", "Database Created");
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_QUERY);
		Log.d("Processing Database Operations", "Table Created");
		// TODO Auto-generated method stub
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}
	
	public void insert(DBedit e, String name, String password, String mail, String question, String answer)
	{
		SQLiteDatabase SQ = e.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(info.USER_NAME, name);
		cv.put(info.PASSWORD, password);
		cv.put(info.MAIL_ID, mail);
		cv.put(info.QUESTION, question);
		cv.put(info.ANSWER, answer);
		long k = SQ.insert(info.TABLE_NAME, null, cv);
		Log.d("Processing Database Operations", "Row Inserted");
	}
	
	public Cursor pull(DBedit e)
	{
		SQLiteDatabase SQ = e.getReadableDatabase();
		String[] columns = {info.USER_NAME, info.PASSWORD, info.MAIL_ID, info.QUESTION, info.ANSWER};
		Cursor CR = SQ.query(info.TABLE_NAME, columns, null, null, null, null, null);
		return CR;
	}

}