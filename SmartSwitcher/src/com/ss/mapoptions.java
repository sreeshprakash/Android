package com.ss;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import com.google.android.maps.MapView.LayoutParams;  
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.sreesh.ss.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
 
public class mapoptions extends MapActivity 
{    
	int selec;
	int range;
	 MapView mapView;
	 
	    /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) 
	    {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main2);
	 
	        mapView = (MapView) findViewById(R.id.mapView);
	        LinearLayout zoomLayout = (LinearLayout)findViewById(R.id.zoom);  
	        View zoomView = mapView.getZoomControls(); 
	        zoomLayout.addView(zoomView, 
	            new LinearLayout.LayoutParams(
	                LayoutParams.WRAP_CONTENT, 
	                LayoutParams.WRAP_CONTENT)); 
	        mapView.displayZoomControls(true);
	        MapOverlay mapOverlay = new MapOverlay();
	        List<Overlay> listOfOverlays = mapView.getOverlays();
	        listOfOverlays.clear();
	        listOfOverlays.add(mapOverlay);
	        Spinner spinner = (Spinner) findViewById(R.id.spinner1);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                    this, R.array.profile_array, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            
            spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(position==0)
                    {
                    	selec=0;
                    	
                    }
                    if(position==1)
                    {
                    	selec=1;
                   
                    }
                    if(position==2)
                    {
                    	selec=2;
                    	
                    }
                }
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
            Spinner spinner1 = (Spinner) findViewById(R.id.spinner2);
            ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(
                    this, R.array.range_array, android.R.layout.simple_spinner_item);
            adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner1.setAdapter(adapter1);
            
            spinner1.setOnItemSelectedListener(new OnItemSelectedListener() {
                public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                    if(position==0)
                    {
                    	range=0;
                    
                    	
                    }
                    if(position==1)
                    {
                    	range=1;
                    
                    }
                    if(position==2)
                    {
                    	range=2;
                    	
                    }
                    if(position==3)
                    {
                    	range=3;
                    	
                    }
                    if(position==4)
                    {
                    	range=4;
           
                    }
                }
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }

            });
	    }
	    @Override
	    protected boolean isRouteDisplayed() {
	        // TODO Auto-generated method stub
	        return false;
	    }
	    class MapOverlay extends com.google.android.maps.Overlay
	    { 
	    	 Button accept = (Button) findViewById(R.id.accept);
			   @Override
		        public boolean draw(Canvas canvas, MapView mapView, 
		        boolean shadow, long when) 
		        {
		            super.draw(canvas, mapView, shadow);                   
		 
		            //---translate the GeoPoint to screen pixels---
		            Point screenPts = new Point();
		            return true;
		        }
	        @Override
	        public boolean onTouchEvent(MotionEvent event, MapView mapView) 
	        {   
	        float lati1;
	        float long1;
	            //---when user lifts his finger---
	            if (event.getAction() == 1) {     
	                final GeoPoint p = mapView.getProjection().fromPixels(
	                    (int) event.getX(),
	                    (int) event.getY());
	                lati1=p.getLatitudeE6();
                    long1=p.getLongitudeE6();
	                    Toast.makeText(getBaseContext(), 
	                        p.getLatitudeE6() / 1E6 + "," + 
	                        p.getLongitudeE6() /1E6 , 
	                        Toast.LENGTH_SHORT).show();
	                    accept.setOnClickListener(new OnClickListener(){

							public void onClick(View arg0) {
								// TODO Auto-generated method stub
								  try {
				                        File sdcard1 = Environment.getExternalStorageDirectory();
				                        FileWriter fw = new FileWriter(sdcard1 + "/Test.txt",true);
				                        double a=p.getLatitudeE6();
				                        int b=(int)a;
				                        int c=b/1000000;
				                        int d=b%1000000;
				                        int l1=b/100000;
				                        int l2=b/10000;
				                        double a1=p.getLongitudeE6();
				                        int b1=(int)a1;
				                        int c1=b1/1000000;
				                        int d1=b1%1000000;
				                        int h1=b1/100000;
				                        int h2=b1/10000;
				                        if((l2%100==0)&&(h2%100==0)){
				                        	   if(selec==0){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"00"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"00"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"00"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        			}
				                        else if((l2%100!=0)&&(h2%100==0)){
				                        	
				                        	if(l1%10==0){
						                        if(selec==0){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"00"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"00"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"00"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        				}
				                        	if(l1%10!=0){
						                        if(selec==0){
						                        	fw.write(c+"."+d+" "+c1+"."+"00"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+d+" "+c1+"."+"00"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+d+" "+c1+"."+"00"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        				}
				                        	}
				                        	else if((l2%100==0)&&(h2%100!=0)){
				                        
				                        		if(h1%10==0){
						                        if(selec==0){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"0"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"0"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+"0"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        	
				                        		}
				                        	if(h1%10!=0){
						                        if(selec==0){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"00"+d+" "+c1+"."+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        		}
				                        	}
				                        	else if((l2%100!=0)&&(h2%100!=0)){
				                        	if((l1%10==0)&&(h1%10==0)){
						                        if(selec==0){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"0"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"0"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+"0"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        		}
				                        	if((l1%10!=0)&&(h1%10==0)){
						                        if(selec==0){
						                        	fw.write(c+"."+d+" "+c1+"."+"0"+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+d+" "+c1+"."+"0"+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+d+" "+c1+"."+"0"+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        		}
				                        	if((l1%10==0)&&(h1%10!=0)){
						                        if(selec==0){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+"0"+d+" "+c1+"."+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        		}
				                        	if((l1%10!=0)&&(h1%10!=0)){
						                        if(selec==0){
						                        	fw.write(c+"."+d+" "+c1+"."+d1+" "+1+" "+range);	
						                        }
						                        if(selec==1){
						                        	fw.write(c+"."+d+" "+c1+"."+d1+" "+2+" "+range);
						                        }
						                        if(selec==2){
						                        	fw.write(c+"."+d+" "+c1+"."+d1+" "+3+" "+range);
						                        }
						                        Toast.makeText(getBaseContext(), "The location has been entered", 
								                        Toast.LENGTH_SHORT).show();
						                        fw.write("\n");
						                        fw.close();
				                        		}
				                        	}
				                        	
							
				                    } catch (FileNotFoundException e) {
				                        e.printStackTrace();
				                    } catch (IOException e) {
				                        e.printStackTrace();
				                    } 
								  
							}
	                    	
	                    });
	                       
	            }                            
	            return false;
	        }        
	    }
	    }