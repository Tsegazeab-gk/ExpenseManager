package com.tsegazeabg.com.expensemanager.storage.database;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.AccountEntry;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.TransactionsEntry;
import com.tsegazeabg.com.expensemanager.storage.database.ExpenseManagerContract.CategoryEntry;

/**
 * Created by user on 6/22/2016.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 2;
    private static final String DATABASE_NAME = "ExpenseManager.db";


    private static final String TEXT_TYPE = " TEXT";
    private static final String INT_TYPE = " int";
    private static final String DOUBLE_TYPE = " double";
    private static final String DATE_TYPE = " DATETIME DEFAULT CURRENT_TIMESTAMP";
    private static final String COMMA_SEP = ", ";

    private static final String CREATE_TABLE_ACCOUNT =
            "CREATE TABLE " + AccountEntry.TABLE_NAME + " (" +
                    AccountEntry._ID + " INTEGER PRIMARY KEY," +
                    AccountEntry.COLUMN_NAME_ACCOUNT_NAME + TEXT_TYPE +
                    ")";

    private static final String CREATE_TABLE_ACCOUNT_TRANSACTIONS =
            "CREATE TABLE " + TransactionsEntry.TABLE_NAME + " (" +
                    TransactionsEntry._ID + " INTEGER PRIMARY KEY," +
                    TransactionsEntry.COLUMN_NAME_AMOUNT + DOUBLE_TYPE + COMMA_SEP +
                    TransactionsEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    TransactionsEntry.COLUMN_NAME_ACCOUNT_ID + INT_TYPE + COMMA_SEP +
                    ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + INT_TYPE + COMMA_SEP +
                    TransactionsEntry.COLUMN_NAME_CATEGORY_ID + INT_TYPE + COMMA_SEP +
                    TransactionsEntry.COLUMN_NAME_TRANSACTION_DATE + TEXT_TYPE + COMMA_SEP +
                    ExpenseManagerContract.COLUMN_NAME_CREATED_DATE + TEXT_TYPE + COMMA_SEP +
                    TransactionsEntry.COLUMN_NAME_TRANSACTION_TYPE + TEXT_TYPE + COMMA_SEP +
                  /*  " FOREIGN KEY (" + AccountTransactionsEntry.COLUMN_NAME_ACCOUNT_ID + ") REFERENCES " + AccountEntry.TABLE_NAME +
                    "(" + AccountEntry._ID + ")," +*/
                    " FOREIGN KEY (" + TransactionsEntry.COLUMN_NAME_CATEGORY_ID + ") REFERENCES " + CategoryEntry.TABLE_NAME +
                    "(" + CategoryEntry._ID + "))";

    private static final String CREATE_TABLE_CATEGORY =
            "CREATE TABLE " + CategoryEntry.TABLE_NAME + " (" +
                    CategoryEntry._ID + " INTEGER PRIMARY KEY, " +
                    CategoryEntry.COLUMN_NAME_CATEGORY_NAME + TEXT_TYPE + COMMA_SEP +
                    ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + INT_TYPE +
                    " )";

    private static final String DELETE_TABLE_ACCOUNT = "DROP TABLE IF EXISTS " + AccountEntry.TABLE_NAME;
    private static final String DELETE_TABLE_ACCOUNT_TRANSACTIONS = "DROP TABLE IF EXISTS " + TransactionsEntry.TABLE_NAME;
    private static final String DELETE_TABLE_CATEGORY = "DROP TABLE IF EXISTS " + CategoryEntry.TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_ACCOUNT);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_ACCOUNT_TRANSACTIONS);

        db.execSQL("insert into " + AccountEntry.TABLE_NAME + "(" + AccountEntry.COLUMN_NAME_ACCOUNT_NAME + ") values('Awash')");
        db.execSQL("insert into " + AccountEntry.TABLE_NAME + "(" + AccountEntry.COLUMN_NAME_ACCOUNT_NAME + ") values('Commercial')");


        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('Salary',0)");
        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('Loan',0)");

        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('House Rent',1)");
        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('Food',1)");
        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('Transport',1)");
        db.execSQL("insert into " + CategoryEntry.TABLE_NAME + "(" + CategoryEntry.COLUMN_NAME_CATEGORY_NAME + "," +
                ExpenseManagerContract.COLUMN_NAME_CATEGORY_TYPE + ") values('Transfer',2)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE_ACCOUNT_TRANSACTIONS);
        db.execSQL(DELETE_TABLE_CATEGORY);
        db.execSQL(DELETE_TABLE_ACCOUNT);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}
