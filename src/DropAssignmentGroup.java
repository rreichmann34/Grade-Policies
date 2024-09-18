///////////////////////////////////////////////////////////////////////////////
//
// Title: The DropAssignmentGroup creates an array list of SimpleAssignment(s) and drops
// numDropped number of assignments from the array list when any of the get methods are
// called. This class can also get, set, and add new values into the assignments array.
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

public class DropAssignmentGroup {
  private ArrayList<SimpleAssignment> assignments; // array of SimpleAssignment(s)
  private int numDropped; // contains the number of SimpleAssignment(s) to drop
  public final double PERCENT_OF_TOTAL; // the percent of the total grade that this
                                        // DropAssignmentGroup is worth

  /**
   * Creates a new DropAssignmentGroup and creates an array of SimpleAssignment(s) with drops
   * SimpleAssignment(s) to be dropped.
   * 
   * @param percent the percent of the grade that this DropAssignmentGroup is worth
   * @param drops   the number of SimpleAssignment(s) to drop
   */
  public DropAssignmentGroup(double percent, int drops) {
    assignments = new ArrayList<SimpleAssignment>();
    PERCENT_OF_TOTAL = percent;
    numDropped = drops;
  }

  /**
   * Adds a single SimpleAssignment to assignments
   * 
   * @param assignment the SimpleAssignment to add to assignments
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds multiple SimpleAssignment(s) to assignments
   * 
   * @param assignmentBatch the SimpleAssignment(s) to be added to assignments
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    // loop through and add assignmentBatch to assignments array list
    for (int i = 0; i < assignmentBatch.length; i++) {
      assignments.add(assignmentBatch[i]);
    }
  }

  /**
   * Creates a deep copy of the assignments array and then drops the lowest n scores from the new
   * array. If n is greater than or equal to the amount of SimpleAssignment(s) in assignments,
   * return an empty array list of SimpleAssignment(s).
   * 
   * @param assignments stores the scores that the new list will consider dropping
   * @param n           the number of SimpleAssignment(s) to drop
   * @return a new array with n SimpleAssignment(s) dropped from it
   */
  public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments,
      int n) {
    if (n >= assignments.size()) {
      return new ArrayList<SimpleAssignment>(); // returns an empty array list
    }

    // creates a deep copy of assignments
    ArrayList<SimpleAssignment> list = new ArrayList<SimpleAssignment>();
    for (int i = 0; i < assignments.size(); i++) {
      list.add(assignments.get(i));
    }
    // removes the lowest n scores from the new array list
    for (int i = 0; i < n; i++) {
      list.remove(getLowestScoreIndex(list));
    }
    return list;
  }

  /**
   * Retrieves the SimpleAssignment at a given index in assignments.
   * 
   * @param i the index to retrieve
   * @return the SimpleAssignment at index i in assignments
   */
  public SimpleAssignment getAssignment(int i) {
    return assignments.get(i);
  }

  /**
   * Retrieves the index of the lowest score in a given array.
   * 
   * @param assignments the array to remove the lowest score from
   * @return the index of the lowest score in the array
   */
  public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {
    double lowest = assignments.get(0).getPoints();
    int index = 0;
    // finds lowest index in assignments
    for (int i = 1; i < assignments.size(); i++) { // loop through assignments array list
      if (assignments.get(i).getPoints() < lowest) {
        lowest = assignments.get(i).getPoints();
        index = i;
      }
    }
    return index;
  }

  /**
   * Retrieves the amount of SimpleAssignment(s) in assignments
   * 
   * @return the size of the assignments array
   */
  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Calculates the amount of points earned in this DropAssignmentGroup after removing numDropped
   * assignments.
   * 
   * @return the calculated total amount of points
   */
  public double getPoints() {
    ArrayList<SimpleAssignment> list = dropLowestN(assignments, numDropped); // drop numDropped
    double total = 0.0; // assignments first
    for (int i = 0; i < list.size(); i++) { // loop through new list
      total += list.get(i).getPoints();
    }
    return total;
  }

  /**
   * Calculates the maximum amount of points possible in this DropAssignmentGroup after removing
   * numDropped assignments.
   * 
   * @return the calculated total amount of points
   */
  public int getTotalPossible() {
    ArrayList<SimpleAssignment> list = dropLowestN(assignments, numDropped); // drop numDropped
    int total = 0; // assignments first
    for (int i = 0; i < list.size(); i++) { // loop through new list
      total += list.get(i).POINTS_POSSIBLE;
    }
    return total;
  }

  /**
   * Creates a new array list and drops numDropped SimpleAssignment(s) from the new array list. Then
   * checks to see if all of the SimpleAssignment(s) in the new list are complete.
   * 
   * @return true if the new list contains only completed SimpleAssignment(s) and false otherwise
   */
  public boolean isComplete() {
    ArrayList<SimpleAssignment> list = dropLowestN(assignments, numDropped); // drop numDropped
    boolean allComplete = true; // assignments first
    for (int i = 0; i < list.size(); i++) { // loop through new list
      if (!list.get(i).isComplete()) { // if one or more SimpleAssignment(s) aren't complete, then
        allComplete = false; // the method will return false
      }
    }
    return allComplete;
  }

  /**
   * Returns a string value of all of the SimpleAssignment(s) in this DropAssignmentGroup separated
   * by ", ".
   */
  public String toString() {
    String returnString = "";
    for (int i = 0; i < assignments.size(); i++) { // loop through assignments
      returnString += assignments.get(i) + ", ";
    }
    return returnString;
  }
}
