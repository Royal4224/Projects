//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 Benchmarker
// Course: CS 300 Fall 2021
//
// Author: Roy Wang
// Email: rtwang2@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

/**
 * Benchmarker Class, that times the bruteForce() and hack() methods of PasswordHacker, and runs a
 * race between the two methods and compares their (average) run times.
 *
 * @author Roy Wang
 */


public class Benchmarker {

  /**
   * Using the System.currentTimeMillis() method, record the time in long before and after calling
   * the PasswordHacker’s bruteForce() method. Return the elapsed time in milliseconds.
   * 
   * @param ph - the PasswordHacker object that bruteForce() is being called from
   * @return the elapsed time in milliseconds
   */
  public static long timeBruteForce(PasswordHacker ph) {
    long before = System.currentTimeMillis();
    ph.bruteForce();
    long after = System.currentTimeMillis();

    // return time that has elapsed from before to after calling the method.
    return after - before;
  }

  /**
   * Using the System.currentTimeMillis() method, record the time in long before and after calling
   * the PasswordHacker’s hack() method. Return the elapsed time in milliseconds.
   * 
   * @param ph - the PasswordHacker object that hack() is being called from
   * @return the elapsed time in milliseconds
   */
  public static long timeHack(PasswordHacker ph) {
    long before = System.currentTimeMillis();
    ph.hack();
    long after = System.currentTimeMillis();
    
    // return time that has elapsed from before to after calling the method.
    return after - before;
  }

  /**
   * Race the two approaches against each other. Calculates the MEAN (average) time for each method
   * over numRuns runs. Return the results of this race as a String
   * 
   * @param passwordLength - the length of the password being guessed
   * @param numRuns        - the number of times the race is being run
   * @return the results of the race between bruteForce() and hack() methods
   */
  public static String race(int passwordLength, int numRuns) {
    double bruteAverage = 0.0;
    double hackAverage = 0.0;

    // create a new PasswordHacker for each run of the race, and add the times of bruteForce() and
    // hack() to their respective averages.
    for (int i = 0; i < numRuns; i++) {
      PasswordHacker ph = new PasswordHacker(passwordLength);
      bruteAverage += timeBruteForce(ph);
      hackAverage += timeHack(ph);
    }
    // calculate the mean time for each method over numRun runs.
    bruteAverage /= numRuns;
    hackAverage /= numRuns;
    String results = "Brute force " + passwordLength + ": " + bruteAverage + "\n" + "Hack "
        + passwordLength + ": " + hackAverage;

    return results;
  }

  public static void main(String[] args) {
    int passwordLength = 7;
    System.out.println(Benchmarker.race(passwordLength, 10));
    // PasswordHacker ph = new PasswordHacker(5);
    // System.out.println(ph.generateGuess(10));
  }
}
