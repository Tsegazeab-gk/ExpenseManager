package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;

public interface GetAccountByIdInteractor extends Interactor {
    interface Callback {
        void onAccountRetrieved(Account account);

        void noAccountFound();
    }
}
