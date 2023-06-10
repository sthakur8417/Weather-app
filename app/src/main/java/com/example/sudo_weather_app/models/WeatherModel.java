package com.example.sudo_weather_app.models;

import java.io.Serializable;
import java.util.ArrayList;

public class WeatherModel implements Serializable {

    public String address, timezone;
    public double tzoffset;
    public ArrayList<DailyModel> days;
    public PresentWeatherCondition currentConditions;

    public WeatherModel(String address, String timezone, double tzoffset, ArrayList<DailyModel> dailyModelsArrayList, PresentWeatherCondition presentWeatherCondition) {
        this.address = address;
        this.timezone = timezone;
        this.tzoffset = tzoffset;
        this.days = dailyModelsArrayList;
        this.currentConditions = presentWeatherCondition;
    }

    public ArrayList<DailyModel> getDailyModelsArrayList() {
        return days;
    }

    public void setDailyModelsArrayList(ArrayList<DailyModel> dailyModelsArrayList) {
        this.days = dailyModelsArrayList;
    }

    public String getUser_Address() {
        return address;
    }

    public void setUser_Address(String user_Address) {
        this.address = user_Address;
    }

    public String getUser_Timezone() {
        return timezone;
    }

    public void setUser_Timezone(String user_Timezone) {
        this.timezone = user_Timezone;
    }

    public PresentWeatherCondition getPresentWeatherCondition() {
        return currentConditions;
    }

    public void setPresentWeatherCondition(PresentWeatherCondition presentWeatherCondition) {
        this.currentConditions = presentWeatherCondition;
    }

    public double getTzoffset() {
        return tzoffset;
    }

    public void setTzoffset(double tzoffset) {
        this.tzoffset = tzoffset;
    }
}
