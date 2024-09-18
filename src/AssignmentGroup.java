///////////////////////////////////////////////////////////////////////////////
//
// Title: The AssignmentGroup class holds an array of SimpleAssignment(s). Most methods in this
// class get, set and add values inside the assignments array.
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

public class AssignmentGroup {
  private ArrayList<SimpleAssignment> assignments; // array of SimpleAssignment(s)
  public final double PERCENT_OF_TOTAL; // the percent of the total grade that this AssignmentGroup
                                        // accounts for

  /**
   * Creates a new AssignmentGroup and initializes the assignments array
   * 
   * @param percent the percent of the grade that this AssignmentGroup is worth
   */
  public AssignmentGroup(double percent) {
    PERCENT_OF_TOTAL = percent;
    assignments = new ArrayList<SimpleAssignment>();
  }

  /**
   * Adds a single SimpleAssignment to the assignments array
   * 
   * @param assignment the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    assignments.add(assignment);
  }

  /**
   * Adds a set of SimpleAssignment(s) to the assignments array
   * 
   * @param assignmentBatch an array of SimpleAssignment(s) to add
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    // loop through and add assignmentBatch to assignments
    for (int i = 0; i < assignmentBatch.length; i++) {
      assignments.add(assignmentBatch[i]);
    }
  }

  /**
   * Retrieves the SimpleAssignment at the given index in assignments. The assignments array must be
   * not null.
   * 
   * @param i the index to retrieve. Must be >= 0 and < the size of assignments.
   * @return the SimpleAssignment at index i or null if i is not in the bounds of the array
   */
  public SimpleAssignment getAssignment(int i) {
    // i is 0 <= i < assignments.size and assignments is not null
    if (i >= 0 && assignments.size() > 0 && i < assignments.size()) {
      return assignments.get(i);
    }
    return null;
  }

  /**
   * Retrieves the number of SimpleAssignment(s) in the assignments array.
   * 
   * @return the size of assignments
   */
  public int getNumAssignments() {
    return assignments.size();
  }

  /**
   * Calculates the total amount of points earned for all SimpleAssignment(s) in assignments.
   * 
   * @return the calculated points value
   */
  public double getPoints() {
    double total = 0.0;
    for (int i = 0; i < assignments.size(); i++) { // loop through assignments
      total += assignments.get(i).getPoints(); // increment total by points
    }
    return total;
  }

  /**
   * Calculates the amount of points that are possible for all SimpleAssignment(s) in assignments.
   * 
   * @return the calculated points value
   */
  public int getTotalPossible() {
    int total = 0;
    for (int i = 0; i < assignments.size(); i++) { // loop through assignments
      total += assignments.get(i).POINTS_POSSIBLE; // increment total by points
    }
    return total;
  }

  /**
   * Checks to see if all SimpleAssignment(s) in assignments are complete. If one or more
   * assignments are not complete, then the entire method will return false;
   * 
   * @return true if all SimpleAssignment(s) are completed and false otherwise
   */
  public boolean isComplete() {
    boolean allComplete = true;
    for (int i = 0; i < assignments.size(); i++) { // loop through assignments
      if (!assignments.get(i).isComplete()) {
        allComplete = false; // if any of the assignments aren't complete, return false;
        break;
      }
    }
    return allComplete;
  }

  /**
   * Returns a string value of all of the SimpleAssignment(s) currently stored in assignments
   * 
   * @return all SimpleAssignment scores separated by a ","
   */
  public String toString() {
    String list = "";
    for (int i = 0; i < assignments.size(); i++) { // loop through assignments
      list += assignments.get(i) + ", ";
    }
    return list;
  }
}
