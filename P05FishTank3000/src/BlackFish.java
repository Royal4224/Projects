//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Black Fish
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
 * Black Fish Class, emulates a fish with speed 2 that swims between a "source" tank object and a
 * "destination" tank object.
 * 
 * @author Roy Wang
 */
public class BlackFish extends Fish {

  private TankObject source;
  private TankObject destination;

  /**
   * Creates a new BlackFish with speed 2, and designates a source object and destination object in
   * the tank
   * 
   * @param source
   * @param destination
   */
  public BlackFish(TankObject source, TankObject destination) {
    super(2, "images" + File.separator + "black.png");
    this.source = source;
    this.destination = destination;
  }

  /**
   * Makes one speed move towards destination
   */
  public void moveTowardsDestination() {
    // calculate the distance between this tank object and the destination object
    double dx = destination.getX() - super.getX();
    double dy = destination.getY() - super.getY();
    int d = (int) Math.sqrt(dx * dx + dy * dy);

    // calculate the new position of this tank object based on the fish's speed and distance between
    // this object and the destination object
    float newX = (float) (super.getX() + (super.speed() * dx) / d);
    float newY = (float) (super.getY() + (super.speed() * dy) / d);

    super.setX(newX);
    super.setY(newY);
  }

  /**
   * Returns true if this black fish is over another tank object, and false otherwise
   * 
   * @param other
   * @return true if this black fish is over another tank object, false otherwise
   */
  public boolean isOver(TankObject other) {
    // get the boundaries of the "other" tank object
    float lowerWidth = other.getX() - other.getImage().width;
    float lowerHeight = other.getY() - other.getImage().height;
    float upperWidth = other.getX() + other.getImage().width;
    float upperHeight = other.getY() + other.getImage().height;

    // if fish is within those boundaries, return true. otherwise, return false.
    return (super.getX() >= lowerWidth && super.getX() <= upperWidth)
        && (super.getY() >= lowerHeight && super.getY() <= upperHeight);
  }

  /**
   * Overrides Fish.swim() method moves the fish between two decorations
   */
  @Override
  public void swim() {
    // move the fish towards its destination
    // if destination is reached (meaning this fish is over its destination),
    // switch source and destination
    super.startSwimming();
    moveTowardsDestination();
    if (isOver(this.destination)) {
      TankObject temp = this.destination;
      this.destination = source;
      this.source = temp;
    }
  }
}
