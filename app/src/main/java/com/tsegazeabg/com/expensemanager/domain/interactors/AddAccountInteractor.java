package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;

public interface AddAccountInteractor extends Interactor {

    interface Callback {
        void OnAccountAdded();

        void OnAccountNotAdded();
    }
}
