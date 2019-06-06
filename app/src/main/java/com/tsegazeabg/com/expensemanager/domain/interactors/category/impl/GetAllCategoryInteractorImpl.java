package com.tsegazeabg.com.expensemanager.domain.interactors.category.impl;

import android.database.Cursor;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.GetAllCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

import java.util.List;

/**
 * Created by user on 9/17/2016.
 */
public class GetAllCategoryInteractorImpl extends AbstractInteractor implements GetAllCategoryInteractor {


    private Callback mCallback;
    private CategoryRepository mCategoryRepository;
    private int mType;

    public GetAllCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                         CategoryRepository mCategoryRepository,
                                        Callback mCallback,int type
    ) {
        super(threadExecutor, mainThread);
        this.mCategoryRepository = mCategoryRepository;
        this.mCallback = mCallback;

        this.mType=type;
    }


    @Override
    public void run() {

        final  Cursor categories = mCategoryRepository.getAllCat(mType);

        if (categories.equals(null) || categories.equals(" ")) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCategoryEmpty();
                }
            });
        } else {
            // Show Categories on the main thread
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCategoryRetrieved(categories);
                }
            });
        }

    }


}
