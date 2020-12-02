package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailedWeather extends AppCompatActivity {
    WeatherCard weatherCard;
    TextView currentTemperature,minTemp,maxTemp,windSpeed,humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        currentTemperature=(TextView)findViewById(R.id.currentTemp);
        minTemp=(TextView)findViewById(R.id.minTemp);
        maxTemp=(TextView)findViewById(R.id.maxTemp);
        windSpeed=(TextView)findViewById(R.id.windSpeed);
        humidity=(TextView)findViewById(R.id.humidity);

        currentTemperature.setText((int) weatherCard.getTemp());
        minTemp.setText((int) weatherCard.getTempMin());
        maxTemp.setText((int) weatherCard.getTempMax());
        windSpeed.setText((int) weatherCard.getWindSpeed());
        humidity.setText((int) weatherCard.getHumidity());
    }
}