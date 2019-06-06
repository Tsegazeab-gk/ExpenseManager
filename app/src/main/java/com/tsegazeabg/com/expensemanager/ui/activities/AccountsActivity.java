package com.tsegazeabg.com.expensemanager.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.AccountRepository;
import com.tsegazeabg.com.expensemanager.domain.executor.impl.ThreadExecutor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.presentation.presenters.AccountPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.AccountPresenterImpl;
import com.tsegazeabg.com.expensemanager.storage.AccountRepImpl;
import com.tsegazeabg.com.expensemanager.threading.MainThreadImpl;

import java.util.List;

public class AccountsActivity extends AppCompatActivity implements View.OnClickListener,
        View.OnLongClickListener , AccountPresenter.View{
    Button buttonCreateAccounts;
    Context context;
    String id;
    private AccountRepository accountController;  // to be removed

    private AccountPresenter mAccountPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_accounts);

            init();

    }
private void init(){
   // accountController = new AccountRepository(getApplicationContext());

    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    buttonCreateAccounts = (Button) findViewById(R.id.buttonCreateAccounts);

    buttonCreateAccounts.setOnClickListener(this);
    //countRecords();
    //readRecords();

    //instantiate the presenter
    mAccountPresenter=new AccountPresenterImpl(ThreadExecutor.getInstance(),
            MainThreadImpl.getInstance(),
            this,
            new AccountRepImpl(this));

}
    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.add_accounts_form, null, false);
        final EditText editTextAccountName = (EditText) formElementsView.findViewById(R.id.editTextAccountName);

        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Account")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        String accountName = editTextAccountName.getText().toString();
                        //Account acc = new Account();
                       // acc.setName(accountName);
                       // accountController.open();
                       // boolean createSuccessful = accountController.insert(acc);

                        mAccountPresenter.addNewAccount(accountName);
                    }
                }).show();


    }

    @Override
    public boolean onLongClick(View view) {


        context = view.getContext();
        id = view.getTag().toString();
        final CharSequence[] items = {"Edit", "Delete"};

        new AlertDialog.Builder(context).setTitle("Account Record")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                            editRecord(Integer.parseInt(id));
                        } else if (item == 1) {

                            boolean deleteSuccessful = mAccountPresenter.deleteAccount(Long.parseLong(id));
/*
                            if (deleteSuccessful) {
                                Toast.makeText(context, "Account record was deleted.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Unable to delete Account record.", Toast.LENGTH_SHORT).show();
                            }*/


                            //countRecords();
                            //readRecords();

                        }
                        // dialog.dismiss();

                    }
                }).show();

        //Toast.makeText(context, "Account information was saved.", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void editRecord(final int accountId) {

        //Account objectAccount =
                mAccountPresenter.getAccountByID(accountId);

    }

    @Override
    public void onAccountAdded() {

        Toast.makeText(this, "Account information was saved.", Toast.LENGTH_SHORT).show();
        mAccountPresenter.getAllAccounts();

    }

    @Override
    public void onAccountDeleted() {
        Toast.makeText(this, "Account record was deleted.", Toast.LENGTH_LONG).show();
        mAccountPresenter.getAllAccounts();
    }

    @Override
    public void onAccountNotAdded() {
        Toast.makeText(this, "Unable to save Account information.", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showAccounts(List<Account> accounts) {
        LinearLayout linearLayoutRecords = (LinearLayout) findViewById(R.id.linearLayoutRecords);
        linearLayoutRecords.removeAllViews();

        if (accounts.size() > 0) {

            for (Account obj : accounts) {


                int id = obj.getId();
                String studentFirstname = obj.getName();

                String textViewContents = studentFirstname;

                TextView textViewStudentItem = new TextView(this);
                textViewStudentItem.setPadding(0, 10, 0, 10);
                textViewStudentItem.setWidth(100);
                textViewStudentItem.setText(textViewContents);
                textViewStudentItem.setTag(Integer.toString(id));

                linearLayoutRecords.addView(textViewStudentItem);
                textViewStudentItem.setOnLongClickListener(this);
            }

        } else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");

            linearLayoutRecords.addView(locationItem);
        }

        int recordCount = accounts.size();
        TextView textViewRecordCount = (TextView) findViewById(R.id.textViewRecordCount);
        textViewRecordCount.setText(recordCount + " records found.");

    }

    @Override
    public void onAccountRetrieved(final Account account) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.add_accounts_form, null, false);

        final EditText editTextAccountName = (EditText) formElementsView.findViewById(R.id.editTextAccountName);
        editTextAccountName.setText(account.getName());
        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Edit Record" + id + "m")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                //Account objectStudent = new Account();
                                //objectStudent.setId(account.getId());
                               // objectStudent.setName(editTextAccountName.getText().toString());
                                //dialog.cancel();
                                //Log.v("Id in activity", ((String.valueOf(id))));
                                //boolean updateSuccessful =
                                        mAccountPresenter.editAccount(account.getId(),editTextAccountName.getText().toString());
/*
                                if (updateSuccessful) {
                                    Toast.makeText(context, "Account record was updated.", Toast.LENGTH_SHORT).show();
                                    // countRecords();
                                    // readRecords();
                                } else {
                                    Toast.makeText(context, "Unable to update Account record.id=" + id, Toast.LENGTH_SHORT).show();
                                }*/
                            }

                        }).show();

    }

    @Override
    public void onAccountUpdated() {
        Toast.makeText(this, "Account record was updated.", Toast.LENGTH_SHORT).show();
        mAccountPresenter.getAllAccounts();

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccountPresenter.resume();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String message) {

    }
}
