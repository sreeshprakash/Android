package com.ss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.*;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.graphics.drawable.Drawable;
import android.net.NetworkInfo.DetailedState;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.os.Vibrator;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.TimePicker.OnTimeChangedListener;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.*;
import android.content.*;
import com.sreesh.ss.R;


public class SsActivity extends Activity {
	protected LocationManager locationManager;
	NotificationManager notificationManager;
	private static final long MINIMUM_DISTANCE_CHANGE_FOR_UPDATES = 1; // in Meters
    private static final long MINIMUM_TIME_BETWEEN_UPDATES = 1000; // in Milliseconds
    /** Called when the activity is first created. */
    private Button closeButton;
    
    /** overriding the back button to act as home button */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.main);
        
        Button next = (Button) findViewById(R.id.manual);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Activity2.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
        	
locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        
        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER, 
                MINIMUM_TIME_BETWEEN_UPDATES, 
                MINIMUM_DISTANCE_CHANGE_FOR_UPDATES,
                new MyLocationListener()
        );        
        normalbutton();
        silentbutton();
        vibratebutton();
        
    } 
    private class MyLocationListener implements LocationListener {
    	check chec=new check();
    	final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
        public void onLocationChanged(Location location) {
        	
        	try{
        	Reader inp = new FileReader(Environment.getExternalStorageDirectory()+"/test.txt"); // 1
        	StreamTokenizer tstream = new StreamTokenizer(inp); // 2
        	tstream.parseNumbers(); // 3
        	double a = 0;
        	double b = 0;
        	double c1 = 0;
        	double ran1;
        	int ran;
        	int c = 8;
        	int i=0;
        	tstream.nextToken(); // 5
        	while (tstream.ttype != StreamTokenizer.TT_EOF) // 6
        	{
        	if(i==0){
        	    a=tstream.nval;
        	}
        	if(i==1){
        	    b=tstream.nval;
        	}
        	if(i==2){
        	    c1=tstream.nval;
        	    c=(int)c1;
         	}
        	if(i==3){
        		ran1=tstream.nval;
        		ran=(int)ran1;
        		i=-1;
        	    chec.profcheckn(a, b, c, ran);
        	}
        	tstream.nextToken(); // 8
        	i++;
        	}	
        	}catch(FileNotFoundException e){
        		
        	} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
							 
							 
							 
	       }
        

        public void onStatusChanged(String s, int i, Bundle b) {
           
        }

        public void onProviderDisabled(String s) {
           
        }

        public void onProviderEnabled(String s) {
           
        }

    }
        protected void normalbutton(){
    	Button btn=(Button)findViewById(R.id.normalbutton);
    	btn.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			normal();	
			}
    		
    	});
    }
    protected void silentbutton(){
    	Button btn=(Button)findViewById(R.id.silentbutton);
    	btn.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			silent();	
			}
    		
    	});
    }
    protected void vibratebutton(){
    	Button btn=(Button)findViewById(R.id.vibratebutton);
    	btn.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			vibrate();	
			}
    		
    	});
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
       
    }
        public void normal(){
        	final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
    	final Calendar c = Calendar.getInstance();
    	final TextView mTextField=(TextView)findViewById(R.id.txt1);
    	final TimePicker tp1=(TimePicker)findViewById(R.id.TimePick);
    	int sec;
    	int hour=tp1.getCurrentHour();
    	int min;
    	if((tp1.getCurrentHour()<c.get(Calendar.HOUR_OF_DAY))||((tp1.getCurrentHour()==c.get(Calendar.HOUR_OF_DAY))&&(tp1.getCurrentMinute()<c.get(Calendar.MINUTE))))
    	{
    		    		hour=24+hour;
    	}
    		hour=hour-c.get(Calendar.HOUR_OF_DAY);
    	min=tp1.getCurrentMinute()-c.get(Calendar.MINUTE);
    	sec=(((hour*3600)+(min*60))*1000);
    	
    	CountDownTimer a=new CountDownTimer(sec , 1000) {
    	public void onTick(long millisUntilFinished) {
    	    mTextField.setText("Normal mode in "+ millisUntilFinished / 1000 +" seconds");
    	} //all calculations in milliseconds

    	public void onFinish() {
    	    mTextField.setText("Profile changed");
    	    maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    	}
    	};
    	a.start();
    	
    		
    	
    }
    public void silent(){
    	final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
    	final Calendar c = Calendar.getInstance();
    	final TextView mTextField=(TextView)findViewById(R.id.txt2);
    	final TimePicker tp1=(TimePicker)findViewById(R.id.TimePick);
    	int sec;
    	int hour=tp1.getCurrentHour();
    	int min;
    	if((tp1.getCurrentHour()<c.get(Calendar.HOUR_OF_DAY))||((tp1.getCurrentHour()==c.get(Calendar.HOUR_OF_DAY))&&(tp1.getCurrentMinute()<c.get(Calendar.MINUTE))))
    	{
    		    		hour=24+hour;
    	}
    		hour=hour-c.get(Calendar.HOUR_OF_DAY);
    	min=tp1.getCurrentMinute()-c.get(Calendar.MINUTE);
    	sec=(((hour*3600)+(min*60))*1000);
    	
    	new CountDownTimer(sec , 1000) {
    	public void onTick(long millisUntilFinished) {
    	    mTextField.setText("Silent mode in "+ millisUntilFinished / 1000+" seconds");
    	}

    	public void onFinish() {
    	    mTextField.setText("Profile changed");
    	    maudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    	}
    	}.start();
    }
    public void vibrate(){
    	final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
    	final Calendar c = Calendar.getInstance();
    	final TextView mTextField=(TextView)findViewById(R.id.txt3);
    	final TimePicker tp1=(TimePicker)findViewById(R.id.TimePick);
    	int sec;
    	int hour=tp1.getCurrentHour();
    	int min;
    	if((tp1.getCurrentHour()<c.get(Calendar.HOUR_OF_DAY))||((tp1.getCurrentHour()==c.get(Calendar.HOUR_OF_DAY))&&(tp1.getCurrentMinute()<c.get(Calendar.MINUTE))))
    	{
    		    		hour=24+hour;
    	}
    		hour=hour-c.get(Calendar.HOUR_OF_DAY);
    	min=tp1.getCurrentMinute()-c.get(Calendar.MINUTE);
    	sec=(((hour*3600)+(min*60))*1000);
    	
    	new CountDownTimer(sec , 1000) {
    	public void onTick(long millisUntilFinished) {
    	    mTextField.setText("Vibrate mode in "+ millisUntilFinished / 1000+" seconds");
    	}

    	public void onFinish() {
    	    mTextField.setText("Profile changed");
    	    maudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
    	}
    	}.start();
    }
    
    class check{
    	final AudioManager maudio=(AudioManager)getSystemService(AUDIO_SERVICE);
    	double latl;
    	double latr;
    	double lonl;
		double lonr;
		 int pro1;
    public void profcheckn(double lat,double lon,int pro,int range){
    	Context context1 = SsActivity.this
        	    .getApplicationContext();
        notificationManager = (NotificationManager) context1
        	    .getSystemService(NOTIFICATION_SERVICE);
        Notification updateComplete = new Notification();
        updateComplete.icon =R.drawable.icon;
        updateComplete.tickerText = context1
        	    .getText(R.string.notification_title);
        updateComplete.when = System.currentTimeMillis();
        Intent notificationIntent = new Intent(context1,
        	    SsActivity.class);
        	PendingIntent contentIntent = PendingIntent.getActivity(context1, 0,
        	    notificationIntent, 0);
        	String contentTitle = context1.getText(R.string.notification_title).toString();
    	double le=0;
    	if(range==4){
    		le=0.01;
    	}
    	if(range==3){
    		le=0.008;
    	}
    	if(range==2){
    		le=0.006;
    	}
    	if(range==1){
    		le=0.004;
    	}
    	if(range==0){
    		le=0.002;
    	}
    	latl=lat-le;
    	latr=lat+le;
    	lonl=lon-le;
    	lonr=lon+le;
    	pro1=pro;
    	Context context = getApplicationContext();
    	int duration = Toast.LENGTH_SHORT;
    	Toast.makeText(getBaseContext(), 
                "called"+lat+" "+lon+" "+pro+" "+le, 
                Toast.LENGTH_SHORT).show();
    	Location l = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	if(pro1==1){
    	    	if(((l.getLatitude()>=latl)&&(l.getLatitude()<=latr))&&((l.getLongitude()>=lonl)&&(l.getLongitude()<=lonr))){    				
    				maudio.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
    				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    				long milliseconds = 2000;
    				v.vibrate(milliseconds);
    				String contentText;
        		    contentText = context1.getText(
        		R.string.notification_info_success1).toString();
        		updateComplete.setLatestEventInfo(context1, contentTitle,
        		    contentText, contentIntent);
    				notificationManager.notify(26, updateComplete);
    			}
    	}
    	if(pro1==2){
	    	if(((l.getLatitude()>=latl)&&(l.getLatitude()<=latr))&&((l.getLongitude()>=lonl)&&(l.getLongitude()<=lonr))){    				
				maudio.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
				long milliseconds = 2000;
				v.vibrate(milliseconds);
				String contentText;
    		    contentText = context1.getText(
    		R.string.notification_info_success2).toString();
    		updateComplete.setLatestEventInfo(context1, contentTitle,
    		    contentText, contentIntent);
				notificationManager.notify(26, updateComplete);
			}
    	}
	    if(pro1==3){
    	    	if(((l.getLatitude()>=latl)&&(l.getLatitude()<=latr))&&((l.getLongitude()>=lonl)&&(l.getLongitude()<=lonr))){    				
    				maudio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
    				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
    				long milliseconds = 2000;
    				v.vibrate(milliseconds);
    				String contentText;
        		    contentText = context1.getText(
        		R.string.notification_info_success3).toString();
        		updateComplete.setLatestEventInfo(context1, contentTitle,
        		    contentText, contentIntent);
    				notificationManager.notify(26, updateComplete);
    			}
    	}
	}
    }
    }
