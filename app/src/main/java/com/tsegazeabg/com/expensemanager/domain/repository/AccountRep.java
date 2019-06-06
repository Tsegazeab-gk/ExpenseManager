package com.tsegazeabg.com.expensemanager.domain.repository;

import com.tsegazeabg.com.expensemanager.domain.model.Account;

import java.util.List;

/**
 * Created by user on 9/15/2016.
 */
public interface AccountRep {


    Account readSingleRecord(long _id);

    boolean insert(Account acc);

    List<Account> getAllAccounts();

    boolean update(Account accountsObject);

    boolean delete(long _id);

    int count();


}
