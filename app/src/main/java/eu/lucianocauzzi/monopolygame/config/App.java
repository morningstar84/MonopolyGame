package eu.lucianocauzzi.monopolygame.config;

import android.app.Application;

import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by lucio on 02/04/16.
 */
public class App extends Application {


    @Override
    public void onCreate() {
        super.onCreate();

        Fabric.with(this, new Crashlytics());
    }

}
