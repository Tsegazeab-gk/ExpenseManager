package com.tsegazeabg.com.expensemanager.storage.database;

import android.provider.BaseColumns;

/**
 * Created by user on 6/22/2016.
 */

public final class ExpenseManagerContract {
    // To prevent someone from accidentally instantiating the contract class,
    // give it an empty constructor.
    public static final String COLUMN_NAME_CREATED_DATE = "created_date";
    public static final String COLUMN_NAME_CATEGORY_TYPE = "type";


    public ExpenseManagerContract() {
    }

    /* Inner class that defines the table contents */
    public static abstract class AccountEntry implements BaseColumns {
            public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_ACCOUNT_NAME = "name";

    }

    public static abstract class TransactionsEntry implements BaseColumns {
        public static final String TABLE_NAME = "transactions";
        public static final String COLUMN_NAME_AMOUNT = "amount";
        public static final String COLUMN_NAME_DESCRIPTION = "description";


        public static final String COLUMN_NAME_TRANSACTION_DATE = "transaction_date";

        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_TRANSACTION_TYPE = "transaction_type";  // T/S for Transfer..

    }


    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_CATEGORY_NAME = "name";

        //0 for income 1 for expense
    }


}


