package com.tsegazeabg.com.expensemanager.domain.interactors.category.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAccountByIdInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.GetCategoryByIdInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

/**
 * Created by user on 9/16/2016.
 */
public class GetCategoryByIdInteractorImpl extends AbstractInteractor implements GetCategoryByIdInteractor {


    private Callback mCallback;
    private CategoryRepository mCategoryRepository;
    private long mId;

    public GetCategoryByIdInteractorImpl(Executor threadExecutor, MainThread mainThread, CategoryRepository categoryRepository,
                                         Callback callBack, long id) {
        super(threadExecutor, mainThread);
        if (categoryRepository == null || callBack == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mCategoryRepository = categoryRepository;
        mCallback = callBack;
        this.mId=id;
    }


    @Override
    public void run() {
        // retrieve the costs from the database
        final Category category = mCategoryRepository.readSingleRecord(mId);

        // sort them so the most recent cost items come first, and oldest comes last
        //Collections.sort(accounts, mCostComparator);

        // Show costs on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {


                if (category == null) { // we didn't find the cost we were looking for

                    // notify this on the main thread
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onCategoryNotFound();
                        }
                    });
                } else { // we found it!

                    // send it on the main thread
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onCategoryRetrieved(category);
                        }
                    });
                }
            }
        });

    }


}
