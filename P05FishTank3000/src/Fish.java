//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish
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
 * Fish class that extends TankObject and overrides several methods. Fish can swim, be dragged, and
 * move appropriately
 * 
 * @author Roy Wang
 */
public class Fish extends TankObject {
  private int speed; // swimming speed of this fish
  private boolean isSwimming; // indicates whether this fish is swimming or not

  /**
   * Creates a new fish with given speed, image file name, assigns the fish a random position
   * 
   * @param speed
   * @param fishImageFileName
   * @throws IllegalArgumentException
   */
  public Fish(int speed, String fishImageFileName) throws IllegalArgumentException {
    // create fish of given file name with random position
    super(TankObject.tank.randGen.nextInt(tank.width), TankObject.tank.randGen.nextInt(tank.height),
        fishImageFileName);

    // throw IAE for illegal speed
    if (speed <= 0) {
      throw new IllegalArgumentException("Warning: speed cannot be negative");
    }
    this.speed = speed;
  }

  /**
   * Creates a new orange fish with speed 5, random position
   */
  public Fish() {
    this(5, "images" + File.separator + "orange.png");
  }

  /**
   * Overrides the draw() method implemented in the parent class. This method sets the position of
   * this fish to follow the mouse moves if it is dragging, calls its swim() method if it is
   * swimming, and draws it to the display window.
   */
  @Override
  public void draw() {
    if (isSwimming) {
      swim();
    }
    super.draw();
  }

  /**
   * Checks whether this fish is swimming
   * 
   * @return true if fish is swimming, false otherwise
   */
  public boolean isSwimming() {
    return this.isSwimming;
  }

  /**
   * Starts swimming this fish
   */
  public void startSwimming() {
    super.stopDragging();
    this.isSwimming = true;
  }

  /**
   * Stops swimming this fish
   */
  public void stopSwimming() {
    this.isSwimming = false;
  }

  /**
   * Gets the speed of this fish
   * 
   * @return current speed of this fish
   */
  public int speed() {
    return this.speed;
  }

  /**
   * Moves horizontally the fish one speed step from left to right.
   */
  public void swim() {
    this.isSwimming = true;
    // moves the fish one speed step from left to right, and moves the fish to the left side of the
    // display window if the fish right boundary of the display window
    super.setX((super.getX() + speed) % TankObject.tank.width);
  }
}
