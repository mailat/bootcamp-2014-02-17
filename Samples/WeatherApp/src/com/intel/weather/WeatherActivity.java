package com.intel.weather;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

public class WeatherActivity extends Activity {

    String loadUrl ;
    TextView textview;
    private String response ="";
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.weather_page);
        Log.d("Novosibirsk", "second activity onCreate");
        
        //read the passed value
        Intent intent = getIntent();
        String city = intent.getStringExtra("city");
        
        //we change the text on the TextView with the city
        textview = (TextView) findViewById(R.id.labelText);
        textview.setText("City is:" + city);
        
        //prepare the JSON url for the weather
        city = city.replaceAll(" ", "%20");
        loadUrl = "http://api.openweathermap.org/data/2.5/weather?q=" + city + ",ru&units=metric";
        
        //the right way to get the weather
        new WeatherReaderUpdater().execute(loadUrl);
    }
    
    //new WeatherReaderUpdater().execute();  
    private class WeatherReaderUpdater extends AsyncTask<String, Void, Void> {

        @Override
        protected Void doInBackground(String... params) {

                try {
                        // transporter for our in->out call
                        HttpClient client = new DefaultHttpClient();
                        HttpGet httpget = new HttpGet(loadUrl);

                        response = client.execute(httpget, new BasicResponseHandler());
                        Log.d("Novosibirsk", "response back: " + response);
                } catch (Throwable e) {
                        e.printStackTrace();
                        Log.d("Novosibirsk", "response back: " + e.getMessage());
                }

                return null;
        }

        @Override
        protected void onPostExecute(Void result) {
        try {
                StringBuffer buffer = new StringBuffer();
                JSONObject jObj = new JSONObject(response);
                        
                //get JSONObject - coord
                JSONObject jsonObj = jObj.getJSONObject("coord");
                
                //get string from coord - lat, lon
                buffer.append(jsonObj.getString("lat")).append(" - ");
                buffer.append(jsonObj.getString("lon")).append(" , temp: ");
                        
                //get JSONObject - main
                jsonObj = jObj.getJSONObject("main");
                //get temp
                buffer.append(jsonObj.getString("temp"));
                
                textview.setText(buffer.toString());
                        
                } catch (JSONException e) {
                        e.printStackTrace();
                }
        }
}   
}
