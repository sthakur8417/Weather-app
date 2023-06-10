package com.example.sudo_weather_app.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sudo_weather_app.ForecastDailyScreen;
import com.example.sudo_weather_app.R;
import com.example.sudo_weather_app.models.DailyModel;

import java.util.List;

public class DailyRecyclerViewListAdapter extends RecyclerView.Adapter<DailyRecyclerViewListAdapter.DailyRecyclerViewHolder> {

    private ForecastDailyScreen forecastDailyScreen;
    private List<DailyModel> dailyModelList;
    private Boolean isSelected;

    public DailyRecyclerViewListAdapter(ForecastDailyScreen forecastDailyScreen, List<DailyModel> dailyModelList, Boolean isSelected) {
        this.forecastDailyScreen = forecastDailyScreen;
        this.dailyModelList = dailyModelList;
        this.isSelected = isSelected;
    }

    @NonNull
    @Override
    public DailyRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DailyRecyclerViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.daily_recyclerview_layout_screen, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DailyRecyclerViewHolder holder, int position) {
        holder.dailyLayout_date.setText(forecastDailyScreen.getDateTime(dailyModelList.get(position).getDatetimeEpoch(),3));
        String minTemp = String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).tempmin);

        holder.dailyLayout_MorningTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).hours.get(8).temp));
        holder.dailyLayout_AfternoonTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).hours.get(13).temp));
        holder.dailyLayout_EveningTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).hours.get(17).temp));
        holder.dailyLayout_NightTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).hours.get(22).temp));

        holder.dailyLayout_imageWeather.setImageResource(forecastDailyScreen.getIcon(dailyModelList.get(position).getWeather_Icon()));
        holder.dailyLayout_temperature.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), dailyModelList.get(position).tempmax)+"/"+minTemp);
        holder.dailyLayout_weather.setText(dailyModelList.get(position).description);
        holder.dailyLayout_percipitation.setText("("+String.format("%.0f",dailyModelList.get(position).getPrecipprob())+"%"+" precip."+")");

        holder.dailyLayout_UV.setText("UV Index: " +String.format("%.0f",dailyModelList.get(position).getUvindex()));

    }

    @Override
    public int getItemCount() {
        return dailyModelList.size();
    }

    public class DailyRecyclerViewHolder extends RecyclerView.ViewHolder {

        TextView dailyLayout_MorningTemp, dailyLayout_MorningText, dailyLayout_AfternoonTemp, dailyLayout_AfternoonText, dailyLayout_EveningTemp, dailyLayout_EveningText, dailyLayout_NightTemp, dailyLayout_NightText;
        TextView dailyLayout_date, dailyLayout_temperature, dailyLayout_weather, dailyLayout_percipitation, dailyLayout_UV;
        ImageView dailyLayout_imageWeather;

        public DailyRecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            dailyLayout_MorningTemp = itemView.findViewById(R.id.dailyLayout_MorningTemp);
            dailyLayout_MorningText = itemView.findViewById(R.id.dailyLayout_MorningText);
            dailyLayout_AfternoonTemp = itemView.findViewById(R.id.dailyLayout_AfternoonTemp);
            dailyLayout_AfternoonText = itemView.findViewById(R.id.dailyLayout_AfternoonText);
            dailyLayout_EveningTemp = itemView.findViewById(R.id.dailyLayout_EveningTemp);
            dailyLayout_EveningText = itemView.findViewById(R.id.dailyLayout_EveningText);
            dailyLayout_NightTemp = itemView.findViewById(R.id.dailyLayout_NightTemp);
            dailyLayout_NightText = itemView.findViewById(R.id.dailyLayout_NightText);
            dailyLayout_date = itemView.findViewById(R.id.dailyLayout_date);
            dailyLayout_temperature = itemView.findViewById(R.id.dailyLayout_temperature);
            dailyLayout_weather = itemView.findViewById(R.id.dailyLayout_weather);
            dailyLayout_percipitation = itemView.findViewById(R.id.dailyLayout_percipitation);
            dailyLayout_UV = itemView.findViewById(R.id.dailyLayout_UV);
            dailyLayout_imageWeather = itemView.findViewById(R.id.dailyLayout_imageWeather);
        }

    }
}