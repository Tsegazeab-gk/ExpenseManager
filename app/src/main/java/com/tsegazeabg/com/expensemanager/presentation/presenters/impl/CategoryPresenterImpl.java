package com.tsegazeabg.com.expensemanager.presentation.presenters.impl;

import android.database.Cursor;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;

import com.tsegazeabg.com.expensemanager.domain.interactors.category.AddCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.DeleteCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.EditCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.GetAllCategoryInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.GetCategoryByIdInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.AddCategoryInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.DeleteCategoryInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.EditCategoryInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.GetAllCategoryInteractorImpl;

import com.tsegazeabg.com.expensemanager.domain.interactors.category.impl.GetCategoryByIdInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;

import com.tsegazeabg.com.expensemanager.presentation.presenters.CategoryPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.AbstractPresenter;

import java.util.List;

public class CategoryPresenterImpl extends AbstractPresenter implements CategoryPresenter,
        GetAllCategoryInteractor.Callback ,GetCategoryByIdInteractor.Callback,
        DeleteCategoryInteractor.Callback,
        EditCategoryInteractor.Callback,
AddCategoryInteractor.Callback{

    private View mView;
    private CategoryRepository mCategoryRepository;
    private int mtype;

    public CategoryPresenterImpl(Executor executor, MainThread mainThread, View view,
                                 CategoryRepository categoryRepository) {
        super(executor, mainThread);
        mView=view;
        mCategoryRepository=categoryRepository;

    }

    @Override
    public void getAllCategoriesByType(int type) {

        //getall categories
        GetAllCategoryInteractor getAllCategoryInteractor=new GetAllCategoryInteractorImpl(
                mExecutor,mMainThread,mCategoryRepository,this,type);
        getAllCategoryInteractor.execute();
    }

    @Override
    public void addNewCategory(String name, int type) {
        AddCategoryInteractor addCategoryInteractor=new AddCategoryInteractorImpl(mExecutor,mMainThread,mCategoryRepository,
                this,
                name,type);
        addCategoryInteractor.execute();

    }


    @Override
    public void getCategoryByID(long id) {
        GetCategoryByIdInteractor getCategoryByIdInteractor=new GetCategoryByIdInteractorImpl(mExecutor,mMainThread,mCategoryRepository,
                this, id);
        getCategoryByIdInteractor.execute();
    }

    @Override
    public Boolean deleteCategory(long id) {
        DeleteCategoryInteractor deleteCategoryInteractor=new DeleteCategoryInteractorImpl(mExecutor,mMainThread,mCategoryRepository,
                this, id);
        deleteCategoryInteractor.execute();
        return true;
    }

    @Override
    public void editCategory(long id, String name, int type) {
        EditCategoryInteractor editCategoryInteractor=new EditCategoryInteractorImpl(mExecutor,mMainThread,mCategoryRepository,
                this,id, name,type);
        editCategoryInteractor.execute();
    }


    @Override
    public void resume() {
       getAllCategoriesByType(mtype);

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }



    @Override
    public void onCategoryRetrieved(Cursor listCategory) {
        mView.showCategory(listCategory);

    }

    @Override
    public void onCategoryEmpty() {
        mView.onCategoryEmpty();

    }

    @Override
    public void OnCategoryAdded() {
        mView.onCategoryAdded();
    }

    @Override
    public void OnCategoryNotAdded() {
mView.onCategoryNotAdded();
    }

    @Override
    public void CategoryDeleted() {
        mView.onCategoryDeleted();

    }

    @Override
    public Void onCategoryNotDeleted() {
        mView.onCategoryNotDeleted();
        return null;
    }

    @Override
    public void OnCategoryUpdated() {
        mView.onCategoryUpdated();

    }

    @Override
    public void OnCategoryNotUpdated() {
       mView.onCategoryNotUpdated();

    }

    @Override
    public void onCategoryRetrieved(Category category) {
        mView.onCategoryRetrieved(category);

    }

    @Override
    public void onCategoryNotFound() {
        mView.onCategoryEmpty();

    }
}
