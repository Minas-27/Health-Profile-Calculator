import java.time.LocalDate;
import java.time.Period;

public class HealthProfile {
    private String firstName;
    private String lastName;
    private String gender;
    private int birthMonth;
    private int birthDay;
    private int birthYear;
    private double heightInCentiMeter;
    private double weightInKg;

    // Constructor
    public HealthProfile(String firstName, String lastName, String gender, int birthMonth, int birthDay, int birthYear, double heightInCentiMeter, double weightInKg) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthMonth = birthMonth;
        this.birthDay = birthDay;
        this.birthYear = birthYear;
        this.heightInCentiMeter = heightInCentiMeter;
        this.weightInKg = weightInKg;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getBirthMonth() { return birthMonth; }
    public void setBirthMonth(int birthMonth) { this.birthMonth = birthMonth; }

    public int getBirthDay() { return birthDay; }
    public void setBirthDay(int birthDay) { this.birthDay = birthDay; }

    public int getBirthYear() { return birthYear; }
    public void setBirthYear(int birthYear) { this.birthYear = birthYear; }

    public double getHeightInInches() { return heightInCentiMeter; }
    public void setHeightInInches(double heightInInches) { this.heightInCentiMeter = heightInInches; }

    public double getWeightInPounds() { return weightInKg; }
    public void setWeightInPounds(double weightInPounds) { this.weightInKg = weightInPounds; }

    // Method to calculate age
    public int getAge() {
        LocalDate birthDate = LocalDate.of(birthYear, birthMonth, birthDay);
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthDate, currentDate).getYears();
    }

    // Method to calculate BMI
    public double getBMI() {
        return (weightInKg * 703) / (heightInCentiMeter * heightInCentiMeter);
    }

    // Method to calculate maximum heart rate
    public int getMaxHeartRate() {
        return 220 - getAge();
    }

    // Method to calculate target heart rate range
    public String getTargetHeartRateRange() {
        int maxHeartRate = getMaxHeartRate();
        int lowerBound = (int) (maxHeartRate * 0.5);
        int upperBound = (int) (maxHeartRate * 0.85);
        return lowerBound + " - " + upperBound + " bpm";
    }

    // Method to display BMI chart
    public static void displayBMIChart() {
        System.out.println("\nBMI VALUES:");
        System.out.println("Underweight: less than 18.5");
        System.out.println("Normal:      18.5 - 24.9");
        System.out.println("Overweight:  25 - 29.9");
        System.out.println("Obese:       30 or greater");
    }
}
