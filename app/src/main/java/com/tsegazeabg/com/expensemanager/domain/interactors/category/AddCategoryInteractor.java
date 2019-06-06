package com.tsegazeabg.com.expensemanager.domain.interactors.category;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;

import java.util.List;

public interface AddCategoryInteractor extends Interactor {
    interface Callback {
        void OnCategoryAdded();

        void OnCategoryNotAdded();
    }

}
