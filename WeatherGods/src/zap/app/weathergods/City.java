package zap.app.weathergods;
 
import android.app.Activity;
import android.content.SharedPreferences;
 
public class City {
     
    SharedPreferences prefs;
     
    public City(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }
    String getCity(){
        return prefs.getString("city", "Las Vegas, US");        
    }
     
    void setCity(String city){
        prefs.edit().putString("city", city).commit();
    }
     
}