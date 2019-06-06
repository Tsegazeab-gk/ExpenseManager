package com.tsegazeabg.com.expensemanager.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.repository.CategoryRepository;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/17/2016.
 */
public class CategoryRepositoryImpl implements CategoryRepository {

    private static CategoryRepositoryImpl INSTANCE;

    private DBHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public CategoryRepositoryImpl(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    public static CategoryRepositoryImpl getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepositoryImpl(context);
        }

        return INSTANCE;
    }

    @Override
    public boolean insert(Category cat) {


        ContentValues contentValue = new ContentValues();
        contentValue.put(ExpenseManagerContract.CategoryEntry.COLUMN_NAME_CATEGORY_NAME, cat.getName());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE, cat.getType());

        boolean bool = mDatabase.insert(ExpenseManagerContract.CategoryEntry.TABLE_NAME, null, contentValue) > 0;

        return bool;
    }

    @Override
    public Category readSingleRecord(long _id) {
        Category cat = null;
        //  open();
        String sql = "SELECT * FROM " + ExpenseManagerContract.CategoryEntry.TABLE_NAME + " WHERE _id = " + _id;

        Cursor cursor = mDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.CategoryEntry._ID)));
            String name = cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.CategoryEntry.COLUMN_NAME_CATEGORY_NAME));

            cat = new Category();
            cat.setId(id);
            cat.setName(name);
        }

        return cat;
    }


    @Override
    public Cursor getAllCat(int type) {

        mDatabase = mDbHelper.getReadableDatabase();
        Category cat = null;
        List<Category> listCat = new ArrayList<Category>();
        String sql;
        //  open();
        if ((type == 0) || (type == 1) || (type == 3)) {
            sql = "SELECT * FROM " + ExpenseManagerContract.CategoryEntry.TABLE_NAME + " WHERE type = " + type;
        } else {
            sql = "SELECT * FROM " + ExpenseManagerContract.CategoryEntry.TABLE_NAME;
        }
        Cursor cursor = mDatabase.rawQuery(sql, null);
        Log.v("Pres", "presenter rep" + type + cursor.getCount());

        {
            while (cursor.moveToNext()) {
                int id = Integer.parseInt(cursor.getString(0));
                String name = cursor.getString(1);

                cat = new Category();
                cat.setId(id);
                cat.setName(name);
                listCat.add(cat);
            }
        }
        //cursor.close();

        return cursor;
    }

    @Override
    public boolean update(Category cat) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseManagerContract.CategoryEntry.COLUMN_NAME_CATEGORY_NAME, cat.getName());

        boolean bool = (mDatabase.update(ExpenseManagerContract.CategoryEntry.TABLE_NAME, contentValues,
                ExpenseManagerContract.CategoryEntry._ID + " = " + cat.getId(), null) > 0);
        return bool;
    }

    @Override
    public boolean delete(long _id) {

        boolean bool = (mDatabase.delete(ExpenseManagerContract.CategoryEntry.TABLE_NAME, ExpenseManagerContract.CategoryEntry._ID + "=" + _id, null) > 0);
        return bool;
    }
}
