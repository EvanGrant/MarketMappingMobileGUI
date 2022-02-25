package com.example.marketmapping;

public class Store {

    private String Name; //Name of store in database

    public Store(){}
    public Store(String Name) {
        this.Name = Name;
    }

    public String getName() { //getter
        return Name;
    }

    public void setName(String name) { //setter
        Name = name;
    }
}
