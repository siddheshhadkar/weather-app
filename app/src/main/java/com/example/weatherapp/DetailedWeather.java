package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.HourlyWeatherForecast;
import net.aksingh.owmjapis.model.param.Cloud;
import net.aksingh.owmjapis.model.param.Main;
import net.aksingh.owmjapis.model.param.Temp;
import net.aksingh.owmjapis.model.param.WeatherData;
import net.aksingh.owmjapis.model.param.Wind;

import java.util.Date;
import java.util.Set;

public class DetailedWeather extends AppCompatActivity {
    WeatherCard weatherCard;
    private OWM owm;
    TextView currentTemperature,minTemp,maxTemp,windSpeed,humidity,cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_weather);
        owm = new OWM(APIKey.KEY);
        owm.setUnit(OWM.Unit.METRIC);
        Intent i = getIntent();
        Integer cityid = i.getIntExtra("CITYID",0);
        String cityname=i.getStringExtra("CITYNAME");
        cityName=findViewById(R.id.cityName);
        cityName.setText(cityname);
        new GetHourlyForecast().execute(cityid);


    }

    class GetHourlyForecast extends AsyncTask<Integer, Void, Void> {

        int[] decsIDs = new int[] {R.id.desc1, R.id.desc2, R.id.desc3,R.id.desc4 };
        int[] timeIDs = new int[] {R.id.time1, R.id.time2, R.id.time3,R.id.time4 };
        int[] smallTempIDs = new int[] {R.id.smallTemp1, R.id.smallTemp2, R.id.smallTemp3,R.id.smallTemp4 };

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Log.e("do in",integers.toString());

                HourlyWeatherForecast weatherForecast = owm.hourlyWeatherForecastByCityId(integers[0]);

                WeatherData weatherData=weatherForecast.getDataList().get(0);
                Main temp=weatherData.getMainData();
                Wind windData=weatherData.getWindData();

                currentTemperature= findViewById(R.id.currentTemperature);
                minTemp= findViewById(R.id.minTemp);
                maxTemp= findViewById(R.id.maxTemp);
                windSpeed= findViewById(R.id.windSpeed);
                humidity= findViewById(R.id.humidity);

                currentTemperature.setText(temp.getTemp().toString());
                minTemp.setText(temp.getTempMin().toString());
                maxTemp.setText(temp.getTempMax().toString());
                windSpeed.setText(windData.getSpeed().toString());
                humidity.setText(temp.getHumidity().toString());

                for (int i=0;i<4;i++){
                    WeatherData wData=weatherForecast.getDataList().get(i+1);
                    String cloudData=wData.getWeatherList().get(0).getMainInfo();
                    Log.e("Data",wData.toString());
                    Main mainData=wData.getMainData();
                    String date=wData.getDateTimeText();

                    TextView desc=findViewById(decsIDs[i]);
                    TextView time=findViewById(timeIDs[i]);
                    TextView smallTemp=findViewById(smallTempIDs[i]);

                    desc.setText(cloudData);
                    time.setText(date);
                    smallTemp.setText(mainData.getTemp().toString());

                }
            } catch (APIException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }

}