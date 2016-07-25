package com.example.flicksclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class MovieDetailsActivity extends AppCompatActivity {
    ImageView ivMovieImage;
    TextView tvMovieName;
    TextView tvMovieReleaseDate;
    RatingBar rbMovieRatings;
    TextView tvMoviesOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        String Title = getIntent().getStringExtra("Title");
        String ReleaseDate = getIntent().getStringExtra("ReleaseDate");
        String Overview = getIntent().getStringExtra("Overview");
        double Ratings = getIntent().getDoubleExtra("Ratings", 20);
        String ImageURL = getIntent().getStringExtra("ImageURL");

        ivMovieImage = (ImageView) findViewById(R.id.ivMovieImage);
        tvMovieName = (TextView)findViewById(R.id.tvMovieName);
        rbMovieRatings =(RatingBar)findViewById(R.id.rbMovieRatings);
        tvMovieReleaseDate = (TextView)findViewById(R.id.tvMovieReleaseDate);
        tvMoviesOverview =(TextView)findViewById(R.id.tvMoviesOverview);

        Picasso.with(MovieDetailsActivity.this).load(ImageURL).fit().centerCrop()
                .placeholder(R.drawable.placeholder_img)
                .error(R.drawable.placeholder_error)
                .transform(new RoundedCornersTransformation(10, 10)).into(ivMovieImage);
        tvMovieName.setText(Title);
        tvMovieReleaseDate.setText(ReleaseDate);
        tvMoviesOverview.setText(Overview);
        rbMovieRatings.setRating((float) Ratings);
    }
}
