package com.tsegazeabg.com.expensemanager.domain.interactors.category;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;

public interface EditCategoryInteractor extends Interactor {

    interface Callback {
        void OnCategoryUpdated();

        void OnCategoryNotUpdated();
    }

}
