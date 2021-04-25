import java.util.*;

public class SushiRestaurant {

    // Define available plates from conveyor belt as static variables
    private static double Grey = 4.95;
    private static double Green = 3.95;
    private static double Yellow = 2.95;
    private static double Red = 1.95;
    private static double Blue = 0.95;
    private static double Soup = 2.50;

    // Set variables
    private static String plateType;
    private static int plateNumber;
    private static double plateCost;
    private static double cost;
    private static double lunchMenu;
    private static int totalPlates;
    private static double totalCost;
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

        System.out.println("What day of the week is it? (select Monday to Sunday)");
        String weekday = scanner.nextLine();

        System.out.println("What hour is it? (select from 00.00 to 23.59 - fmt hh.mm");
        Double hour = scanner.nextDouble();

        System.out.println("How many customers are we going to serve today?");
        int customerNumber = scanner.nextInt();

        // Iterate through all customer inputs and create total order
        List<Plate> totalOrder = new ArrayList<>();
        for (int i = 1; i <= customerNumber; i++) {

            System.out.println("Hint: At any time, type 'stop' to stop the order");
            List<Plate> ord = new ArrayList<>();
            System.out.println("Please place order for customer " + i);
            System.out.println("Select type and amount of plates (type amount):");
            while (true) {
                String type = scanner.next();
                if (type.equals("stop")) {
                    break;
                }
                int amount = scanner.nextInt();
                ord.add(new Plate(plateType(type), amount));
            }
            // Print customer order cost before optimizing discounts
            orderCost(ord, i, weekday, hour);
            // Add all customer orders to a new list and use that to calculate discounts
            totalOrder.addAll(ord);

        }


        // -------USER STORY 1 --- orders without Lunch menu

        // Create order for first 3 customers
        List<Plate> order1 = new ArrayList<>();
        List<Plate> order2 = new ArrayList<>();
        List<Plate> order3 = new ArrayList<>();

        // Example 1 - Guest got 5 Blue plates
        order1.add(new Plate(Blue, 5));

        orderCost(order1, 1, null, null);

        // Example 2 - Guest got 5 Grey plates
        order2.add(new Plate(Grey, 5));

        orderCost(order2, 2, null, null);

        // Example 3 - Guest got 1 of each plate
        order3.add(new Plate(Grey, 1));
        order3.add(new Plate(Green, 1));
        order3.add(new Plate(Yellow, 1));
        order3.add(new Plate(Red, 1));
        order3.add(new Plate(Blue, 1));

        orderCost(order3, 3, null, null);

        // -------USER STORY 2 --- orders with Lunch menu, with Soup

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

        orderCost(order4, 4, "Monday", 11.00);

        // Example 5 - Guest got Lunch menu plus 3 plates
        order5.add(new Plate(Soup, 1));
        order5.add(new Plate(Grey, 2));
        order5.add(new Plate(Green, 3));
        order5.add(new Plate(Red, 2));

        orderCost(order5, 5, "Tuesday", 13.00);

        // Example 6 - Guest could not get Lunch menu because it was Weekend
        order6.add(new Plate(Soup, 1));
        order6.add(new Plate(Grey, 2));
        order6.add(new Plate(Green, 3));
        order6.add(new Plate(Red, 2));

        orderCost(order6, 6, "Saturday", 15.00);

        // Example 7 - Guest could not get Lunch menu because he/she ordered before menu times
        order7.add(new Plate(Soup, 1));
        order7.add(new Plate(Grey, 2));
        order7.add(new Plate(Green, 3));
        order7.add(new Plate(Red, 2));

        orderCost(order7, 7, "Friday", 9.00);

        // -------USER STORY 3 --- orders with Lunch menu, with or without Soup

        List<Plate> order8 = new ArrayList<>();
        List<Plate> order9 = new ArrayList<>();
        List<Plate> order10 = new ArrayList<>();

        // Example 8 - Guest got Lunch menu plus 2 plates
        order8.add(new Plate(Yellow, 1));
        order8.add(new Plate(Grey, 2));
        order8.add(new Plate(Green, 2));
        order8.add(new Plate(Blue, 2));

