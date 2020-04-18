public class CalculateFare {
    private double COST_PER_KILOMETER;
    private int COST_PER_MINUTE;
    private double MINIMUM_FARE;
    private RideRepository rideRepository = new RideRepository();

    //  ENUM FOR RIDE TYPES
    public enum RideType {NORMAL, PREMIUM}

    // COSTS OF RIDE TYPES
    public CalculateFare(CalculateFare.RideType rideType) {
        if (rideType.equals(RideType.NORMAL)) {
            this.COST_PER_KILOMETER = 10;
            this.COST_PER_MINUTE = 1;
            this.MINIMUM_FARE = 5;
        }
        if (rideType.equals(RideType.PREMIUM)) {
            this.COST_PER_KILOMETER = 15;
            this.COST_PER_MINUTE = 2;
            this.MINIMUM_FARE = 20;
        }
    }

    //  DEFAULT CONSTRUCTOR
    public CalculateFare() { }

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