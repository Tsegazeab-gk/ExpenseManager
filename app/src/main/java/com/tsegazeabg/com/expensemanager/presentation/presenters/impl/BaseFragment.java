package com.tsegazeabg.com.expensemanager.presentation.presenters.impl;

import android.app.Fragment;

import com.tsegazeabg.com.expensemanager.presentation.presenters.FragmentNavigation;

public class BaseFragment extends Fragment implements FragmentNavigation.View {

    protected FragmentNavigation navigationpresenter ;

    @Override
    public void atachPresenter(FragmentNavigation presenter) {
navigationpresenter=presenter;
    }
}
