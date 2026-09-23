/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Thando
 */
public abstract class RescueCase 
    implements RescueOperations { 
    private String rescueCaseId;
    private String animalName;
    private String species;
    private String rescueLocation;
    private String assignedRanger;
    private int numberOfRescueDays;
    private double dailyCareCost;
    private String currentRescueStatus;

    public RescueCase(String rescueCaseId, String animalName, String species, 
                      String rescueLocation, String assignedRanger, 
                      int numberOfRescueDays, double dailyCareCost) {
        this.rescueCaseId = rescueCaseId;
        this.animalName = animalName;
        this.species = species;
        this.rescueLocation = rescueLocation;
        this.assignedRanger = assignedRanger;
        this.numberOfRescueDays = numberOfRescueDays;
        this.dailyCareCost = dailyCareCost;
        this.currentRescueStatus = "Reported";
    }

    // Abstract methods for children - FOR POLYMORPHISM
    public abstract double calculateTotalRescueCost();
    public abstract String determineRescuePriority();
    public abstract void displaySpecificInfo();

    // Operations from Interface
    @Override
    public void startRescue() { this.currentRescueStatus = "Rescue in Progress"; }
    @Override
    public void completeRescue() { this.currentRescueStatus = "Completed"; }

    @Override
    public String generateSummary() {
        return "Case ID: " + rescueCaseId + " | Type: " + this.getClass().getSimpleName() +
               " | Species: " + species + " | Ranger: " + assignedRanger +
               " | Priority: " + determineRescuePriority() + " | Status: " + currentRescueStatus +
               " | Total Cost: R" + String.format("%.2f", calculateTotalRescueCost());
    }

    // Getters and Setters - ENCAPSULATION
    public String getRescueCaseId() { return rescueCaseId; }
    public String getSpecies() { return species; }
    public String getRescueLocation() { return rescueLocation; }
    public String getAssignedRanger() { return assignedRanger; }
    public int getNumberOfRescueDays() { return numberOfRescueDays; }
    public double getDailyCareCost() { return dailyCareCost; }
    public String getCurrentRescueStatus() { return currentRescueStatus; }
    public void setCurrentRescueStatus(String status) { this.currentRescueStatus = status; }
    public String getAnimalName() { return animalName; }
}
