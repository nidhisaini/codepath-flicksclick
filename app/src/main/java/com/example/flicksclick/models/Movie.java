package com.example.flicksclick.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by NidhiSaini on 7/20/16.
 */
public class Movie {

    String posterPath;
    String originalTitle;
    String overview;
    String backdropPath;
    double voteAverage;
    String releaseDate;
    int id;


    //getters
    public int getId() {
        return id;
    }

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w780/%s", backdropPath);
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }


    //constructor object that can take a json obj and extract out fields for each property
    public Movie(JSONObject jsonObject) throws JSONException {

        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.voteAverage = jsonObject.getDouble("vote_average");
        this.releaseDate = jsonObject.getString("release_date");
        this.id = jsonObject.getInt("id");
    }

    //get a list of Json movies and iterate through each json array and convert each of them to a movie
    public static ArrayList<Movie> fromJSONArray(JSONArray array) {

        ArrayList<Movie> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}


