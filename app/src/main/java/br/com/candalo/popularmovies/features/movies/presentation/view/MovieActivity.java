package br.com.candalo.popularmovies.features.movies.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.App;
import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.data.di.DaggerMovieComponent;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.presentation.presenter.MoviePresenter;
import br.com.candalo.popularmovies.features.movies.presentation.view.adapter.MovieAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import hugo.weaving.DebugLog;

public class MovieActivity extends AppCompatActivity implements MovieView {

    @Inject
    MoviePresenter presenter;
    @BindView(R.id.pb_loading_movies)
    ProgressBar loadingMoviesProgressBar;
    @BindView(R.id.rv_movies)
    RecyclerView moviesRecyclerView;
    @BindView(R.id.tv_internet_error)
    TextView internetErrorTextView;
    private List<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        injectDependencies();
        setupToolbar();
        setupRecyclerView();
        setupPresenter(savedInstanceState);
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
            actionBar.setTitle(R.string.movie_list_toolbar_title);
        }
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);
    }

    private void setupPresenter(Bundle savedInstanceState) {
        presenter.attachTo(this);
        if (savedInstanceState == null) {
            presenter.onGetMoviesByPopularityOptionSelected();
        }
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_list_menu, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.popularity_ordination:
                presenter.onGetMoviesByPopularityOptionSelected();
                break;
            case R.id.rating_ordination:
                presenter.onGetMoviesByRatingOptionSelected();
                break;
            case R.id.starred_ordination:
                presenter.onGetStarredMoviesOptionSelected();
            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(Movie.class.getName(), Parcels.wrap(movies));
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        movies = Parcels.unwrap(savedInstanceState.getParcelable(Movie.class.getName()));
        presenter.showLoadedMovies(movies);
    }

    @Override
    @DebugLog
    public void onMoviesLoaded(List<Movie> movies) {
        this.movies = movies;
        MovieAdapter movieAdapter = new MovieAdapter(movies, this);
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void onMovieSelected(Movie movie) {
        Intent intent = new Intent(this, MovieDetailsActivity.class);
        intent.putExtra(Movie.class.getName(), Parcels.wrap(movie));
        startActivity(intent);
    }

    @Override
    public void showLoading() {
        internetErrorTextView.setVisibility(View.INVISIBLE);
        loadingMoviesProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingMoviesProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showNetworkErrorMessage() {
        internetErrorTextView.setVisibility(View.VISIBLE);
    }
}
