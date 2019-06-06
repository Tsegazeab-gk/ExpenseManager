package com.tsegazeabg.com.expensemanager.dao_to_be_removed;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.AccountEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 6/22/2016.
 */
public class AccountRepository {

    private static AccountRepository INSTANCE;
    public DBHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase database;


    public AccountRepository(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    public static AccountRepository getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new AccountRepository(context);

        }
        return INSTANCE;
    }

    public AccountRepository open() throws SQLException {
        if (mDbHelper == null) {
            mDbHelper = new DBHelper(mContext);
        }
        database = mDbHelper.getWritableDatabase();
        return this;

    }

    public void close() {
        mDbHelper.close();
    }

    public Account readSingleRecord(int _id) {
        open();
        Account accountObject = null;
        //  open();
        String sql = "SELECT * FROM " + AccountEntry.TABLE_NAME + " WHERE _id = " + _id;
        Cursor cursor = database.rawQuery(sql, null);

        if (cursor.moveToFirst()) {

            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(AccountEntry._ID)));
            String firstname = cursor.getString(cursor.getColumnIndex(AccountEntry.COLUMN_NAME_ACCOUNT_NAME));

            accountObject = new Account();
            accountObject.setId(id);
            accountObject.setName(firstname);
        }

        cursor.close();
        close();
        return accountObject;
    }

    public boolean insert(Account acc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(AccountEntry.COLUMN_NAME_ACCOUNT_NAME, acc.getName());
        boolean createSuccessful = database.insert(AccountEntry.TABLE_NAME, null, contentValue) > 0;
        return createSuccessful;
    }

    // public Cursor fetch()
    public List<Account> fetch() {
        open();
        List<Account> listAccount = new ArrayList<Account>();

        String[] columns = new String[]{AccountEntry._ID, AccountEntry.COLUMN_NAME_ACCOUNT_NAME};

        Cursor cursor = database.query(AccountEntry.TABLE_NAME, columns, null,
                null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(AccountEntry._ID)));
                String name = cursor.getString(cursor.getColumnIndex(AccountEntry.COLUMN_NAME_ACCOUNT_NAME));
                Account acc = new Account();
                acc.setId(id);
                acc.setName(name);
                listAccount.add(acc);

            } while (cursor.moveToNext());
        }
       /* if (cursor != null) {
            cursor.moveToFirst();
        }*/

        cursor.close();
        close();
        return listAccount;
    }

    public boolean update(Account accountsObject) {
        open();
        //  long _id, String name
        String where = AccountEntry._ID + " = '" + accountsObject.getId() + "'";
        Log.v("Id", String.valueOf(accountsObject.getId()));
        String[] whereArgs = {Integer.toString(accountsObject.getId())};
        ContentValues contentValues = new ContentValues();
        contentValues.put(AccountEntry.COLUMN_NAME_ACCOUNT_NAME, accountsObject.getName());

        Boolean bool = database.update(AccountEntry.TABLE_NAME, contentValues,
                where, null) > 0;
        // AccountEntry._ID + " = " + account.getId(), null)>0;
        close();
        return bool;
    }

    public boolean delete(long _id) {
        open();
        boolean res = database.delete(AccountEntry.TABLE_NAME, AccountEntry._ID + "=" + _id, null) > 0;
        close();
        return res;
    }

    public int count() {
        open();
        String sql = "SELECT * FROM " + AccountEntry.TABLE_NAME;
        int recordCount = database.rawQuery(sql, null).getCount();
        close();
        return recordCount;

    }
}
