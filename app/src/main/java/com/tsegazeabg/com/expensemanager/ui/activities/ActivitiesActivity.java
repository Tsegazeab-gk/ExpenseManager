package com.tsegazeabg.com.expensemanager.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.adapters.ActivitiesAdapter;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.TransactionsRepository;
import com.tsegazeabg.com.expensemanager.domain.model.Transactions;

import java.util.List;

public class ActivitiesActivity extends AppCompatActivity {

    private TransactionsRepository mTransactionsRepository;
    private RecyclerView mRecycler;
    private ActivitiesAdapter mActivitiesAdapter;

    private Spinner mAccFilterSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activities);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mRecycler = (RecyclerView) findViewById(R.id.recycler_ativities);
        mRecycler.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecycler.setLayoutManager(linearLayoutManager);

        mTransactionsRepository = TransactionsRepository.getInstance(this);
        List<Transactions> transactionsList = mTransactionsRepository.getTransactions();

        mActivitiesAdapter = new ActivitiesAdapter(this, transactionsList);

        mRecycler.setAdapter(mActivitiesAdapter);

        mAccFilterSpinner = (Spinner) findViewById(R.id.spinner_filter_activities);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.action_about) {
            Intent intent = new Intent(this, AboutApp.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
