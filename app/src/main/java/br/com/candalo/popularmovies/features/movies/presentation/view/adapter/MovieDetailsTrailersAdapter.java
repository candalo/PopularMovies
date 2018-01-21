package br.com.candalo.popularmovies.features.movies.presentation.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieDetailsView;


public class MovieDetailsTrailersAdapter extends RecyclerView.Adapter<MovieDetailsTrailersAdapter.MovieDetailsViewHolder> {

    private List<Video> trailers;
    private MovieDetailsView movieDetailsView;

    public MovieDetailsTrailersAdapter(List<Video> trailers, MovieDetailsView movieDetailsView) {
        this.trailers = trailers;
        this.movieDetailsView = movieDetailsView;
    }

    @Override
    public MovieDetailsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_movie_trailer, parent, false);

        return new MovieDetailsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieDetailsViewHolder holder, int position) {
        Video trailer = trailers.get(position);
        holder.movieTrailerTitle.setText(trailer.getName());
        holder.bind(trailer);
        removeDividerFromLastItem(holder.itemDivider, position);
    }

    private void removeDividerFromLastItem(View divider, int position) {
        if (trailers.size() == position + 1) {
            divider.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return trailers != null ? trailers.size() : 0;
    }

    class MovieDetailsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Video trailer;
        TextView movieTrailerTitle;
        View itemDivider;

        MovieDetailsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            movieTrailerTitle = itemView.findViewById(R.id.tv_trailer_title);
            itemDivider = itemView.findViewById(R.id.view_divider);
        }

        void bind(Video trailer) {
            this.trailer = trailer;
        }

        @Override
        public void onClick(View v) {
            movieDetailsView.onTrailerItemClicked(trailer);
        }
    }
}
