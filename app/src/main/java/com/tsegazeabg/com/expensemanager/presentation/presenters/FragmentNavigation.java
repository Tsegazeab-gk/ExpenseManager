package com.tsegazeabg.com.expensemanager.presentation.presenters;

import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.BaseFragment;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.TransactionPresenterImpl;

public interface FragmentNavigation {
    void addFragment(BaseFragment fragment);

    interface View {
        void atachPresenter(FragmentNavigation presenter);

    }
}
