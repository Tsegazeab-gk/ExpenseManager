package com.tsegazeabg.com.expensemanager.di.module;

import android.app.Application;
import android.content.Context;

import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;
import com.tsegazeabg.com.expensemanager.domain.repository.TransactionRepository;
import com.tsegazeabg.com.expensemanager.storage.AccountRepImpl;
import com.tsegazeabg.com.expensemanager.storage.CategoryRepositoryImpl;
import com.tsegazeabg.com.expensemanager.storage.TransactionRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by user on 11/21/2017.
 */
@Module
public class ApplicationModule {
    private Application mApplication;

    public ApplicationModule(Application mApplication) {
        this.mApplication = mApplication;
    }

    @Provides
    //@ApplicationContext
    @Singleton
    Context provideContext() {
        return mApplication;
    }


    /*
        @Provides @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
            return userCache;
        }
    */
    @Provides
    @Singleton
    AccountRep provideAccountRep(AccountRepImpl accountRepository) {
        return accountRepository;
    }

    @Provides
    @Singleton
    CategoryRepository provideCategoryRep(CategoryRepositoryImpl categoryRepository) {
        return categoryRepository;
    }

    @Provides
    @Singleton
    TransactionRepository provideTransactionRep(TransactionRepositoryImpl transactionRepository) {
        return transactionRepository;
    }
}
