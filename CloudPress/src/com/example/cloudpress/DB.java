package com.example.cloudpress;

import android.provider.BaseColumns;

public class DB {
	public DB()
	{
		
	}
	public static abstract class info implements BaseColumns
	{
		public static final String USER_NAME= "user_name" ;
		public static final String PASSWORD= "password" ;
		public static final String MAIL_ID= "mail";
		public static final String QUESTION= "question";
		public static final String ANSWER= "answer";
		public static final String DATABASE_NAME= "user_details" ;
		public static final String TABLE_NAME= "users" ;
		 
		 
	}

}
