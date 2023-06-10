package com.example.sudo_weather_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;

import com.example.sudo_weather_app.adapters.DailyRecyclerViewListAdapter;
import com.example.sudo_weather_app.models.DailyModel;
import com.example.sudo_weather_app.models.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ForecastDailyScreen extends AppCompatActivity {

    Date dateTimeValue;
    Boolean isSelected;
    DailyRecyclerViewListAdapter dailyRecyclerViewListAdapter;
    ArrayList<DailyModel> dailyModelArrayList;
    RecyclerView dailyForecast_RecyclerView;
    WeatherModel weatherModel;

    SimpleDateFormat entireDateValue = new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
    SimpleDateFormat timeValue = new SimpleDateFormat("h:mm a", Locale.getDefault());
    SimpleDateFormat dayValueDate = new SimpleDateFormat("EEEE", Locale.getDefault());
    SimpleDateFormat getDayValueDaily = new SimpleDateFormat("EEEE, MM/dd", Locale.getDefault());
    String entireDateValueStr, timeValueStr, dayValueDateStr, getDayValueDailyStr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast_daily_screen);

        setIntentData();
        setUIScreen();
    }

    private void setUIScreen() {
        dailyForecast_RecyclerView = findViewById(R.id.dailyForecast_RecyclerView);
        setTitle(weatherModel.address +" 15 day");

        dailyModelArrayList = new ArrayList<>();
        dailyModelArrayList.addAll(weatherModel.getDailyModelsArrayList());

        dailyRecyclerViewListAdapter = new DailyRecyclerViewListAdapter(this, dailyModelArrayList,isSelected);
        dailyForecast_RecyclerView.setAdapter(dailyRecyclerViewListAdapter);
        dailyForecast_RecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    void setIntentData(){
        weatherModel = (WeatherModel) getIntent().getExtras().get("data");
        isSelected = getIntent().getBooleanExtra("isSelected", true);
        Log.d("data", "data: "+getIntent().getExtras().get("data"));
    }

    public String getDateTime(long datetimeEpoch, int xReturn)
    {
        dateTimeValue = new Date(datetimeEpoch * 1000);

        entireDateValueStr = entireDateValue.format(dateTimeValue);
        timeValueStr = timeValue.format(dateTimeValue);
        dayValueDateStr = dayValueDate.format(dateTimeValue);
        getDayValueDailyStr = getDayValueDaily.format(dateTimeValue);

        String returnValue = entireDateValueStr;

        switch(xReturn){
            case 1:
                returnValue = timeValueStr;
                break;
            case 2:
                returnValue = dayValueDateStr;
                break;
            case 3:
                returnValue = getDayValueDailyStr;
                break;
        }
        return returnValue;
    }

    @SuppressLint("DiscouragedApi")
    public int getIcon(String icon)
    {
        icon = icon.replace("-", "_");
        return this.getResources().getIdentifier(icon, "drawable", this.getPackageName());
    }
}