package com.sushi.restaurant.menu;

import com.sushi.restaurant.Plate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PlainMenu extends Menu {


    public PlainMenu(Map<String, Plate> order, int guestNumber) {
        super(order, guestNumber);
    }

    @Override
    public List<Plate> createMenu() {
        return new ArrayList<>(getOrder().values());
    }

    @Override
    protected double getPrice() {
        return 0;
    }
}
