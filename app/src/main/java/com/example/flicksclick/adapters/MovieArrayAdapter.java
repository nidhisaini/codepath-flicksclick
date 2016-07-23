package com.example.flicksclick.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.flicksclick.R;
import com.example.flicksclick.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;


public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView movieImage;
        ImageView moveBackdropImage;
    }

    //Constructor
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //get data for item position
        Movie movie = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        int orientation = getContext().getResources().getConfiguration().orientation;

        //check if existing view being resued
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

                viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                convertView.setTag(viewHolder);
            } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                convertView = inflater.inflate(R.layout.item_movie, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);

                viewHolder.moveBackdropImage = (ImageView) convertView.findViewById(R.id.ivBackdropImage);
                convertView.setTag(viewHolder);
            }

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        viewHolder.title.setText(movie.getOriginalTitle());
        viewHolder.overview.setText(movie.getOverview());

        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop()
                    .placeholder(R.drawable.placeholder_img)
                    .error(R.drawable.placeholder_error)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.movieImage);
        }else if(orientation == Configuration.ORIENTATION_LANDSCAPE){

            Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                    .placeholder(R.drawable.placeholder_img)
                    .error(R.drawable.placeholder_error)
                    .transform(new RoundedCornersTransformation(10, 10))
                    .into(viewHolder.moveBackdropImage);
        }
        // Return the completed view to render on screen
        return convertView;
    }
}
