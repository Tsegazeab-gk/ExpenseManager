package com.tsegazeabg.com.expensemanager.domain.interactors.category;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;

public interface DeleteCategoryInteractor extends Interactor {

    interface Callback {
        void CategoryDeleted();

        Void onCategoryNotDeleted();
    }
}
