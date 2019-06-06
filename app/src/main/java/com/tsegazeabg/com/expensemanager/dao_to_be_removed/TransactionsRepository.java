package com.tsegazeabg.com.expensemanager.dao_to_be_removed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.model.Transactions;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.TransactionsEntry;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.CategoryEntry;
import com.tsegazeabg.com.expensemanager.utils.CalendarConvertor;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by user on 6/22/2016.
 */
public class TransactionsRepository {

    private static TransactionsRepository INSTANCE;
    public DBHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;
    private CalendarConvertor calendarConvertor;
    private DBHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TransactionsRepository(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    public static TransactionsRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new TransactionsRepository(context);

        }
        return INSTANCE;
    }


    public TransactionsRepository open() throws SQLException {
        if (dbHelper == null) {
            dbHelper = new DBHelper(context);
        }
        database = dbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        dbHelper.close();
    }


    public Cursor readAccounts() {
        open();
        //List<Category> listCategories = new ArrayList<Category>();
        String[] columns = new String[]{ExpenseManagerContract.AccountEntry._ID, ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME};

        Cursor cursor = database.query(ExpenseManagerContract.AccountEntry.TABLE_NAME, columns, null,
                null, null, null, null);

        if (cursor != null) {
            cursor.moveToNext();
        }

        return cursor;
    }

    public boolean insert(Transactions transactions) {

        mDatabase = mDbHelper.getWritableDatabase();

        ContentValues contentValue = new ContentValues();
        contentValue.put(TransactionsEntry.COLUMN_NAME_AMOUNT, transactions.getmAmount());
        contentValue.put(TransactionsEntry.COLUMN_NAME_DESCRIPTION, transactions.getmDescription());
        contentValue.put(TransactionsEntry.COLUMN_NAME_ACCOUNT_ID, transactions.getmAccount());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE, transactions.getmCategoryType());
        contentValue.put(TransactionsEntry.COLUMN_NAME_CATEGORY_ID, transactions.getmCategory());

        contentValue.put(TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE, transactions.getmTransactionDate().toString());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CREATED_DATE, transactions.getmCreatedDate().toString());

        boolean bool = mDatabase.insert(ExpenseManagerContract.TransactionsEntry.TABLE_NAME, null, contentValue) > 0;
        return bool;
    }


    public Cursor fetch() {
        String[] columns = new String[]{TransactionsEntry._ID, TransactionsEntry.COLUMN_NAME_AMOUNT,
                TransactionsEntry.COLUMN_NAME_DESCRIPTION, TransactionsEntry.COLUMN_NAME_ACCOUNT_ID,
                TransactionsEntry.COLUMN_NAME_CATEGORY_ID, TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE};

        Cursor cursor = database.query(TransactionsEntry.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, float amount, String desc, int accountID, int categoryID, String date) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(TransactionsEntry.COLUMN_NAME_AMOUNT, amount);
        contentValues.put(TransactionsEntry.COLUMN_NAME_DESCRIPTION, desc);
        contentValues.put(TransactionsEntry.COLUMN_NAME_ACCOUNT_ID, accountID);
        contentValues.put(TransactionsEntry.COLUMN_NAME_CATEGORY_ID, categoryID);
        contentValues.put(TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE, date);


        int i = database.update(TransactionsEntry.TABLE_NAME, contentValues,
                TransactionsEntry._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(TransactionsEntry.TABLE_NAME, TransactionsEntry._ID + "=" + _id, null);
    }

    public List<Transactions> getTransactions() {
        mDatabase = mDbHelper.getReadableDatabase();
        List<Transactions> list = new ArrayList<Transactions>();



        /*String[] columns = new String[]{TransactionsEntry._ID, TransactionsEntry.COLUMN_NAME_AMOUNT,
                TransactionsEntry.COLUMN_NAME_DESCRIPTION, TransactionsEntry.COLUMN_NAME_ACCOUNT_ID,
                TransactionsEntry.COLUMN_NAME_CATEGORY_ID,
                TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE,
                CategoryEntry.COLUMN_NAME_CATEGORY_NAME};*/
        String columns = TransactionsEntry.TABLE_NAME + "." + TransactionsEntry._ID + "," + TransactionsEntry.COLUMN_NAME_AMOUNT + "," +
                TransactionsEntry.COLUMN_NAME_DESCRIPTION + "," + TransactionsEntry.COLUMN_NAME_ACCOUNT_ID + "," +
                TransactionsEntry.COLUMN_NAME_CATEGORY_ID + "," +
                TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE + "," +
                "category." + ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + "," +
                "category." + CategoryEntry.COLUMN_NAME_CATEGORY_NAME;
        String sql = "select " + columns + " from " + TransactionsEntry.TABLE_NAME
                + "  INNER JOIN " + CategoryEntry.TABLE_NAME + "  ON " +
                TransactionsEntry.TABLE_NAME + "." + TransactionsEntry.COLUMN_NAME_CATEGORY_ID + "=" + CategoryEntry.TABLE_NAME +
                "." + CategoryEntry._ID;
        ;
        Log.v("cursor", sql);
        Cursor cursor = mDatabase.rawQuery(sql, null);
        Log.v("cursor  size=", cursor.getCount() + "");
        if (cursor != null) {
            cursor.moveToFirst();
            do {
                Transactions tra = new Transactions();
                tra.setmId(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsEntry._ID))));
                tra.setmAmount(cursor.getDouble(cursor.getColumnIndex(TransactionsEntry.COLUMN_NAME_AMOUNT)));
                tra.setmDescription(cursor.getString(cursor.getColumnIndex(TransactionsEntry.COLUMN_NAME_DESCRIPTION)));
                tra.setmAccount(cursor.getInt(cursor.getColumnIndex(TransactionsEntry.COLUMN_NAME_ACCOUNT_ID)));
                tra.setmCategoryType(cursor.getInt(cursor.getColumnIndex(ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE)));
                tra.setmCategoryName(cursor.getString(cursor.getColumnIndex(CategoryEntry.COLUMN_NAME_CATEGORY_NAME)));

                //String strDate= DateUtils.formatDate(cursor.getD)
                tra.setmTransactionDate((cursor.getString(cursor.getColumnIndex(TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE))));
                // tra.setmStringTraDate(cursor.getString(cursor.getColumnIndexOrThrow(TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE)));

                list.add(tra);
                Log.v("List ID=", tra.getmId() + "");
                Log.v("List Amount=", tra.getmAmount() + "");
                Log.v("List Desc=", tra.getmDescription() + "");
                Log.v("List Date=", tra.getmTransactionDate() + "");
                Log.v("String Date=", tra.getmStringTraDate() + "");


            } while (cursor.moveToNext());
        }
        return list;
    }
}
