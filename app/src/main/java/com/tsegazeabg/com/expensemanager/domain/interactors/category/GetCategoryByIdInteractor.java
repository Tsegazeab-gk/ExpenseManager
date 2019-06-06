package com.tsegazeabg.com.expensemanager.domain.interactors.category;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;

public interface GetCategoryByIdInteractor extends Interactor {
    interface Callback {
        void onCategoryRetrieved(Category category);

        void onCategoryNotFound();
    }
}
