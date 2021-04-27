package com.sushi.restaurant.menu;

import com.sushi.restaurant.Plate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MenuFactory {


    public static Menu createMenu(String day, Double time, Map<String, Plate> order, int guestNumber){

        if (day != null || time != null) {
            if (isLunch(day, time)){
                Optional<String> optional = order.keySet().stream().filter(LunchMenu.LUNCH_MENU_PREDICATE).findAny();
                if(optional.isPresent()){
                    return new LunchMenu(order, guestNumber, optional.get());
                }
            }
        }

        return new PlainMenu(order, guestNumber);


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


}
