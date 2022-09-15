//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Blue Fish
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
 * Blue Fish class, which emulates a blue fish that swims from right to left at speed 2
 * 
 * @author Roy Wang
 */
public class BlueFish extends Fish {
  /**
   * Creates a new blue fish with speed 2, blue fish image
   */
  public BlueFish() {
    super(2, "images" + File.separator + "blue.png");
  }

  /**
   * Overrides Fish.swim() method. Begins swimming the fish right to left
   */
  @Override
  public void swim() {
    super.startSwimming();

    // if x-position reaches zero, move fish to the right side of the display window.
    if (super.getX() - super.speed() <= 0) {
      super.setX(TankObject.tank.width);
    } else { // else, swim fish from right to left.
      super.setX(super.getX() - super.speed());
    }
  }
}
