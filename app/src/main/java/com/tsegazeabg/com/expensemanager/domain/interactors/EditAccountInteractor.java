package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;

public interface EditAccountInteractor extends Interactor {

    interface Callback {
        void OnAccountUpdted();

        void OnAccountNotUpdated();
    }

}
