package com.example.sudo_weather_app.apiData;

import android.net.Uri;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.sudo_weather_app.MainActivity;
import com.example.sudo_weather_app.R;
import com.example.sudo_weather_app.models.WeatherModel;
import com.google.gson.Gson;
import org.json.JSONObject;

public class ApiDataGet {

    private static RequestQueue requestQueue;
    private static WeatherModel weatherModel;


    private static void parseJSON(String s, MainActivity mainActivity)
    {
        Gson gsonDays;
        try {
            gsonDays = new Gson();
            weatherModel = gsonDays.fromJson(s, WeatherModel.class);
            //Log.d("112233", String.valueOf(weatherModel.days.get(1).tempmax));
            mainActivity.setWeatherData(weatherModel);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ParseJsonError",e.toString());
        }
    }

    public static void getApiData(MainActivity mainActivity,String user_Location, boolean fahrenheit)
    {
        Uri.Builder uriBuilder;
        String urlString;
        JsonObjectRequest objectRequest;
        try {

            requestQueue = Volley.newRequestQueue(mainActivity);

            uriBuilder = Uri.parse(mainActivity.getString(R.string.api_url)).buildUpon();
            uriBuilder.appendEncodedPath(user_Location);
            uriBuilder.appendQueryParameter("unitGroup", (fahrenheit ? "us" : "metric"));
            uriBuilder.appendQueryParameter("lang", "en");
            uriBuilder.appendQueryParameter("key", mainActivity.getString(R.string.api_key));
            urlString = uriBuilder.build().toString();

            Log.d("urlString", urlString);

            Response.Listener<JSONObject> jsonObjectListener =
                    response ->
                    {
                        Log.d("Response", response.toString());
                        parseJSON(response.toString(), mainActivity);
                    };
            Response.ErrorListener jsonObjectError =
                    error1 -> mainActivity.setWeatherData(null);

            objectRequest =
                    new JsonObjectRequest(Request.Method.GET, urlString,
                            null, jsonObjectListener, jsonObjectError);

            Log.d("getApiData1111", objectRequest.toString());
            requestQueue.add(objectRequest);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
