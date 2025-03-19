package com.products.model;

public class Memento{
    private final String name;
    private final String lastName;

    public Memento(String name, String lastName){
        this.name = name;
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }
}
