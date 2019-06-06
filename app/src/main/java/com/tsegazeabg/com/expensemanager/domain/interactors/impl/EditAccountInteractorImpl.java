package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.EditAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;

public class EditAccountInteractorImpl extends AbstractInteractor implements EditAccountInteractor {

    private AccountRep mAccountRep;
    private Callback mCallback;
    private long mId;
    private String mName;

    public EditAccountInteractorImpl(Executor threadExecutor, MainThread mainThread, AccountRep accountRep, Callback callback,
                                     String name,long id) {
        super(threadExecutor, mainThread);
        this.mAccountRep=accountRep;
        this.mCallback=callback;
        this.mId=id;
        this.mName=name;

    }

    @Override
    public void run() {
// retrieve the costs from the database
        final Account accounts = mAccountRep.readSingleRecord(mId);
accounts.setName(mName);
mAccountRep.update(accounts);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.OnAccountUpdted();
            }
        });

    }


}
