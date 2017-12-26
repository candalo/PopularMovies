package br.com.candalo.popularmovies;


import android.app.Application;

import br.com.candalo.popularmovies.base.data.di.ApplicationComponent;
import br.com.candalo.popularmovies.base.data.di.DaggerApplicationComponent;

public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.create();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
