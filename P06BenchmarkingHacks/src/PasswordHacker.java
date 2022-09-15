//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 PasswordHacker
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
 * PasswordHacker Class, which creates a new PasswordHacker that implements a bruteForce way to open
 * a LockBox, and a method to hack the LockBox. The PasswordHacker can also generate a guess for a
 * LockBox given the passwordLength and the guess number.
 *
 * @author Roy Wang
 */

public class PasswordHacker {
  private LockBox toPick;
  private int passwordLength;

  /**
   * This constructor should store the provided password length for future reference and create a
   * new LockBox with a password of the same length. You don’t need to handle (or throw)
   * IllegalArgumentExceptions here for the length check, since LockBox will do that for you.
   * 
   * @param passwordLength - length of the password being guessed
   */
  public PasswordHacker(int passwordLength) {
    this.toPick = new LockBox(passwordLength);
    this.passwordLength = passwordLength;
  }

  /**
   * First, reset the LockBox so that you have something to hack into. Next, ask it for the
   * password. Finally, use the password to open the LockBox.
   * 
   * Complexity: O(1) <- TO BE FILLED
   */
  public void hack() {
    toPick.reset();
    String pwd = toPick.hackMe();
    toPick.authenticate(pwd);
  }

  /**
   * First, reset the LockBox so that you have something to hack into. Use the generateGuess()
   * method to generate guesses methodically until the LockBox opens.
   * 
   * Complexity: O(N * 10^N) <- TO BE FILLED
   */
  public void bruteForce() {
    toPick.reset();
    int count = 0;
    // while the LockBox isn't open, generate a new guess and compare it against the LockBox's
    // password.
    while (!toPick.isOpen()) {
      toPick.authenticate(generateGuess(count));
      count++;
    }
  }

  /**
   * Given the number of times you’ve guessed a password (count), produce the password to try on
   * this iteration. If this is guess 0 and the passwordLength is 5, this method should return
   * “00000”. If this is guess 5, return “00005”, and so forth.
   * 
   * @param count - the number of times a password has been guessed
   * @return the generated guess for the password being guessed.
   */
  public String generateGuess(int count) {
    String guess = "";
    // add every digit of count to the guess, and add 0's to the beginning of the guess if the count
    // is not long enough.
    StringBuilder sb = new StringBuilder(guess);
    for (int i = 0; i < passwordLength; i++) {
      sb.insert(0, (count % 10));
      count = count / 10;
    }
    return sb.toString();
  }
}
