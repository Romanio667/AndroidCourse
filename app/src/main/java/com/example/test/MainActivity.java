package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.test.data.Movie;
import com.example.test.utils.JSONUtils;
import com.example.test.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*JSONObject jsonObject=NetworkUtils.getJSONFromNetwork(NetworkUtils.POPULARITY, 5);
        ArrayList<Movie> movies= JSONUtils.getMoviesFromJSON(jsonObject);
        StringBuilder builder = new StringBuilder();
        for(Movie movie : movies){
            builder.append(movie.getTitle()).append("\n");
        }
        Log.i("MyResult", builder.toString());*/
        JSONObject jsonObject=NetworkUtils.getJSONFromNetwork(NetworkUtils.TOP_RATED,3);
        if(jsonObject==null) {
            Toast.makeText(this,"Произошла ошибка", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Успешно", Toast.LENGTH_SHORT).show();
        }

    }
}