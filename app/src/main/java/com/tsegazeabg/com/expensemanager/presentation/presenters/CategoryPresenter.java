package com.tsegazeabg.com.expensemanager.presentation.presenters;

import android.database.Cursor;

import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.BasePresenter;
import com.tsegazeabg.com.expensemanager.ui.base.BaseView;

import java.util.List;

public interface CategoryPresenter extends BasePresenter {
    interface View extends BaseView {

        void onCategoryAdded();

        void onCategoryDeleted();

        void onCategoryNotAdded();

        void showCategory(Cursor categories);

        void onCategoryRetrieved(Category category);

        void onCategoryUpdated();
        void onCategoryNotUpdated();
        void onCategoryEmpty();
        void onCategoryNotDeleted();

    }

    void getAllCategoriesByType(int id);

    void addNewCategory(String name,int type);

    void getCategoryByID(long id);


    Boolean deleteCategory(long id);

    void editCategory(long id, String name,int type);


}
