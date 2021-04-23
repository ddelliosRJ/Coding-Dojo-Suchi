import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class SushiRestaurant {

    public static void main(String[] args) {

        // Define available plates from conveyor belt
        HashMap<String, Double> plates = new HashMap<>();
        plates.put("Grey", 4.95);
        plates.put("Green", 3.95);
        plates.put("Yellow", 4.95);
        plates.put("Red", 1.95);
        plates.put("Blue", 0.95);

        // Alternative
        double Grey = 4.95;
        double Green = 3.95;
        double Yellow = 2.95;
        double Red = 1.95;
        double Blue = 0.95;
        double Soup = 2.50;

        // Also add order aggregation for optimal lunch menu discount, for group of customers

        // Order map for every customer
        LinkedHashMap<Double, Integer> order1 = new LinkedHashMap<>(); // this can be an object
        LinkedHashMap<Double, Integer> order2 = new LinkedHashMap<>();
        LinkedHashMap<Double, Integer> order3 = new LinkedHashMap<>();
        LinkedHashMap<Double, Integer> order4 = new LinkedHashMap<>();
        LinkedHashMap<Double, Integer> order5 = new LinkedHashMap<>();
        LinkedHashMap<Double, Integer> order6 = new LinkedHashMap<>();

        // Example 1 - Guest got 5 Blue plates //TODO: this can also be made using UserInput
        order1.put(Blue, 5);
        orderCost(order1, 1, null);

        // Example 2 - Guest got 5 Grey plates
        order2.put(Grey, 5);
        orderCost(order2, 2, null);

        // Example 3 - Guest got 1 of each plate
        order3.put(Grey, 1);
        order3.put(Green, 1);
        order3.put(Yellow, 1);
        order3.put(Red, 1);
        order3.put(Blue, 1);
        orderCost(order3, 3, null);

        // Example 4 - Guest got Lunch menu plus two plates
        order4.put(Soup, 1);
        order4.put(Grey, 2);
        order4.put(Green, 2);
        order4.put(Blue, 2);
        orderCost(order4, 4, "Monday");

        // Example 5 - Guest got Lunch menu plus 3 plates
        order5.put(Soup, 1);
        order5.put(Grey, 2);
        order5.put(Green, 3);
        order5.put(Red, 2);
        orderCost(order5, 5, "Tuesday");

        // Example 6 - Guest got Lunch menu plus  plates
        order6.put(Soup, 1);
        order6.put(Grey, 2);
        order6.put(Green, 3);
        order6.put(Red, 2);
        orderCost(order6, 5, "Saturday");
    }

    private static void orderCost(LinkedHashMap<Double, Integer> order, int guestNumb, String Day) {

        int plateNumber;
        double plateCost;
        double cost;
        int res;
        int old = 0;
        double lunchMenu = 0;
        int totalPlates = 0;
        double totalCost = 0;
        int resPlates;
        String[] Days = new String[]{"Saturday", "Sunday"};
<<<<<<< HEAD
        // TODO: do the same with time
=======
        // TODO: do the same with time. Create a method to deal with it
>>>>>>> Add cost calculation and lunch menu.

        // Continue as usual if day is Weekend and if time is not between lunch hours
        if (Arrays.asList(Days).contains(Day)) {
            System.out.println("Sorry, lunch menu is not available for " + Day);
        } else if (Day != null) { // set lunch menu price
            lunchMenu = 8.50;
        }

        // Add first four plates plus soup with a fixed amount and iterate from the fifth object if lunch if eligible.
        for (double key : order.keySet()) { // iterate through order
            plateCost = key; // get price
            plateNumber = order.get(key); // get number of plates
            totalPlates += plateNumber; // calculate total plates until now
            resPlates = totalPlates - 5; // calc remaining plates after menu

            if (lunchMenu != 0) { // if menu is set

                if ((resPlates > 0)) { // if number of plates is past the fixed menu plate number(5)

                    res = resPlates - old; // get remaining plates
                    cost = (res * plateCost); // calculate costs
                    totalCost += cost;
                    old = resPlates; // set remaining plates to last value

                } else {

                    cost = lunchMenu;
                    totalCost = lunchMenu;

                }

            } else {

                cost = (plateNumber * plateCost);
                totalCost += cost;

            }

            System.out.println("Guest " + guestNumb + " got " + plateNumber + " plate(s) and paid for his order " + cost + "fr.");

        }
        double tc = Math.round(totalCost * 100.0) / 100.0;
        System.out.println("Guest " + guestNumb + " got " + totalPlates + " plate(s) and paid for his order " + tc + "fr.");
    }

}
