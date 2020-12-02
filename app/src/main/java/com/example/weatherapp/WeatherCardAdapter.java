package com.example.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        // TODO: 2/12/20 set card fields through holder object
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
        public MainCardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(weatherCards.get(position));
                }
            });
        }
    }

    class ExtraCardViewHolder extends RecyclerView.ViewHolder {
        public ExtraCardViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onItemClick(weatherCards.get(position));
                }
            });
        }
    }
}
