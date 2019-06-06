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
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.dao_to_be_removed.CategoryRepository;
import com.tsegazeabg.com.expensemanager.domain.executor.impl.ThreadExecutor;
import com.tsegazeabg.com.expensemanager.domain.model.Category;
import com.tsegazeabg.com.expensemanager.presentation.presenters.CategoryPresenter;
import com.tsegazeabg.com.expensemanager.presentation.presenters.impl.CategoryPresenterImpl;
import com.tsegazeabg.com.expensemanager.storage.CategoryRepositoryImpl;
import com.tsegazeabg.com.expensemanager.threading.MainThreadImpl;

public class IncomeActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener ,CategoryPresenter.View{

    Button btnCreateIncomeCat;

    private ListView listView;
    private EditText categoryName;

    //private CategoryRepository categoryController;
    private CategoryPresenter mCategoryPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
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

    listView = (ListView)
            findViewById(R.id.list_income_categories);
    listView.setOnItemClickListener(this);


    btnCreateIncomeCat = (Button) findViewById(R.id.buttonCreateIncomeCat);

    btnCreateIncomeCat.setOnClickListener(this);
    //categoryController = new CategoryRepository(getApplicationContext());
    //Instantiate
    mCategoryPresenter=new CategoryPresenterImpl(ThreadExecutor.getInstance(),MainThreadImpl.getInstance(), this,new CategoryRepositoryImpl(this));
}
    @Override
    public void onClick(View v) {
        final Context context = v.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View formElementsView = inflater.inflate(R.layout.category_input_form, null, false);
        final EditText editTextAccountName = (EditText) formElementsView.findViewById(R.id.categoryName);


        new AlertDialog.Builder(context)
                .setView(formElementsView)
                .setTitle("Create Income Category")
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //.cancel();
                        String accountName = editTextAccountName.getText().toString();
                        mCategoryPresenter.addNewCategory(accountName,0);

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
                            //editRecord((int) id);
                            mCategoryPresenter.getCategoryByID(id);
                        } else if (item == 1) {
                            boolean deleteSuccessful=mCategoryPresenter.deleteCategory(id);

                        }
                    }
                }).show();
    }


    @Override
    public void onCategoryAdded() {
        Toast.makeText(this, "Category was saved.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(0);

    }

    @Override
    public void onCategoryDeleted() {
        Toast.makeText(this, "Category was deleted!.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(0);

    }

    @Override
    public void onCategoryNotAdded() {
        Toast.makeText(this, "Category was not Saved!.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(0);

    }

    @Override
    public void showCategory(Cursor categories) {
        String[] from = new String[]{"_id", "name"};

        int[] to = new int[]{R.id.incomeID, R.id.incomeCategoryName};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.category_list, categories, from, to) {
        };
        //adapter.notifyDataSetChanged();
        listView.setAdapter(adapter);

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

                                //dialog.cancel();
                                //Log.v("Id in activity", ((String.valueOf(id))));
                              mCategoryPresenter.editCategory(category.getId(),editTextCatName.getText().toString(),0);

                            }

                        }).show();

    }

    @Override
    public void onCategoryUpdated() {
        Toast.makeText(getApplicationContext(), "Category record was updated.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(0);

    }

    @Override
    public void onCategoryNotUpdated() {
        Toast.makeText(getApplicationContext(), "Category record was not  updated.", Toast.LENGTH_SHORT).show();
        mCategoryPresenter.getAllCategoriesByType(0);

    }

    @Override
    public void onCategoryEmpty() {
        Toast.makeText(getApplicationContext(), "Category is Empty.", Toast.LENGTH_SHORT).show();
            }

    @Override
    public void onCategoryNotDeleted() {
        Toast.makeText(this, " Category was not deleted!.", Toast.LENGTH_SHORT).show();

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
        mCategoryPresenter.getAllCategoriesByType(0);
    }
}
