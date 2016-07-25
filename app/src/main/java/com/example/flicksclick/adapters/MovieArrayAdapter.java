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
    Movie movie ;
    int orientation;


    // View lookup cache
    private static class ViewHolder {
        TextView title;
        TextView overview;
        ImageView movieImage;
        ImageView movieBackdropImage;
    }


    //Constructor
    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, 0, movies);
    }


    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
            return 0;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       // int viewType = this.getItemViewType(position);
        //get data for item position
        int viewType = 0;
        movie = getItem(position);

        int type = getItemViewType(position);


        if (movie.getVoteAverage() >= 5) {
        /*switch (viewType) {

            case 0:*/
            ViewHolder viewHolderOne;// Check if an existing view is being reused, otherwise inflate the view
            // view lookup cache stored in tag

            orientation = getContext().getResources().getConfiguration().orientation;

            //check if existing view is being reused
            if (convertView == null) {
                viewHolderOne = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());

                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    convertView = inflater.inflate(R.layout.item_movie, parent, false);
                    viewHolderOne.title = (TextView) convertView.findViewById(R.id.tvTitle);
                    viewHolderOne.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                    viewHolderOne.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
                    convertView.setTag(viewHolderOne);

                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    convertView = inflater.inflate(R.layout.item_movie, parent, false);
                    viewHolderOne.title = (TextView) convertView.findViewById(R.id.tvTitle);
                    viewHolderOne.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                    viewHolderOne.movieBackdropImage = (ImageView) convertView.findViewById(R.id.ivBackdropImage);
                    convertView.setTag(viewHolderOne);
                }

            } else {

                viewHolderOne = (ViewHolder) convertView.getTag();
            }
            if (movie != null) {
                // Populate the data into the template view using the data object
                if (viewHolderOne.title != null) {
                    viewHolderOne.title.setText(movie.getOriginalTitle());
                }

                if (viewHolderOne.overview != null) {
                    viewHolderOne.overview.setText(movie.getOverview());
                }

                if (viewHolderOne.movieImage != null) {
                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        Picasso.with(getContext()).load(movie.getPosterPath()).fit().centerCrop()
                                .placeholder(R.drawable.placeholder_img)
                                .error(R.drawable.placeholder_error)
                                .transform(new RoundedCornersTransformation(10, 10))
                                .into(viewHolderOne.movieImage);
                    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

                        Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                                .placeholder(R.drawable.placeholder_img)
                                .error(R.drawable.placeholder_error)
                                .transform(new RoundedCornersTransformation(10, 10))
                                .into(viewHolderOne.movieBackdropImage);
                    }//end of viewHolderOne.movieImage != null*/
                }

            }//end of if(movie !=  null)
        }
        else  if (movie.getVoteAverage() < 5) {
                /*break;

            case 1:*/
            ViewHolder viewHolderTwo;

            orientation = getContext().getResources().getConfiguration().orientation;

            //check if existing view being resued
            if (convertView == null) {
                viewHolderTwo = new ViewHolder();
                LayoutInflater inflater = LayoutInflater.from(getContext());

                if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                    convertView = inflater.inflate(R.layout.item_movie_five_stars, parent, false);
                    viewHolderTwo.movieBackdropImage = (ImageView) convertView.findViewById(R.id.ivFullBackdropImage);
                    convertView.setTag(viewHolderTwo);
                } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    convertView = inflater.inflate(R.layout.item_movie_five_stars, parent, false);
                    viewHolderTwo.movieBackdropImage = (ImageView) convertView.findViewById(R.id.ivFullBackdropImage);
                    convertView.setTag(viewHolderTwo);
                }

            } else {

                viewHolderTwo = (ViewHolder) convertView.getTag();
            }

            if (movie != null) {
                if (viewHolderTwo.movieBackdropImage != null) {
                    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
                        Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                                .placeholder(R.drawable.placeholder_img)
                                .error(R.drawable.placeholder_error)
                                .transform(new RoundedCornersTransformation(10, 10))
                                .into(viewHolderTwo.movieBackdropImage);
                    } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

                        Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop()
                                .placeholder(R.drawable.placeholder_img)
                                .error(R.drawable.placeholder_error)
                                .transform(new RoundedCornersTransformation(10, 10))
                                .into(viewHolderTwo.movieBackdropImage);
                    }
                }//end of viewHolderTwo.movieBackdropImage
            } // end of  if(movie !=  null)

        }
                /*break;
*/
           /* default:
                break;*/


        return convertView;
    }

}
