package id.semmi.mymvptrial.Model;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by semmi on 14/04/2016.
 */
public class RetrofitService {
    private RetrofitInterface myInterface;

    private final static String ENDPOINT = "https://gist.githubusercontent.com/semmiverian/02bd02e00fb6d4e771d05ff5bb3b9a8f/raw/41a5b54ebb48ca6b2c140a1408287c141641ea61/";

    public RetrofitService(){
        Retrofit retrofit2 = new Retrofit.Builder()
                        .baseUrl(ENDPOINT)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                        .build();

         this.myInterface = retrofit2.create(RetrofitInterface.class);
    }

    public RetrofitInterface getMyInterface() {
        return myInterface;
    }

    public Observable<List<Library>> getObservable(Observable<List<Library>> observable){

        return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    public interface RetrofitInterface{
        @GET("library.json")
        Observable<List<Library>> fetchLibrary();
    }
}
