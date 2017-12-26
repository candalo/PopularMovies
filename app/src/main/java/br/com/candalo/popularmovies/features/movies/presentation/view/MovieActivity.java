package br.com.candalo.popularmovies.features.movies.presentation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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
import butterknife.OnClick;
import hugo.weaving.DebugLog;

public class MovieActivity extends AppCompatActivity implements MovieView {

    @Inject
    MoviePresenter presenter;
    @BindView(R.id.btn_call_api)
    Button callApiButton;
    @BindView(R.id.pb_loading_movies)
    ProgressBar loadingMoviesProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies);
        injectDependencies();
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

    @OnClick(R.id.btn_call_api)
    void onClickCallApiButton() {
        presenter.onCallApiButtonClicked();
    }

    @Override
    @DebugLog
    public void onMoviesLoaded(List<Movie> movies) {

    }

    @Override
    public void showLoading() {
        callApiButton.setVisibility(View.INVISIBLE);
        loadingMoviesProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingMoviesProgressBar.setVisibility(View.INVISIBLE);
        callApiButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }
}
