package com.sushi.restaurant;

import com.sushi.restaurant.menu.Menu;
import com.sushi.restaurant.menu.MenuFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CustomerOrder {

    private final Map<String, Plate> order;

    private final int guestNumber;

    private final String day;

    private final Double time;

    public CustomerOrder(Map<String, Plate> order, int guestNumber, String day, Double time) {

        this.order = order;
        this.guestNumber = guestNumber;
        this.day = day;
        this.time = time;
    }


    public void orderCost() {

        Menu discountMenu = MenuFactory.createMenu(day, time, order, guestNumber);

        discountMenu.calculateCost();

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
