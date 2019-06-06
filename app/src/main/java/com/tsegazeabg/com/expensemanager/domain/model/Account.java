package com.tsegazeabg.com.expensemanager.domain.model;

/**
 * Created by user on 6/22/2016.
 */
public class Account {
    int id;
    String name;

    public Account() {
    }

    public Account(String mname) {
        this.name=mname;
    }

    public Account(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
