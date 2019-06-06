package com.tsegazeabg.com.expensemanager.ui.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tsegazeabg.com.expensemanager.R;

import com.tsegazeabg.com.expensemanager.domain.executor.impl.ThreadExecutor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.presentation.presenters.CategoryPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.CategoryPresenterImpl;
import com.tsegazeabg.com.expensemanager.storage.CategoryRepositoryImpl;
import com.tsegazeabg.com.expensemanager.threading.MainThreadImpl;

import java.util.ArrayList;
import java.util.List;

public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener, CategoryPresenter.View {

    Button mBtnCreateExpenseCat;

    private ListView mExpListView;
    private EditText mCategoryName;

    //private CategoryRepository categoryController;
    private CategoryPresenter mCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

      init();
    }

    void init(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mExpListView = (ListView) findViewById(R.id.list_expense_categories);
        mExpListView.setOnItemClickListener(this);

        mBtnCreateExpenseCat = (Button) findViewById(R.id.buttonCreateExpenseCat);

        mBtnCreateExpenseCat.setOnClickListener(this);

        //Instantiate
        mCategoryPresenter=new CategoryPresenterImpl(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(),
                this,new CategoryRepositoryImpl(this));


    }

    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);
        final EditText editTextAccountName = (EditText) formElementsView.findViewById(R.id.categoryName);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Expense Category")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //.cancel();
                        String accountName = editTextAccountName.getText().toString();

                        mCategoryPresenter.addNewCategory(accountName,1);

                    }
                }).show();
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, final long id) {


        final Context context = view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);
        final EditText editTextAccountName = (EditText) formElementsView.findViewById(R.id.categoryName);

        final CharSequence[] items = {"Edit", "Delete"};

        new AlertDialog.Builder(this).setTitle("Select Action")
                .setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        if (item == 0) {
                           // editRecord((Long) id);
                            mCategoryPresenter.getCategoryByID(id);
                        } else if (item == 1) {
                            //boolean deleteSuccessful = categoryController.delete(id);
                            boolean deleteSuccessful=mCategoryPresenter.deleteCategory(id);

                        }
                    }
                }).show();
    }

    @Override
    public void onCategoryAdded() {
        Toast.makeText(this, "Category was saved.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(1);

    }

    @Override
    public void onCategoryDeleted() {
        Toast.makeText(this, "Expense Category was deleted!.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(1);


    }

    @Override
    public void onCategoryNotAdded() {

        Toast.makeText(this, "Category is not saved.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showCategory(Cursor categories) {

        String[] from = new String[]{"_id", "name"};

        int[] to = new int[]{R.id.incomeID, R.id.incomeCategoryName};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.category_list, categories, from, to) {
        };

        mExpListView.setAdapter(adapter);

    }

    @Override
    public void onCategoryRetrieved(final Category category) {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);

        final EditText editTextCatName = (EditText) formElementsView.findViewById(R.id.categoryName);

        editTextCatName.setText(category.getName());
        new AlertDialog.Builder(this)
                .setView(formElementsView)
                .setTitle("Edit Record")
                .setPositiveButton("Save Changes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                               /* Category objectCat = new Category();
                                objectCat.setId(id);
                                objectCat.setName(editTextCatName.getText().toString());
                                */
                                mCategoryPresenter.editCategory(category.getId(),editTextCatName.getText().toString(),1);
                                //dialog.cancel();

                            }

                        }).show();



    }

    @Override
    public void onCategoryUpdated() {
        Toast.makeText(getApplicationContext(), "Category record was updated.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(1);


    }

    @Override
    public void onCategoryNotUpdated() {
        Toast.makeText(getApplicationContext(), "Category record was not updated.", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onCategoryEmpty() {

    }

    @Override
    public void onCategoryNotDeleted() {
        Toast.makeText(this, "Expense Category was not deleted!.", Toast.LENGTH_SHORT).show();

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

    @Override
    protected void onResume() {
        super.onResume();
        mCategoryPresenter.getAllCategoriesByType(1);
    }
}
