package com.tsegazeabg.com.expensemanager.domain.interactors.category.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.AddCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

public class AddCategoryInteractorImpl extends AbstractInteractor implements AddCategoryInteractor {

    private CategoryRepository mCategoryRepository;
    private Callback mCallback;
    private String mCatName;
    private int mType;

    public AddCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread, CategoryRepository categoryRepository,
                                     Callback callback,
                                     String name, int type) {
        super(threadExecutor, mainThread);
        this.mCategoryRepository=categoryRepository;
        this.mCallback=callback;
        this.mCatName=name;
        this.mType=type;

    }

    @Override
    public void run() {
          // Create new category
        Category category=new Category(mCatName,mType);
        Boolean isInserted=mCategoryRepository.insert(category);

        if (isInserted) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.OnCategoryAdded();
                }
            });
        }else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.OnCategoryNotAdded();
                }
            });
        }

    }
}
