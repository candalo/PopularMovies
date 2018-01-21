package br.com.candalo.popularmovies.features.movies.presentation.view;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.App;
import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.data.di.DaggerMovieComponent;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.domain.models.MovieReview;
import br.com.candalo.popularmovies.features.movies.domain.models.Video;
import br.com.candalo.popularmovies.features.movies.presentation.presenter.MovieDetailsPresenter;
import br.com.candalo.popularmovies.features.movies.presentation.view.adapter.MovieDetailsReviewsAdapter;
import br.com.candalo.popularmovies.features.movies.presentation.view.adapter.MovieDetailsTrailersAdapter;
import br.com.candalo.popularmovies.features.movies.util.MovieUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {

    @Inject
    MovieDetailsPresenter presenter;
    @BindView(R.id.tv_movie_title)
    TextView movieTitleTextView;
    @BindView(R.id.iv_backdrop_thumbnail)
    ImageView backdropThumbnailImageView;
    @BindView(R.id.tv_release_date)
    TextView releaseDateTextView;
    @BindView(R.id.tv_user_average)
    TextView userAverageTextView;
    @BindView(R.id.cb_favorite)
    CheckBox favoriteCheckBox;
    @BindView(R.id.tv_synopsis)
    TextView synopsisTextView;
    @BindView(R.id.rv_movie_trailers)
    RecyclerView movieTrailersRecyclerView;
    @BindView(R.id.rv_movie_reviews)
    RecyclerView movieReviewsRecyclerView;
    private Movie movie;
    private boolean isStarred;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        injectDependencies();
        setupToolbar();
        setupRecyclerViews();
        movie = getMovieData();
        setupScreenData();
        setupPresenter();
    }

    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerMovieComponent
                .builder()
                .applicationComponent(((App)getApplication()).getApplicationComponent())
                .build()
                .inject(this);
    }

    private void setupToolbar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.movie_details_toolbar_title);
        }
    }

    private void setupRecyclerViews() {
        movieTrailersRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieTrailersRecyclerView.setHasFixedSize(true);
        movieReviewsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        movieReviewsRecyclerView.setHasFixedSize(true);
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

    private void setupPresenter() {
        presenter.attachTo(this);
        presenter.loadMovieDetails(movie.getId());
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
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

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showErrorMessage(String errorMessage) {

    }

    @Override
    public void showNetworkErrorMessage() {

    }

    @Override
    public void onTrailersLoaded(List<Video> trailers) {
        MovieDetailsTrailersAdapter adapter = new MovieDetailsTrailersAdapter(trailers, this);
        movieTrailersRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onTrailerItemClicked(Video trailer) {
        startActionViewIntent(getString(R.string.youtube_trailer_url, trailer.getKey()));
    }

    @Override
    public void onReviewsLoaded(List<MovieReview> reviews) {
        MovieDetailsReviewsAdapter adapter = new MovieDetailsReviewsAdapter(reviews, this);
        movieReviewsRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onReviewItemClicked(MovieReview review) {
        startActionViewIntent(review.getUrl());
    }

    private void startActionViewIntent(String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    @Override
    public void showStarred() {
        favoriteCheckBox.setChecked(true);
        isStarred = true;
    }

    @Override
    public void showUnstarred() {
        favoriteCheckBox.setChecked(false);
        isStarred = false;
    }

    @OnClick(R.id.cb_favorite)
    void onClickFavoriteCheckBox() {
        if (isStarred) {
            favoriteCheckBox.setChecked(true);
            return;
        }
        presenter.onMovieStarred(movie);
    }
}
