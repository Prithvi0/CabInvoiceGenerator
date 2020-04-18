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
}