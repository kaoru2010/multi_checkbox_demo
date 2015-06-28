package com.example.kaoru.multicheckboxdemo;

import android.app.Application;

/**
 * Created by kaoru on 2015/06/28.
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ModelStore.registerModel(ModelKey.MY_MODEL, new MyModel());
    }
}
