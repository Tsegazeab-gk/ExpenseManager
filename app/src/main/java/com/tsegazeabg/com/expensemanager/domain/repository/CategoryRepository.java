package com.tsegazeabg.com.expensemanager.domain.repository;

import android.database.Cursor;

import com.tsegazeabg.com.expensemanager.domain.model.Category;

import java.util.List;

/**
 * Created by user on 9/17/2016.
 */
public interface CategoryRepository {

    boolean insert(Category cat);

    Category readSingleRecord(long _id);

    Cursor getAllCat(int type);  //type  0 for income 1 for for expense

    boolean update(Category cat);

    boolean delete(long _id);


}
