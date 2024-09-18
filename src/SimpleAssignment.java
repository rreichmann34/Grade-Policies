///////////////////////////////////////////////////////////////////////////////
//
// Title: The SimpleAssignment class is the basis for the rest of the classes in this folder. The
// class creates SimpleAssignments with scores, bonuses, and uses methods to retrieve and
// set the private variables.
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
import java.util.Random; 

public class SimpleAssignment {
  private boolean bonusAvailable; // Once this SimpleAssignment completes, this will either add a
                                  // bonus or not to the final score
  private boolean isComplete; // true if this SimpleAssignment is complete, false otherwise
  public final int POINTS_POSSIBLE; // max earnable points for this SimpleAssignment
  private double pointsEarned; // the points earned on this SimpleAssignment

  /**
   * Creates a new SimpleAssignment object with points being the max possible points. There is
   * automatically no bonus for this object.
   * 
   * @param points the max points that can be earned for this assignment. Must be >= 1.
   */
  public SimpleAssignment(int points) {
    if (points < 1) { // points cannot be negative
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
  }

  /**
   * Creates a new SimpleAssignment object with points being the max possible points and bonus being
   * if there is a bonus available.
   * 
   * @param points the max points that can be earned for this assignment. Must be >= 1.
   * @param bonus  boolean value determining whether or not there is a bonus available
   */
  public SimpleAssignment(int points, boolean bonus) {
    if (points < 1) { // points cannot be negative
      POINTS_POSSIBLE = 1;
    } else {
      POINTS_POSSIBLE = points;
    }
    bonusAvailable = bonus;
  }

  /**
   * Return the points this SimpleAssignment earned. If the pointsEarned is greater than the
   * POINTS_POSSIBLE, then POINTS_POSSIBLE is returned instead.
   * 
   * @return the points for this SimpleAssignment
   */
  public double getPoints() {
    if (pointsEarned > POINTS_POSSIBLE) { // earned points cannot be greater than the earnable
                                          // points
      return POINTS_POSSIBLE;
    }
    return pointsEarned;
  }

  /**
   * Sets this SimpleAssignment to complete, sets a score, and mark the assignment as complete. If
   * the assignment is already complete, do nothing.
   * 
   * @param score the score that this SimpleAssignment earned
   */
  public void complete(double score) {
    // only runs if SimpleAssignment is not complete
    if (isComplete == false) {
      pointsEarned = score;
      isComplete = true;
    }
  }

  /**
   * A bonus is available if the SimpleAssignment has a bonus and if it is complete. The bonus is
   * equal to POINTS_POSSIBLE * 0.5 and is added to the existing pointsEarned. If the pointsEarned
   * is greater than the POINTS_POSSIBLE for this SimpleAssignment, then pointsEarned is set to
   * POINTS_POSSIBLE.
   */
  public void awardBonus() {
    if (bonusAvailable && isComplete == true) { // bonus is available and assignment is complete
      pointsEarned += 0.05 * POINTS_POSSIBLE;
      bonusAvailable = false;
      if (pointsEarned > POINTS_POSSIBLE) {
        pointsEarned = POINTS_POSSIBLE; // set pointsEarned back down to POINTS_POSSIBLE if
                                        // pointsEarend exceeds the maximum
      }
    }
  }

  /**
   * Checks to see if this SimpleAssignment is complete
   * 
   * @return true if the SimpleAssignment is complete and false otherwise
   */
  public boolean isComplete() {
    return isComplete;
  }

  /**
   * Creates random assignment scores with a mean of 80% and a standard deviation of 15%.
   * 
   * @param n        the number of assignment scores to create
   * @param maxScore the maximum score value to create
   * @return an array of the SimpleAssignments created under those requirements
   */
  public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
    Random rand = new Random();
    SimpleAssignment[] result = new SimpleAssignment[n];
    double mean = 0.80;
    double std = 0.15;
    for (int i = 0; i < n; i++) {
      double pctScore = rand.nextGaussian(mean, std);
      result[i] = new SimpleAssignment(maxScore);
      result[i].complete(pctScore * maxScore);
    }
    return result;
  }

  /**
   * Creates a string version of the score that this SimpleAssignment has in the form "score/max
   * points". If the assignment has not yet been completed, the score will be represented by an
   * asterisk(*) followed by /max score
   * 
   * @return a string version of the points scored on this SimpleAssignment
   */
  public String toString() {
    if (isComplete == true) {
      return pointsEarned + "/" + POINTS_POSSIBLE;
    }
    return "*/" + POINTS_POSSIBLE;
  }
}
