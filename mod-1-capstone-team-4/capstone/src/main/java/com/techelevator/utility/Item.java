package com.techelevator.utility;

public class Item {

    //Parameters
    private String name;
    private double price;
    private String category;


    //Getters % Setters

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }



    public String getCategory() {
        return category;
    }

    //Constructor

    public Item(){}

    public Item(String name, double price){
        this.name = name;
        this.price = price;

    }
    public Item(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }


}
