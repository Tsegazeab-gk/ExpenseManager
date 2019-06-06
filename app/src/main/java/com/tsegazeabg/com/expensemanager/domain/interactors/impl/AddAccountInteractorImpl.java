package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import android.accounts.Account;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;

public class AddAccountInteractorImpl extends AbstractInteractor implements AddAccountInteractor {

    private AccountRep mAccountRep;
    private AddAccountInteractor.Callback mCallback;
    private String mName;

    public AddAccountInteractorImpl(Executor threadExecutor, MainThread mainThread, AccountRep accountRep,Callback callback,
                                    String name) {
        super(threadExecutor, mainThread);
        this.mAccountRep=accountRep;
        this.mCallback=callback;
        this.mName=name;

    }

    @Override
    public void run() {
// Create new account
        com.tsegazeabg.com.expensemanager.domain.model.Account account=new com.tsegazeabg.com.expensemanager.domain.model.Account(mName);
        mAccountRep.insert(account);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.OnAccountAdded();
            }
        });

    }
}
