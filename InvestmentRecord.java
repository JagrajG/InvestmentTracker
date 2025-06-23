public class InvestmentRecord {
    public String metal;
    public double amount;
    public double purchasePrice;
    public double pricePerOz;
    public double spotPrice;
    public double profitLoss;

    public InvestmentRecord(String metal, double amount, double purchasePrice, double pricePerOz, double spotPrice, double profitLoss) {
        this.metal = metal;
        this.amount = amount;
        this.purchasePrice = purchasePrice;
        this.pricePerOz = pricePerOz;
        this.spotPrice = spotPrice;
        this.profitLoss = profitLoss;
    }
}
