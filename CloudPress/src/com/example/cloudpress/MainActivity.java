package com.example.cloudpress;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {
	EditText editText1;
	EditText editText2;
	Button login;
	Button signup;
	int counter=3;
	Context CTX = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void login(View v)
	{
		editText1 = (EditText)findViewById(R.id.editText1);
		editText2 = (EditText)findViewById(R.id.editText2);
		login = (Button)findViewById(R.id.login1);
		signup = (Button)findViewById(R.id.signup1);
		login.setOnClickListener((OnClickListener) this);
		signup.setOnClickListener((OnClickListener) this);	
		
	}
	
	public void showMessage(View v)
	{
		Toast.makeText(getApplicationContext(), "Invalid Username/Password", Toast.LENGTH_SHORT).show();
	}
	
	public void onClick(View v)
	{
		String name= editText1.getText().toString();
		String password = editText2.getText().toString();
		
		switch (v.getId()){
		case R.id.login1:
			DBedit e = new DBedit(CTX);
			Cursor CR = e.pull(e);
			CR.moveToFirst();
			boolean loginstatus = false;
			String NAME = "";
			
			do
			{
				if(name.equals(CR.getString(0))&&(password.equals(CR.getString(1))))
				{
					loginstatus = true;
					NAME = CR.getString(0);
				}
			}while(CR.moveToNext());
			
			if(loginstatus)
			{
				Toast.makeText(getApplicationContext(), "Login Success  \n Welcome "+NAME, Toast.LENGTH_SHORT).show();
				Intent i=new Intent (this,Second.class);
				System.out.println("check 1 --------");
				startActivity(i);
				finish();
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
				finish();
			}
			break;
			/*if (name.equals("a") && password.equals("b"))
					{
					Intent i=new Intent (this,AppTasks.class);
					startActivity(i);
					}
			else {
			      Toast.makeText(getApplicationContext(), "Wrong Credentials",Toast.LENGTH_SHORT).show();
			    	      counter--;
			    	      if(counter==0){
			    	    	 Toast.makeText(getApplicationContext(), "Maximum attempts Exceeded",Toast.LENGTH_LONG).show();
			    	         login.setEnabled(false);
			    	      }
			}*/
			case R.id.signup1:
				Intent j=new Intent (this,Registeration.class);
				startActivity(j);
				break;
				
			case R.id.button1:
			try {
				Toast.makeText(getApplicationContext(), "enc", Toast.LENGTH_SHORT).show();
				System.out.println("enc");
				com.example.cloudpress.EncryptFile.main(null);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				break;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
