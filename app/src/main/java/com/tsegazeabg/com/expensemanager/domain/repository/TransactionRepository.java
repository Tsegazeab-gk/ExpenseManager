package com.tsegazeabg.com.expensemanager.domain.repository;

import com.tsegazeabg.com.expensemanager.domain.model.Transactions;

/**
 * Created by user on 9/18/2016.
 */
public interface TransactionRepository {

    boolean insert(Transactions accountTransactions);
}
