package com.example.sudo_weather_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sudo_weather_app.adapters.HourlyRecyclerViewListAdapter;
import com.example.sudo_weather_app.apiData.ApiDataGet;
import com.example.sudo_weather_app.models.HourlyModel;
import com.example.sudo_weather_app.models.WeatherModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Date dateTimeValue;
    SimpleDateFormat entireDateValue = new SimpleDateFormat("EEE MMM dd h:mm a, yyyy", Locale.getDefault());
    SimpleDateFormat timeValue = new SimpleDateFormat("h:mm a", Locale.getDefault());
    SimpleDateFormat dayValueDate = new SimpleDateFormat("EEEE", Locale.getDefault());
    String entireDateValueStr, timeValueStr, dayValueDateStr, getDayValueDailyStr;

    private Menu mainActi_menu;
    Boolean isSelected  = true;
    TextView mainActi_feelsLike, mainActi_temp, mainActi_morningTemp, mainActi_internetCheck, mainActi_weatherText;
    TextView mainActi_visibility, mainActi_dateTime, mainActi_humidity, mainActi_eveningTemp, mainActi_afternoonTemp;
    TextView mainActi_sunrise, mainActi_sunset, mainActi_uV, mainActi_windSpeed, mainActi_nightTemp;
    SwipeRefreshLayout swipeRefreshLayout;
    ImageView imageView;
    WeatherModel weatherModel;
    RecyclerView mainActi_HourlyData;
    private SharedPreferences sharedPreferences;
    ConstraintLayout mainActi_constraintLayout;
    SpannableStringBuilder spannableStringBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setIdToLayout();
    }

    private void checkPrefSettings() {
        if(sharedPreferences.getString("location","Chicago, Illinois").isEmpty())
        {
            sharedPreferences.edit().putString("location", "Chicago, Illinois").apply();
            sharedPreferences.edit().putBoolean("isSelected", isSelected).apply();
        }else
        {
            isSelected = sharedPreferences.getBoolean("isSelected", true);
        }
        onScreenRefreshCall();
        if(checkInternetConnection())
        {
            mainActi_constraintLayout.setVisibility(View.VISIBLE);
            mainActi_internetCheck.setVisibility(View.GONE);
            Log.d("Before Call data",sharedPreferences.getAll().toString());
            getApiDataCall(sharedPreferences.getString("location", "Chicago , Illinois"));
        }
        else
        {
            mainActi_constraintLayout.setVisibility(View.GONE);
            mainActi_internetCheck.setVisibility(View.VISIBLE);
        }
    }

    private void setIdToLayout() {
        mainActi_morningTemp = findViewById(R.id.mainActi_morningTemp);
        mainActi_afternoonTemp = findViewById(R.id.mainActi_afternoonTemp);
        mainActi_eveningTemp = findViewById(R.id.mainActi_eveningTemp);
        mainActi_nightTemp = findViewById(R.id.mainActi_nightTemp);
        mainActi_sunset = findViewById(R.id.mainActi_sunset);
        mainActi_sunrise = findViewById(R.id.mainActi_sunRise);
        mainActi_uV = findViewById(R.id.mainActi_uV);
        mainActi_windSpeed = findViewById(R.id.mainActi_windSpeed);
        mainActi_humidity = findViewById(R.id.mainActi_humidity);
        mainActi_dateTime = findViewById(R.id.mainActi_date);
        mainActi_visibility = findViewById(R.id.mainActi_visibility);
        mainActi_weatherText = findViewById(R.id.mainActi_weatherText);
        mainActi_internetCheck = findViewById(R.id.mainActi_internetCheck);
        mainActi_temp = findViewById(R.id.mainActi_temp);
        mainActi_feelsLike = findViewById(R.id.mainActi_feelsLike);
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
        imageView = findViewById(R.id.mainActi_Image);
        mainActi_HourlyData = findViewById(R.id.mainActi_HourlyData);
        mainActi_constraintLayout = findViewById(R.id.mainActi_constraintLayout);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        Log.d("Shared Preference",sharedPreferences.getAll().toString());
        checkPrefSettings();
    }

    private void getApiDataCall(String location)
    {
        ApiDataGet.getApiData(this, location, isSelected);
        //Log.d("GetAPI",weatherModel.toString());
    }

    private boolean checkInternetConnection()
    {
        ConnectivityManager connectivityManager = getSystemService(ConnectivityManager.class);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnectedOrConnecting());
    }

    public void onScreenRefreshCall()
    {
        swipeRefreshLayout.setOnRefreshListener(() ->
        {
            if(checkInternetConnection())
            {
                getApiDataCall(sharedPreferences.getString("location","Chicago , Illinois"));
                swipeRefreshLayout.setRefreshing(false);
                mainActi_constraintLayout.setVisibility(View.VISIBLE);
                mainActi_internetCheck.setVisibility(View.GONE);
            }
            else{
                mainActi_constraintLayout.setVisibility(View.GONE);
                mainActi_internetCheck.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        this.mainActi_menu = menu;
        if (isSelected)
        {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.units_f));
        } else
        {
            menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.units_c));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(checkInternetConnection()) {
            if (item.getItemId() == R.id.menuItem_Units) {
                    if (isSelected) {
                        isSelected = false;
                        mainActi_menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.units_c));
                        sharedPreferences.edit().putBoolean("isSelected", isSelected).apply();
                    } else {
                        isSelected = true;
                        mainActi_menu.getItem(0).setIcon(ContextCompat.getDrawable(this, R.drawable.units_f));
                        sharedPreferences.edit().putBoolean("isSelected", isSelected).apply();
                    }
                    getApiDataCall(sharedPreferences.getString("location", "Chicago , Illinois"));
            } else if (item.getItemId() == R.id.menuItem_Daily) {
                    Intent intent = new Intent(this, ForecastDailyScreen.class);
                    intent.putExtra("isSelected", isSelected);
                    intent.putExtra("data", weatherModel);
                    startActivity(intent);
            } else if (item.getItemId() == R.id.menuItem_Location) {
                    displayDialogBox();
            } else {
                return super.onOptionsItemSelected(item);
            }
        }
        else {
            Toast.makeText(getApplicationContext(), "Please check your internet connection !!", Toast.LENGTH_LONG).show();
        }
        return true;
    }

    //Change required here
    private void displayDialogBox()
    {
        if (checkInternetConnection())
        {
            android.app.AlertDialog.Builder alertBuilder = new android.app.AlertDialog.Builder(this);

            final EditText et = new EditText(this);

            et.setGravity(Gravity.CENTER_HORIZONTAL);
            alertBuilder.setView(et);

            alertBuilder.setTitle("Enter Location");
            alertBuilder.setMessage("\nFor US Location, enter as 'City', or 'City, State'\n\nFor international  locations enter as 'City,Country'");

            alertBuilder.setPositiveButton("OK", (dialog, id) ->
            {
                sharedPreferences.edit().putString("location", et.getText().toString()).apply();
                sharedPreferences.edit().putBoolean("isSelected", isSelected).apply();
                getApiDataCall(et.getText().toString());
            });
            alertBuilder.setNegativeButton("Cancel", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = alertBuilder.create();
            dialog.show();
        } else
        {
            Toast.makeText(getApplicationContext(), "This requires devices to be connected to the internet", Toast.LENGTH_LONG).show();
        }
    }

    //Change required
    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    public void setWeatherData(WeatherModel weatherModel1)
    {
        weatherModel = weatherModel1;

        if (weatherModel == null)
        {
            Toast.makeText(this, "Please enter a valid location", Toast.LENGTH_SHORT).show();
            return;
        }
        setTitle(weatherModel.address);

        Log.d("xxx",getDateTime(weatherModel.currentConditions.datetimeEpoch,0));

        mainActi_dateTime.setText(getDateTime(weatherModel.currentConditions.datetimeEpoch,0));
        mainActi_temp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.currentConditions.temp));
        mainActi_feelsLike.setText("Feels Like "+String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.currentConditions.feelslike));
        mainActi_weatherText.setText(weatherModel.currentConditions.conditions +" ("+String.format("%.0f",weatherModel.currentConditions.cloudcover)+"% "+"clouds"+")");

        String strGusting = String.format(" gusting to %.0f " + (isSelected ? "mph" : "mps"), weatherModel.currentConditions.windgust);
        mainActi_windSpeed.setText(String.format("Winds: "+getCompassDirection(weatherModel.currentConditions.winddir)+" at %.0f " + (isSelected ? "mph" : "mps"), weatherModel.currentConditions.windspeed) +strGusting);

        mainActi_humidity.setText("Humidity: " + String.format("%.0f",weatherModel.currentConditions.humidity)+"%");
        mainActi_uV.setText("UV Index: " + String.format("%.0f",weatherModel.currentConditions.uvindex));
        mainActi_visibility.setText("Visibility: " +weatherModel.currentConditions.visibility +" mi");


        int orientation = this.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT)
        {
            mainActi_morningTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(8).temp));
            mainActi_afternoonTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(13).temp));
            mainActi_eveningTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(17).temp));
            mainActi_nightTemp.setText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(23).temp));
        } else
        {
            mainActi_morningTemp.setText(getText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(8).temp)+" 8 am","8 am"));
            mainActi_afternoonTemp.setText(getText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(13).temp)+" 1 pm","1 pm"));
            mainActi_eveningTemp.setText(getText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(17).temp)+" 5 pm","5 pm"));
            mainActi_nightTemp.setText(getText(String.format("%.0f°" + (isSelected ? "F" : "C"), weatherModel.days.get(0).hours.get(23).temp)+" 11 pm","11 pm"));;
        }

        mainActi_sunrise.setText("Sunrises "+getDateTime(weatherModel.currentConditions.sunriseEpoch,1));
        mainActi_sunset.setText("Sunset "+getDateTime(weatherModel.currentConditions.sunsetEpoch,1));
        imageView.setImageResource(getIcon(weatherModel.currentConditions.icon));

        ArrayList<HourlyModel> list = new ArrayList<>();
        for(int i = 0 ; i<4 ; i++)
        {
            list.addAll(weatherModel.getDailyModelsArrayList().get(i).getHours());
        }

        HourlyRecyclerViewListAdapter mAdapter = new HourlyRecyclerViewListAdapter(list, this,isSelected);
        mainActi_HourlyData.setAdapter(mAdapter);
        mainActi_HourlyData.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

    }

    private SpannableStringBuilder getText(String strText,String indexText)
    {
        spannableStringBuilder = new SpannableStringBuilder(strText);
        SuperscriptSpan superscriptSpan = new SuperscriptSpan();
        spannableStringBuilder.setSpan(superscriptSpan, strText.indexOf(indexText), strText.indexOf(indexText) + (indexText).length(),
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        showSmallSizeText(indexText,strText);
        return spannableStringBuilder;
    }

    private void showSmallSizeText(String s, String strText)
    {
        RelativeSizeSpan relativeSizeSpan = new RelativeSizeSpan(.5f);
        spannableStringBuilder.setSpan(relativeSizeSpan, strText.indexOf(s), strText.indexOf(s) + (s).length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
    }

    private String getCompassDirection(long degrees)
    {
        if (degrees >= 337.5 || degrees < 22.5)
            return "N";
        if (degrees >= 22.5 && degrees < 67.5)
            return "NE";
        if (degrees >= 67.5 && degrees < 112.5)
            return "E";
        if (degrees >= 112.5 && degrees < 157.5)
            return "SE";
        if (degrees >= 157.5 && degrees < 202.5)
            return "S";
        if (degrees >= 202.5 && degrees < 247.5)
            return "SW";
        if (degrees >= 247.5 && degrees < 292.5)
            return "W";
        if (degrees >= 292.5 && degrees < 337.5)
            return "NW";
        return "X";
    }


    public String getDateTime(long datetimeEpoch, int xReturn)
    {
        dateTimeValue = new Date(datetimeEpoch * 1000);

        entireDateValueStr = entireDateValue.format(dateTimeValue);
        timeValueStr = timeValue.format(dateTimeValue);
        dayValueDateStr = dayValueDate.format(dateTimeValue);

        String returnValue = entireDateValueStr;

        switch(xReturn){
            case 1:
                returnValue = timeValueStr;
                break;
            case 2:
                returnValue = dayValueDateStr;
                break;
            default:
                returnValue = entireDateValueStr;
        }
        return returnValue;
    }

    public int getIcon(String icon)
    {
        icon = icon.replace("-", "_");
        return this.getResources().getIdentifier(icon, "drawable", this.getPackageName());
    }
}