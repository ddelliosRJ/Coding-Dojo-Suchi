package com.sushi.restaurant;

import java.util.*;

public class SushiRestaurant {

    // Put some colour in our lives
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_WHITE = "\u001B[37m";

    // Define available plates from conveyor belt as static variables
    private static final double GREY = 4.95;
    private static final double GREEN = 3.95;
    private static final double YELLOW = 2.95;
    private static final double RED = 1.95;
    private static final double BLUE = 0.95;
    private static final double SOUP = 2.50;

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

        System.out.println("Available plates for today:\n");
        System.out.println("\t" + ANSI_WHITE + "1. Grey\t\t - " + GREY + "fr" + ANSI_RESET);
        System.out.println("\t" + ANSI_GREEN + "2. Green\t - " + GREEN + "fr" + ANSI_RESET);
        System.out.println("\t" + ANSI_YELLOW + "3. Yellow\t - " + YELLOW + "fr" + ANSI_RESET);
        System.out.println("\t" + ANSI_RED + "4. Red\t\t - " + RED + "fr" + ANSI_RESET);
        System.out.println("\t" + ANSI_BLUE + "5. Blue\t\t - " + BLUE + "fr" + ANSI_RESET);
        System.out.println("\t" + ANSI_PURPLE + "6. Soup\t\t - " + SOUP + "fr" + ANSI_RESET + "\n");
        System.out.println("Lunch menu is also available from Monday to Friday, 11.00 to 16.59!");
        System.out.println("-------------------------------------------------------------------");
        System.out.println("You can get:\n");
        System.out.println(" - 1 " + ANSI_PURPLE + "Soup" + ANSI_RESET + " and 4 plates or");
        System.out.println(" - 5 plates with at least 1 " + ANSI_BLUE + "Blue" + ANSI_RESET + " or " + ANSI_RED + "Red" + ANSI_RESET + "\n");
        System.out.println("for a fixed price of only 8.50fr");
        System.out.println("-------------------------------------------------------------------\n");

