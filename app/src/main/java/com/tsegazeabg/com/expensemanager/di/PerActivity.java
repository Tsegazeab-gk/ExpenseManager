package com.tsegazeabg.com.expensemanager.di;

import java.lang.annotation.Retention;

import javax.inject.Scope;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by user on 11/25/2017.
 */
@Scope
@Retention(RUNTIME)
public @interface PerActivity {
}
