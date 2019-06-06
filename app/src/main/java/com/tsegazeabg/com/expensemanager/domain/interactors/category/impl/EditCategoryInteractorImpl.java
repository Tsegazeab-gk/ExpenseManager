package com.tsegazeabg.com.expensemanager.domain.interactors.category.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.EditAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.EditCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

public class EditCategoryInteractorImpl extends AbstractInteractor implements EditCategoryInteractor {

    private CategoryRepository mCategoryRepository;
    private Callback mCallback;
    private long mId;
    private String mName;
    int mType;


    public EditCategoryInteractorImpl(Executor threadExecutor, MainThread mainThread, CategoryRepository categoryRepository,
                                      Callback callback,
                                      long id, String name, int type) {
        super(threadExecutor, mainThread);
        this.mCategoryRepository=categoryRepository;
        this.mCallback=callback;
        this.mId=id;
        this.mName=name;
        this.mType=type;

    }

    @Override
    public void run() {
// retrieve the costs from the database
        final Category category = mCategoryRepository.readSingleRecord(mId);
category.setName(mName);
category.setType(mType);
Boolean isCategoryinserted=mCategoryRepository.update(category);
if(isCategoryinserted) {
    mMainThread.post(new Runnable() {
        @Override
        public void run() {
            mCallback.OnCategoryUpdated();
        }
    });
}else {
    mMainThread.post(new Runnable() {
        @Override
        public void run() {
            mCallback.OnCategoryNotUpdated();
        }
    });
}

    }


}
