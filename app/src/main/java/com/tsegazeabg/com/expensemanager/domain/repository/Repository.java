package com.tsegazeabg.com.expensemanager.domain.repository;


import com.tsegazeabg.com.expensemanager.domain.model.SampleModel;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface Repository {

    boolean insert(SampleModel model);

    boolean update(SampleModel model);

    SampleModel get(Object id);

    boolean delete(SampleModel model);
}
