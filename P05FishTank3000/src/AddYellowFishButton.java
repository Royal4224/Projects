//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Add Yellow Fish Button
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

import java.io.File;

/**
 * Add Yellow Fish Button Class which extends Button and adds a yellow fish to the tank every time
 * it is pressed.
 * 
 * @author Roy Wang
 */
public class AddYellowFishButton extends Button {
  /**
   * Initializes a yellow fish button with appropriate labels and position.
   * 
   * @param x - x-position of the button
   * @param y - y-position of the button
   */
  public AddYellowFishButton(float x, float y) {
    super("Add Yellow", x, y);
  }

  /**
   * Overrides Button.mousePressed() method. Adds a Yellow Fish to tank every time it is pressed.
   */
  @Override
  public void mousePressed() {
    Button.tank.objects.add(new Fish(2, "images" + File.separator + "yellow.png"));
  }
}
