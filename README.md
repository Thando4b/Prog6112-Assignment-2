# Prog6112-Assignment-2
Wild Life SA Rescue Operations Systems
# EndangerdSpeciesRescue
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
# InjuredAnimalRescue
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
# OrphanedAnimalRescue
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
# RescueCase
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
# RescueOperations
public interface RescueOperations {
    void startRescue();
    void completeRescue();
    String generateSummary();  
}
# WildlifeRescueApp
public class WildlifeRescueApp {
    static ArrayList<RescueCase> rescueList = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=========================================");
            System.out.println("WILDLIFE RESCUE OPERATIONS SYSTEM");
            System.out.println("=========================================");
            System.out.println("1. Create Rescue Case\n2. Search Rescue Case\n3. Update Rescue Status\n4. Display All Rescue Cases\n5. Rescue Report\n6. Exit");
            System.out.print("Select an option: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": createCase(); break;
                case "2": searchCase(); break;
                case "3": updateStatus(); break;
                case "4": displayAll(); break;
                case "5": rescueReport(); break;
                case "6": System.exit(0);
                default: System.out.println("Invalid menu selection. Try again.");
            }
        }
    }

    static void createCase() {
        try {
            System.out.print("Rescue Case ID: "); String id = sc.nextLine();
            if (id.isBlank()) { System.out.println("ID cannot be blank"); return; }
            if (!isUniqueId(id)) { System.out.println("ID already exists!"); return; }

            System.out.print("Animal Name: "); String name = sc.nextLine();
            System.out.print("Species: "); String species = sc.nextLine();
            if (species.isBlank()) { System.out.println("Species cannot be blank"); return; }
            System.out.print("Rescue Location: "); String location = sc.nextLine();
            if (location.isBlank()) { System.out.println("Location cannot be blank"); return; }
            System.out.print("Assigned Ranger: "); String ranger = sc.nextLine();
            if (ranger.isBlank()) { System.out.println("Ranger cannot be blank"); return; }
            System.out.print("Number of Rescue Days: "); int days = Integer.parseInt(sc.nextLine());
            System.out.print("Daily Care Cost: "); double daily = Double.parseDouble(sc.nextLine());
            if (days <= 0 || daily <= 0) { System.out.println("Numeric values must be > 0"); return; }

            System.out.println("Select Type: 1-Injured 2-Orphaned 3-Endangered");
            String type = sc.nextLine();

            RescueCase newCase = null;
            if (type.equals("1")) {
                System.out.print("Injury Description: "); String inj = sc.nextLine();
                System.out.print("Vet Cost: "); double vet = Double.parseDouble(sc.nextLine());
                System.out.print("Surgery Required (true/false): "); boolean surg = Boolean.parseBoolean(sc.nextLine());
                newCase = new InjuredAnimalRescue(id, name, species, location, ranger, days, daily, inj, vet, surg);
            } else if (type.equals("2")) {
                System.out.print("Estimated Age Months: "); int age = Integer.parseInt(sc.nextLine());
                System.out.print("Feeding Cost: "); double feed = Double.parseDouble(sc.nextLine());
                System.out.print("Foster Care Required (true/false): "); boolean foster = Boolean.parseBoolean(sc.nextLine());
                newCase = new OrphanedAnimalRescue(id, name, species, location, ranger, days, daily, age, feed, foster);
            } else if (type.equals("3")) {
                System.out.print("Conservation Classification: "); String clas = sc.nextLine();
                System.out.print("Security Cost: "); double sec = Double.parseDouble(sc.nextLine());
                System.out.print("Specialist Team Required (true/false): "); boolean spec = Boolean.parseBoolean(sc.nextLine());
                newCase = new EndangeredSpeciesRescue(id, name, species, location, ranger, days, daily, clas, sec, spec);
            }
            rescueList.add(newCase);
            System.out.println("Rescue Case Created Successfully!");
        } catch (Exception e) { System.out.println("Invalid input: " + e.getMessage()); }
    }

    static boolean isUniqueId(String id) {
        for (RescueCase rc : rescueList) if (rc.getRescueCaseId().equalsIgnoreCase(id)) return false;
        return true;
    }

    static void searchCase() {
        System.out.print("Enter Rescue Case ID to search: "); String id = sc.nextLine();
        for (RescueCase rc : rescueList) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                System.out.println(rc.generateSummary()); rc.displaySpecificInfo(); return;
            }
        }
        System.out.println("Case not found");
    }

    static void updateStatus() {
        System.out.print("Enter Case ID to update: "); String id = sc.nextLine();
        for (RescueCase rc : rescueList) {
            if (rc.getRescueCaseId().equalsIgnoreCase(id)) {
                System.out.print("New Status (Reported/Rescue in Progress/Under Observation/Completed): ");
                rc.setCurrentRescueStatus(sc.nextLine()); System.out.println("Status Updated"); return;
            }
        }
        System.out.println("Case not found");
    }

    static void displayAll() {
        if (rescueList.isEmpty()) { System.out.println("No rescue cases"); return; }
        for (RescueCase rc : rescueList) { System.out.println(rc.generateSummary()); }
    }

    static void rescueReport() {
        System.out.println("\n=========================================");
        System.out.println("WILDLIFE RESCUE REPORT");
        System.out.println("=========================================");
        double totalCost = 0;
        for (RescueCase rc : rescueList) {
            System.out.println("Case ID: " + rc.getRescueCaseId());
            System.out.println("Type: " + rc.getClass().getSimpleName());
            System.out.println("Species: " + rc.getSpecies());
            System.out.println("Location: " + rc.getRescueLocation());
            System.out.println("Ranger: " + rc.getAssignedRanger());
            System.out.println("Priority: " + rc.determineRescuePriority());
            System.out.println("Status: " + rc.getCurrentRescueStatus());
            System.out.println("Total Cost: R" + rc.calculateTotalRescueCost());
            System.out.println("-----------------------------------------");
            totalCost += rc.calculateTotalRescueCost();
        }
        System.out.println("Total Rescue Cases : " + rescueList.size());
        System.out.println("Total Rescue Cost : R" + String.format("%.2f", totalCost));
    }
}

