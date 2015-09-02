package zap.app.weathergods;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;



public class Weather extends Fragment {
    Typeface weatherFont;
     
    TextView cityField;
    TextView updatedField;
    TextView detailsField;
    TextView currentTemperatureField;
    TextView weatherIcon;
     
    Handler handler;
 
    public Weather(){   
        handler = new Handler();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sreesh, container, false);
        cityField = (TextView)rootView.findViewById(R.id.city_field);
        updatedField = (TextView)rootView.findViewById(R.id.updated_field);
        detailsField = (TextView)rootView.findViewById(R.id.details_field);
        currentTemperatureField = (TextView)rootView.findViewById(R.id.current_temperature_field);
        weatherIcon = (TextView)rootView.findViewById(R.id.weather_icon);
         
        weatherIcon.setTypeface(weatherFont);
        return rootView; 
    }
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);  
	    weatherFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/gods.ttf");    
	    updateWeatherData(new City(getActivity()).getCity());
	}
     
	
	private void updateWeatherData(final String city){
	    new Thread(){
	        public void run(){
	            final JSONObject json = Fetch.getJSON(getActivity(), city);
	            if(json == null){
	                handler.post(new Runnable(){
	                    public void run(){
	                        Toast.makeText(getActivity(), 
	                                getActivity().getString(R.string.place_not_found), 
	                                Toast.LENGTH_LONG).show(); 
	                    }
	                });
	            } else {
	                handler.post(new Runnable(){
	                    public void run(){
	                        renderWeather(json);
	                    }
	                });
	            }               
	        }
	    }.start();
	}
	
	private void renderWeather(JSONObject json){
	    try {
	        cityField.setText(json.getString("name").toUpperCase(Locale.US) + 
	                ", " + 
	                json.getJSONObject("sys").getString("country"));
	         
	        JSONObject details = json.getJSONArray("weather").getJSONObject(0);
	        JSONObject main = json.getJSONObject("main");
	        detailsField.setText(
	                details.getString("description").toUpperCase(Locale.US) +
	                "\n" + "Humidity: " + main.getString("humidity") + "%" +
	                "\n" + "Pressure: " + main.getString("pressure") + " hPa");
	         
	        currentTemperatureField.setText(
	                    String.format("%.2f", main.getDouble("temp"))+ " ℃");
	 
	        DateFormat df = DateFormat.getDateTimeInstance();
	        String updatedOn = df.format(new Date(json.getLong("dt")*1000));
	        updatedField.setText("Last update: " + updatedOn);
	 
	        setWeatherIcon(details.getInt("id"),
	                json.getJSONObject("sys").getLong("sunrise") * 1000,
	                json.getJSONObject("sys").getLong("sunset") * 1000);
	        
	    }catch(Exception e){
	        Log.e("WeatherGods", "One or more fields not found in the JSON data");
	    }
	}
	
	private void setWeatherIcon(int actualId, long sunrise, long sunset){
	    int id = actualId / 100;
	    String icon = "";
	    if(actualId == 800){
	        long currentTime = new Date().getTime();
	        if(currentTime>=sunrise && currentTime<sunset) {
	            icon = getActivity().getString(R.string.weather_sunny);
	            Toast.makeText(getActivity(),"Bask under the glory of Helios", Toast.LENGTH_LONG).show();
	        } else {
	            icon = getActivity().getString(R.string.weather_clear_night);
	            Toast.makeText(getActivity(),"Luna and her stars watch over you", Toast.LENGTH_LONG).show();
	        }
	    } else {
	        switch(id) {
	        case 2 : icon = getActivity().getString(R.string.weather_thunder);
	        Toast.makeText(getActivity(),"Yield to the might of Thor", Toast.LENGTH_LONG).show();
	                 break;         
	        case 3 : icon = getActivity().getString(R.string.weather_drizzle);
	        Toast.makeText(getActivity(),"Indra grazes you with his blessings", Toast.LENGTH_LONG).show();	        
	                 break;     
	        case 7 : icon = getActivity().getString(R.string.weather_foggy);
	        Toast.makeText(getActivity(),"Niflheim has descended upon Midgard", Toast.LENGTH_LONG).show();
	                 break;
	        case 8 : icon = getActivity().getString(R.string.weather_cloudy);
	        Toast.makeText(getActivity(),"Saranyu is up to her usual antics", Toast.LENGTH_LONG).show();
	                 break;
	        case 6 : icon = getActivity().getString(R.string.weather_snowy);
	        Toast.makeText(getActivity(),"Elsa is in a foul mood", Toast.LENGTH_LONG).show();
	                 break;
	        case 5 : icon = getActivity().getString(R.string.weather_rainy);
	        Toast.makeText(getActivity(),"Poseidon's wrath is upon you", Toast.LENGTH_LONG).show();
	                 break;
	        }
	    }
	    weatherIcon.setText(icon);
	}

	public void changeCity(String city){
	    updateWeatherData(city);
	}
	
}