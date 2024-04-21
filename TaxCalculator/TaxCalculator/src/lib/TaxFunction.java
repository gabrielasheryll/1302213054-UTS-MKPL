package lib;

public class TaxFunction {
	
	private static final double TAX_RATE = 0.05;
    private static final int BASIC_TAX_FREE_INCOME = 54000000; // Rp 54.000.000
    private static final int MARRIED_TAX_FREE_ADDITION = 4500000; // Rp 4.500.000
    private static final int CHILD_TAX_FREE_ADDITION = 1500000; // Rp 1.500.000 per anak

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		if (isMarried) {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - (54000000 + 4500000 + (numberOfChildren * 1500000))));
		}else {
			tax = (int) Math.round(0.05 * (((monthlySalary + otherMonthlyIncome) * numberOfMonthWorking) - deductible - 54000000));
		}
		
		if (tax < 0) {
			return 0;
		}else {
			return tax;
		}
			 
	}
	
}