# RescueTest
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
/**
 *
 * @author Thando
 */
public class RescueTest {
    public static void main(String[] args) {
        System.out.println("--- RUNNING TESTS ---");
        
        // Test 1: Cost Calculation
        InjuredAnimalRescue injured = new InjuredAnimalRescue(
            "WR101","Leo","African Elephant",
            "Kruger","John",10,500,
            "Broken leg",10000,true);
        double expected = 20000;
        double actual = injured.calculateTotalRescueCost();
        if (expected == actual) {
            System.out.println("Test 1 - Cost Calculation: PASSED");
        } else {
            System.out.println("Test 1 - FAILED");
        }

        // Test 2: Priority
        OrphanedAnimalRescue orphan = new OrphanedAnimalRescue(
            "WR102","Baby","White Rhino",
            "Giyani","Sarah",5,300,3,2000,true);
        String priority = orphan.determineRescuePriority();
        System.out.println("Test 2 - Priority Critical for baby: " + 
            (priority.equals("Critical") ? "PASSED" : "FAILED"));

        // Test 3: Status Update
        RescueCase rc = new InjuredAnimalRescue(
            "WR103","A","Lion","Limpopo","Mike",
            2,100,"Cut",500,false);
        rc.startRescue();
        System.out.println("Test 3 - Start Rescue: " + 
            (rc.getCurrentRescueStatus().equals("Rescue in Progress") ? "PASSED" : "FAILED"));
        rc.completeRescue();
        System.out.println("Test 4 - Complete Rescue: " + 
            (rc.getCurrentRescueStatus().equals("Completed") ? "PASSED" : "FAILED"));

        // Test 5: Duplicate ID Check
        WildlifeRescueApp.rescueList.clear();
        RescueCase rc1 = new OrphanedAnimalRescue(
            "WR104","B","Rhino","Giyani","Anna",
            4,100,8,1000,false);
        WildlifeRescueApp.rescueList.add(rc1);
        boolean duplicateCheck = !WildlifeRescueApp.isUniqueId("WR104");
        System.out.println("Test 5 - Duplicate Prevention: " + 
            (duplicateCheck ? "PASSED" : "FAILED"));
        
        System.out.println("--- ALL TESTS DONE ---");
    }
}
