import java.util.Scanner;

public class WTCHarrisTwoThree {
    public static void main(String[] args) {
        // Ask the user what type of transportation they want to use
        // Display table of options for type selected:
        //      cost of transportation, whether purchase is ticket or rental,
        //      averages speed, number of passengers allowed
        // Ask user to select which mode they want to use
        // Display ALL information about selected mode using toString override
        // Ask user to verify that this is the mode they want
        // Ask user how many passengers need to travel using that mode
        // Display the total cost
        // Display travel instructions

        // Get user desired transportation type
        String type;
        do {
            System.out.print("Input desired transportation type (land/air/water): ");
            type = getUserString();
        } while (!isValidType(type));
        Transportation[] modes = getVehicleArray(type);

        // Table header
        System.out.printf("%-10s %-20s %-15s %-20s %-15s %-2s\n", "Type", "Mode", "Purchase", "Price", "Avg. Speed", "Passengers");
        // Table contents
        printTable(modes);

        // Loop for chosen mode verification
        Transportation chosenMode;
        do {
            // Loop for getting user desired transportation mode
            String mode;
            do {
                System.out.print("Input desired mode: ");
                mode = getUserString();
            } while (!isValidMode(mode, modes));

            // Set chosenMode to corresponding object reference
            chosenMode = getVehicle(mode, modes);
            System.out.printf("%s chosen as mode of transportation. Is this correct? (y/n): ", chosenMode.getVehicle());
        } while (!getUserString().startsWith("y"));

        // Loop for getting price multiplier (days or passengers)
        int passengers = 0;
        int days = 0;
        do {
            if (chosenMode.getRental()) {
                System.out.print("How many days would you like to rent?: ");    // Terrible
                days = getUserInt();
            }
            System.out.print("How many passengers need to travel?: ");
            passengers = getUserInt();

            if (passengers > chosenMode.getPassengers()) {
                System.out.print("");
            }
        } while (passengers <= 0 || (chosenMode.getRental()) && days <= 0);

        System.out.printf("\nTotal price: $%.2f\n", chosenMode.totalPrice(passengers)); // Not actually distinguishing between day vs. passenger
    }

    public static boolean isValidType(String type) {
        return (type.equals("land") || type.equals("water") || type.equals("air"));
    }

    public static boolean isValidMode(String mode, Transportation[] vehicles) {
        for (Transportation vehicle : vehicles) {
            if (vehicle.getVehicle().toLowerCase().equals(mode)) {return true;}
        }
        return false;
    }

    public static void printTable(Transportation[] vehicles) {
        for ( Transportation vehicle : vehicles ) {
            System.out.print(vehicle);
        }
        System.out.println();
    }

    public static Transportation getVehicle(String mode, Transportation[] vehicles) {
        for ( Transportation vehicle : vehicles ) {
            if (vehicle.getVehicle().toLowerCase().equals(mode)) {return vehicle;}
        }
        return null;
    }

    public static Transportation[] getVehicleArray(String type) {
        switch (type) {
            case "land":
                return new Transportation[] {new Automobile(), new Bike(), new Bus(), new Train()};
            case "water":
                return new Transportation[] {new Boat(), new Ship(), new Submarine()};
            case "air":
                return new Transportation[] {new Dirigible(), new Helicopter(), new HotAirBalloon(), new Plane()};
            default:
                return null;
        }
    }

    public static String getUserString() {
        Scanner input = new Scanner(System.in);
        return input.nextLine().toLowerCase();
    }

    public static int getUserInt() {
        Scanner input = new Scanner(System.in);
        int userInt = input.nextInt();
        input.nextLine();
        return userInt;
    }
}
