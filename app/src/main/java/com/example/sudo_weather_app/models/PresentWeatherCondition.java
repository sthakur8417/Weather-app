package com.example.sudo_weather_app.models;

import java.io.Serializable;

public class PresentWeatherCondition implements Serializable {

    public long datetimeEpoch, sunriseEpoch, sunsetEpoch;
    public long winddir;
    public double feelslike, humidity, windgust, windspeed, visibility, uvindex;
    public double cloudcover;
    public double temp;
    public String conditions;
    public String icon;


    public long getDatetimeEpoch() {
        return datetimeEpoch;
    }

    public void setDatetimeEpoch(long datetimeEpoch) {
        this.datetimeEpoch = datetimeEpoch;
    }

    public long getSunriseEpoch() {
        return sunriseEpoch;
    }

    public void setSunriseEpoch(long sunriseEpoch) {
        this.sunriseEpoch = sunriseEpoch;
    }

    public long getSunsetEpoch() {
        return sunsetEpoch;
    }

    public void setSunsetEpoch(long sunsetEpoch) {
        this.sunsetEpoch = sunsetEpoch;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getWindgust() {
        return windgust;
    }

    public void setWindgust(double windgust) {
        this.windgust = windgust;
    }

    public double getWindspeed() {
        return windspeed;
    }

    public void setWindspeed(double windspeed) {
        this.windspeed = windspeed;
    }

    public double getVisibility() {
        return visibility;
    }

    public void setVisibility(double visibility) {
        this.visibility = visibility;
    }

    public double getUvindex() {
        return uvindex;
    }

    public void setUvindex(double uvindex) {
        this.uvindex = uvindex;
    }

    public long getWinddir() {
        return winddir;
    }

    public void setWinddir(long winddir) {
        this.winddir = winddir;
    }

    public double getCloudcover() {
        return cloudcover;
    }

    public void setCloudcover(double cloudcover) {
        this.cloudcover = cloudcover;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
