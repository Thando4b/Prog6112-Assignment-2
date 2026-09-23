/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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