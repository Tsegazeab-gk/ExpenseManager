package com.tsegazeabg.com.expensemanager.domain.interactors.category.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.DeleteCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

public class DeleteCategoryInteractorImpl extends AbstractInteractor implements DeleteCategoryInteractor {

    private CategoryRepository mCategoryRepository;
    private Callback mCallback;
    private long mId;

    public DeleteCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread, CategoryRepository categoryRepository, Callback callback,
                                        long id) {
        super(threadExecutor, mainThread);
        this.mCategoryRepository=categoryRepository;
        this.mCallback=callback;
        this.mId=id;

    }

    @Override
    public void run() {
// Delete Category
 Boolean isDeleted=mCategoryRepository.delete(mId);

        if(isDeleted) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.CategoryDeleted();
                }
            });
        }else
        {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.onCategoryNotDeleted();
                }
            });
        }

    }
}
