package com.example.weatherapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private OWM owm;
    private List<WeatherCard> weatherCards = new ArrayList<>();
    private WeatherCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        owm = new OWM(APIKey.KEY);
        owm.setUnit(OWM.Unit.METRIC);

        new GetCurrentWeather().execute();

        weatherCards = getWeatherCards();
        adapter = new WeatherCardAdapter();
        adapter.setWeatherCards(weatherCards);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

    }

    private List<WeatherCard> getWeatherCards() {
        // TODO: 2/12/20 fetch current and favourite weather cards
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        weatherCards.add(new WeatherCard("A", 1, 1, 1, 1, 1, 1, 1));
        return weatherCards;
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
//                HourlyWeatherForecast weatherForecast = owm.hourlyWeatherForecastByCityId(1268295);
//                HourlyWeatherForecast weatherForecast = owm.hourlyWeatherForecastByCityName("Mumbai", OWM.Country.INDIA);
                CurrentWeather currentWeather = owm.currentWeatherByCoords(34.251935, 93.489513);
                Log.e("MAP", currentWeather.getMainData().toString());
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