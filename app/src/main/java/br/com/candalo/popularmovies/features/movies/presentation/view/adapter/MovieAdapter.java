package br.com.candalo.popularmovies.features.movies.presentation.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieView;
import br.com.candalo.popularmovies.features.movies.util.MovieUtils;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    private List<Movie> movies;
    private MovieView movieView;

    public MovieAdapter(List<Movie> movies, MovieView movieView) {
        this.movies = movies;
        this.movieView = movieView;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_poster_thumbnail, parent, false);

        return new MoviesViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        Movie movie = movies.get(position);
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {
        return movies != null ? movies.size() : 0;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context context;
        private ImageView posterThumbnailImageView;
        private Movie movie;

        MoviesViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            posterThumbnailImageView = itemView.findViewById(R.id.iv_poster_thumbnail);
            posterThumbnailImageView.setOnClickListener(this);
        }

        void bind(Movie movie) {
            this.movie = movie;
            String thumbnail = MovieUtils.MOVIE_IMAGE_BASE_URL + movie.getPosterThumbnail();
            MovieUtils.loadImage(context, thumbnail, posterThumbnailImageView);
        }


        @Override
        public void onClick(View view) {
            movieView.onMovieSelected(movie);
        }
    }

}
