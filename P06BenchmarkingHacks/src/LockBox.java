//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P06 LockBox
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

import java.util.Random;

/**
 * Represents the LockBox Object, which generates a password of length passwordlength, authenticates
 * guesses against the generated password, and has accessors for the password (for hacking) and the
 * isOpen. Also can reset and close itself.
 *
 * @author Roy Wang
 */

public class LockBox {
  protected static Random randGen;
  private String password;
  private boolean isOpen;

  /**
   * This constructor initializes the static random number generator IF AND ONLY IF it has not yet
   * been initialized. If the provided length is <=0, throw an IllegalArgumentException. Otherwise,
   * generate a random password of numbers only, with length equal to passwordLength.
   * 
   * @param passwordLength - length of password to be generated
   * 
   * @throws IllegalArgumentException
   */

  public LockBox(int passwordLength) throws IllegalArgumentException {
    if (LockBox.randGen == null)
      randGen = new Random();

    if (passwordLength <= 0)
      throw new IllegalArgumentException("Invalid password length! Nice try :)");

    password = "";
    // concatenate randomly generated digits to the password.
    for (int i = 0; i < passwordLength; i++) {
      password += randGen.nextInt(10);
    }
  }

  /**
   * Checks the provided guess against the stored password. If the guess is correct, set isOpen to
   * true. Otherwise, false.
   * 
   * @param guess - guess of the password
   */
  public void authenticate(String guess) {
    if (guess.equals(password)) {
      this.isOpen = true;
    }
  }

  /**
   * Returns the stored password of this LockBox.
   * 
   * @return the stored password of this LockBox.
   */
  public String hackMe() {
    return this.password;
  }

  /**
   * Accessor for the isOpen field, to check whether your authentication was successful.
   * 
   * @return the isOpen field of this LockBox.
   */
  public boolean isOpen() {
    return this.isOpen;
  }

  /**
   * Resets the isOpen instance field to false.
   */
  public void reset() {
    this.isOpen = false;
  }

}
