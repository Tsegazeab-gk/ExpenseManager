package com.tsegazeabg.com.expensemanager.domain.interactors.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.SampleInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.base.AbstractInteractor;
import com.tsegazeabg.com.expensemanager.domain.repository.Repository;

/**
 * This is an interactor boilerplate with a reference to a model repository.
 * <p/>
 */
public class SampleInteractorImpl extends AbstractInteractor implements SampleInteractor {

    private SampleInteractor.Callback mCallback;
    private Repository mRepository;

    public SampleInteractorImpl(Executor threadExecutor,
                                MainThread mainThread,
                                Callback callback, Repository repository) {
        super(threadExecutor, mainThread);
        mCallback = callback;
        mRepository = repository;
    }

    @Override
    public void run() {
        // TODO: Implement this with your business logic
    }
}
