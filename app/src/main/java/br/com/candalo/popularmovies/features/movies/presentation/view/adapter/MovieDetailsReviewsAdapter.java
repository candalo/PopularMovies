package br.com.candalo.popularmovies.features.movies.presentation.view.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.presentation.view.MovieDetailsView;

public class MovieDetailsReviewsAdapter extends RecyclerView.Adapter<MovieDetailsReviewsAdapter.MovieReviewsViewHolder> {

    private List<MovieReview> movieReviews;
    private MovieDetailsView movieDetailsView;

    public MovieDetailsReviewsAdapter(List<MovieReview> movieReviews, MovieDetailsView movieDetailsView) {
        this.movieReviews = movieReviews;
        this.movieDetailsView = movieDetailsView;
    }

    @Override
    public MovieReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.item_movie_review, parent, false);

        return new MovieReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewsViewHolder holder, int position) {
        MovieReview movieReview = movieReviews.get(position);
        holder.commentTextView.setText(movieReview.getContent());
        holder.bind(movieReview);
        removeDividerFromLastItem(holder.itemDivider, position);
    }

    private void removeDividerFromLastItem(View divider, int position) {
        if (movieReviews.size() == position + 1) {
            divider.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return movieReviews != null ? movieReviews.size() : 0;
    }

    class MovieReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private MovieReview movieReview;
        TextView commentTextView;
        View itemDivider;

        public MovieReviewsViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            commentTextView = itemView.findViewById(R.id.tv_comment);
            itemDivider = itemView.findViewById(R.id.view_divider);
        }

        void bind(MovieReview movieReview) {
            this.movieReview = movieReview;
        }

        @Override
        public void onClick(View v) {
            movieDetailsView.onReviewItemClicked(movieReview);
        }
    }
}
