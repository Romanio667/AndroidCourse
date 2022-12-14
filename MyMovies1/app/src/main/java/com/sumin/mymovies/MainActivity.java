package com.sumin.mymovies;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.sumin.mymovies.data.MainViewModel;
import com.sumin.mymovies.data.Movie;
import com.sumin.mymovies.utils.JSONUtils;
import com.sumin.mymovies.utils.NetworkUtils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPosters;
    private MovieAdapter movieAdapter;
    private Switch switchSort;
    private TextView textViewTopRated;
    private TextView textViewPopularity;

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        switchSort=findViewById(R.id.switchSort);
        textViewPopularity=findViewById(R.id.textViewPopularity);
        textViewTopRated=findViewById(R.id.textViewTopRated);
        recyclerViewPosters=findViewById(R.id.recyclerViewPosters);
        recyclerViewPosters.setLayoutManager(new GridLayoutManager(this,2));
        movieAdapter=new MovieAdapter();
        switchSort.setChecked(true);
        recyclerViewPosters.setAdapter(movieAdapter);
        switchSort.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                setMethodOfSort(isChecked);
            }
        });
        switchSort.setChecked(false);
        movieAdapter.setOnPosterClickListener(new MovieAdapter.OnPosterClickListener() {
            @Override
            public void onPosterClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked"+position, Toast.LENGTH_SHORT).show();
            }
        });
        movieAdapter.setOnReachEndListener(new MovieAdapter.OnReachEndListener() {
            @Override
            public void onReachEnd() {
                Toast.makeText(MainActivity.this, "?????????? ????????????", Toast.LENGTH_SHORT).show();
            }
        });
        LiveData<List<Movie>> moviesFromLiveData=viewModel.getMovies();
        moviesFromLiveData.observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(@Nullable List<Movie> movies) {
                movieAdapter.setMovies(movies);
            }
        });
    }

    public void onClickSetPopularity(View view) {
        setMethodOfSort(false);
        switchSort.setChecked(false);
    }

    public void onClickSetTopRated(View view) {
        setMethodOfSort(true);
        switchSort.setChecked(true);
    }
    private void setMethodOfSort(boolean isTopRated){
        int methodOfSort;
        if(isTopRated){
            textViewTopRated.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewPopularity.setTextColor(getResources().getColor(R.color.white_color));
            methodOfSort=NetworkUtils.TOP_RATED;
        }else {
            textViewPopularity.setTextColor(getResources().getColor(R.color.colorAccent));
            textViewTopRated.setTextColor(getResources().getColor(R.color.white_color));
            methodOfSort=NetworkUtils.POPULARITY;
        }
        downloadData(methodOfSort,1);

    }

    private  void downloadData(int methodOfSort, int page){
        JSONObject jsonObject=NetworkUtils.getJSONFromNetwork(methodOfSort,1);
        ArrayList<Movie> movies=JSONUtils.getMoviesFromJSON(jsonObject);
        if(movies!=null && !movies.isEmpty()) {
            movieAdapter.setMovies(movies);
            for(Movie movie : movies){
                viewModel.insertMovie(movie);
            }
        }
    }

}
