public class CalculateFare {
    private static final int COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final int MINIMUM_FARE = 5;
    private double totalFare;

    //  DEFAULT CONSTRUCTOR
    public CalculateFare() {
    }

    //  METHOD TO CALCULATE MINIMUM FARE FOR NORMAL RIDE
    public double calculateTotalFare(double distance, int time) {
        double totalRideFare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        if (totalRideFare < MINIMUM_FARE) {
            return MINIMUM_FARE;
        }
        return totalRideFare;
    }

    //  METHOD TO CALCULATE MINIMUM FARE FOR MULTIPLE NORMAL RIDES
    public double calculateTotalFare(Ride[] rides) {
        for (Ride ride : rides)
            totalFare += this.calculateTotalFare(ride.distance, ride.time);
        return totalFare;
    }
}