package com.sushi.restaurant;

import java.util.*;

//import static com.sushi.restaurant.CustomerOrder.orderCost;

public class SushiRestaurant {

    // Define available plates from conveyor belt as static variables
    private static final double Grey = 4.95;
    private static final double Green = 3.95;
    private static final double Yellow = 2.95;
    private static final double Red = 1.95;
    private static final double Blue = 0.95;
    private static final double Soup = 2.50;

    private static double price;

    public static void main(String[] args) {

        // TODO: user input is not very optimized, since it will not catch weird cases, but it does the job for now
        // -------USER INPUT TO CREATE ORDERS -----------------
        Scanner scanner = new Scanner(System.in);
        // Welcome to our sushi restaurant
        // -- user input
        System.out.println("***********************************");
        System.out.println("* Welcome to our Sushi Restaurant *");
        System.out.println("***********************************\n");

        System.out.println("Do you want to enter order or go through ready examples? (type order or examples)");
        String choice = scanner.nextLine();
        if (choice.equals("order")) {

            System.out.println("What day of the week is it? (select Monday to Sunday)");
            String weekday = scanner.nextLine();

            System.out.println("What hour is it? (select from 00.00 to 23.59 - fmt hh.mm");
            Double hour = scanner.nextDouble();

            System.out.println("How many customers are we going to serve today?");
            int customerNumber = scanner.nextInt();

            // Iterate through all customer inputs and create total order
            List<Plate> totalOrder = new ArrayList<>();
            for (int i = 1; i <= customerNumber; i++) {

                System.out.println("Hint: At any time, type 'done' to finish the order");
                List<Plate> ord = new ArrayList<>();
                System.out.println("Please place order for customer " + i);
                System.out.println("Select type and amount of plates (type amount):");
                while (true) {
                    String type = scanner.next();
                    if (type.equals("done")) {
                        break;
                    }
                    int amount = scanner.nextInt();
                    ord.add(new Plate(plateType(type), amount));
                }
                // Print customer order cost before optimizing discounts
                CustomerOrder co = new CustomerOrder(ord, i, weekday, hour);
                co.orderCost();
                // Add all customer orders to a new list and use that to calculate discounts
                totalOrder.addAll(ord);

            }
        } else {


            // -------USER STORY 1 --- orders without Lunch menu
            System.out.println("----------------------------------------");
            System.out.println("USER STORY 1 - orders without lunch menu\n");
            System.out.println("----------------------------------------");


            // Create order for first 3 customers
            List<Plate> order1 = new ArrayList<>();
            List<Plate> order2 = new ArrayList<>();
            List<Plate> order3 = new ArrayList<>();

            // Example 1 - Guest got 5 Blue plates
            order1.add(new Plate(Blue, 5));
            CustomerOrder co1 = new CustomerOrder(order1, 1, null, null);
            co1.orderCost();

            // Example 2 - Guest got 5 Grey plates
            order2.add(new Plate(Grey, 5));
            CustomerOrder co2 = new CustomerOrder(order2, 2, null, null);
            co2.orderCost();

            // Example 3 - Guest got 1 of each plate
            order3.add(new Plate(Grey, 1));
            order3.add(new Plate(Green, 1));
            order3.add(new Plate(Yellow, 1));
            order3.add(new Plate(Red, 1));
            order3.add(new Plate(Blue, 1));

            CustomerOrder co3 = new CustomerOrder(order3, 3, null, null);
            co3.orderCost();

            // -------USER STORY 2 --- orders with Lunch menu, with Soup
            System.out.println("-------------------------------------------");
            System.out.println("USER STORY 2 - orders Lunch menu, with Soup");
            System.out.println("-------------------------------------------\n");

            // Create order for first 4 customers
            List<Plate> order4 = new ArrayList<>();
            List<Plate> order5 = new ArrayList<>();
            List<Plate> order6 = new ArrayList<>();
            List<Plate> order7 = new ArrayList<>();

            // Example 4 - Guest got Lunch menu plus two plates
            order4.add(new Plate(Soup, 1));
            order4.add(new Plate(Grey, 2));
            order4.add(new Plate(Green, 2));
            order4.add(new Plate(Blue, 2));

            CustomerOrder co4 = new CustomerOrder(order4, 4, "Monday", 11.00);
            co4.orderCost();

            // Example 5 - Guest got Lunch menu plus 3 plates
            order5.add(new Plate(Soup, 1));
            order5.add(new Plate(Grey, 2));
            order5.add(new Plate(Green, 3));
            order5.add(new Plate(Red, 2));

            CustomerOrder co5 = new CustomerOrder(order5, 5, "Tuesday", 13.00);
            co5.orderCost();

            // Example 6 - Guest could not get Lunch menu because it was Weekend
            order6.add(new Plate(Soup, 1));
            order6.add(new Plate(Grey, 2));
            order6.add(new Plate(Green, 3));
            order6.add(new Plate(Red, 2));

            CustomerOrder co6 = new CustomerOrder(order6, 6, "Saturday", 15.00);
            co6.orderCost();

            // Example 7 - Guest could not get Lunch menu because he/she ordered before menu times
            order7.add(new Plate(Soup, 1));
            order7.add(new Plate(Grey, 2));
            order7.add(new Plate(Green, 3));
            order7.add(new Plate(Red, 2));

            CustomerOrder co7 = new CustomerOrder(order7, 7, "Friday", 9.00);
            co7.orderCost();

            // -------USER STORY 3 --- orders with Lunch menu, with or without Soup
            System.out.println("----------------------------------------------------------");
            System.out.println("USER STORY 3 -orders with Lunch menu, with or without Soup");
            System.out.println("----------------------------------------------------------\n");

            List<Plate> order8 = new ArrayList<>();
            List<Plate> order9 = new ArrayList<>();

            // Example 8 - Guest got Lunch menu plus 2 plates
            order8.add(new Plate(Yellow, 1));
            order8.add(new Plate(Grey, 2));
            order8.add(new Plate(Green, 2));
            order8.add(new Plate(Blue, 2));

            CustomerOrder co8 = new CustomerOrder(order8, 8, "Friday", 13.00);
            co8.orderCost();

            // Example 9 - Guest got Lunch menu plus 3 plates
            order9.add(new Plate(Red, 1));
            order9.add(new Plate(Grey, 2));
            order9.add(new Plate(Green, 2));
            order9.add(new Plate(Yellow, 3));

            CustomerOrder co9 = new CustomerOrder(order9, 9, "Thursday", 16.59);
            co9.orderCost();

        }
    }

    private static double plateType(String plate) {
        if ("Grey".equals(plate)) {
            price = Grey;
        } else if ("Green".equals(plate)) {
            price = Green;
        } else if ("Yellow".equals(plate)) {
            price = Yellow;
        } else if ("Red".equals(plate)) {
            price = Red;
        } else if ("Blue".equals(plate)) {
            price = Blue;
        } else if ("Soup".equals(plate)) {
            price = Soup;
        }
        return price;
    }
}
