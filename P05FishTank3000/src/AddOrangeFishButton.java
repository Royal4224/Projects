//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Add Orange Fish Button
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
 * Add Orange Fish Button Class, which extends Button and adds an orange fish to the tank every time
 * it is pressed.
 * 
 * @author Roy Wang
 */
public class AddOrangeFishButton extends Button {
  /**
   * Initializes a orange fish button with appropriate labels and position.
   * 
   * @param x - x-position of the button
   * @param y - y-position of the button
   */
  public AddOrangeFishButton(float x, float y) {
    super("Add Orange", x, y);
  }

  /**
   * Overrides Button.mousePressed() method. Adds an Orange Fish to tank every time it is pressed.
   */
  @Override
  public void mousePressed() {
    Button.tank.objects.add(new Fish());
  }
}
