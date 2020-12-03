package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedWeather extends AppCompatActivity {
    WeatherCard weatherCard;
    TextView currentTemperature,minTemp,maxTemp,windSpeed,humidity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        Intent i = getIntent();
//        cityanme = i.getStringExtra("CITYNAME");
//        currentTemperature.setText(temp + " \u00B0C");

        currentTemperature= findViewById(R.id.currentTemp);
        minTemp= findViewById(R.id.minTemp);
        maxTemp= findViewById(R.id.maxTemp);
        windSpeed= findViewById(R.id.windSpeed);
        humidity= findViewById(R.id.humidity);

        currentTemperature.setText((int) weatherCard.getTemp());
        minTemp.setText((int) weatherCard.getTempMin());
        maxTemp.setText((int) weatherCard.getTempMax());
        windSpeed.setText((int) weatherCard.getWindSpeed());
        humidity.setText((int) weatherCard.getHumidity());
    }
}