package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAccountByIdInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAllAccountsInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;

import java.util.List;

/**
 * Created by user on 9/16/2016.
 */
public class GetAccountByIdInteractorImpl extends AbstractInteractor implements GetAccountByIdInteractor {


    private Callback mCallback;
    private AccountRep mAccRepository;
    private long mId;

    public GetAccountByIdInteractorImpl(Executor threadExecutor, MainThread mainThread, AccountRep accRep, Callback callBack,long id) {
        super(threadExecutor, mainThread);
        if (accRep == null || callBack == null) {
            throw new IllegalArgumentException("Arguments can not be null!");
        }

        mAccRepository = accRep;
        mCallback = callBack;
        this.mId=id;
    }


    @Override
    public void run() {
        // retrieve the costs from the database
        final Account accounts = mAccRepository.readSingleRecord(mId);

        // sort them so the most recent cost items come first, and oldest comes last
        //Collections.sort(accounts, mCostComparator);

        // Show costs on the main thread
        mMainThread.post(new Runnable() {
            @Override
            public void run() {


                if (accounts == null) { // we didn't find the cost we were looking for

                    // notify this on the main thread
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.noAccountFound();
                        }
                    });
                } else { // we found it!

                    // send it on the main thread
                    mMainThread.post(new Runnable() {
                        @Override
                        public void run() {
                            mCallback.onAccountRetrieved(accounts);
                        }
                    });
                }
            }
        });

    }


}
