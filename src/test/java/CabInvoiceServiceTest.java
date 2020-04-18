import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceServiceTest {
    //  OBJECT TO ACCESS NORMAL AND PREMIUM RIDES
    CalculateFare calculateNormalRideFare;
    CalculateFare calculatePremiumRideFare;

    //  METHOD TO RUN BEFORE THE TEST CASES FOR NORMAL AND PREMIUM RIDES
    @Before
    public void cabInvoiceService() {
        calculateNormalRideFare = new CalculateFare(CalculateFare.RideType.NORMAL);
        calculatePremiumRideFare = new CalculateFare(CalculateFare.RideType.PREMIUM);
    }

    //  TEST CASES FOR NORMAL RIDE FARES
    @Test
    public void givenDistanceAndTimeForNormalRide_WhenInvoiceGenerator_ShouldReturnTotalJourneyFare() {
        double distance = 2.0;
        int time = 5;
        double totalFare = calculateNormalRideFare.calculateTotalFare(distance, time);
        Assert.assertEquals(25, totalFare, 0.0);
    }

    @Test
    public void givenDistanceAndTimeForNormalRide_WhenInvoiceGenerator_ShouldReturnMinimumJourneyFare() {
        double distance = 0.1;
        int time = 1;
        double totalFare = calculateNormalRideFare.calculateTotalFare(distance, time);
        Assert.assertEquals(5, totalFare,0.0);
    }

    //  TEST CASE FOR MULTIPLE NORMAL RIDE FARES
    @Test
    public void givenDistanceAndTimeForNormalRides_WhenInvoiceGenerator_ShouldReturnMultipleJourneyFare() {

        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        //  USING ENHANCED INVOICE GENERATOR
        InvoiceSummary summary = calculateNormalRideFare.calculateTotalFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(summary, expectedInvoiceSummary);
    }

    //  TEST CASES USING ENHANCED INVOICE GENERATOR FOR NORMAL RIDES
    @Test
    public void givenMultipleNormalRides_WhenInvoiceGenerator_ShouldReturnTotalRides() {
        Ride[] rides = {
                new Ride(1.0, 3),
                new Ride(2.0, 2),
                new Ride(3.0, 1)
        };
        InvoiceSummary summary = calculateNormalRideFare.calculateTotalFare(rides);
        Assert.assertEquals(3, summary.totalRides, 0.0);
    }

    @Test
    public void givenMultipleNormalRides_WhenInvoiceGenerator_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(1.0, 2),
                new Ride(2.0, 3)};
        InvoiceSummary summary = calculateNormalRideFare.calculateTotalFare(rides);
        Assert.assertEquals(35, summary.totalFare, 0.0);
    }

    @Test
    public void givenMultipleNormalRides_WhenInvoiceGenerator_ShouldReturnAverageFare() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(4.0, 10),
                new Ride(2.0, 10)
        };
        InvoiceSummary summary = calculateNormalRideFare.calculateTotalFare(rides);
        Assert.assertEquals(35, summary.averageFare,0.0);
    }

    //  TEST CASES FOR INVOICE SERVICE (NORMAL RIDES)
    @Test
    public void givenUserIdAndNormalRides_WhenInvoiceGenerator_ShouldReturnInvoiceServiceSummary() {
        String userId = "hell0";
        Ride[] rides = {
                new Ride(2.5, 5),
                new Ride(2.5, 10),
                new Ride(3.5, 15)
        };
        calculateNormalRideFare.addRides(userId,rides);
        InvoiceSummary summary = calculateNormalRideFare.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,115);
        Assert.assertEquals(expectedSummary,summary);
    }

    //  TEST CASES FOR INVOICE SERVICE (NORMAL RIDES)
    @Test
    public void givenPremiumRides_WhenInvoiceGenerator_ShouldReturnInvoiceServiceSummary() {
        String userId = "w0r1d";
        Ride[] rides = {
                new Ride(2.5, 5),
                new Ride(2.50, 10),
                new Ride(3.5, 15)
        };
        calculatePremiumRideFare.addRides(userId, rides);
        InvoiceSummary summary = calculatePremiumRideFare.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3, 187.5);
        Assert.assertEquals(expectedSummary, summary);
    }
}