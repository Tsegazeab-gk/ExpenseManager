package com.tsegazeabg.com.expensemanager.domain.model;

/**
 * Created by user on 6/22/2016.
 */
public class Category {

    int id;
    String name;
    int type;   // 0 for income 1 for expense category

    public Category() {
    }

    public Category(int id, String name, int type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public Category(String name, int type) {
        this.name = name;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return
                name;
    }
}
