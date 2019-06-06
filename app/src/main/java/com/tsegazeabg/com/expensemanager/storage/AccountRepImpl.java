package com.tsegazeabg.com.expensemanager.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 9/15/2016.
 */
public class AccountRepImpl implements AccountRep {

    private static AccountRepImpl INSTANCE = null;


    //private final AccountRep mAccRepository;

    private Context mContext;
    private SQLiteDatabase database;

    private DBHelper mDbHelper;


    public AccountRepImpl(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    public static AccountRepImpl getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AccountRepImpl(context);
        }
        return INSTANCE;
    }


    public static void destroyInstance() {
        INSTANCE = null;
    }

    public void close() {
        mDbHelper.close();
    }

    @Override
    public Account readSingleRecord(long _id) {
        database = mDbHelper.getWritableDatabase();
        Account accountObject = null;
        //  open();
        String sql = "SELECT * FROM " + ExpenseManagerContract.AccountEntry.TABLE_NAME + " WHERE _id = " + _id;
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.AccountEntry._ID)));
            String firstname = cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME));

            accountObject = new Account();
            accountObject.setId(id);
            accountObject.setName(firstname);
        }

        cursor.close();


        return accountObject;
    }

    @Override
    public boolean insert(Account acc) {
        database = mDbHelper.getWritableDatabase();
        ContentValues contentValue = new ContentValues();
        contentValue.put(ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME, acc.getName());
        boolean createSuccessful = database.insert(ExpenseManagerContract.AccountEntry.TABLE_NAME, null, contentValue) > 0;

        return createSuccessful;
    }

    @Override
    public List<Account> getAllAccounts() {

        database = mDbHelper.getReadableDatabase();
        List<Account> listAccount = new ArrayList<Account>();

        String[] columns = new String[]{ExpenseManagerContract.AccountEntry._ID, ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME};

        Cursor cursor = database.query(ExpenseManagerContract.AccountEntry.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.AccountEntry._ID)));
                String name = cursor.getString(cursor.getColumnIndex(ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME));
                Account acc = new Account();
                acc.setId(id);
                acc.setName(name);
                listAccount.add(acc);

            } while (cursor.moveToNext());
        }
        return listAccount;
    }

    @Override
    public boolean update(Account accountsObject) {
        String where = ExpenseManagerContract.AccountEntry._ID + " = '" + accountsObject.getId() + "'";
        Log.v("Id", String.valueOf(accountsObject.getId()));
        String[] whereArgs = {Integer.toString(accountsObject.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(ExpenseManagerContract.AccountEntry.COLUMN_NAME_ACCOUNT_NAME, accountsObject.getName());

        Boolean bool = database.update(ExpenseManagerContract.AccountEntry.TABLE_NAME, contentValues,
                where, null) > 0;
        // AccountEntry._ID + " = " + account.getId(), null)>0;
        return bool;
    }

    @Override
    public boolean delete(long _id) {
        boolean res = database.delete(ExpenseManagerContract.AccountEntry.TABLE_NAME, ExpenseManagerContract.AccountEntry._ID + "=" + _id, null) > 0;

        return res;
    }

    @Override
    public int count() {
        return 0;
    }
}
