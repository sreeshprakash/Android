package com.example.cloudpress;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registeration extends Activity implements OnClickListener {
	
	EditText USER_NAME, USER_PASSWORD, CONFIRM_PASSWORD, MAIL_ID, QUESTION, ANSWER;
	String name, pass, cpass, mail, quest, ans;
	Button REGISTER;
	Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registeration);
		
		USER_NAME = (EditText)findViewById(R.id.name);
		USER_PASSWORD = (EditText)findViewById(R.id.password);
		CONFIRM_PASSWORD = (EditText)findViewById(R.id.retypepass);
		MAIL_ID = (EditText)findViewById(R.id.mailid);
		QUESTION = (EditText)findViewById(R.id.editText1);
		ANSWER = (EditText)findViewById(R.id.editText2);
		REGISTER = (Button)findViewById(R.id.button11);
		REGISTER.setOnClickListener((OnClickListener) this); 
	}
	
	@Override
	public void onClick(View a) 
	{	
		Toast.makeText(getBaseContext(), "in", Toast.LENGTH_LONG).show();
		Toast.makeText(getBaseContext(), a.getId()
				, Toast.LENGTH_LONG).show();
		switch (a.getId())
		{
		
		case R.id.button11:
			Toast.makeText(getBaseContext(), "Am in", Toast.LENGTH_LONG).show();
			name = USER_NAME.getText().toString();
			pass = USER_PASSWORD.getText().toString();
			cpass = CONFIRM_PASSWORD.getText().toString();
			mail = MAIL_ID.getText().toString();
			quest = QUESTION.getText().toString();
			ans = ANSWER.getText().toString();
			
			if(!pass.equals(cpass))
			{
				Toast.makeText(getBaseContext(), "Password not matching", Toast.LENGTH_LONG).show();
				USER_NAME.setText("");
				USER_PASSWORD.setText("");
				CONFIRM_PASSWORD.setText("");
				
			}
			else
			{
				DBedit DB = new DBedit(context);
				DB.insert(DB,name,pass,mail,quest,ans);
				Toast.makeText(getBaseContext(), "Registration Successful", Toast.LENGTH_LONG).show();
				finish();
			}
			break;
		
		}
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.registeration, menu);
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
