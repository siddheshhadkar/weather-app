package com.example.weatherapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.City;
import net.aksingh.owmjapis.model.param.WeatherData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    private final List<WeatherCard> weatherCards = new ArrayList<>();
    private OWM owm;
    private WeatherCardAdapter adapter;
    final Context context = this;

    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialToolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        owm = new OWM(APIKey.KEY);
        owm.setUnit(OWM.Unit.METRIC);
        weatherCards.add(new WeatherCard());

        latitude = 19.2403;
        longitude = 73.1305;
        new GetCurrentWeather().execute();
        getExtraCards();

        adapter = new WeatherCardAdapter();
        adapter.setWeatherCards(weatherCards);
        adapter.setOnItemClickListener(card -> {
            Intent i = new Intent(this, DetailedWeather.class);
            i.putExtra("CITYID", card.getCityID());
            i.putExtra("CITYNAME", card.getCity());
            startActivity(i);
        });

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void getExtraCards() {
        SharedPreferences preferences = getSharedPreferences("cities", MODE_PRIVATE);
        Set<String> cityNames;
        cityNames = preferences.getStringSet("CITIES", new HashSet<>());
        for (String city : cityNames) {
            new GetCurrentWeather().execute(city);
        }
    }

    private void addNewLocation(String city) {
        SharedPreferences preferences = getSharedPreferences("cities", MODE_PRIVATE);
        Set<String> cityNames;
        cityNames = preferences.getStringSet("CITIES", new HashSet<>());
        SharedPreferences.Editor editor = preferences.edit();
        cityNames.add(city);
        editor.putStringSet("CITIES", cityNames);
        editor.apply();

        new GetCurrentWeather().execute(city);
    }

    private void removeLocation(String city) {
        SharedPreferences preferences = getSharedPreferences("cities", MODE_PRIVATE);
        Set<String> cityNames;
        cityNames = preferences.getStringSet("CITIES", new HashSet<>());
        cityNames.remove(city);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_location_menu) {
            Log.e("test","Clicked");
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.custom_dialog, null);

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);

            // set prompts.xml to alertdialog builder
            alertDialogBuilder.setView(promptsView);

            final EditText userInput = (EditText) promptsView
                    .findViewById(R.id.inputCityName);

            alertDialogBuilder
                    .setCancelable(true)
                    .setPositiveButton("Add",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    // get user input and set it to result
                                    // edit text
                                    addNewLocation(userInput.getText().toString());
                                    dialog.cancel();
                                }
                            })
                    .setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            // create alert dialog
            AlertDialog alertDialog = alertDialogBuilder.create();

            // show it
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    class GetCurrentWeather extends AsyncTask<String, Void, CurrentWeather> {

        @Override
        protected CurrentWeather doInBackground(String... strings) {
            try {
                CurrentWeather currentWeather;
                if (strings.length == 0) {
                    currentWeather = owm.currentWeatherByCoords(latitude, longitude);
                    Log.e("Check results","City Name"+currentWeather.getCityName());
                } else {
                    currentWeather = owm.currentWeatherByCityName(strings[0]);
                    Log.e("Check results","City Name"+currentWeather.getCityName());
                }

                WeatherCard card = new WeatherCard();
                card.setCity(currentWeather.getCityName());
                card.setHumidity(currentWeather.getMainData().getHumidity());
                card.setTemp(currentWeather.getMainData().getTemp());
                card.setTempMin(currentWeather.getMainData().getTempMin());
                card.setTempMax(currentWeather.getMainData().getTempMax());
                card.setWindSpeed(currentWeather.getWindData().getSpeed());
                card.setWeatherIconDescription(currentWeather.getWeatherList().get(0).getMainInfo());
                card.setCityID(currentWeather.getCityId());

                if (strings.length == 0) {
                    weatherCards.set(0, card);
                } else {
                    weatherCards.add(card);
                }
            } catch (APIException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(CurrentWeather currentWeather) {
            super.onPostExecute(currentWeather);
            adapter.setWeatherCards(weatherCards);
        }
    }

}