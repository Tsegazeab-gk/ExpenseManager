package com.tsegazeabg.com.expensemanager.ui.activities;

import android.app.DatePickerDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.AccountRepository;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.CategoryRepository;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.TransactionsRepository;
import com.tsegazeabg.com.expensemanager.domain.executor.Executor;
import com.tsegazeabg.com.expensemanager.domain.executor.impl.ThreadExecutor;
import com.tsegazeabg.com.expensemanager.domain.model.Account;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.domain.model.Transactions;
import com.tsegazeabg.com.expensemanager.presentation.presenters.TransactionPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.BaseFragment;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.TransactionPresenterImpl;
import com.tsegazeabg.com.expensemanager.storage.TransactionRepositoryImpl;
import com.tsegazeabg.com.expensemanager.threading.MainThreadImpl;
import com.tsegazeabg.com.expensemanager.ui.fragments.DatePickerFragment;
import com.tsegazeabg.com.expensemanager.ui.fragments.ExpenseFragment;
import com.tsegazeabg.com.expensemanager.ui.fragments.IncomeFragment;
import com.tsegazeabg.com.expensemanager.ui.fragments.TransferFragment;
import com.tsegazeabg.com.expensemanager.utils.DateUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class TransactionActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, AdapterView.OnItemSelectedListener,
        TransactionPresenter.View {

    static final String TAG = "TransactionActivity";
    protected double mAmount;
    protected String mDescription;
    protected int mAccount;
    protected int mCategoryType;

    // R.id.input_cost_category)
    protected int mCategory;
    protected String mTransactionDate;
    protected String mCreatedDate;
    //@Bind(R.id.input_date)
    TextView mDateTextView;
    //@Bind(R.id.input_amount)
    EditText mAmountEditText;
    //@Bind(R.id.input_description)
    EditText mDescriptionEditText;
    Spinner mCategoryTypeSpinner, mCategorySpinner;
    private Spinner mAccSpinner;

    TabLayout mTabLayout;
    ViewPager mViewPager;

    private int[] tabIcons = {
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_camera
    };
    //private TransactionsRepository mTransactionsRepository;
    //private AccountRepository mAccountRepository;
    private CategoryRepository mCategoryRepository;


    private TransactionPresenter mTransactionPresenter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_transaction);
        init();

    }

    private void init() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setDisplayShowTitleEnabled(false);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        // instantiate the presenter

        mCategoryRepository = CategoryRepository.getInstance(this);

        mAccSpinner = (Spinner) findViewById(R.id.input_accounts);


        mDateTextView = (TextView) findViewById(R.id.input_date);
        mDateTextView.setOnClickListener(this);

        mAmountEditText = (EditText) findViewById(R.id.input_amount);
        mDescriptionEditText = (EditText) findViewById(R.id.input_description);
        mCategorySpinner = (Spinner) findViewById(R.id.input_category);

        mCategoryTypeSpinner = (Spinner) findViewById(R.id.input_category_type);

        mCategoryTypeSpinner.setOnItemSelectedListener(this);

        mCategorySpinner.setOnItemSelectedListener(this);
        // showCategory((int)mCategoryTypeSpinner.getSelectedItemId());

        mViewPager= (ViewPager) findViewById(R.id.transaction_viewpager);
        setupViewPager(mViewPager);
        mTabLayout= (TabLayout) findViewById(R.id.transaction_tabs);
        mTabLayout.setupWithViewPager(mViewPager);

        setupTabIcons();

        mTransactionPresenter=new TransactionPresenterImpl(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),this,
                new TransactionRepositoryImpl(this),getApplicationContext());

    }
    private void setupTabIcons() {
        mTabLayout.getTabAt(0).setIcon(tabIcons[0]);
        mTabLayout.getTabAt(1).setIcon(tabIcons[1]);
        mTabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_about) {
            //extractFormData();
            Log.v(TAG, "" + mAccount);

            return true;
        }
        //noinspection SimplifiableIfStatement
        else if (id == R.id.action_save) {

            extractFormData();  ///Transactions transactions = new Transactions();
            Boolean bool = false;

                mTransactionPresenter.addTransaction(mAmount, mDescription, mAccount, mCategoryType, mCategory,
                        mTransactionDate,
                        mCreatedDate);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void extractFormData() {
        // extract data from the form
        try {
            mAmount = Double.valueOf(mAmountEditText.getText().toString());
        } catch (NumberFormatException e) {
            mAmount = 0.0;
        }

        // extract description and category
        mDescription = mDescriptionEditText.getText().toString();
        mCategoryType = (int) mCategoryTypeSpinner.getSelectedItemId();

Account acc= (Account) mAccSpinner.getSelectedItem();
        mAccount = acc.getId();

        mTransactionDate = mDateTextView.getText().toString();
        //  mDateTextView.getText().t;

        mCreatedDate = DateUtils.formatDate(DateUtils.getToday());

        mCategory = mCategoryRepository.getCategoryId(mCategorySpinner.getSelectedItem().toString());


        Log.v(TAG, "Amount=" + mAmountEditText.getText().toString() + "Account+" + mAccount + "Sub CAt=" + mCategory);
    }




    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new IncomeFragment(), "INC");
        adapter.addFragment(new ExpenseFragment(), "EXP");
        adapter.addFragment(new TransferFragment(), "TRA");
        viewPager.setAdapter(adapter);
    }

    public void showAccounts(List<Account> accounts) {


        ArrayAdapter<com.tsegazeabg.com.expensemanager.domain.model.Account> adapter =
                new ArrayAdapter<com.tsegazeabg.com.expensemanager.domain.model.Account>(this
                 ,android.R.layout.simple_spinner_item, accounts);
// Specify the layout to use when the list of choices appears
       // adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        mAccSpinner.setAdapter(adapter);



    }

    @Override
    public void onTransactionAdded() {
        Toast.makeText(this, "Transaction Added", Toast.LENGTH_SHORT).show();
        onBackPressed();

    }

    @Override
    public void onTransactionNotAdded() {
        Toast.makeText(this, "Transaction not Added", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void setFragment(BaseFragment fragment) {



    }


    public void showCategory(int id) {
        
        mCategoryRepository = CategoryRepository.getInstance(this);
        List<Category> categories = mCategoryRepository.getAllCatByType(id);
        ArrayAdapter<Category> adapter =
                new ArrayAdapter<Category>(this, android.R.layout.simple_list_item_1, categories);
// Specify the layout to use when the list of choices appears
        //adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mCategorySpinner.setAdapter(adapter);
        // mSubCategorySpinner.notify();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mTransactionDate = DateUtils.formatDate(DateUtils.getToday());
        mDateTextView.setText(mTransactionDate);
         mTransactionPresenter.resume();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        mTransactionDate = DateUtils.formatDate(DateUtils.createDate(year, monthOfYear, dayOfMonth));
        mDateTextView.setText(mTransactionDate);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.input_date) {
            showDatePickerDialog(v);
        }
    }

    private void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setListener(this);
        newFragment.show(getFragmentManager(), "datePicker");
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        Toast.makeText(this, "Category spinner" + mCategoryTypeSpinner.getSelectedItemId(), Toast.LENGTH_SHORT).show();
        // showCategory(0);
        if (spinner.getId() == R.id.input_category_type) {
            //Toast.makeText(this,"Category spinner"+spinner.getId()+" "+R.id.input_category,Toast.LENGTH_LONG).show();
            showCategory((int) id);
        }
        if (spinner.getId() == R.id.input_category) {

            // Category mSelected = (Category) spinner.getItemAtPosition(position);
            // Log.v("Id:", "" + mSelected.getId());
            Toast.makeText(this, "boom mSubCategorySpinner" + mCategorySpinner.getSelectedItem(), Toast.LENGTH_LONG).show();

        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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



    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList =new ArrayList<>();
        private final  List<String> mFragmentTitleList=new ArrayList<>();

         public ViewPagerAdapter(FragmentManager fm) {
             super(fm);
         }

         @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {

            return mFragmentList.size();
        }
         public void addFragment(Fragment fragment, String title) {
             mFragmentList.add(fragment);
             mFragmentTitleList.add(title);
         }

         @Override
         public CharSequence getPageTitle(int position) {
             return mFragmentTitleList.get(position);
         }
    }
}
