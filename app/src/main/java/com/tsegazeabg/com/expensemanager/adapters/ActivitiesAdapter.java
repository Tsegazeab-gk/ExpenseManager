package com.tsegazeabg.com.expensemanager.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tsegazeabg.com.expensemanager.R;
import com.tsegazeabg.com.expensemanager.domain.model.Transactions;

import java.util.List;

/**
 * Created by user on 9/19/2016.
 */
public class ActivitiesAdapter extends RecyclerView.Adapter<ActivitiesAdapter.ViewHolder> {

    private List<Transactions> mTransactionsList;
    private LayoutInflater mLayoutInflater;

    private List<Transactions> mTransactionsFilterList;
    private Transactions mTransaction;
    private Context mContext;

    public ActivitiesAdapter(Context context, List<Transactions> mTransactionsList) {
        if (mTransactionsList == null) {
            throw new IllegalArgumentException("mTransactionsList must not be null");
        }
        this.mContext = context;
        this.mTransactionsList = mTransactionsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_list_activities, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Transactions transactions = mTransactionsList.get(position);

        if (transactions.getmCategoryType() == 0) {
            holder.textAmount.setText("" + transactions.getmAmount());

            holder.textCategoryType.setText("Income");
            holder.textAmount.setTextColor(Color.GREEN);
        } else if (transactions.getmCategoryType() == 1) {
            holder.textAmount.setText("" + transactions.getmAmount());

            holder.textCategoryType.setText("Expense");
            holder.textAmount.setTextColor(Color.RED);
        } else {
            holder.textAmount.setText("" + transactions.getmAmount());
            holder.textCategoryType.setText("Transfer");
        }

        holder.textDesc.setText(transactions.getmDescription());

        if (transactions.getmAccount() == 0) {
            holder.textAccount.setText("Cash");
        } else {
            holder.textAccount.setText("Saving");
        }
        holder.textTransactionDate.setText("Date:" + transactions.getmTransactionDate());
        holder.textCategory.setText(transactions.getmCategoryName());
//
//        String dateStr = DateUtils.formatDateTime(
//                holder.textTransactionDate.getContext(),
//                transactions.getmTransactionDate(),
//                DateUtils.FORMAT_ABBREV_ALL);
    }

    @Override
    public int getItemCount() {
        return mTransactionsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected TextView textTransactionDate;
        protected TextView textAmount;
        protected TextView textAccount;
        protected TextView textCategoryType;
        protected TextView textCategory;
        protected TextView textDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            textTransactionDate = (TextView) itemView.findViewById(R.id.recyler_transaction_date);
            textAmount = (TextView) itemView.findViewById(R.id.recycler_amount);
            textAccount = (TextView) itemView.findViewById(R.id.recycler_account);
            textCategoryType = (TextView) itemView.findViewById(R.id.recyler_category_type);
            textCategory = (TextView) itemView.findViewById(R.id.recycler_category);
            textDesc = (TextView) itemView.findViewById(R.id.recycler_description);

        }
    }


}
