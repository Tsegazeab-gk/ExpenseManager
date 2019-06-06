package com.tsegazeabg.com.expensemanager.storage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.tsegazeabg.com.expensemanager.domain.model.Transactions;
import com.tsegazeabg.com.expensemanager.domain.repository.TransactionRepository;
import com.tsegazeabg.com.expensemanager.storage.database.DBHelper;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.TransactionsEntry;

/**
 * Created by user on 9/18/2016.
 */
public class TransactionRepositoryImpl implements TransactionRepository {

    private DBHelper mDbHelper;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public TransactionRepositoryImpl(Context context) {
        this.mContext = context;
        mDbHelper = new DBHelper(context);
    }

    @Override
    public boolean insert(Transactions transactions) {

        mDatabase = mDbHelper.getWritableDatabase();

        ContentValues contentValue = new ContentValues();
        contentValue.put(TransactionsEntry.COLUMN_NAME_AMOUNT, transactions.getmAmount());
        contentValue.put(TransactionsEntry.COLUMN_NAME_DESCRIPTION, transactions.getmDescription());
        contentValue.put(TransactionsEntry.COLUMN_NAME_ACCOUNT_ID, transactions.getmAccount());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE, transactions.getmCategoryType());
        contentValue.put(TransactionsEntry.COLUMN_NAME_CATEGORY_ID, transactions.getmCategory());

        contentValue.put(TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE, transactions.getmTransactionDate());
        contentValue.put(ExpenseManagerContract.COLUMN_NAME_CREATED_DATE, transactions.getmCreatedDate());

        boolean bool = mDatabase.insert(ExpenseManagerContract.TransactionsEntry.TABLE_NAME, null, contentValue) > 0;
        return bool;
    }
}
