package com.tsegazeabg.com.expensemanager.presentation.presenters;


import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.BasePresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.BaseFragment;
import com.tsegazeabg.com.expensemanager.ui.base.BaseView;

import java.util.Date;
import java.util.List;

/**
 * Created by user on 9/16/2016.
 */
public interface TransactionPresenter extends BasePresenter {

    void getRandomFragment();

    void getAllAccounts();

    // TODO: Add your presenter methods

    //void getCategoryByType(int type);

    void addTransaction(double mAmount, String mDescription, int mAccount,
                        int mCategoryType, int mSubCategory, String mTransactionDate, String mCreationDate);

    interface View extends BaseView {
        // TODO: Add your view methods

        void showAccounts(List<Account> accounts);

        //void showCategory(List<Category> categories);

        void onTransactionAdded();

        void onTransactionNotAdded();

        void setFragment(BaseFragment fragment);


    }

}
