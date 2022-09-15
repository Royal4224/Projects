//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Linked Node
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
 * Linked Node Class, represents a node in a linked list that stores data of generic type, and keeps
 * track of the next node in the list. Has getters for next node and the data stored in the current
 * node, as well as a setter for the next node.
 * 
 * @author Roy Wang
 *
 */

public class LinkedNode<T> {

  private T data; // generic data stored in this LinkedNode
  private LinkedNode<T> next; // the node that this node points to "next"

  /**
   * Constructor that sets private instance field data to given input
   * 
   * @param data - the generic data to be stored in this LinkedNode
   */
  public LinkedNode(T data) {
    this.data = data;
  }

  /**
   * Constructor that sets the data stored in this node to the given data, as well as the next
   * pointer to the given LinkedNode.
   * 
   * @param data - the generic data to be stored in this LinkedNode
   * @param next - the LinkedNode to be set as the next node of this node.
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }

  /**
   * Getter for the next node pointer of this node
   * 
   * @return next node to this node
   */
  public LinkedNode<T> getNext() {
    return next;
  }

  /**
   * Getter for the data stored in this node.
   * 
   * @return the data stored in this node
   */
  public T getData() {
    return data;
  }

  /**
   * Return the toString representation of the data stored in this node.
   */
  @Override
  public String toString() {
    return data.toString();
  }

  /**
   * Setter for the next node pointer of this node.
   * 
   * @param next - the LinkedNode to be set as the next node of this node.
   */
  public void setNext(LinkedNode<T> next) {
    this.next = next;
  }
}
