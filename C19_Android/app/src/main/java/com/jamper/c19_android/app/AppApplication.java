package com.jamper.c19_android.app;

import android.app.Application;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import static com.jamper.c19_android.utils.ActivityUtils.getCurrentYear;

public class AppApplication extends Application {
    private List<String> yearList = new ArrayList<>();
    private static AppApplication application;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        addYear();
    }


    public static AppApplication getApplication() {
        return application;
    }

    public List<String> getYearList() {
        return yearList;
    }

    private void addYear(){
        yearList.clear();
        AsyncTask.execute(() -> {
            for (int i = getCurrentYear(); i >= 1950; i--) {
                yearList.add(String.valueOf(i));
            }
        });
    }
}
