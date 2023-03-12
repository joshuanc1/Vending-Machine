package com.techelevator.utility;

public class Gum extends Item{

    public Gum(String name, int price){
        super(name, price);
    }
    public Gum(){
        super();
    }

    //Print category message
    public void message(){
        System.out.println("Chew Chew, Yum!");
    }
}
