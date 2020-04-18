public class InvoiceSummary {
    public int totalRides;
    public double totalFare;
    public double averageFare;

    public InvoiceSummary(int totalRides, double totalFare) {
        this.totalRides = totalRides;
        this.totalFare = totalFare;
        this.averageFare = this.totalFare / this.totalRides;
    }

    @Override
    public boolean equals(Object enhancedInvoice) {
        if (this == enhancedInvoice) return true;
        if (enhancedInvoice == null || getClass() != enhancedInvoice.getClass()) return false;
        InvoiceSummary that = (InvoiceSummary) enhancedInvoice;
        return totalRides == that.totalRides &&
                Double.compare(that.totalFare, totalFare) == 0 &&
                Double.compare(that.averageFare, averageFare) == 0;
    }
}