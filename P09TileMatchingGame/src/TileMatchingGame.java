//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Game
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
 * Tile Matching Game, which simulates a game with a set number of Tile Stack columns, which the
 * user can drop tiles onto. If a tile is dropped onto a tile with the same color as it in the same
 * column, both tiles are removed from the column. The game also supports clearing columns,
 * restarting the game entirely, and returning the overall game state as well as the state of
 * specified columns.
 * 
 * @author Roy Wang
 *
 */
public class TileMatchingGame {
  private TileStack[] columns; // array of TileStack

  /**
   * Creates a new matching tile game with a given number of columns and initializes its contents. A
   * new matching tile game stores an empty TileStack at each of its columns.
   * 
   * @param columnCount - number of columns in this game.
   * 
   * @throws IllegalArgumentException if columnNumber is less or equal to zero.
   */
  public TileMatchingGame(int columnCount) {
    // if columnCount is less than or equal to zero, throw exception.
    if (columnCount <= 0) {
      throw new IllegalArgumentException("invalid columnCount input");
    }
    // initialize columns, create an empty tileStack for each columns index.
    columns = new TileStack[columnCount];
    for (int i = 0; i < columns.length; i++) {
      columns[i] = new TileStack();
    }
  }

  /**
   * Removes all the tiles from a column with a given index.
   * 
   * @param index - of the column to clear
   * 
   * @throws IndexOutOfBoundsException - if index is out of bounds of the indexes of the columns of
   *                                   this game
   */
  public void clearColumn(int index) {
    // if index is out of bounds, throw exception.
    if (index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }
    while (!columns[index].isEmpty()) {
      columns[index].pop();
    }
  }

  /**
   * Returns a string representation of the stack of tiles at a given column index, and an empty
   * string "" if the stack is empty. For instance, if the stack at column index contains BLUE,
   * BLACK, and ORANGE blocks as follows, top: BLUE BLACK ORANGE the returned string will be: "BLUE
   * BLACK ORANGE"
   * 
   * 
   * @param index - the index of a column in this game
   * @return Returns a string representation of the stack of tiles at a given column index
   * 
   * @throws IndexOutOfBoundsException
   */
  public String column(int index) {
    // if index is out of bounds, throw exception
    if (index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }
    // if TileStack at index is empty, return empty string
    if (columns[index].isEmpty()) {
      return "";
    }
    // add all tiles in the TileStack to the StringBuffer while the iterator has next
    StringBuffer str = new StringBuffer();
    TileListIterator it = (TileListIterator) columns[index].iterator();
    while (it.hasNext()) {
      str.append(it.next() + " ");
    }
    return str.toString().trim();
  }

  /**
   * Drops a tile at the top of the stack located at the given column index. If tile will be dropped
   * at the top of an equal tile (of same color), both tiles will be removed from the stack of tiles
   * at column index.
   * 
   * @param tile  - a tile to drop
   * @param index - column position of the stack of tiles where tile will be dropped
   * 
   * @throws IndexOutOfBoundsException
   */
  public void dropTile(Tile tile, int index) {
    if (index < 0 || index >= columns.length) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }
    if (!columns[index].isEmpty() && tile.equals(columns[index].peek())) {
      columns[index].pop();
    } else {
      columns[index].push(tile);
    }
  }

  /**
   * Gets the number of columns in this tile matching game.
   * 
   * @return - the number of columns in this tile matching game
   */
  public int getColumnsNumber() {
    return columns.length;
  }

  /**
   * Restarts the game. All stacks of tiles in the different columns of this game will be empty.
   */
  public void restartGame() {
    // call clearColumn() for all indices of columns
    for (int i = 0; i < columns.length; i++) {
      clearColumn(i);
    }
  }


  /**
   * Returns a string representation of this tile matching game The format of the returned string is
   * as follows: GAME COLUMNS:\n [String representation of each column separated by newline] For
   * instance, a game with 4 columns will be represented as follows. GAME COLUMNS: 0: 1: BLACK BLUE
   * 2: ORANGE YELLOW 3: YELLOW.
   * 
   * @return String representation of this tile matching game.
   */
  public String toString() {
    StringBuffer str = new StringBuffer();
    str.append("GAME COLUMNS:\n");
    // if column isn't empty, add white space and column representation
    // otherwise, just add new line
    for (int i = 0; i < columns.length; i++) {
      str.append(i + ":");
      if (!column(i).equals("")) {
        str.append(" " + column(i));
      }
      str.append("\n");
    }
    return str.toString().trim();
  }

}
