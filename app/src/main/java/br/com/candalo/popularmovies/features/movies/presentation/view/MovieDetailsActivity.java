package br.com.candalo.popularmovies.features.movies.presentation.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.util.MovieUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieDetailsActivity extends AppCompatActivity {

    @BindView(R.id.tv_movie_title)
    TextView movieTitleTextView;
    @BindView(R.id.iv_backdrop_thumbnail)
    ImageView backdropThumbnailImageView;
    @BindView(R.id.tv_release_date)
    TextView releaseDateTextView;
    @BindView(R.id.tv_user_average)
    TextView userAverageTextView;
    @BindView(R.id.tv_synopsis)
    TextView synopsisTextView;
    private Movie movie;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        injectDependencies();
        setupToolbar();
        movie = getMovieData();
        setupScreenData();
    }

    private void injectDependencies() {
        ButterKnife.bind(this);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.movie_details_toolbar_title);
        }
    }

    private Movie getMovieData() {
        return Parcels.unwrap(getIntent().getParcelableExtra(Movie.class.getName()));
    }

    private void setupScreenData() {
        movieTitleTextView.setText(movie.getTitle());
        MovieUtils.loadImage(this, MovieUtils.MOVIE_IMAGE_BASE_URL + movie.getBackdropThumbnail(), backdropThumbnailImageView);
        releaseDateTextView.setText(movie.getReleaseDate().split("-")[0]); //TODO: Change to a most elegant solution
        userAverageTextView.setText(getString(R.string.user_average, movie.getUserAverage()));
        synopsisTextView.setText(movie.getSynopsis());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home:
                finish();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }
}
