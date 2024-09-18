///////////////////////////////////////////////////////////////////////////////
//
// Title: The SimpleAssignmentTester class tests all of SimpleAssignment's constructors
// and methods to ensure that they all return the correct values.
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
import java.util.Scanner;

public class SimpleAssignmentTester {

  /**
   * Checks to make sure getPoints(), isComplete(), and POINTS_POSSIBLE all work for multiple
   * SimpleAssignment objects.
   * 
   * @return true if all tests are passed and false otherwise.
   */
  public static boolean testAccessors() {
    SimpleAssignment a1 = new SimpleAssignment(20);
    SimpleAssignment a2 = new SimpleAssignment(50, true);

    // Testing the accessor methods for a1 before the assignment has been completed
    boolean a1Test1 = a1.getPoints() == 0.0;
    boolean a1Test2 = a1.isComplete() == false;
    boolean a1Test3 = a1.POINTS_POSSIBLE == 20;
    // Testing the accessor methods for a2 before the assignment has been completed
    boolean a2Test1 = a2.getPoints() == 0.0;
    boolean a2Test2 = a2.isComplete() == false;
    boolean a2Test3 = a2.POINTS_POSSIBLE == 50;

    a1.complete(18.0); // a1 is completed for the sake of testing the accessor methods
    a2.complete(40.0); // a2 is completed for the sake of testing the accessor methods

    // Testing the accessor methods for a1 after the assignment has been completed
    boolean a1Test4 = a1.getPoints() == 18.0;
    boolean a1Test5 = a1.isComplete() == true;
    boolean a1Test6 = a1.POINTS_POSSIBLE == 20;
    // Testing the accessor methods for a2 after the assignment has been completed
    boolean a2Test4 = a2.getPoints() == 40.0;
    boolean a2Test5 = a2.isComplete() == true;
    boolean a2Test6 = a2.POINTS_POSSIBLE == 50;

    return a1Test1 && a1Test2 && a1Test3 && a1Test4 && a1Test5 && a1Test6 && a2Test1 && a2Test2
        && a2Test3 && a2Test4 && a2Test5 && a2Test6;
  }

  // PROVIDED
  // This method calls a number of shorter helper methods, which test various cases for a
  // SimpleAssignment with the bonus option available
  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  public static boolean testSimpleBonus() {
    boolean result = testAwardBonus();
    result &= testBonusTwice();
    result &= testNoBonus();
    result &= testBonus105();

    return result;
  }

  /**
   * Tests awardBonus() in SimpleAssignment class
   * 
   * @return true if all tests are passed and false otherwise
   */
  private static boolean testAwardBonus() {
    SimpleAssignment assignment = new SimpleAssignment(100, true); // maxScore is 100, so
    assignment.complete(90.0); // awardBonus() should add 5
    assignment.awardBonus(); // points
    if (assignment.getPoints() == 95.0) {
      return true;
    }
    return false;
  }

  /**
   * Tests awardBonus in SimpleAssignment to see if calling the method twice changes the value
   * 
   * @return true if value is not changed after additional call to awardBonus(), false otherewise.
   */
  private static boolean testBonusTwice() {
    SimpleAssignment assignment = new SimpleAssignment(100, true); // maxScore is 100, so
    assignment.complete(90.0); // awardBonus() should add 5
    assignment.awardBonus(); // points
    assignment.awardBonus();
    if (assignment.getPoints() == 95.0) {
      return true;
    }
    return false;
  }

  /**
   * Tests awardBonus() if no bonus is available for the SimpleAssignment.
   * 
   * @return true if all tests are passed and false otherwise.
   */
  private static boolean testNoBonus() {
    SimpleAssignment assignment = new SimpleAssignment(100, false); // there is no bonus, so the
                                                                    // score should end up as 90.0
    assignment.complete(90.0);
    assignment.awardBonus();
    if (assignment.getPoints() == 90.0) {
      return true;
    }
    return false;
  }

  /**
   * Tests awardBonus() when the pointsEarned exceeds POINTS_POSSIBLE. POINTS_POSSIBLE should be
   * returned by the method call.
   * 
   * @return true if tests are passed and false otherwise.
   */
  private static boolean testBonus105() {
    SimpleAssignment assignment = new SimpleAssignment(100, true); // there is no bonus, so the
                                                                   // score should end up as 90.0
    assignment.complete(98.0);
    assignment.awardBonus();
    if (assignment.getPoints() == 100.0) {
      return true;
    }
    return false;
  }

  // PROVIDED
  // This method calls a number of shorter helper methods, all of which test error cases
  // in the SimpleAssignment class.
  // By breaking these out into shorter tests, you can use the debugger to step through this
  // method and quickly determine which of the tests are failing, if any.
  public static boolean testSimpleErrorCases() {
    boolean result = testBadConstructorInput();
    result &= testBonusIncomplete();
    result &= testBadPointsEarned();
    result &= testCompleteAssignmentTwice();

    return result;
  }

  /**
   * Tests calling the SimpleAssignment constructor with negative values. The pointsEarned should be
   * set to 1 if this happens.
   * 
   * @return true if both constructors create the correct value, false otherwise.
   */
  private static boolean testBadConstructorInput() {
    SimpleAssignment assignment1 = new SimpleAssignment(-5);
    SimpleAssignment assignment2 = new SimpleAssignment(-5, true);

    boolean t1 = assignment1.POINTS_POSSIBLE == 1;
    boolean t2 = assignment2.POINTS_POSSIBLE == 1;

    return t1 && t2;
  }

  /**
   * Tests awardBonus() for when the SimpleAssignment has not been completed. The pointsEarned
   * should still be at 0.0.
   * 
   * @return true if tests are passed, false otherwise.
   */
  private static boolean testBonusIncomplete() {
    SimpleAssignment assignment = new SimpleAssignment(100, false); // there is no bonus, so the
                                                                    // score should end up as 90.0
    assignment.awardBonus();
    if (assignment.getPoints() == 0.0) {
      return true;
    }
    return false;
  }

  /**
   * Test complete() method in SimpleAssignment class to make sure pointsEarned does not exceed
   * POINTS_POSSIBLE.
   * 
   * @return true if tests are passed, false otherwise.
   */
  private static boolean testBadPointsEarned() {
    SimpleAssignment assignment = new SimpleAssignment(100, false); // a1 has max of 100 points
    assignment.complete(120.0);
    if (assignment.getPoints() == 100.0) {
      return true;
    }
    return false;
  }

  /**
   * Tests complete() in SimpleAssignment class to make sure that the same SimpleAssignment cannot
   * be completed more than once.
   * 
   * @return true if tests are passed, false otherwise.
   */
  private static boolean testCompleteAssignmentTwice() {
    SimpleAssignment assignment = new SimpleAssignment(100, false); // a1 has max of 100 points
    assignment.complete(80.0);
    assignment.complete(90.0);
    if (assignment.getPoints() == 80.0) {
      return true;
    }
    return false;
  }

  // PROVIDED
  // This method reports whether all provided SimpleAssignmentTester methods
  // have passed.
  public static boolean runAllTests() {
    return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
  }

  public static void main(String[] args) {
    System.out.println("basic: " + (testAccessors() ? "PASS" : "fail"));
    System.out.println("bonus: " + (testSimpleBonus() ? "PASS" : "fail"));
    System.out.println("edge cases: " + (testSimpleErrorCases() ? "PASS" : "fail"));
  }

}
