package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddTransactionInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.model.Transactions;
import com.tsegazeabg.com.expensemanager.domain.repository.TransactionRepository;
import com.tsegazeabg.com.expensemanager.utils.DateUtils;

import java.util.Date;

/**
 * Created by user on 9/18/2016.
 */
public class AddTransactionInteractorImpl extends AbstractInteractor implements AddTransactionInteractor {

    protected double mAmount;
    protected String mDescription;
    protected int mAccount;
    protected int mCategoryType;
    protected int mCategory;
    protected String mTransactionDate;
    protected String mCreationDate;
    private TransactionRepository mTransactionRepository;
    private AddTransactionInteractor.Callback mCallback;


    public AddTransactionInteractorImpl(Executor threadExecutor, MainThread mainThread,
                                        TransactionRepository transactionRepository, Callback mCallback,
                                        double mAmount, String mDescription, int mAccount,
                                        int mCategoryType, int mSubCategory, String mTransactionDate,
                                        String mCreationDate) {
        super(threadExecutor, mainThread);
        this.mTransactionRepository = transactionRepository;
        this.mCallback = mCallback;
        this.mAmount = mAmount;
        this.mDescription = mDescription;
        this.mAccount = mAccount;
        this.mCategoryType = mCategoryType;
        this.mCategory = mSubCategory;
        this.mTransactionDate = mTransactionDate;
        this.mCreationDate = mCreationDate;
    }

    @Override
    public void run() {
        Transactions transactions = new Transactions(mAmount, mDescription, mAccount, mCategoryType, mCategory, mTransactionDate,mCreationDate);
transactions.setmCategoryType(0);
        Boolean bool = mTransactionRepository.insert(transactions);

        if (bool) {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.OnTransactionAdded();
                }
            });
        } else {
            mMainThread.post(new Runnable() {
                @Override
                public void run() {
                    mCallback.OnTransactionNotAdded();

                }
            });
        }


    }
}
