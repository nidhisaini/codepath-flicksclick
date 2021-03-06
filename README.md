**### CodePath : FlickClick**

**User Stories:**

**Completed user stories:**
- User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.
- Lists should be fully optimized for performance with the ViewHolder pattern.
- Views should be responsive for both landscape/portrait mode.
- In portrait mode, the poster image, title, and movie overview is shown.
- In landscape mode, the rotated layout should use the backdrop image instead and show the title and movie overview to the right of it.
- Add pull-to-refresh for popular stream with SwipeRefreshLayoutDisplay a nice default placeholder graphic for each image during loading 
- Improve the user interface through styling and coloring 
- For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is 
- Stretch: Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- Less popular videos rely on the detailed page should show an image preview that can initiate playing a YouTube video.
- Stretch: Add a play icon overlay to popular movies to indicate that the movie can be played.
- Stretch: Add a rounded corners for the images using the Picasso transformations.


**Extensions(not included in current version):**
- See the trailers API for video information. Here's a sample request.
- displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous ListViews and use different ViewHolder layout files for popular movies and less popular ones.
- Stretch: Allow video posts to be played in full-screen using the YouTubePlayerView
- When clicking on a popular movie (i.e. a movie voted for more than 5 stars) the video should be played immediately.
- Stretch: Leverage the data binding support module to bind data into one or more activity layout templates. 
- Stretch: Apply the popular ButterKnife annotation library to reduce view boilerplate.



**FlickClick Demo**

![flickclickdemo1](https://cloud.githubusercontent.com/assets/5497374/17090467/9673e4f6-51e5-11e6-883f-5331d5d40a75.gif)
