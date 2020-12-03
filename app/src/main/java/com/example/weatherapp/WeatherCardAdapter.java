package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WeatherCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<WeatherCard> weatherCards;
    private OnItemClickListener listener;

    public void setWeatherCards(List<WeatherCard> weatherCards) {
        this.weatherCards = weatherCards;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == R.layout.main_weather_card) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_weather_card, parent, false);
            return new MainCardViewHolder(itemView);
        } else {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.extra_weather_card, parent, false);
            return new ExtraCardViewHolder(itemView);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        WeatherCard card = weatherCards.get(position);
        if (position == 0) {
            MainCardViewHolder mainCardViewHolder = (MainCardViewHolder) holder;
            mainCardViewHolder.cityName.setText(card.getCity());
            mainCardViewHolder.currentTemp.setText(card.getTemp() + " \u00B0C");
            mainCardViewHolder.minTemp.setText(card.getTempMin() + " \u00B0C");
            mainCardViewHolder.maxTemp.setText(card.getTempMax() + " \u00B0C");
            mainCardViewHolder.windSpeed.setText("Wind Speed: " + card.getWindSpeed());
            mainCardViewHolder.humidity.setText("Humidity: " + card.getHumidity());
            mainCardViewHolder.weatherInfo.setText(card.getWeatherIconDescription());
        } else {
            ExtraCardViewHolder extraCardViewHolder = (ExtraCardViewHolder) holder;
            extraCardViewHolder.cityName.setText(card.getCity());
            extraCardViewHolder.currentTemp.setText(card.getTemp() + " \u00B0C");
            extraCardViewHolder.weatherInfo.setText(card.getWeatherIconDescription());
        }
    }

    @Override
    public int getItemCount() {
        return weatherCards.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return R.layout.main_weather_card;
        else
            return R.layout.extra_weather_card;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(WeatherCard card);
    }

    class MainCardViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName;
        public TextView currentTemp;
        public TextView minTemp;
        public TextView maxTemp;
        public TextView windSpeed;
        public TextView humidity;
        public TextView weatherInfo;

        public MainCardViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.cityName);
            currentTemp = itemView.findViewById(R.id.currentTemp);
            minTemp = itemView.findViewById(R.id.minTemp);
            maxTemp = itemView.findViewById(R.id.maxTemp);
            windSpeed = itemView.findViewById(R.id.windSpeed);
            humidity = itemView.findViewById(R.id.humidity);
            weatherInfo = itemView.findViewById(R.id.weatherInfo);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(weatherCards.get(position));
                }
            });
        }
    }

    class ExtraCardViewHolder extends RecyclerView.ViewHolder {
        public TextView cityName;
        public TextView currentTemp;
        public TextView weatherInfo;

        public ExtraCardViewHolder(@NonNull View itemView) {
            super(itemView);
            cityName = itemView.findViewById(R.id.smallCardCityName);
            currentTemp = itemView.findViewById(R.id.smallCardCurrentTemp);
            weatherInfo = itemView.findViewById(R.id.smallCardWeatherInfo);

            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(weatherCards.get(position));
                }
            });
        }
    }
}
