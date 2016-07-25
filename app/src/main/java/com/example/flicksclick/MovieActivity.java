package com.example.flicksclick;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.flicksclick.adapters.MovieArrayAdapter;
import com.example.flicksclick.models.Movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {
    String url = "https://api.themoviedb.org/3/movie/now_playing?api_key=188ea0ba688350f4c5c4ddf56bc354bd";
    private SwipeRefreshLayout swipeContainer;
    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvItems;
    ImageButton ibPlay;
    int movieId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //check for orientation change
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_movie);

        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_movie);
        }


        lvItems = (ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvItems.setAdapter(movieAdapter);


        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;

                try {
                    movieJsonResults = response.getJSONArray("results");
                        movies.clear();
                        movies.addAll(Movie.fromJSONArray(movieJsonResults));
                        movieAdapter.notifyDataSetChanged();

                    Log.d("DEBUG", movieJsonResults.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int pos = position;
                //String rowid = movies.get(position);
                String title = movies.get(pos).getOriginalTitle();
                String releseDate = movies.get(pos).getReleaseDate();
                String overView = movies.get(pos).getOverview();
                double ratings = movies.get(pos).getVoteAverage();
                String imageURL = movies.get(pos).getBackdropPath();
                movieId = movies.get(pos).getId();

                // first parameter is the context, second is the class of the activity to launch
                Intent i = new Intent(MovieActivity.this, MovieDetailsActivity.class);
                i.putExtra("Title", title);
                i.putExtra("ReleaseDate", releseDate);
                i.putExtra("Overview", overView);
                i.putExtra("Ratings", ratings);
                i.putExtra("ImageURL", imageURL);
                startActivity(i); // brings up the second activity
            }
        });

       /* ibPlay =(ImageButton)findViewById(R.id.ibPlay);

        ibPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MovieActivity.this, VideoActivity.class);
                i.putExtra("movieId",movieId);
                startActivity(i);

            }
        });*/
    }

    private void fetchTimelineAsync(int i) {

         movieAdapter.notifyDataSetChanged();
        // Now we call setRefreshing(false) to signal refresh has finished


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeContainer.setRefreshing(false);
            }
        }, 5000);
    }
}
