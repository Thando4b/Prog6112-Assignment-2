/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thando
 */
public class InjuredAnimalRescue extends RescueCase{
    private String injuryDescription;
    private double veterinaryTreatmentCost;
    private boolean surgeryRequired;

    public InjuredAnimalRescue(String id, String name, String species, String location, 
                               String ranger, int days, double dailyCost,
                               String injuryDesc, double vetCost, boolean surgery) {
        super(id, name, species, location, ranger, days, dailyCost);
        this.injuryDescription = injuryDesc;
        this.veterinaryTreatmentCost = vetCost;
        this.surgeryRequired = surgery;
    }

    @Override
    public double calculateTotalRescueCost() {
        double base = (getNumberOfRescueDays() * getDailyCareCost()) + veterinaryTreatmentCost;
        if (surgeryRequired) base += 5000;
        return base;
    }

    @Override
    public String determineRescuePriority() {
        return surgeryRequired ? "Critical" : "High";
    }

    @Override
    public void displaySpecificInfo() {
        System.out.println("Injury: " + injuryDescription + " | Surgery: " + surgeryRequired + " | Vet Cost: R" + veterinaryTreatmentCost);
    }
}
    

