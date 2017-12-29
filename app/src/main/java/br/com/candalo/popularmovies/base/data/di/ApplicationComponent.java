package br.com.candalo.popularmovies.base.data.di;

import android.content.Context;

import javax.inject.Singleton;

import br.com.candalo.popularmovies.network.di.NetworkModule;
import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {ApplicationModule.class, NetworkModule.class})
@Singleton
public interface ApplicationComponent {

    Context context();

    Retrofit createRetrofit();

}
