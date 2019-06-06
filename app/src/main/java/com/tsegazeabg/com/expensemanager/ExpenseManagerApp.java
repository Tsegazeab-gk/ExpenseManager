package com.tsegazeabg.com.expensemanager;

import android.app.Application;

import net.danlew.android.joda.JodaTimeAndroid;

/**
 * Created by user on 9/15/2016.
 */
public class ExpenseManagerApp extends Application {


    //private static ExpenseManagerApp mApplication;

   // private ApplicationComponent applicationComponent;


    @Override
    public void onCreate() {
        super.onCreate();

        JodaTimeAndroid.init(this);

       // mApplication = this;
        // initiate Timber
        //Timber.plant(new DebugTree());
        // Setup handler for uncaught exceptions.
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                handleUncaughtException(thread, e);
            }
        });


       // initApplicationComponent();
    }
    /*

    private void initApplicationComponent() {
         applicationComponent= DaggerApplicationComponent.builder()
               // .applicationModule(new ApplicationModule(this))
                .build();


        applicationComponent.inject(this);


    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
*/
    public void handleUncaughtException(Thread thread, Throwable e) {
        e.printStackTrace(); // not all Android versions will print the stack trace automatically
/*
        Intent intent = new Intent ();
        intent.setAction ("com.mydomain.SEND_LOG"); // see step 5.
        intent.setFlags (Intent.FLAG_ACTIVITY_NEW_TASK); // required when starting from Application
        startActivity (intent);
        System.exit(1); // kill off the crashed app
        */
    }
}
