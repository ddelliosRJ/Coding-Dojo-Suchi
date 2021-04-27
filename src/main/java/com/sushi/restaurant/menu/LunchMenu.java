package com.sushi.restaurant.menu;

import com.sushi.restaurant.Plate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LunchMenu extends Menu {

    public static Predicate<String> LUNCH_MENU_PREDICATE = type -> type.equals("Soup") || type.equals("Red") || type.equals("Blue");

    private final int DISCOUNT_ITEMS = 4;

    private final String lunchPlate;

    public LunchMenu(Map<String, Plate> order, int guestNumber, String discountPlate) {
        super(order, guestNumber);
        this.lunchPlate = discountPlate;
    }


    @Override
    protected List<Plate> createMenu() {

           Plate plate = getOrder().get(lunchPlate);

           plate.setAmount(plate.amount() - 1);

           getOrder().put(lunchPlate, plate);

           Map<String, Plate> discountedOrder = getOrder().entrySet()
                   .stream()
                   .filter(entry->!entry.getKey().equals(lunchPlate))
                   .limit(DISCOUNT_ITEMS)
                   .map(entry->{
                       Plate toDiscount =  entry.getValue();

                       toDiscount.setAmount(toDiscount.amount()-1);
                       return toDiscount;
                  }).collect(Collectors.toMap(Plate::type, Function.identity()));


           getOrder().forEach(discountedOrder::putIfAbsent);

            return new ArrayList<>(discountedOrder.values());

    }

    @Override
    public void calculateCost(){

        double cost = cost();

        System.out.println("⚫ TOTAL COST: Guest " + getGuestNumber() + " got lunch menu and " + getTotalPlates() + " plate(s) and paid for his order " + cost + "fr.\n");

    }


    @Override
    protected double getPrice() {
        return 8.5;
    }
}
