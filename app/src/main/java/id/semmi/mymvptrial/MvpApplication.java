package id.semmi.mymvptrial;

import android.app.Application;

import id.semmi.mymvptrial.Model.RetrofitService;

/**
 * Created by semmi on 14/04/2016.
 */
public class MvpApplication extends Application {
    private RetrofitService service;

    @Override
    public void onCreate() {
        super.onCreate();
        service = new RetrofitService();
    }

    public RetrofitService getService() {
        return service;
    }
}
