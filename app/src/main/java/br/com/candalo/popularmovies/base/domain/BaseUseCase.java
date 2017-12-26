package br.com.candalo.popularmovies.base.domain;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseUseCase<T, Params> implements UseCase<T, Params> {

    private CompositeDisposable disposables = new CompositeDisposable();

    protected abstract Observable<T> getObservable(Params params);

    @Override
    public <S extends Observer<T> & Disposable> void execute(S observer, Params params) {
        Observable<T> observable = getObservable(params)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        disposables.add(observable.subscribeWith(observer));
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}
