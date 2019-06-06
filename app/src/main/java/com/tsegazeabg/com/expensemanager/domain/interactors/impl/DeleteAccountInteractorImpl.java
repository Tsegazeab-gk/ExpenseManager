package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.DeleteAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;

public class DeleteAccountInteractorImpl extends AbstractInteractor implements DeleteAccountInteractor {

    private AccountRep mAccountRep;
    private DeleteAccountInteractor.Callback mCallback;
    private long mId;

    public DeleteAccountInteractorImpl(Executor threadExecutor, MainThread mainThread, AccountRep accountRep, Callback callback,
                                       long id) {
        super(threadExecutor, mainThread);
        this.mAccountRep=accountRep;
        this.mCallback=callback;
        this.mId=id;

    }

    @Override
    public void run() {
// Create new account
        //com.tsegazeabg.com.expensemanager.domain.model.Account account=new com.tsegazeabg.com.expensemanager.domain.model.Account(mId);
        mAccountRep.delete(mId);

        mMainThread.post(new Runnable() {
            @Override
            public void run() {
                mCallback.OnAccountDeleted();
            }
        });

    }
}
