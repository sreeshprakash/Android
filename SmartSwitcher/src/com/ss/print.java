package com.ss;
import java.io.File;
import java.io.FileWriter;

import com.sreesh.ss.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;
public class print extends Activity { //log data
	 public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
	        setContentView(R.layout.main3);
	        final RadioButton radio1 = (RadioButton)findViewById(R.id.avai);
	        final RadioButton radio2 = (RadioButton)findViewById(R.id.navai);
	        File sdcard1 = Environment.getExternalStorageDirectory();
        	final File file = new File(sdcard1 + "/Test.txt");
        	if(file.exists()){
        		radio1.toggle();
        	}
        	else{
        		radio2.toggle();
        	}
	        Button resetloc = (Button) findViewById(R.id.resetloc);
	        resetloc.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	if(file.exists()){	            		
	            	File sdcard1 = Environment.getExternalStorageDirectory();
	            	File file1 = new File(sdcard1 + "/Test.txt");
	            	boolean deleted = file1.delete();
	            	Toast.makeText(getBaseContext(), "The locations have been reset", 
	                        Toast.LENGTH_SHORT).show();
	            	radio2.toggle();
	            	}
	            	else{
		            	Toast.makeText(getBaseContext(), "The locations have not been set", 
		                        Toast.LENGTH_SHORT).show();
	            	}
	            }
	        });  
	 }
}
