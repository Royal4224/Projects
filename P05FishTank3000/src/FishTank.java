//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Fish Tank 3000
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
import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;
import processing.core.PImage;

/**
 * Fish Tank Application that extends PApplet, incorporates interactive buttons, and contains/adds
 * different kinds of fish and decorations
 * 
 * @author Roy Wang
 */

public class FishTank extends PApplet {
  private PImage backgroundImage; // PImage object which represents the background image
  protected ArrayList<TankListener> objects; // list storing interactive objects
  protected Random randGen; // Generator of random numbers
  private TankObject flower; // Flower decoration
  private TankObject log; // log decoration
  private TankObject ship; // Ship decoration
  private TankObject shell; // shell decoration


  /**
   * Sets the size of this PApplet to 800 width x 600 height
   */
  @Override
  public void settings() {
    size(800, 600);
  }

  /**
   * Defines initial environment properties such as screen size and loads the background image and
   * fonts as the program starts. It also initializes all data fields.
   */
  @Override
  public void setup() {
    // Set and display the title of the display window
    this.getSurface().setTitle("Fish Tank 3000");
    // Set the location from which images are drawn to CENTER
    this.imageMode(PApplet.CENTER);
    // Set the location from which rectangles are drawn.
    this.rectMode(PApplet.CORNERS);
    // rectMode(CORNERS) interprets the first two parameters of rect() method
    // as the location of one corner, and the third and fourth parameters as
    // the location of the opposite corner.
    // rect() method draws a rectangle to the display window

    this.focused = true; // Confirms that our Processing program is focused,
    // meaning that it is active and will accept mouse or keyboard input.

    // sets the text alignment to center
    this.textAlign(PApplet.CENTER, PApplet.CENTER);

    // load the background image, create empty AL, create randGen

    this.backgroundImage = this.loadImage("images/background.png");

    objects = new ArrayList<>();

    randGen = new Random();

    TankObject.setProcessing(this);
    // declare our decoration objects and add them to the objects list
    flower = new TankObject(430, 60, "images" + File.separator + "flower.png");
    log = new TankObject(580, 470, "images" + File.separator + "log.png");
    shell = new TankObject(65, 520, "images" + File.separator + "shell.png");
    ship = new TankObject(280, 535, "images" + File.separator + "ship.png");

    addObject(flower);
    addObject(log);
    addObject(shell);
    addObject(ship);

    // add our black fish
    addObject(new BlackFish(log, flower));
    addObject(new BlackFish(shell, flower));

    Button.setProcessing(this);
    // add our buttons
    addObject(new AddBlueFishButton(43, 16));
    addObject(new AddOrangeFishButton(129, 16));
    addObject(new AddYellowFishButton(215, 16));
    addObject(new ClearTankButton(301, 16));
  }

  /**
   * Continuously draws and updates the application display window
   */
  @Override
  public void draw() {
    // clear the display window by drawing the background image
    this.image(backgroundImage, this.width / 2, this.height / 2);

    // traverse the objects list and draw each of the objects to this display window
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).draw();
    }

  }

  /**
   * Callback method called each time the user presses the mouse
   */
  @Override
  public void mousePressed() {
    // traverse the objects list and call mousePressed method of the first object being clicked in
    // the list
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i).isMouseOver()) {
        objects.get(i).mousePressed();
        break;
      }
    }
  }

  /**
   * Callback method called each time the mouse is released
   */
  @Override
  public void mouseReleased() {
    // TODO traverse the objects list and call each object's mouseReleased() method
    for (int i = 0; i < objects.size(); i++) {
      objects.get(i).mouseReleased();
    }
  }

  /**
   * Adds an instance of TankListener passed as input to the objects ArrayList
   * 
   * @param object
   */
  public void addObject(TankListener object) {
    objects.add(object);
  }

  /**
   * Callback method called each time the user presses a key
   */
  @Override
  public void keyPressed() {
    switch (Character.toUpperCase(this.key)) {
      case 'O': // if O is pressed, create a new orange fish, add it to objects AL
        objects.add(new Fish());
        break;
      case 'Y': // if Y is pressed, create a new yellow fish with speed 2, add it to objects AL
        objects.add(new Fish(2, "images" + File.separator + "yellow.png"));
        break;
      case 'B': // if B is pressed, create a new blue fish, add it to objects AL
        objects.add(new BlueFish());
      case 'R': // if R is pressed, delete the first instance of fish being dragged, if any
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i).isMouseOver() && objects.get(i) instanceof Fish) {
            objects.remove(i);
            break;
          }
        }
        break;
      case 'S': // if S is pressed, call swim() from every instance of Fish stored in objects AL
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            Fish temp = (Fish) objects.get(i);
            temp.startSwimming();
          }
        }
        break;
      case 'X': // if X is pressed, stop swimming all instances of Fish stored in objects AL
        for (int i = 0; i < objects.size(); i++) {
          if (objects.get(i) instanceof Fish) {
            Fish temp = (Fish) objects.get(i);
            temp.stopSwimming();
          }
        }
        break;
      case 'C': // if C is pressed, remove all instances of Fish from tank
        this.clear();
        break;
    }
  }

  /**
   * Clears the fish tank by removing all TankListener objects from the objects AL, and redraws the
   * background
   */
  public void clear() {
    // remove all instances of Fish from the objects AL. once removed, decrement the pointer to
    // properly adjust for the updated AL size.
    for (int i = 0; i < objects.size(); i++) {
      if (objects.get(i) instanceof Fish) {
        objects.remove(i);
        i--;
      }
    }
    // redraw the background and other remaining objects
    this.draw();
  }

  public static void main(String[] args) {
    PApplet.main("FishTank"); // do not add any other statement to the main method
    // The PApplet.main() method takes a String input parameter which represents
    // the name of your PApplet class.
  }
}
