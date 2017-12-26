package br.com.candalo.popularmovies.base.di;

import javax.inject.Singleton;

import br.com.candalo.popularmovies.network.di.NetworkModule;
import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = NetworkModule.class)
@Singleton
public interface ApplicationComponent {

    Retrofit createRetrofit();

}
