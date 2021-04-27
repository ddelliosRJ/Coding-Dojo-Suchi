package com.sushi.restaurant;

public class Plate {

    private double price;
    private int amount;


    public Plate(double price, int amount) {
        this.price = price;
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double newPrice){
        price = newPrice;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int newAmount){
        amount = newAmount;
    }

    public String type() {

        String type = null;
        if (price == 2.50) {
            type = "Soup";
        } else if (price == 0.95) {
            type = "Blue";
        } else if (price == 1.95) {
            type = "Red";
        } else if (price == 2.95) {
            type = "Yellow";
        } else if (price == 3.95) {
            type = "Green";
        } else if (price == 4.95) {
            type = "Grey";
        }

        return type;
    }
}