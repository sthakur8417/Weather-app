package com.example.sudo_weather_app.models;

import java.io.Serializable;
import java.util.ArrayList;

public class DailyModel implements Serializable {

    public long datetimeEpoch;
    public double tempmax;
    public double tempmin;
    public double precipprob;
    public double uvindex;
    public String description;
    public String icon;
    public ArrayList<HourlyModel> hours;


    public double getTempmin() {
        return tempmin;
    }

    public void setTempmin(double tempmin) {
        this.tempmin = tempmin;
    }

    public double getPrecipprob() {
        return precipprob;
    }

    public void setPrecipprob(double precipprob) {
        this.precipprob = precipprob;
    }

    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public void setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
    }

    public double getTempmax() {
        return tempmax;
    }

    public void setTempmax(double tempmax) {
        this.tempmax = tempmax;
    }

    public double getUvindex() {
        return uvindex;
    }

    public void setUvindex(double uvindex) {
        this.uvindex = uvindex;
    }

    public ArrayList<HourlyModel> getHours() {
        return hours;
    }

    public void setHours(ArrayList<HourlyModel> hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeather_Icon() {
        return icon;
    }

    public void setWeather_Icon(String weather_Icon) {
        this.icon = weather_Icon;
    }
}
