package com.intel.weather;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.activity_main);
        Log.d("Novosibirsk", "onCreate");
    }
    
    public void showWeatherActivity (View v)
    {
        //jump in the second activity
        Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
        
        //we read the input city
        EditText cityInputText = (EditText) findViewById(R.id.cityInputText);
        String city = cityInputText.getText().toString();
        Log.d("Novosibirsk", "Our city is:" + city);
        
        //we add the city to the Intent and pass it to the second activity
        intent.putExtra("city", city);
        
        //I start the second activity
        startActivity(intent);	
    }
    
    public void showForecastActivity (View v)
    {
        //jump in the second activity
        Intent intent = new Intent(MainActivity.this, ForecastActivity.class);
        
        //we read the input city
        EditText cityInputText = (EditText) findViewById(R.id.cityInputText);
        String city = cityInputText.getText().toString();
        Log.d("Novosibirsk", "Our city is:" + city);
        
        //we add the city to the Intent and pass it to the second activity
        intent.putExtra("city", city);
        
        //I start the second activity
        startActivity(intent);	
    }

}
