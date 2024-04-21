package lib;

public class TaxFunction {
	
	private static final double TAX_RATE = 0.05;
    private static final int BASIC_TAX_FREE_INCOME = 54000000; // Rp 54.000.000
    private static final int MARRIED_TAX_FREE_ADDITION = 4500000; // Rp 4.500.000
    private static final int CHILD_TAX_FREE_ADDITION = 1500000; // Rp 1.500.000 per anak

	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
        int annualIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = calculateTaxableIncome(annualIncome, deductible, isMarried, numberOfChildren);
        int tax = (int) Math.round(TAX_RATE * taxableIncome);

        return tax < 0 ? 0 : tax;
    }

    private static int calculateTaxableIncome(int annualIncome, int deductible, boolean isMarried, int numberOfChildren) {
        int taxFreeIncome = BASIC_TAX_FREE_INCOME;

        if (isMarried) {
            taxFreeIncome += MARRIED_TAX_FREE_ADDITION;
        }

        // Batasi jumlah anak hingga 3 anak
        numberOfChildren = Math.min(numberOfChildren, 3);
        taxFreeIncome += CHILD_TAX_FREE_ADDITION * numberOfChildren;

        return annualIncome - deductible - taxFreeIncome;
    }
	
}
