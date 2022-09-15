//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Clear Tank Button
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
 * Clear Tank Button which extends button and clears the tank every time it is pressed.
 * 
 * @author Roy Wang
 */
public class ClearTankButton extends Button {
  /**
   * Initializes a clear tank button with appropriate labels and position.
   * 
   * @param x - x-position of the button
   * @param y - y-position of the button
   */
  public ClearTankButton(float x, float y) {
    super("clear", x, y);
  }

  /**
   * Overrides Button.mousePressed() method. Clears the fish tank every time it is pressed.
   */
  @Override
  public void mousePressed() {
    Button.tank.clear();
  }
}
