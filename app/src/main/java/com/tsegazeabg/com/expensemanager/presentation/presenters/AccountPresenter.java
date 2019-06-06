package com.tsegazeabg.com.expensemanager.presentation.presenters;

import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.BasePresenter;
import com.tsegazeabg.com.expensemanager.ui.base.BaseView;

import java.util.List;

public interface AccountPresenter extends BasePresenter {
    interface View extends BaseView {

        void onAccountAdded();

        void onAccountDeleted();

        void onAccountNotAdded();

        void showAccounts(List<Account> accounts);

        void onAccountRetrieved(Account Account);

        void onAccountUpdated();

    }

    void getAllAccounts();

    void addNewAccount(String name);

    void getAccountByID(long id);


    Boolean deleteAccount(long id);

    void editAccount(long id, String name);


}
