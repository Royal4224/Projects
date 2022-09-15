//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P11 Assignment Queue
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
 * Array-based heap implementation of a priority queue containing Assignments. Guarantees the
 * min-heap invariant, so that the Assignment at the root should have the earliest due date, and
 * children always have a due date after or at the same time as their parent. The root of a
 * non-empty queue is always at index 0 of this array-heap.
 * 
 * @author Roy Wang
 */
public class AssignmentQueue implements PriorityQueueADT<Assignment>, Iterable<Assignment> {
  private Assignment[] queue; // array min-heap of assignments representing this priority queue
  private int size; // size of this priority queue


  /**
   * Creates a new empty AssignmentQueue with the given capacity
   * 
   * @param capacity Capacity of this AssignmentQueue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer
   */
  public AssignmentQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("capacity must be a postive integer");
    }
    this.size = 0;
    queue = new Assignment[capacity];
  }

  /**
   * Checks whether this AssignmentQueue is empty
   * 
   * @return {@code true} if this AssignmentQueue is empty
   */
  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the size of this AssignmentQueue
   * 
   * @return the size of this AssignmentQueue
   */
  @Override
  public int size() {
    return this.size;
  }

  /**
   * Returns the capacity of this AssignmentQueue
   * 
   * @return the capacity of this AssignmentQueue
   */
  public int capacity() {
    return queue.length;
  }


  /**
   * Removes all elements from this AssignmentQueue
   */
  @Override
  public void clear() {
    // reset AssignmentQueue
    int capacity = queue.length;
    queue = new Assignment[capacity];
    size = 0;
  }

  /**
   * Returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment with the
   * earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException if this AssignmentQueue is empty
   */
  @Override
  public Assignment peek() {
    if (isEmpty()) {
      throw new NoSuchElementException("Empty AssignmentQueue");
    }
    return queue[0];
  }


  /**
   * Adds the given Assignment to this AssignmentQueue at the correct position based on the min-heap
   * ordering. This queue should maintain the min-heap invariant, so that the Assignment at each
   * index has an earlier or equivalent due-date than the Assignments in its child nodes.
   * Assignments should be compared using the Assignment.compareTo() method.
   * 
   * @param e Assignment to add to this AssignmentQueue
   * @throws NullPointerException  if the given Assignment is null
   * @throws IllegalStateException with a descriptive error message if this AssignmentQueue is full
   */
  @Override
  public void enqueue(Assignment e) {
    // if assignment is null, throw NullPointerException
    // if queue is full, throw IllegalStateException
    if (e == null) {
      throw new NullPointerException("given Assignment is null");
    }

    if (size == queue.length) {
      throw new IllegalStateException("AssignmentQueue is full");
    }

    // if queue is empty, no need to percolate, just insert and increment, return;
    if (isEmpty()) {
      queue[size] = e;
      size++;
      return;
    }
    // insert at the last element, and percolate up the heap. increment size appropriately
    queue[size] = e;
    percolateUp(size);
    size++;
  }

  /**
   * Removes and returns the Assignment at the root of this AssignmentQueue, i.e. the Assignment
   * with the earliest due date.
   * 
   * @return the Assignment in this AssignmentQueue with the earliest due date
   * @throws NoSuchElementException with a descriptive error message if this AssignmentQueue is
   *                                empty
   */
  @Override
  public Assignment dequeue() {
    // if queue is empty, throw NoSuchElementException
    if (isEmpty()) {
      throw new NoSuchElementException("Cannot dequeue from an empty AssignmentQueue");
    }

    // swap the root and the largest element
    // null out the last element's slot, decrement size appropriately, and percolate down the heap
    Assignment root = queue[0];
    Assignment newRoot = queue[size - 1];
    queue[0] = newRoot;
    queue[size - 1] = null;
    size--;
    percolateDown(0);

    return root;
  }

  /**
   * Recursive implementation of percolateDown() method. Restores the min-heap invariant of a given
   * subtree by percolating its root down the tree. If the element at the given index does not
   * violate the min-heap invariant (it is due before its children), then this method does not
   * modify the heap. Otherwise, if there is a heap violation, then swap the element with the
   * correct child and continue percolating the element down the heap.
   * 
   * @param i index of the element in the heap to percolate downwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateDown(int i) {
    // TODO provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(n))

    // if invalid index, throw IOOB Exception
    if (i < 0 || i >= queue.length) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }

    // if i is the last child, we have no further to go, return;
    if (i == size) {
      return;
    }

    // get children of this element
    int leftChild = 2 * i + 1;
    int rightChild = 2 * i + 2;

    // if leftChild exists and this element is greater than the left Child, swap and percolate down
    if (leftChild < size && queue[i].compareTo(queue[leftChild]) > 0) {
      swap(i, leftChild);
      percolateDown(leftChild);
    }
    // if Right exists and this element is greater than the right Child, swap and percolate down
    if (rightChild < size && queue[i].compareTo(queue[rightChild]) > 0) {
      swap(i, rightChild);
      percolateDown(rightChild);
    }
  }

  /**
   * Recursive implementation of percolateUp() method. Restores the min-heap invariant of the tree
   * by percolating a leaf up the tree. If the element at the given index does not violate the
   * min-heap invariant (it occurs after its parent), then this method does not modify the heap.
   * Otherwise, if there is a heap violation, swap the element with its parent and continue
   * percolating the element up the heap.
   * 
   * @param i index of the element in the heap to percolate upwards
   * @throws IndexOutOfBoundsException if index is out of bounds - do not catch the exception
   */
  protected void percolateUp(int i) {
    // TODO provide the worst-case runtime complexity of this method assuming that the problem size
    // N is the size of this queue
    // Time complexity: O(log(n))

    // if invalid index, throw IOOB Exception
    if (i < 0 || i >= queue.length) {
      throw new IndexOutOfBoundsException("index out of bounds");
    }

    // if the Assignment at i is the root, we have no further to go, and return;
    if (i == 0) {
      return;
    }

    // get the parentIndex; if the parent is greater than this, swap and recursive call to
    // percolateUp on the updated element
    // else, don't modify the heap
    int parentIndex = (i - 1) / 2;
    if (queue[i].compareTo(queue[parentIndex]) < 0) {
      swap(i, parentIndex);
      percolateUp(parentIndex);
    }
  }

  /**
   * Helper method that swaps Assignments at two given indices.
   * 
   * @param i - first index
   * @param j - second index
   */
  private void swap(int i, int j) {
    // swap the assignments at i and j
    Assignment temp = queue[j];
    queue[j] = queue[i];
    queue[i] = temp;

  }

  /**
   * Returns a deep copy of this AssignmentQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * assignments. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this AssignmentQueue. The returned new assignment queue has the same
   *         length and size as this queue.
   */
  public AssignmentQueue deepCopy() {
    AssignmentQueue copy = new AssignmentQueue(queue.length);

    /**
     * Copy each Assignment from this' queue to the copy's queue Copy this queue's size to the
     * copy's queue
     */
    for (int i = 0; i < queue.length; i++) {
      copy.queue[i] = queue[i];
      copy.size = this.size;
    }
    return copy;
  }

  /**
   * Returns a String representing this AssignmentQueue, where each element (assignment) of the
   * queue is listed on a separate line, in order from earliest to latest.
   * 
   * @see Assignment#toString()
   * @see AssignmentIterator
   * @return a String representing this AssignmentQueue
   */
  public String toString() {
    StringBuilder val = new StringBuilder();
    // iterate through heap; while has next, append the next assignment
    // return built string when done
    AssignmentIterator at = (AssignmentIterator) iterator();
    while (at.hasNext()) {
      val.append(at.next());
      val.append("\n");
    }
    return val.toString();
  }

  /**
   * Returns an Iterator for this AssignmentQueue which proceeds from the earliest to the latest
   * Assignment in the queue.
   * 
   * @see AssignmentIterator
   * @return an Iterator for this AssignmentQueue
   */
  @Override
  public Iterator<Assignment> iterator() {
    return new AssignmentIterator(this);
  }
}
