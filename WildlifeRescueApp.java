
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.util.*;
/**
 *
 * @author Thando
 */
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

