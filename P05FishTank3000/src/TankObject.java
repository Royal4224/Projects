//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Tank Object
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

import processing.core.PImage;

/**
 * Tank Object Class that implements TankListener, serves as the generic type for tank objects like
 * fish and decorations. Implements setters and getters for the position of the object, move method,
 * dragging methods, and overrides/implements all methods from TankListener
 * 
 * @author Roy Wang
 */
public class TankObject implements TankListener {
  protected static FishTank tank; // PApplet object which represents
  // the display window
  protected PImage image; // image of this tank object
  private float x; // x-position of this tank in the display window
  private float y; // y-position of this tank in the display window
  private boolean isDragging; // indicates whether this tank object
  // is being dragged or not
  private static int oldMouseX; // old x-position of the mouse
  private static int oldMouseY; // old y-position of the mouse

  /**
   * 
   * @param x
   * @param y
   * @param imageFileName
   */
  public TankObject(float x, float y, String imageFileName) {
    this.x = x;
    this.y = y;
    this.image = tank.loadImage(imageFileName);
    this.isDragging = false;
  }

  /**
   * Sets the PApplet graphic display window for all TankObjects
   * 
   * @param tank - Fish Tank to be set as the display window for all TankObjects
   */
  public static void setProcessing(FishTank tank) {
    TankObject.tank = tank;
  }

  /**
   * Moves this tank object with dx and dy
   * 
   * @param dx - move to the x-position of this tank object
   * @param dy - move to the y-position of this tank object
   */
  public void move(int dx, int dy) {
    this.x += dx;
    this.y += dy;
  }

  /**
   * Returns the x-position of this tank object
   * 
   * @return x-position of this tank object
   */
  public float getX() {
    return this.x;
  }

  /**
   * Returns the y-position of this tank object
   * 
   * @return y-position of this tank object
   */
  public float getY() {
    return this.y;
  }

  /**
   * Sets the x-position of this object
   * 
   * @param x - x-position that this object is to be set to
   */
  public void setX(float x) {
    this.x = x;
  }

  /**
   * Sets the y-position of this object
   * 
   * @param y - y-position that this object is to be set to
   */
  public void setY(float y) {
    this.y = y;
  }

  /**
   * Returns the image of this tank object
   * 
   * @return image of this tank object
   */
  public PImage getImage() {
    return this.image;
  }

  /**
   * Getter of the isDragging field. Returns true if this object is being dragged, false otherwise
   * 
   * @return the drag state of this object
   */

  public boolean isDragging() {
    return this.isDragging;
  }

  /**
   * Starts dragging this tank object
   */
  public void startDragging() {
    oldMouseX = tank.mouseX;
    oldMouseY = tank.mouseY;
    this.isDragging = true;
  }

  /**
   * Stops dragging this tank object
   */
  public void stopDragging() {
    this.isDragging = false;
  }

  /**
   * Draws this tank object to the display window.
   */
  @Override
  public void draw() {
    // if this tank object is dragging, set its position to follow the movement of the cursor
    if (this.isDragging()) {
      int dx = tank.mouseX - oldMouseX;
      int dy = tank.mouseY - oldMouseY;
      move(dx, dy);
      oldMouseX = tank.mouseX;
      oldMouseY = tank.mouseY;
    }

    // draw this tank object object at its current position
    tank.image(this.image, this.x, y);
  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    // when mouse pressed, start dragging and draw the updating object
    startDragging();
    draw();
  }

  @Override
  public void mouseReleased() {
    // when mouse released, stop dragging
    stopDragging();
  }


  /**
   * Checks whether the mouse is over this tank object
   * 
   * @return true if mouse is over this tank object, false otherwise
   */
  @Override
  public boolean isMouseOver() {
    // checks if the mouse is over this object
    // if the mouse is within the boundaries of the object, return true. otherwise, false
    return tank.mouseX >= x - image.width / 2 && tank.mouseX <= x + image.width / 2
        && tank.mouseY >= y - image.height / 2 && tank.mouseY <= y + image.height / 2;
  }

}
