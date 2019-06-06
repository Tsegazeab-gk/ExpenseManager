package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;

import java.util.List;

/**
 * Created by user on 9/16/2016.
 */
public interface GetAllAccountsInteractor extends Interactor {

    interface Callback {
        // TODO: Add interactor callback methods here

        void onAccountsRetrieved(List<Account> accountList);
    }

}
