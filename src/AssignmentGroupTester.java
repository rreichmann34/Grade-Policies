///////////////////////////////////////////////////////////////////////////////
//
// Title: The AssignmentGroupTester class tests all of AssignmentGroup's constructors
// and methods, all of ScalingAssignmentGroup's constructors and methods, and all of
// DropAssignmentGroup's methods and constructors to ensure that they all return the correct
// values.
//
// Course: CS 300 Fall 2023
//
// Author: Remington Reichmann
// Email: rreichmann@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.util.ArrayList;

public class AssignmentGroupTester {

  /**
   * Tests adding a SimpleAssignment to an AssignmentGroup. Also, tests getNumAssignments() and
   * getAssignment().
   * 
   * @return true if all tests are passed, false otherwise.
   */
  public static boolean testAddOneAssignment() {
    SimpleAssignment assignment = new SimpleAssignment(100);
    AssignmentGroup group = new AssignmentGroup(50.0);
    group.addAssignment(assignment);

    boolean t1 = group.getNumAssignments() == 1;
    boolean t2 = group.getAssignment(0) == assignment;

    return t1 && t2;
  }

  /**
   * Tests adding multiple SimpleAssignment(s) to an AssignmentGroup.
   * 
   * @return true if all tests are passed, false otherwise.
   */
  public static boolean testAddManyAssignments() {
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};
    AssignmentGroup group = new AssignmentGroup(50.0);

    group.addAssignments(list);

    boolean t1 = group.getNumAssignments() == 2;
    boolean t2 = group.getAssignment(0) == assignment1;
    boolean t3 = group.getAssignment(1) == assignment2;

    return t1 && t2 && t3;
  }

  // PROVIDED
  // Verify that the getTotalPossible() method returns the expected value
  // in EACH of the classes which implements the method
  public static boolean testGetTotal() {
    boolean result = testGroupTotal();
    result &= testDropTotal();
    result &= testScaledTotal();

    return result;
  }

  /**
   * Tests getTotalPossible() method in AssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testGroupTotal() {
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};
    AssignmentGroup group = new AssignmentGroup(50.0);

    group.addAssignments(list);

    return group.getTotalPossible() == 150;
  }

  /**
   * Tests getTotalPossible() method in DropAssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testDropTotal() {
    DropAssignmentGroup group = new DropAssignmentGroup(50.0, 1);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};

    group.addAssignments(list);

    System.out.println(group.getTotalPossible());
    return group.getTotalPossible() == 50;
  }

  /**
   * Tests getTotalPossible() method in ScalingAssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testScaledTotal() {
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(50.0, 0.5);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(100);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};

    group.addAssignments(list);

    return group.getTotalPossible() == 100.0;
  }

  // PROVIDED
  // Verify that the getPoints() method returns the expected value in EACH
  // of the classes which implements the method
  public static boolean testGetPoints() {
    boolean result = testGroupPoints();
    result &= testDropPoints();
    result &= testScaledPoints();

    return result;
  }

  /**
   * Tests getPoints() method in AssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testGroupPoints() {
    AssignmentGroup group = new AssignmentGroup(50.0);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};
    assignment1.complete(90.0);
    assignment2.complete(40.0);

    group.addAssignments(list);

    return group.getPoints() == 130.0;
  }

  /**
   * Tests getPoints() method in DropAssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testDropPoints() {
    DropAssignmentGroup group = new DropAssignmentGroup(50.0, 1);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};
    assignment1.complete(90.0);
    assignment2.complete(40.0);

    group.addAssignments(list);

    return group.getPoints() == 90.0;
  }

  /**
   * Tests getPoints() method in ScalingAssignmentGroup.
   * 
   * @return true if the method returns the correct value, false otherwise.
   */
  private static boolean testScaledPoints() {
    ScalingAssignmentGroup group = new ScalingAssignmentGroup(50.0, 2.0);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};
    assignment1.complete(90.0);
    assignment2.complete(40.0);

    group.addAssignments(list);

    return group.getPoints() == 130.0;
  }

  /**
   * Tests the isComplete() method in AssignmentGroup. The entire group should be true, so when only
   * one of the SimpleAssignment(s) is true but the other is false, the method should still return
   * false.
   * 
   * @return true if all tests are passed, false otherwise.
   */
  public static boolean testComplete() {
    AssignmentGroup group = new AssignmentGroup(50.0);
    SimpleAssignment assignment1 = new SimpleAssignment(100);
    SimpleAssignment assignment2 = new SimpleAssignment(50);
    SimpleAssignment[] list = new SimpleAssignment[] {assignment1, assignment2};

    group.addAssignments(list);
    boolean t1 = group.isComplete() == false;
    group.getAssignment(0).complete(90.0);
    boolean t2 = group.isComplete() == false;
    group.getAssignment(1).complete(40.0);
    boolean t3 = group.isComplete() == true;

    return t1 && t2 && t3;
  }

  public static void main(String[] args) {
    if (SimpleAssignmentTester.runAllTests()) {
      System.out.println("add one: " + (testAddOneAssignment() ? "PASS" : "fail"));
      System.out.println("add many: " + (testAddManyAssignments() ? "PASS" : "fail"));
      System.out.println("get total: " + (testGetTotal() ? "PASS" : "fail"));
      System.out.println("get points: " + (testGetPoints() ? "PASS" : "fail"));
      System.out.println("complete: " + (testComplete() ? "PASS" : "fail"));
    } else {
      System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
          + "Do NOT continue with AssignmentGro until you have passed all SimpleAssignment tests.");
    }
  }

}
