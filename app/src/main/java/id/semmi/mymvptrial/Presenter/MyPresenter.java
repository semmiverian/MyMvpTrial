package id.semmi.mymvptrial.Presenter;

import java.util.List;

import id.semmi.mymvptrial.Model.Library;
import id.semmi.mymvptrial.Model.RetrofitService;
import id.semmi.mymvptrial.ui.MainActivity;
import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by semmi on 14/04/2016.
 */
public class MyPresenter implements PresenterInteractor {
    private MainActivity view;
    private RetrofitService service;
    private Subscription subscription;


    public MyPresenter(MainActivity view, RetrofitService service){
        this.view = view;
        this.service = service;
    }
    @Override
    public void loadData() {
        view.setOnProgress();
        Observable<List<Library>> libraryObservable = service.getObservable(service.getMyInterface().fetchLibrary());
        subscription = libraryObservable.subscribe(new Observer<List<Library>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(List<Library> libraries) {
                view.getDataResult(libraries);
            }
        });
    }

    @Override
    public void unSubscribe() {
        if(subscription!=null && !subscription.isUnsubscribed()){
            subscription.unsubscribe();
        }
    }
}
