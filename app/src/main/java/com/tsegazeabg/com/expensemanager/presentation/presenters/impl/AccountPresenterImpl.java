package com.tsegazeabg.com.expensemanager.presentation.presenters.impl;

import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.MainThread;
import com.tsegazeabg.com.expensemanager.domain.interactors.AddAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.DeleteAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.EditAccountInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAccountByIdInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.GetAllAccountsInteractor;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.AddAccountInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.DeleteAccountInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.EditAccountInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.GetAccountByIdInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.interactors.impl.GetAllAccountsInteractorImpl;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.repository.AccountRep;
import com.tsegazeabg.com.expensemanager.presentation.presenters.AccountPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.base.AbstractPresenter;

import java.util.List;

public class AccountPresenterImpl extends AbstractPresenter implements AccountPresenter ,
        GetAllAccountsInteractor.Callback ,AddAccountInteractor.Callback, DeleteAccountInteractor.Callback,
        GetAccountByIdInteractor.Callback,EditAccountInteractor.Callback {

    private AccountPresenter.View mView;
    private AccountRep mAccountRepository;


    public AccountPresenterImpl(Executor executor, MainThread mainThread,View view, AccountRep accountRep) {
        super(executor, mainThread);
        mView=view;
        mAccountRepository=accountRep;
    }

    @Override
    public void getAllAccounts() {

        //getall accounts
        GetAllAccountsInteractor getAllAccountsInteractor=new GetAllAccountsInteractorImpl(
                mExecutor,mMainThread,mAccountRepository,this
        );
        getAllAccountsInteractor.execute();
    }

    @Override
    public void addNewAccount(String name) {
        AddAccountInteractor addAccountInteractor=new AddAccountInteractorImpl(mExecutor,mMainThread,mAccountRepository,this,name);

addAccountInteractor.execute();
    }

    @Override
    public void getAccountByID(long id) {
        GetAccountByIdInteractor getAccountByIdInteractor=new GetAccountByIdInteractorImpl(mExecutor,mMainThread,mAccountRepository,this,id);
        getAccountByIdInteractor.execute();
    }

    @Override
    public Boolean deleteAccount(long id) {

        DeleteAccountInteractor deleteAccountInteractor=new DeleteAccountInteractorImpl(mExecutor,mMainThread,mAccountRepository,
                this,id);
        deleteAccountInteractor.execute();
        return true;
    }

    @Override
    public void editAccount(long id, String name) {
        EditAccountInteractor editAccountInteractor=new EditAccountInteractorImpl(mExecutor,mMainThread,mAccountRepository,this,name, id);
editAccountInteractor.execute();
    }


    @Override
    public void onAccountsRetrieved(List<Account> accountList) {
//List<Account> accounts=
        mView.showAccounts(accountList);
    }

    @Override
    public void resume() {
        getAllAccounts();
    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void onError(String message) {

    }

    @Override
    public void OnAccountAdded() {
        mView.onAccountAdded();

    }

    @Override
    public void OnAccountNotAdded() {
mView.onAccountNotAdded();
    }

    @Override
    public void OnAccountDeleted() {
        mView.onAccountDeleted();
    }

    @Override
    public void onAccountRetrieved(Account account) {
        mView.onAccountRetrieved(account);

    }

    @Override
    public void noAccountFound() {

    }

    @Override
    public void OnAccountUpdted() {
        mView.onAccountUpdated();

    }

    @Override
    public void OnAccountNotUpdated() {

    }
}
