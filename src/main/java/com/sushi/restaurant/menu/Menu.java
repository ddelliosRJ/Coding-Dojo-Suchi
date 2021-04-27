package com.sushi.restaurant.menu;

import com.sushi.restaurant.Plate;

import java.util.List;
import java.util.Map;

public abstract class Menu {
    private Map<String, Plate> order;
    private final int guestNumber;
    private int totalPlates;


    public Menu(Map<String, Plate> order, int guestNumber) {
        this.order = order;
        this.guestNumber = guestNumber;
    }

    protected Map<String, Plate> getOrder() {
        return order;
    }

    protected abstract double getPrice();

    protected abstract List<Plate> createMenu();


    protected double cost(){

        double totalCost = 0;

        List<Plate> plates = createMenu();

        for (Plate plate :  plates) {

            // get price
            double plateCost = plate.price();
            // get number of plates
            int plateNumber = plate.amount();
            // get plate type
            String plateType = plate.type();
            // calculate total plates until now
            totalPlates += plateNumber;

            // calculate cost and add to total
            double cost = Math.round((plateNumber * plateCost) * 100.0) / 100.0;
            totalCost += cost;

            System.out.println("⚪ Plate Cost: Guest " + getGuestNumber() + " got " + plateNumber + " " + plateType + " plate(s) and paid " + cost + "fr.");

        }

        return totalCost + getPrice();

    }

    public void calculateCost(){

        double cost = cost();

        System.out.println("⚫ TOTAL COST: Guest " + getGuestNumber() + " got " + getTotalPlates() + " plate(s) and paid for his order " + cost + "fr.\n");

    }

    protected int getGuestNumber() {
        return guestNumber;
    }


    protected int getTotalPlates() {
        return totalPlates;
    }
}
