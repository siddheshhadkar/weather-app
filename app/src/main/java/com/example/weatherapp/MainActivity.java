package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.appbar.MaterialToolbar;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;

public class MainActivity extends AppCompatActivity {
    OWM owm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        owm = new OWM(APIKey.KEY);
        owm.setUnit(OWM.Unit.METRIC);
        new GetCurrentWeather().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_location_menu) {
            // TODO: 26/11/20 new activity for location search
        }
        return super.onOptionsItemSelected(item);
    }

    class GetCurrentWeather extends AsyncTask<String, Void, CurrentWeather> {

        @Override
        protected CurrentWeather doInBackground(String... strings) {
            try {
                HourlyWeatherForecast weatherForecast = owm.hourlyWeatherForecastByCityId(1268295);
//                HourlyWeatherForecast weatherForecast = owm.hourlyWeatherForecastByCityName("Mumbai", OWM.Country.INDIA);
                Log.e("MAP", "" + weatherForecast.getDataList().size());
            } catch (APIException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(CurrentWeather currentWeather) {
            super.onPostExecute(currentWeather);
        }
    }
}