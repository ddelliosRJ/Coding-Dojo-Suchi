package com.sushi.restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomerOrder {

    private final List<Plate> order;

    private final int guestNumber;

    private final String day;

    private final Double time;

    public CustomerOrder(List<Plate> order, int guestNumber, String day, Double time) {

        this.order = order;
        this.guestNumber = guestNumber;
        this.day = day;
        this.time = time;
    }

    public void orderCost() {

        double lunchMenu = 0;
        int totalPlates = 0;
        double totalCost = 0;

        // Check if customer selected day and time and if he is eligible for lunch menu
        if (day != null || time != null) {
            if (isLunch(day, time)) {
                // Check if customer order has soup or one of the lunch items. Else customer can't get a lunch menu
                if (hasSoup(order) || hasMenuItems(order)) {
                    createLunchMenu(order);
                    lunchMenu = 8.50;
                }
            }
        }

        for (Plate plate : order) {

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

            System.out.println("⚪ Plate Cost: Guest " + guestNumber + " got " + plateNumber + " " + plateType + " plate(s) and paid " + cost + "fr.");

        }
        double tc = totalCost + lunchMenu;

        if (lunchMenu != 0) {
            System.out.println("⚫ TOTAL COST: Guest " + guestNumber + " got lunch menu and " + totalPlates + " plate(s) and paid for his order " + tc + "fr.\n");
        } else
            System.out.println("⚫ TOTAL COST: Guest " + guestNumber + " got " + totalPlates + " plate(s) and paid for his order " + tc + "fr.\n");
    }

    private static boolean isLunch(String day, double time) {

        // Create list with weekend days
        List<String> weekend = new ArrayList<>(Arrays.asList("Saturday", "Sunday"));

        // Continue as usual if day is Weekend and if time is not between lunch hours
        if (weekend.contains(day)) {

            System.out.println("Sorry, lunch menu is only available from Monday to Friday!");
            return false;

        } else if (!isBetweenTime(time)) {

            System.out.println("Sorry, lunch menu is only available from 11:00 to 17:00!");
            return false;
        }

        return true;
    }

    private static boolean isBetweenTime(double time) {
        double timeBefore = 17.00;
        double timeAfter = 11.00;
        return time >= timeAfter && time < timeBefore;
    }

    private static boolean hasSoup(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Soup")) {
//                System.out.println("Order has soup");
                return true;
            }
        }
        return false;
    }

    private static int soupPos(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Soup")) {
                return order.indexOf(plate);
            }
        }
        return 0;
    }

    private static boolean hasMenuItems(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Red") || plate.type().equals("Blue")) { // this way customer is profited because we include in the menu the most expensive plate first
//                System.out.println("Order has red or blue plate");
                return true;
            }
        }
        return false;
    }

    private static int menuItemsPos(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Red") || plate.type().equals("Blue")) {
                return order.indexOf(plate);
            }
        }
        return 0;
    }

    private static void createLunchMenu(List<Plate> order) {

        // Set vars
        double plateNumber;
        int totalPlates = 0;
        int remainingPlates;

        // Check if customer order does not have soup or any of the dishes
        if (hasSoup(order)) {
            Plate plate = order.get(soupPos(order));
            order.remove(plate);
            if (plate.amount() > 1) {
                plate.setAmount(1);
                order.add(plate);
            }
        } else if (hasMenuItems(order)) {
            Plate plate = order.get(menuItemsPos(order));
            order.remove(plate);
            if (plate.amount() > 1) {
                plate.setAmount(1);
                order.add(plate);
            }
        }

        List<Plate> updatedMenu = new ArrayList<>(order);
        // Loop through order and remove first four plates
        for (Plate p : updatedMenu) {
            // get number of plates
            plateNumber = p.amount();
            // calculate total plates until now
            totalPlates += plateNumber;
            // calculate remaining plates
            remainingPlates = totalPlates - 4;

            if (remainingPlates <= 0) {
                order.remove(p);
            } else {
                // get plate position in original order
                int pos = order.indexOf(p);
                // remove plate
                order.remove(p);
                // set new amount and add updated plate to order
                p.setAmount(remainingPlates);
                order.add(pos, p);
                break;
            }

        }

    }

    // TODO: try and calculate optimized costs if I have the time. Right now, we can't calculate combined menus from different or the same customer.
    private static void createCombinedLunchMenu(List<Plate> order) {

        /** Algorithm to calculate combined menus
         *
         * To calculate combined cost, first scan combined order for eligible menus.

            Calculate eligible menus as follows:
                Total Plates mod 5 = number of possible menus

         * Set counter to possible menus
         * Scan order for soups
                If number of soups is equal to possible menus, begin removing plates from order
                else remove soup, reduce counter and scan for red or blue. Do the same as before.
                Continue when counter is zero
        * Remove plates from menu equal to 4 * possible menus. Eg if we can get 2 lm, we must remove 8 plates from the menu
            and so on.
        * This can be combined to createLunchMenu method.
        */
    }

    private static void combinedOrderCost(List<Plate> order, int guestNumber, String day, Double time) {
    }
}
