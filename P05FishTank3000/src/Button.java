//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Button
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
 * Button Class that implements TankListener. Buttons can be pressed, and have a label and position.
 * 
 * @author Roy Wang
 */
public class Button implements TankListener {
  private static final int WIDTH = 85; // Width of this Button
  private static final int HEIGHT = 32; // Height of this Button
  protected static FishTank tank; // PApplet object where this button will be displayed
  private float x; // x-position of this button in the display window
  private float y; // y-position of this button in the display window
  protected String label; // text/label which represents this button

  /**
   * Creates a new Button at a given position within the display window and sets its label
   * 
   * @param label
   * @param x
   * @param y
   */
  public Button(String label, float x, float y) {
    this.label = label;
    this.x = x;
    this.y = y;
  }

  // sets the PApplet display window where this button is displayed and handled
  public static void setProcessing(FishTank tank) {
    Button.tank = tank;
  }

  // Overrides the TankListener.isMouseOver() method
  // Checks whether the mouse is over this button
  // returns true if the mouse is over this button, false otherwise.
  @Override
  public boolean isMouseOver() {
    // calculate the boundaries of this button
    float lowerWidth = this.x - WIDTH / 2.0f;
    float upperWidth = this.x + WIDTH / 2.0f;
    float lowerHeight = this.y - HEIGHT / 2.0f;
    float upperHeight = this.y + HEIGHT / 2.0f;

    // if mouse is within the bounds of this button, return true
    // else, false
    return (TankObject.tank.mouseX >= lowerWidth && TankObject.tank.mouseX <= upperWidth)
        && (TankObject.tank.mouseY >= lowerHeight && TankObject.tank.mouseY <= upperHeight);
  }

  /**
   * Overrides TankListener.draw() method. Draws this button to the display window
   */
  @Override
  public void draw() {
    tank.stroke(0);// set line value to black

    // If the mouse is over this button, sets the fill color to dark gray. Sets the fill color to
    // light gray otherwise
    if (isMouseOver()) {
      tank.fill(100);
    } else {
      tank.fill(200);
    }
    // draw the button (rectangle with a centered text)
    tank.rect(x - WIDTH / 2.0f, y - HEIGHT / 2.0f, x + WIDTH / 2.0f, y + HEIGHT / 2.0f);
    tank.fill(0); // set the fill color to black
    tank.text(label, x, y); // display the text of the current button
  }

  /**
   * Overrides the TankListener.mousePressed() method. Implements the default behavior of this
   * button when the mouse is pressed. This method must be overridden in the sub-classes to
   * implement a specific behavior if needed.
   */
  @Override
  public void mousePressed() {
    // TODO if the mouse is over this button, print
    // "A button was pressed." to the console
    if (isMouseOver()) {
      System.out.println("A button was pressed");
    }
  }

  /**
   * Overrides the TankListener.mouseReleased() method. Implements the default behavior of this
   * button when the mouse is released. This method must be overridden in the sub-classes to
   * implement a specific behavior if needed.
   */
  @Override
  public void mouseReleased() {
    // Leave this method empty
  }
}
