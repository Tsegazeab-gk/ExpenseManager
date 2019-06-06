package com.tsegazeabg.com.expensemanager.presentation.presenters.impl;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddTransactionInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAllAccountsInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.AddCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.GetAllCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.AddTransactionInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.GetAllAccountsInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.GetAllCategoryInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;
import com.tsegazeabg.com.expensemanager.domain.repository.TransactionRepository;
import com.tsegazeabg.com.expensemanager.presentation.presenters.FragmentNavigation;
import com.tsegazeabg.com.expensemanager.presentation.presenters.TransactionPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.AbstractPresenter;
import com.tsegazeabg.com.expensemanager.storage.AccountRepImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 9/16/2016.
 */
public class TransactionPresenterImpl extends AbstractPresenter implements TransactionPresenter,FragmentNavigation,
        GetAllAccountsInteractor.Callback,
            AddTransactionInteractor.Callback {

    private TransactionPresenter.View mView;

    private Context mContext;
    private TransactionRepository mTransactionRepository;
    private int mCatType;



    public TransactionPresenterImpl(Executor executor, MainThread mainThread, TransactionPresenter.View mView,
                                    TransactionRepository mTransactionRepository,
                                    Context context) {
        super(executor, mainThread);
        this.mView = mView;
        this.mTransactionRepository = mTransactionRepository;
        mContext=context;
    }

    @Override
    public void resume() {
        getAllAccounts();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void onAccountsRetrieved(List<Account> accountList) {
        Log.v("TransactionPresenter", "retireved" + accountList.size());
        //  List<DailyTotalCost> dailyTotalCosts = DailyTotalCostConverter.convertCostsToDailyCosts(costList);
        mView.showAccounts(accountList);


    }

    @Override
    public void getRandomFragment() {

    }

    @Override
    public void getAllAccounts() {
        // get all costs
        GetAllAccountsInteractor getCostsInteractor = new GetAllAccountsInteractorImpl(
                mExecutor,
                mMainThread,
                 AccountRepImpl.getInstance(mContext),
                this
        );
        getCostsInteractor.execute();

    }



    @Override
    public void addTransaction(double mAmount, String mDescription, int mAccount, int mCategoryType, int mCategory, String mTransactionDate, String mCreationDate) {
        AddTransactionInteractor addTransactionInteractor = new AddTransactionInteractorImpl(
                mExecutor, mMainThread, mTransactionRepository, this, mAmount, mDescription, mAccount
                , mCategoryType, mCategory, mTransactionDate, mCreationDate
        );
        addTransactionInteractor.execute();
    }





    @Override
    public void OnTransactionAdded() {
        mView.onTransactionAdded();
    }

    @Override
    public void OnTransactionNotAdded() {
        mView.onTransactionNotAdded();
    }


    @Override
    public void addFragment(BaseFragment fragment) {

mView.setFragment(fragment);
    }
}
