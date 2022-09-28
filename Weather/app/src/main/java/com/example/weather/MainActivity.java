package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView textViewPogoda;
    private EditText editTextCity;
    private final String WEATHER_URL="http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=ru&appid=1b0a79aef9746d2b9c676ec67f419f7a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewPogoda=findViewById(R.id.textViewGL);
        editTextCity=findViewById(R.id.editTextTextPersonName2);

    }

    public void onClickShowWeather(View view) {
        String city=editTextCity.getText().toString().trim();
        if(!city.isEmpty()){
            DownloadJsonTask task=new DownloadJsonTask();
            String url=String.format(WEATHER_URL, city);
            task.execute(url);
        }
    }


    private  class  DownloadJsonTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url=null;
            HttpURLConnection urlConnection=null;
            StringBuilder result=new StringBuilder();
            try {
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                while (line!=null){
                    result.append(line);
                    line=bufferedReader.readLine();
                }
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(urlConnection!=null){
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try{
                JSONObject jsonObject=new JSONObject(s);
                String city=jsonObject.getString("name");

                JSONObject main=jsonObject.getJSONObject("main");
                String temp=main.getString("temp");

                JSONArray jsonArray=jsonObject.getJSONArray("weather");
                JSONObject weather=jsonArray.getJSONObject(0);
                String description=weather.getString("description");
                String weatherString=String.format("%s\nТемпература: %s\nНа улице: %s",city,temp,description);
                textViewPogoda.setText(weatherString);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}