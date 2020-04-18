import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CabInvoiceServiceTest {
    //  OBJECT TO ACCESS THROUGHOUT THE PROGRAM
    CalculateFare calculateFare;

    //   METHOD TO RUN BEFORE THE TEST CASES
    @Before
    public void cabInvoiceService() {
            calculateFare = new CalculateFare();
        }

    //  TEST CASES FOR NORMAL RIDE FARES
    @Test
    public void givenDistanceAndTime_WhenInvoiceGenerator_ShouldReturnTotalJourneyFare() {
        double distance = 2.0;
        int time = 5;
        double totalFare = calculateFare.calculateTotalFare(distance, time);
        Assert.assertEquals(25, totalFare, 0.0);
    }

    @Test
    public void givenDistanceAndTime_WhenInvoiceGenerator_ShouldReturnMinimumJourneyFare() {
        double distance = 0.1;
        int time = 1;
        double totalFare = calculateFare.calculateTotalFare(distance, time);
        Assert.assertEquals(5, totalFare,0.0);
    }

    //  TEST CASE FOR MULTIPLE NORMAL RIDE FARES
    @Test
    public void givenDistanceAndTime_WhenInvoiceGenerator_ShouldReturnMultipleJourneyFare() {

        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(0.1, 1)
        };
        //  USING ENHANCED INVOICE GENERATOR
        InvoiceSummary summary = calculateFare.calculateTotalFare(rides);
        InvoiceSummary expectedInvoiceSummary = new InvoiceSummary(2, 30);
        Assert.assertEquals(summary, expectedInvoiceSummary);
    }

    //  TEST CASES USING ENHANCED INVOICE GENERATOR
    @Test
    public void givenMultipleRides_WhenInvoiceGenerator_ShouldReturnTotalRides() {
        Ride[] rides = {
                new Ride(1.0, 3),
                new Ride(2.0, 2),
                new Ride(3.0, 1)
        };
        InvoiceSummary summary = calculateFare.calculateTotalFare(rides);
        Assert.assertEquals(3, summary.totalRides, 0.0);
    }

    @Test
    public void givenMultipleRides_WhenInvoiceGenerator_ShouldReturnTotalFare() {
        Ride[] rides = {new Ride(1.0, 2),
                new Ride(2.0, 3)};
        InvoiceSummary summary = calculateFare.calculateTotalFare(rides);
        Assert.assertEquals(35, summary.totalFare, 0.0);
    }

    @Test
    public void givenMultipleRides_WhenInvoiceGenerator_ShouldReturnAverageFare() {
        Ride[] rides = {
                new Ride(2.0, 5),
                new Ride(4.0, 10),
                new Ride(2.0, 10)
        };
        InvoiceSummary summary = calculateFare.calculateTotalFare(rides);
        Assert.assertEquals(35, summary.averageFare,0.0);
    }

    //  TEST CASES FOR INVOICE SERVICE
    @Test
    public void givenUserIdAndRides_WhenInvoiceGenerator_ShouldReturnInvoiceServiceSummary() {
        String userId = "hell0";
        Ride[] rides = {
                new Ride(2.5, 5),
                new Ride(2.5, 10),
                new Ride(3.5, 15)
        };
        calculateFare.addRides(userId,rides);
        InvoiceSummary summary = calculateFare.getInvoiceSummary(userId);
        InvoiceSummary expectedSummary = new InvoiceSummary(3,115);
        Assert.assertEquals(expectedSummary,summary);
    }
}