        orderCost(order8, 8, "Friday", 13.00);

        // Example 9 - Guest got Lunch menu plus 3 plates
        order9.add(new Plate(Red, 1));
        order9.add(new Plate(Grey, 2));
        order9.add(new Plate(Green, 2));
        order9.add(new Plate(Yellow, 3));

        orderCost(order9, 9, "Thursday", 16.59);

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

    private static void orderCost(List<Plate> order, int guestNumber, String day, Double time) {

        lunchMenu = 0;
        totalPlates = 0;
        totalCost = 0;

        // Check if customer selected day and time and if he is eligible for lunch menu
        if (day != null || time != null) {
            if (isLunch(day, time)) {
                lunchMenu = 8.50;
                // Check if customer order has soup or one of the lunch items. Else customer can't get a lunch menu
                if (hasSoup(order) || hasMenuItems(order)) {
                    createLunchMenu(order);
                }
            }
        }

        for (Plate plate : order) {

            // get price
            plateCost = plate.price();
            // get number of plates
            plateNumber = plate.amount();
            // get plate type
            plateType = plate.type();
            // calculate total plates until now
            totalPlates += plateNumber;

            // calculate cost and add to total
            cost = Math.round((plateNumber * plateCost) * 100.0) / 100.0;
            totalCost += cost;

            System.out.println("Guest " + guestNumber + " got " + plateNumber + " " + plateType + " plate(s) and paid " + cost + "fr.");

        }
        double tc = totalCost + lunchMenu;

        if (lunchMenu != 0) {
            System.out.println("Guest " + guestNumber + " got lunch menu and " + totalPlates + " plate(s) and paid for his order " + tc + "fr.\n");
        } else
            System.out.println("Guest " + guestNumber + " got " + totalPlates + " plate(s) and paid for his order " + tc + "fr.\n");
    }

    public static boolean isLunch(String day, double time) {

        // Create list with weekend days
        List<String> weekend = new ArrayList<>(Arrays.asList("Saturday", "Sunday"));

        // Continue as usual if day is Weekend and if time is not between lunch hours
        if (weekend.contains(day)) {

            System.out.println("Sorry, lunch menu is only available from Monday to Friday");
            return false;

        } else if (!isBetweenTime(time)) {

            System.out.println("Sorry, lunch menu is only available from 11:00 to 17:00");
            return false;
        }

        return true;
    }

    public static boolean isBetweenTime(double time) {
        double timeBefore = 17.00;
        double timeAfter = 11.00;
        return time >= timeAfter && time < timeBefore;
    }

    public static boolean hasSoup(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Soup")) {
//                System.out.println("Order has soup");
                return true;
            }
        }
        return false;
    }

    public static int soupPos(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Soup")) {
                return order.indexOf(plate);
            }
        }
        return 0;
    }

    public static boolean hasMenuItems(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Red") || plate.type().equals("Blue")) { // this way customer is profited because we include in the menu the most expensive plate first
//                System.out.println("Order has red or blue plate");
                return true;
            }
        }
        return false;
    }

    public static int menuItemsPos(List<Plate> order) {

        for (Plate plate : order) {
            if (plate.type().equals("Red") || plate.type().equals("Blue")) {
                return order.indexOf(plate);
            }
        }
        return 0;
    }

    public static void createLunchMenu(List<Plate> order) {

        // Set vars
        double pN;
        int tP = 0;
        int rP;

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
            pN = p.amount();
            // calculate total plates until now
            tP += pN;
            // calculate remaining plates
            rP = tP - 4;

            if (rP <= 0) {
                order.remove(p);
            } else {
                // get plate position in original order
                int pos = order.indexOf(p);
                // remove plate
                order.remove(p);
                // set new amount and add updated plate to order
                p.setAmount(rP);
                order.add(pos, p);
                break;
            }

        }

    }

    // TODO: try and calculate optimized costs if I have the time. Right now, we can't calculate combined menus from different or the same customer.
    public static void createCombinedLunchMenu(List<Plate> order) {
    }

    private static void combinedOrderCost(List<Plate> order, int guestNumber, String day, Double time) {
    }

}
