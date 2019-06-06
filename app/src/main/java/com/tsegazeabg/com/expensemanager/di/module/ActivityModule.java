package com.tsegazeabg.com.expensemanager.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 11/25/2017.
 */
@Module
public class ActivityModule {
    private AppCompatActivity activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    //@PerActivity
    Context provideContext() {
        return activity;
    }

    @Provides
        // @PerActivity
    AppCompatActivity provideActivity() {
        return this.activity;
    }
}
