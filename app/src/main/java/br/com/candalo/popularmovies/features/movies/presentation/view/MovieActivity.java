package br.com.candalo.popularmovies.features.movies.presentation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import br.com.candalo.popularmovies.App;
import br.com.candalo.popularmovies.R;
import br.com.candalo.popularmovies.features.movies.data.di.DaggerMovieComponent;
import br.com.candalo.popularmovies.features.movies.domain.models.Movie;
import br.com.candalo.popularmovies.features.movies.presentation.presenter.MoviePresenter;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        injectDependencies();
        setupRecyclerView();
        presenter.attachTo(this);
    }

    private void injectDependencies() {
        ButterKnife.bind(this);

        DaggerMovieComponent
                .builder()
                .applicationComponent(((App)getApplication()).getApplicationComponent())
                .build()
                .inject(this);
    }

    private void setupRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(layoutManager);
    }

    @Override
    @DebugLog
    public void onMoviesLoaded(List<Movie> movies) {
        MovieAdapter movieAdapter = new MovieAdapter(movies);
        moviesRecyclerView.setAdapter(movieAdapter);
    }

    @Override
    public void showLoading() {
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
}
