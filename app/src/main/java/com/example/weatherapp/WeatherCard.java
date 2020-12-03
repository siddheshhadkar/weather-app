package com.example.weatherapp;

public class WeatherCard {
    private String city;
    private double temp;
    private double tempMin;
    private double tempMax;
    private double humidity;
    private double windSpeed;
    private double weatherIcon;
    private Integer cityID;
    private String weatherIconDescription;

    public WeatherCard() {

    }

    public WeatherCard(String city, double temp, double tempMin, double tempMax, double humidity, double windSpeed, double weatherIcon, String weatherIconDescription,Integer cityID) {
        this.city = city;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.weatherIcon = weatherIcon;
        this.weatherIconDescription = weatherIconDescription;
        this.cityID=cityID;
    }

    public void setCityID(Integer cityID){this.cityID=cityID;}

    public Integer getCityID(){return cityID;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public double getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(double weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public String getWeatherIconDescription() {
        return weatherIconDescription;
    }

    public void setWeatherIconDescription(String weatherIconDescription) {
        this.weatherIconDescription = weatherIconDescription;
    }
}
