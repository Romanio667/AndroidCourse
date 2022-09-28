package com.example.guessstar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.AsyncQueryHandler;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.BitSet;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private ImageView imageViewStar;
    private String url="https://svenskainfluencers.nu/kandisar/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        imageViewStar=findViewById(R.id.imageViewStar);
        getContent();
    }

    private void getContent(){
        DownloadContentTask task=new DownloadContentTask();
        try {
            String content=task.execute(url).get();
            String start
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class DownloadContentTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder result=new StringBuilder();
            URL url=null;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                InputStreamReader inputStreamReader=new InputStreamReader(inputStream);
                BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
                String line=bufferedReader.readLine();
                while(line!=null){
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
    }

    private static class DownloadImageTask extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            StringBuilder result=new StringBuilder();
            URL url=null;
            HttpURLConnection urlConnection=null;
            try {
                url=new URL(strings[0]);
                urlConnection=(HttpURLConnection) url.openConnection();
                InputStream inputStream=urlConnection.getInputStream();
                Bitmap bitmap=BitmapFactory.decodeStream(inputStream);
                return bitmap;

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
    }
}