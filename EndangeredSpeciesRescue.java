/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thando
 */
public class EndangeredSpeciesRescue extends RescueCase {
   private String conservationClassification;
    private double securityCost;
    private boolean specialistTeamRequired;

    public EndangeredSpeciesRescue(String id, String name, String species, 
            String location, String ranger, int days, double dailyCost, 
            String classification, double secCost, boolean specialist) {
        super(id, name, species, location, ranger, days, dailyCost);
        this.conservationClassification = classification;
        this.securityCost = secCost;
        this.specialistTeamRequired = specialist;
    }

    @Override
    public double calculateTotalRescueCost() {
        double base = (getNumberOfRescueDays() * getDailyCareCost()) + securityCost;
        if (specialistTeamRequired) {
            base += 8000;
        }
        return base;
    }

    @Override
    public String determineRescuePriority() {
        if (conservationClassification.equalsIgnoreCase("Critically Endangered")) {
            return "Critical";
        } else if (conservationClassification.equalsIgnoreCase("Endangered")) {
            return "High";
        } else {
            return "Medium";
        }
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Classification: " + conservationClassification + 
                " | Security Cost: R" + securityCost + 
                " | Specialist Team: " + specialistTeamRequired);
    }
}
