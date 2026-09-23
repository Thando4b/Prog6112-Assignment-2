/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thando
 */
public class OrphanedAnimalRescue extends RescueCase {
     private int estimatedAgeMonths;
    private double feedingCost;
    private boolean fosterCareRequired;

    public OrphanedAnimalRescue(String id, String name, String species, String location, String ranger, int days, double dailyCost, int age, double feeding, boolean foster) {
        super(id, name, species, location, ranger, days, dailyCost);
        this.estimatedAgeMonths = age;
        this.feedingCost = feeding;
        this.fosterCareRequired = foster;
    }

    @Override
    public double calculateTotalRescueCost() {
        double base = (getNumberOfRescueDays() * getDailyCareCost()) + feedingCost;
        if (fosterCareRequired) base += 2500;
        return base;
    }

    @Override
    public String determineRescuePriority() {
        if (estimatedAgeMonths < 6) return "Critical";
        else if (estimatedAgeMonths < 12) return "High";
        else return "Medium";
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Age: " + estimatedAgeMonths + " months | Feeding Cost: R" + feedingCost + " | Foster: " + fosterCareRequired);
    }
}
