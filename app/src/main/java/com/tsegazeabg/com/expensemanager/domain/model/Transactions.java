package com.tsegazeabg.com.expensemanager.domain.model;

/**
 * Created by user on 6/22/2016.
 */
public class Transactions {

    int mId;

    double mAmount;
    String mDescription;
    int mAccount;
    int mCategoryType;
    int mCategory;
    String mTransactionDate;
    String mCreatedDate;

    String mCategoryName;
    String mStringTraDate;

    public Transactions() {
    }

    public Transactions(double mAmount, String mDescription, int mAccount, int mCategoryType, int mCategory, String mTransactionDate, String mCreatedDate) {

        this.mAmount = mAmount;
        this.mDescription = mDescription;
        this.mAccount = mAccount;
        this.mCategoryType = mCategoryType;
        this.mCategory = mCategory;
        this.mTransactionDate = mTransactionDate;
        this.mCreatedDate = mCreatedDate;
    }

    public String getmStringTraDate() {
        return mStringTraDate;
    }

    public void setmStringTraDate(String mStringTraDate) {
        this.mStringTraDate = mStringTraDate;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public double getmAmount() {
        return mAmount;
    }

    public void setmAmount(double mAmount) {
        this.mAmount = mAmount;
    }

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public int getmAccount() {
        return mAccount;
    }

    public void setmAccount(int mAccount) {
        this.mAccount = mAccount;
    }

    public int getmCategoryType() {
        return mCategoryType;
    }

    public void setmCategoryType(int mCategoryType) {
        this.mCategoryType = mCategoryType;
    }

    public int getmCategory() {
        return mCategory;
    }

    public void setmCategory(int mCategory) {
        this.mCategory = mCategory;
    }

    public String getmTransactionDate() {
        return mTransactionDate;
    }

    public void setmTransactionDate(String mTransactionDate) {
        this.mTransactionDate = mTransactionDate;
    }

    public String getmCreatedDate() {
        return mCreatedDate;
    }

    public void setmCreatedDate(String mCreatedDate) {
        this.mCreatedDate = mCreatedDate;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }

    public void setmCategoryName(String mCategoryName) {
        this.mCategoryName = mCategoryName;
    }
}
