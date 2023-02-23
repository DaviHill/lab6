public class Transportation {
    private String type;
    private String vehicle;
    private boolean rental;
    private double price;
    private double avgSpeed;
    private int passengers;

    Transportation(String type, String vehicle, boolean rental, double price, double avgSpeed, int passengers) {
        this.type = type;
        this.vehicle = vehicle;
        this.rental = rental;
        this.price = price;
        this.avgSpeed = avgSpeed;
        this.passengers = passengers;
    }

    public void setType(String type) {this.type = type;}
    public void setVehicle(String vehicle) {this.vehicle = vehicle;}
    public void setRental(boolean rental) {this.rental = rental;}
    public void setPrice(double price) {this.price = price;}
    public void setAvgSpeed(double speed) {this.avgSpeed = speed;}
    public void setPassengers(int passengers) {this.passengers = passengers;}

    public String getType() {return this.type;}
    public String getVehicle() {return this.vehicle;}
    public boolean getRental() {return this.rental;}
    public double getPrice() {return this.price;}
    public double getAvgSpeed() {return this.avgSpeed;}
    public int getPassengers() {return this.passengers;}

    public double totalPrice(int rate) {
        return price * rate;
    }

    @Override
    public String toString() {
        // rate is either per "day" or per "person" depending on if transportation is rental
        String rate;
        // purchase is "rental" if rental, "ticket" otherwise
        String purchase;
        if (rental) {
            rate = "day";
            purchase = "rental";
        }
        else {
            rate = "person";
            purchase = "ticket";
        }
        return String.format("%-10s %-20s %-15s %-20s %-15s %-2d\n",
                type,
                vehicle,
                purchase,
                String.format("$%.2f/%s", price, rate),
                String.format("%.0f mph", avgSpeed),
                passengers);
    }
}