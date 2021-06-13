package com.introtomobil.mustafaaydin;

import java.util.ArrayList;

public class Wardrop implements java.io.Serializable {
    private int id;
    private String name;
    private java.util.List<Clothes> clothesList;

    public Wardrop(int id, String name) {
        this.id = id;
        this.name = name;
        this.clothesList = new ArrayList<Clothes>();
    }

    public java.util.List<Clothes> getClothesList() {
        return clothesList;
    }

    public void deleteFromClothesList(int cNo) {
        this.clothesList.remove(cNo);
    }
    public void addToClothesList(Clothes clothes) {
        this.clothesList.add(clothes);
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
}
