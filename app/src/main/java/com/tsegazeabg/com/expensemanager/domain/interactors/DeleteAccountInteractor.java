package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;

public interface DeleteAccountInteractor extends Interactor {

    interface Callback {
        void OnAccountDeleted();
    }
}
