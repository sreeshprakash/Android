package com.ss;

import android.app.Activity;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.sreesh.ss.R;

public class Activity2 extends Activity {
	    /** Called when the activity is first created. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main1);
        
        Button next1 = (Button) findViewById(R.id.next1);
        next1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent1 = new Intent(view.getContext(), mapoptions.class);
                startActivityForResult(myIntent1,0);
            }
        });  
        Button reset = (Button) findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent1 = new Intent(view.getContext(), print.class);
                startActivityForResult(myIntent1,0);
            }
        });  
    final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
    Button togglebutton1=(Button)findViewById(R.id.radioButton1);
	Button togglebutton2=(Button)findViewById(R.id.radioButton2);
	Button togglebutton3=(Button)findViewById(R.id.radioButton3);
	togglebutton1.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
							// TODO Auto-generated method stub
			maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);

		}
	});
    togglebutton2.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			maudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);

			
		}
	});

	
	togglebutton3.setOnClickListener(new OnClickListener() {
		
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			maudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
		}
	});
}
}