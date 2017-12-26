package br.com.candalo.popularmovies.features.movies.presentation.view;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
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

    class MoviesViewHolder extends RecyclerView.ViewHolder {

        private static final String BASE_THUMBNAIL_URL = "http://image.tmdb.org/t/p/w780/";
        private Context context;
        private ImageView posterThumbnailImageView;

        MoviesViewHolder(View itemView, Context context) {
            super(itemView);
            this.context = context;
            posterThumbnailImageView = itemView.findViewById(R.id.iv_poster_thumbnail);
        }

        void bind(Movie movie) {
            String thumbnail = BASE_THUMBNAIL_URL + movie.getPosterThumbnail();
            Picasso.with(context).load(thumbnail).into(posterThumbnailImageView);
        }
    }

}
