package lib;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

public class Employee {

    private String employeeId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String address;

    private LocalDate joinDate;  // Gabungkan yearJoined, monthJoined, dayJoined

    private boolean isForeigner;
    private boolean gender; // true = Laki-laki, false = Perempuan

    private int monthlySalary;
    private int otherMonthlyIncome;
    private int annualDeductible;

    private String spouseName;
    private String spouseIdNumber;

    private List<String> childNames;
    private List<String> childIdNumbers;

	private int monthWorkingInYear; 

    // Menggunakan parameter object
    public Employee(EmployeeParams params) {
        this.employeeId = params.employeeId;
        this.firstName = params.firstName;
        this.lastName = params.lastName;
        this.idNumber = params.idNumber;
        this.address = params.address;
        this.joinDate = params.joinDate;
        this.isForeigner = params.isForeigner;
        this.gender = params.gender;

        childNames = new LinkedList<>();
        childIdNumbers = new LinkedList<>();
    }

    // Definisi kelas EmployeeParams untuk parameter object
    public static class EmployeeParams {
        private String employeeId;
        private String firstName;
        private String lastName;
        private String idNumber;
        private String address;
        private LocalDate joinDate;
        private boolean isForeigner;
        private boolean gender;

        public EmployeeParams setEmployeeId(String employeeId) {
            this.employeeId = employeeId;
            return this;
        }

        public EmployeeParams setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public EmployeeParams setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public EmployeeParams setIdNumber(String idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public EmployeeParams setAddress(String address) {
            this.address = address;
            return this;
        }

        public EmployeeParams setJoinDate(LocalDate joinDate) {
            this.joinDate = joinDate;
            return this;
        }

        public EmployeeParams setIsForeigner(boolean isForeigner) {
            this.isForeigner = isForeigner;
            return this;
        }

        public EmployeeParams setGender(boolean gender) {
            this.gender = gender;
            return this;
        }
    }

    public void setMonthlySalary(int grade) {
        int baseSalary = 0;
        if (grade == 1) {
            baseSalary = 3000000;
        } else if (grade == 2) {
            baseSalary = 5000000;
        } else if (grade == 3) {
            baseSalary = 7000000;
        }

        calculateForeignerSalaryIncrease(baseSalary);
    }

    private void calculateForeignerSalaryIncrease(int baseSalary) {
        if (isForeigner) {
            monthlySalary = (int) (baseSalary * 1.5);
        } else {
            monthlySalary = baseSalary;
        }
    }

    public void setAnnualDeductible(int deductible) {
        this.annualDeductible = deductible;
    }

    public void setAdditionalIncome(int income) {
        this.otherMonthlyIncome = income;
    }

    public void setSpouse(String spouseName, String spouseIdNumber) {
        this.spouseName = spouseName;
        this.spouseIdNumber = spouseIdNumber;
    }

    public void addChild(String childName, String childIdNumber) {
        childNames.add(childName);
        childIdNumbers.add(childIdNumber);
    }

    public int getAnnualIncomeTax() {
        // Menghitung berapa lama pegawai bekerja dalam setahun ini, jika pegawai sudah bekerja dari tahun sebelumnya maka otomatis dianggap 12 bulan.
        LocalDate date = LocalDate.now();

        if (date.getYear() == joinDate.getYear()) {
            monthWorkingInYear = date.getMonthValue() - joinDate.getMonthValue();
        } else {
            monthWorkingInYear = 12;
        }

        return TaxFunction.calculateTax(monthlySalary, otherMonthlyIncome, monthWorkingInYear, annualDeductible, spouseIdNumber.equals(""), childIdNumbers.size());
    }
}
