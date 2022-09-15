//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Stack
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

import java.util.EmptyStackException;
import java.util.Iterator;

/**
 * Tile Stack, which represents a linked stack of Tiles which keeps track of the top Tile. Tiles can
 * be pushed into, popped off, and peeked from the stack. TileStack also supports an iterator to
 * iterate through the stack from top to bottom, and has getters for size and the empty-state of the
 * stack.
 * 
 * @author Roy Wang
 *
 */
public class TileStack implements Iterable<Tile>, StackADT<Tile> {
  private Node top; // refers to the top of the linked stack.
  private int size; // keeps track of the number of tiles stored in the stack

  public TileStack() {
    top = null;
    size = 0;
  }

  /**
   * Pushes the provided tile at top of this stack.
   * 
   * @param element - an element to be added
   */
  @Override
  public void push(Tile tile) {
    Node newTile = new Node(tile);
    // if Tile Stack is empty, done after setting new Tile to top
    // else, set the top to the new tile, and top's next to the previous top
    // increment size appropriately
    if (isEmpty()) {
      top = newTile;
    } else {
      Node currTop = top;
      top = newTile;
      top.setNext(currTop);
    }
    size++;
  }

  /**
   * Removes and returns the tile at the top of this stack.
   * 
   * @return the removed tile
   * 
   * @throws EmptyStackException
   */
  @Override
  public Tile pop() {
    // if Tile Stack is empty, throw exception
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    Tile topTile = top.getTile();
    // if Tile Stack has 3 or more elements, set top to top's next, and set top's next pointer to
    // top's next next
    // if TileStack has 2 elements, set top to top's next and top's next pointer to null
    // else, Tile Stack has 1 element, so set top to null
    if (size >= 3) {
      top = top.getNext();
      top.setNext(top.getNext());
    } else if (size == 2) {
      top = top.getNext();
      top.setNext(null);
    } else {
      top = null;
    }
    // decrement size appropriately
    size--;
    return topTile;
  }

  /**
   * Returns the tile at the top of this stack.
   * 
   * @return the tile at the top of this stack.
   * 
   * @throws EmptyStackException
   */
  @Override
  public Tile peek() {
    // if Tile Stack is empty, throw exception
    // otherwise, return the top's tile
    if (isEmpty()) {
      throw new EmptyStackException();
    }
    return top.getTile();
  }

  /**
   * Check whether this stack is empty.
   * 
   * @return true if this stack contains no elements, otherwise false.
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this stack.
   * 
   * @return the size of this stack
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns an iterator to iterate through this stack starting from its top.
   * 
   * @return an iterator to iterate through elements of type Tile
   */
  @Override
  public Iterator<Tile> iterator() {
    TileListIterator iterator = new TileListIterator(top);
    return iterator;
  }

}