        System.out.println("Do you want to enter order or go through ready examples? (type 'order' or 'examples')");
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
                List<Plate> plateList = new ArrayList<>();
                System.out.println("Please place order for customer " + i);
                System.out.println("Select type and amount of plates (type 'plate type' 'plate amount'. Example: Yellow 2):");
                while (true) {
                    String type = scanner.next();
                    if ("done".equals(type)) {
                        break;
                    }
                    int amount = scanner.nextInt();
                    plateList.add(new Plate(plateType(type), amount));
                }
                // Print customer order cost before optimizing discounts
                CustomerOrder co = new CustomerOrder(plateList, i, weekday, hour);
                co.orderCost();
                // Add all customer orders to a new list and use that to calculate discounts
                totalOrder.addAll(plateList);

            }
        } else if (choice.equals("examples")) {


            // -------USER STORY 1 --- orders without Lunch menu
            System.out.println("----------------------------------------");
            System.out.println("USER STORY 1 - orders without lunch menu");
            System.out.println("----------------------------------------\n");


            // Create order for 3 customers
            List<Plate> platesList1 = new ArrayList<>();
            List<Plate> platesList2 = new ArrayList<>();
            List<Plate> platesList3 = new ArrayList<>();

            // Example 1 - Guest got 5 Blue plates
            platesList1.add(new Plate(BLUE, 5));
            CustomerOrder co1 = new CustomerOrder(platesList1, 1, null, null);
            co1.orderCost();

            // Example 2 - Guest got 5 Grey plates
            platesList2.add(new Plate(GREY, 5));
            CustomerOrder co2 = new CustomerOrder(platesList2, 2, null, null);
            co2.orderCost();

            // Example 3 - Guest got 1 of each plate
            platesList3.add(new Plate(GREY, 1));
            platesList3.add(new Plate(GREEN, 1));
            platesList3.add(new Plate(YELLOW, 1));
            platesList3.add(new Plate(RED, 1));
            platesList3.add(new Plate(BLUE, 1));

            CustomerOrder co3 = new CustomerOrder(platesList3, 3, null, null);
            co3.orderCost();

            // -------USER STORY 2 --- orders with Lunch menu, with Soup
            System.out.println("-------------------------------------------");
            System.out.println("USER STORY 2 - orders Lunch menu, with Soup");
            System.out.println("-------------------------------------------\n");

            // Create order for 4 customers
            List<Plate> platesList4 = new ArrayList<>();
            List<Plate> platesList5 = new ArrayList<>();
            List<Plate> platesList6 = new ArrayList<>();
            List<Plate> platesList7 = new ArrayList<>();

            // Example 4 - Guest got Lunch menu plus two plates
            platesList4.add(new Plate(SOUP, 1));
            platesList4.add(new Plate(GREY, 2));
            platesList4.add(new Plate(GREEN, 2));
            platesList4.add(new Plate(BLUE, 2));

            CustomerOrder co4 = new CustomerOrder(platesList4, 4, "Monday", 11.00);
            co4.orderCost();

            // Example 5 - Guest got Lunch menu plus 3 plates
            platesList5.add(new Plate(SOUP, 1));
            platesList5.add(new Plate(GREY, 2));
            platesList5.add(new Plate(GREEN, 3));
            platesList5.add(new Plate(RED, 2));

            CustomerOrder co5 = new CustomerOrder(platesList5, 5, "Tuesday", 13.00);
            co5.orderCost();

            // Example 6 - Guest could not get Lunch menu because it was Weekend
            platesList6.add(new Plate(SOUP, 1));
            platesList6.add(new Plate(GREY, 2));
            platesList6.add(new Plate(GREEN, 3));
            platesList6.add(new Plate(RED, 2));

            CustomerOrder co6 = new CustomerOrder(platesList6, 6, "Saturday", 15.00);
            co6.orderCost();

            // Example 7 - Guest could not get Lunch menu because he/she ordered before menu times
            platesList7.add(new Plate(SOUP, 1));
            platesList7.add(new Plate(GREY, 2));
            platesList7.add(new Plate(GREEN, 3));
            platesList7.add(new Plate(RED, 2));

            CustomerOrder co7 = new CustomerOrder(platesList7, 7, "Friday", 9.00);
            co7.orderCost();

            // -------USER STORY 3 --- orders with Lunch menu, with or without Soup
            System.out.println("----------------------------------------------------------");
            System.out.println("USER STORY 3 -orders with Lunch menu, with or without Soup");
            System.out.println("----------------------------------------------------------\n");

            List<Plate> platesList8 = new ArrayList<>();
            List<Plate> platesList9 = new ArrayList<>();

            // Example 8 - Guest got Lunch menu plus 2 plates
            platesList8.add(new Plate(YELLOW, 1));
            platesList8.add(new Plate(GREY, 2));
            platesList8.add(new Plate(GREEN, 2));
            platesList8.add(new Plate(BLUE, 2));

            CustomerOrder co8 = new CustomerOrder(platesList8, 8, "Friday", 13.00);
            co8.orderCost();

            // Example 9 - Guest got Lunch menu plus 3 plates
            platesList9.add(new Plate(RED, 1));
            platesList9.add(new Plate(GREY, 2));
            platesList9.add(new Plate(GREEN, 2));
            platesList9.add(new Plate(YELLOW, 3));

            CustomerOrder co9 = new CustomerOrder(platesList9, 9, "Thursday", 16.59);
            co9.orderCost();

        } else {
            System.out.println("Sorry, I did not understand you!");
        }
    }

    private static double plateType(String plate) {
        if ("Grey".equals(plate)) {
            price = GREY;
        } else if ("Green".equals(plate)) {
            price = GREEN;
        } else if ("Yellow".equals(plate)) {
            price = YELLOW;
        } else if ("Red".equals(plate)) {
            price = RED;
        } else if ("Blue".equals(plate)) {
            price = BLUE;
        } else if ("Soup".equals(plate)) {
            price = SOUP;
        }
        return price;
    }
}
