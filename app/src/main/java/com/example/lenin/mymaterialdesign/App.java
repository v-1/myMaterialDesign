package com.example.lenin.mymaterialdesign;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

@SuppressWarnings("WeakerAccess")
public class App extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        setLeakCanary();
    }
    private void setLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
    }
}
