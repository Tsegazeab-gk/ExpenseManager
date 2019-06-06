package com.tsegazeabg.com.expensemanager.domain.interactors;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;

/**
 * Created by user on 9/18/2016.
 */
public interface AddTransactionInteractor extends Interactor {

    interface Callback {
        void OnTransactionAdded();

        void OnTransactionNotAdded();
    }

}
