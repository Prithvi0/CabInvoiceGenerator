public class CalculateFare {
    private static final int COST_PER_KILOMETER = 10;
    private static final int COST_PER_MINUTE = 1;
    private static final int MINIMUM_FARE = 5;
    private RideRepository rideRepository = new RideRepository();

    //  DEFAULT CONSTRUCTOR
    public CalculateFare() {
    }

    //  METHOD TO CALCULATE MINIMUM FARE FOR NORMAL RIDE
    public double calculateTotalFare(double distance, int time) {
        double totalRideFare = distance * COST_PER_KILOMETER + time * COST_PER_MINUTE;
        return Math.max(totalRideFare, MINIMUM_FARE);
    }

    //  METHOD TO CALCULATE MINIMUM FARE FOR MULTIPLE NORMAL RIDES
    public InvoiceSummary calculateTotalFare(Ride[] rides) {
        double totalFare = 0;
        for (Ride ride : rides)
            totalFare += this.calculateTotalFare(ride.distance, ride.time);
        return new InvoiceSummary(rides.length, totalFare);
    }

    //  METHOD TO GET USER ID AND LIST OF RIDES FROM RIDE REPOSITORY
    public void addRides(String userId, Ride[] rides) {
        rideRepository.addRides(userId,rides);
    }

    //  METHOD TO RETURN INVOICE SERVICE SUMMARY
    public InvoiceSummary getInvoiceSummary(String userId) {
        return this.calculateTotalFare(rideRepository.getRides(userId));
    }
}