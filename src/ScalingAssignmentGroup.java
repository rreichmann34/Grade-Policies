///////////////////////////////////////////////////////////////////////////////
//
// Title: The ScalingAssignmentGroup class creates an AssignmentGroup object and calls many of the
// same methods. The only major difference between ScalingAssignmentGroup and
// AssignmentGroup is that the final score is multiplied by a scalingFactor.
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
public class ScalingAssignmentGroup {
  private AssignmentGroup group; // creates a new AssignmentGroup object to reference
  public final double PERCENT_OF_TOTAL; // the percent of the total grade that this
                                        // ScalingAssignmentGroup is worth
  private double scalingFactor; // the scale that the final score will be multiplied by

  /**
   * Creates a new ScalingAssignmentGroup object.
   * 
   * @param percent the percent of the total grade that this ScalingAssignmentGroup is worth
   * @param scale   the scaling factor for this object. Must be between 0 and 1.
   */
  public ScalingAssignmentGroup(double percent, double scale) {
    PERCENT_OF_TOTAL = percent;
    if (scale > 1.0 || scale < 0) {
      scale = 1.0; // sets scale to 1.0 if it is negative or > 1.0
    }
    scalingFactor = scale;
    group = new AssignmentGroup(percent);
  }

  /**
   * Adds a single SimpleAssignment to group.
   * 
   * @param assignment the SimpleAssignment to add
   */
  public void addAssignment(SimpleAssignment assignment) {
    group.addAssignment(assignment);
  }

  /**
   * Adds a set of SimpleAssignment(s) to group
   * 
   * @param assignmentBatch the list of SimpleAssignment(s) to add
   */
  public void addAssignments(SimpleAssignment[] assignmentBatch) {
    group.addAssignments(assignmentBatch);
  }

  /**
   * Retrieves a SimpleAssignment from the given index.
   * 
   * @param i the index to retrieve a SimpleAssignment from
   * @return the SimpleAssignment at the given index or null if no such SimpleAssignment exists
   */
  public SimpleAssignment getAssignment(int i) {
    return group.getAssignment(i);
  }

  /**
   * Retrieves the amount of SimpleAssignment(s) in group
   * 
   * @return the size of group's assignments list
   */
  public int getNumAssignments() {
    return group.getNumAssignments();
  }

  /**
   * Calculates the total amount of points in this ScalingAssignmentGroup. If this value is greater
   * than the maximum amount of points, return the maximum amount of points instead.
   * 
   * @return the calculated number of points
   */
  public double getPoints() {
    double total = group.getPoints();
    if (total > getTotalPossible()) {
      return getTotalPossible();
    }
    return total;
  }

  /**
   * Calculates the total amount of earnable points in this ScalingAssignmentGrop.
   * 
   * @return the calculated total amount of points
   */
  public double getTotalPossible() {
    return (double) group.getTotalPossible() * scalingFactor;
  }

  /**
   * Checks to see if all SimpleAssignment(s) in this ScalingAssigmentGroup are completed
   * 
   * @return true if all SimpleAssignment(s) are complete and false otherwise
   */
  public boolean isComplete() {
    return group.isComplete();
  }

  /**
   * Returns a string value of the total score earned in this ScalingAssignmentGroup.
   */
  public String toString() {
    return "" + this.getPoints() / this.getTotalPossible();
  }
}
