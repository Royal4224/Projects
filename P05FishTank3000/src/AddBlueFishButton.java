//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Add Blue Fish Button
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
 * Add Blue Fish Button Class which extends the Button class and adds a blue fish to the tank every
 * time the button is pressed.
 * 
 * @author Roy Wang
 */
public class AddBlueFishButton extends Button {
  /**
   * Initializes a blue fish button with appropriate labels and position.
   * 
   * @param x - x-position of the button
   * @param y - y-position of the button
   */
  public AddBlueFishButton(float x, float y) {
    super("Add Blue", x, y);
  }

  /**
   * Overrides Button.mousePressed() method. Adds a Blue Fish to tank every time it is pressed.
   */
  @Override
  public void mousePressed() {
    Button.tank.objects.add(new BlueFish());
  }
}
