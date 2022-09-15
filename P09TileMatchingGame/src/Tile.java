//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile
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
 * This class models a Tile of a specific color, and has getters for the color and string
 * representation of the current Tile. Also supports an equals method, which determines equality
 * based on the colors of the compared Tiles.
 * 
 * @author Roy Wang
 *
 */
public class Tile {
  private Color color; // color of this Tile

  /**
   * Creates a Tile with a specific color
   * 
   * @param color color to be assigned to this tile
   */
  public Tile(Color color) {
    this.color = color;
  }

  /**
   * Gets the color of this tile
   * 
   * @return the color of this tile
   */
  public Color getColor() {
    return color;
  }


  /**
   * Returns a string representation of this tile
   * 
   * @return the color of this tile as a string
   */
  @Override
  public String toString() {
    return color.name();
  }


  /**
   * Checks whether this tile equals to the other object provided as input
   * 
   * @return true if other is a Tile and has the same color as this tile
   */
  @Override
  public boolean equals(Object other) {
    // if object is null, return false
    if (other == null) {
      return false;
    }
    // if instanceof Tile and other's color equals this' color, return true;
    if (other instanceof Tile) {
      Tile otherTile = (Tile) other;
      if (otherTile.getColor() == this.color) {
        return true;
      }
    }
    return false;
  }
}
