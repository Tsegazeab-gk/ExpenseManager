package com.tsegazeabg.com.expensemanager.domain.interactors.category;

import android.database.Cursor;

import com.tsegazeabg.com.expensemanager.domain.interactors.base.Interactor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;

import java.util.List;

public interface GetAllCategoryInteractor extends Interactor {

    interface Callback {
        void onCategoryRetrieved(Cursor listCategory);

        void onCategoryEmpty();


    }
}
