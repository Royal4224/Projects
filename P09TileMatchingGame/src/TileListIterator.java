//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile List Iterator
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Tile List Iterator, which serves as an iterator on a singly-linked list of Tile nodes. Supports
 * hasNext() and next() methods, and implements Iterator<Tile>.
 * 
 * @author Roy Wang
 *
 */
public class TileListIterator implements Iterator<Tile> {
  private Node next; // Keeps track of next element in iteration

  /**
   * Creates a new iterator to iterate through a list of tiles starting from its head.
   * 
   * @param head - reference to the head of the linked list of tiles to traverse
   */
  public TileListIterator(Node head) {
    // Creates a new iterator to iterate through a list of tiles starting from its head
    // head is a reference to the head of the linked list of tiles
    next = head;
  }

  /**
   * Checks whether there are more tiles in the iteration.
   * 
   * @return true if iteration has more tiles, false otherwise
   */
  @Override
  public boolean hasNext() {
    // if next is null, no more nodes to iterate through, return false
    if (next == null) {
      return false;
    }
    // if next isn't null or it has a next itself, there is still a node(s) to iterate over
    return next != null || next.getNext() != null;
  }

  /**
   * Returns the next tile in the iteration.
   * 
   * @return the next tile in the iteration
   */
  @Override
  public Tile next() {
    // if a next doesn't exist, throw NSEE exception
    if (!hasNext()) {
      throw new NoSuchElementException("No more tiles to iterate over");
    }
    // get the tile at the current next node, and set next to it's next node.
    Tile tile = next.getTile();
    next = next.getNext();
    return tile;
  }

}
