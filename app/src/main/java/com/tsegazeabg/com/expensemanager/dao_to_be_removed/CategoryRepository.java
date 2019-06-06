package com.tsegazeabg.com.expensemanager.dao_to_be_removed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.CategoryEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/22/2016.
 */
public class CategoryRepository {

    private static CategoryRepository INSTANCE;
    public DBHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public CategoryRepository(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    public static CategoryRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryRepository(context);

        }
        return INSTANCE;
    }

    public CategoryRepository open() throws SQLException {
        if (mDbHelper == null) {
            mDbHelper = new DBHelper(mContext);
        }
        mDatabase = mDbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        mDbHelper.close();
    }

    public boolean insert(Category cat) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(CategoryEntry.COLUMN_NAME_CATEGORY_NAME, cat.getName());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE, cat.getType());
        open();
        boolean bool = mDatabase.insert(CategoryEntry.TABLE_NAME, null, contentValue) > 0;

        return bool;
    }

    public List<Category> getAllCatByType(int type) {

        mDatabase = mDbHelper.getReadableDatabase();
        Category cat;
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
        cursor.close();

        return listCat;
    }

    public Category readSingleRecord(int _id) {
        open();
        Category cat = null;
        //  open();
        String sql = "SELECT * FROM " + CategoryEntry.TABLE_NAME + " WHERE _id = " + _id;

        Cursor cursor = mDatabase.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CategoryEntry._ID)));
            String name = cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_NAME_CATEGORY_NAME));

            cat = new Category();
            cat.setId(id);
            cat.setName(name);
        }


        cursor.close();
        close();
        return cat;
    }

    public Cursor readCategories(int id) {
        open();
        //List<Category> listCategories = new ArrayList<Category>();
        String[] columns = new String[]{CategoryEntry._ID, CategoryEntry.COLUMN_NAME_CATEGORY_NAME};

        Cursor cursor = mDatabase.query(CategoryEntry.TABLE_NAME, columns, ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + "=" + id,
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToNext();
        }

        return cursor;
    }

    public boolean update(Category cat) {
        open();
        ContentValues contentValues = new ContentValues();
        contentValues.put(CategoryEntry.COLUMN_NAME_CATEGORY_NAME, cat.getName());

        boolean bool = (mDatabase.update(CategoryEntry.TABLE_NAME, contentValues,
                CategoryEntry._ID + " = " + cat.getId(), null) > 0);
        return bool;
    }

    public boolean delete(long _id) {

        boolean bool = (mDatabase.delete(CategoryEntry.TABLE_NAME, CategoryEntry._ID + "=" + _id, null) > 0);
        return bool;
    }

    public int getCategoryId(String selectedItem) {

        String sql = "SELECT * FROM " + CategoryEntry.TABLE_NAME + " WHERE name = '" + selectedItem + "'";

        Cursor cursor = mDatabase.rawQuery(sql, null);
        int id = 0;
        if (cursor.moveToFirst()) {

            id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(CategoryEntry._ID)));

        }
        return id;
    }
}
