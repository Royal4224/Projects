//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P11 Assignment Queue Tester
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

import java.util.NoSuchElementException;

/**
 * Tester class for the AssignmentQueue implementation of PriorityQueueADT
 * 
 * @author Roy Wang
 */
public class AssignmentQueueTester {
  /**
   * Tests the functionality of the constructor for AssignmentQueue Should implement at least the
   * following tests:
   *
   * - Calling the AssignmentQueue with an invalid capacity should throw an IllegalArgumentException
   * - Calling the AssignmentQueue with a valid capacity should not throw any errors, and should
   * result in a new AssignmentQueue which is empty, and has size 0
   *
   * @return true if the constructor of AssignmentQueue functions properly
   * @see AssignmentQueue#AssignmentQueue(int)
   */
  public static boolean testConstructor() {
    Assignment.resetGenerator();
    try {
      AssignmentQueue aq;
      // Scenario 1 - invalid capacity input
      try {
        aq = new AssignmentQueue(-1);
        return false;
      } catch (IllegalArgumentException e) {
        // expected behavior
      }
      try {
        aq = new AssignmentQueue(0);
        return false;
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      // Scenario 2 - valid constructor call
      aq = new AssignmentQueue(1);
      if (!aq.isEmpty() || aq.size() != 0 || aq.capacity() != 1) {
        System.out.println("constructor error");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Your AssignmentQueue constructor threw an unexpected exception");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the enqueue() and peek() methods Should implement at least the
   * following tests:
   *
   * - Calling peek on an empty queue should throw a NoSuchElementException - Calling enqueue on a
   * queue which is empty should add the Assignment, and increment the size of the queue - Calling
   * enqueue on a non-empty queue should add the Assignment at the proper position, and increment
   * the size of the queue. Try add at least 5 assignments - Calling peek on a non-empty queue
   * should always return the Assignment with the earliest due date - Calling enqueue on a full
   * queue should throw an IllegalStateException - Calling enqueue with a null Assignment should
   * throw a NullPointerException
   *
   * @return true if AssignmentQueue.enqueue() and AssignmentQueue.peek() function properly
   */
  public static boolean testEnqueue() {
    Assignment.resetGenerator();
    try {
      AssignmentQueue aq = new AssignmentQueue(5);
      // Scenario 1 - call peek on an empty queue
      try {
        aq.peek();
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }
      Assignment p1 = new Assignment("p1", 9, 23, 10);
      Assignment p2 = new Assignment("p2", 10, 12, 11);
      Assignment p3 = new Assignment("p3", 10, 15, 12);
      Assignment p4 = new Assignment("p4", 11, 1, 5);
      Assignment p5 = new Assignment("p5", 12, 15, 0);

      // Scenario 2 - call enqueue on an empty queue
      aq.enqueue(p1);
      String str = aq.toString();
      String expected = p1.toString() + "\n";
      if (aq.isEmpty() || aq.size() != 1 || !str.equals(expected)) {
        System.out.println("improper incrementation of size");
      }

      // Scenario 3 - call enqueue on a non-empty queue
      aq = new AssignmentQueue(5);
      aq.enqueue(p2);
      aq.enqueue(p5);
      aq.enqueue(p4);
      aq.enqueue(p3);
      aq.enqueue(p1);

      str = aq.toString();
      expected = p1.toString() + "\n" + p2.toString() + "\n" + p3.toString() + "\n" + p4.toString()
          + "\n" + p5.toString() + "\n";
      if (aq.isEmpty() || aq.size() != 5 || !str.equals(expected)) {
        System.out
            .println("improper incrementation of size or Assignments insert at incorrect postions");
        return false;
      }

      // Scenario 4 - call peek on non-empty queue
      aq = new AssignmentQueue(5);
      aq.enqueue(p2);
      aq.enqueue(p5);
      aq.enqueue(p4);
      if (!aq.peek().equals(p2)) {
        System.out.println("error with peek method");
        return false;
      }
      aq.enqueue(p3);
      if (!aq.peek().equals(p2)) {
        System.out.println("error with peek method");
        return false;
      }
      aq.enqueue(p1);
      if (!aq.peek().equals(p1)) {
        System.out.println("error with peek method");
        return false;
      }

      // Scenario 5 - call enqueue on full queue
      Assignment p6 = new Assignment("p6", 1, 1, 1);
      try {
        aq.enqueue(p6);
        return false;
      } catch (IllegalStateException e) {
        // expected behavior
      }

      // Scenario 6 - call enqueue with a null Assignment
      try {
        aq.enqueue(null);
        return false;
      } catch (NullPointerException e) {
        // expected behavior
      }
    } catch (Exception e) {
      System.out.println("Your enqueue() or peek() method has thrown an unexpected exception");
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of dequeue() and peek() methods. The peek() method must return without
   * removing the assignment with the highest priority in the queue. The dequeue() method must
   * remove, and return the assignment with the highest priority in the queue. The size must be
   * decremented by one, each time the dequeue() method is successfully called. Try to check the
   * edge cases (calling peek and dequeue on an empty queue, and calling dequeue on a queue of size
   * one). For normal cases, try to consider dequeuing from a queue whose size is at least 6. Try to
   * consider cases where percolate-down recurses on left and right.
   * 
   * @return true if AssignmentQueue.dequeue() and AssignmentQueue.peek() function properly
   */
  public static boolean testDequeuePeek() {
    Assignment.resetGenerator();
    try {
      // Scenario 1 - peek and dequeue on an empty queue
      AssignmentQueue aq = new AssignmentQueue(50);

      try {
        aq.peek();
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      try {
        aq.dequeue();
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 2 - call dequeue on queue of size 1
      Assignment p7 = new Assignment("7", 1, 7, 1);
      aq.enqueue(p7);
      Assignment dequeued = aq.dequeue();
      if (!dequeued.equals(p7) || aq.size() != 0 || !aq.isEmpty()) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      // Scenario 3 - Normal case, dequeue on a queue of size 6+
      Assignment p8 = new Assignment("8", 1, 8, 1);
      Assignment p12 = new Assignment("12", 1, 12, 1);
      Assignment p15 = new Assignment("15", 1, 15, 1);
      Assignment p10 = new Assignment("10", 1, 10, 1);
      Assignment p20 = new Assignment("20", 1, 20, 1);
      Assignment p35 = new Assignment("35", 1, 35, 1);

      aq.enqueue(p7);
      aq.enqueue(p8);
      aq.enqueue(p12);
      aq.enqueue(p15);
      aq.enqueue(p10);
      aq.enqueue(p20);
      aq.enqueue(p35);

      dequeued = aq.dequeue();
      if (!dequeued.equals(p7) || aq.size() != 6) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      dequeued = aq.dequeue();
      if (!dequeued.equals(p8) || aq.size() != 5) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      dequeued = aq.dequeue();
      if (!dequeued.equals(p10) || aq.size() != 4) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      // all of these dequeue's are a part of Scenario 3, testing dequeue on queue of size 6+
      dequeued = aq.dequeue();
      if (!dequeued.equals(p12) || aq.size() != 3) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      dequeued = aq.dequeue();
      if (!dequeued.equals(p15) || aq.size() != 2) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      dequeued = aq.dequeue();
      if (!dequeued.equals(p20) || aq.size() != 1) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }

      dequeued = aq.dequeue();
      if (!dequeued.equals(p35) || aq.size() != 0 || !aq.isEmpty()) {
        System.out.println("problem with dequeue/size decrementation");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Your dequeue() or peek() method has thrown an unexpected exception");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the clear() method Should implement at least the following
   * scenarios: - clear can be called on an empty queue with no errors - clear can be called on a
   * non-empty queue with no errors - After calling clear, the queue should contain no Assignments
   *
   * @return true if AssignmentQueue.clear() functions properly
   */
  public static boolean testClear() {
    Assignment.resetGenerator();
    try {

      // Scenario 1 - call clear on empty queue
      AssignmentQueue aq = new AssignmentQueue(5);
      aq.clear();
      if (!aq.isEmpty() || aq.size() != 0 || aq.capacity() != 5) {
        System.out.println("error with clear()");
        return false;
      }

      // Scenario 2 - call clear on non-empty queue
      aq = new AssignmentQueue(6);
      Assignment p2 = new Assignment("p2", 10, 12, 11);
      Assignment p4 = new Assignment("p4", 11, 1, 5);
      Assignment p5 = new Assignment("p5", 12, 15, 0);

      aq.enqueue(p5);
      aq.enqueue(p2);
      aq.enqueue(p4);

      aq.clear();
      if (!aq.isEmpty() || aq.size() != 0 || aq.capacity() != 6) {
        System.out.println("error with clear()");
        return false;
      }

      // Scenario 3 - ensure queue has no Assignments after calling clear
      AssignmentIterator at = (AssignmentIterator) aq.iterator();
      if (at.hasNext()) {
        System.out.println("queue shouldn't have assignments");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Your clear() method has thrown an unexpected exception");
      return false;
    }

    return true;
  }

  /**
   * Tests all the methods of the AssignmentQueue class
   * 
   */
  public static boolean runAllTests() {
    Assignment.resetGenerator();
    /*
     * testConstructor(); testEnqueue(); testDequeuePeek(); testClear();
     */

    return testConstructor() && testEnqueue() && testDequeuePeek() && testClear();
  }

  /**
   * Main method
   * 
   * @param args input arguments if any
   */
  public static void main(String[] args) {
    System.out.println(runAllTests());
    /*
     * AssignmentQueue aq = new AssignmentQueue(50); Assignment p10 = new Assignment("CS300 P10",
     * 12, 6, 22); Assignment p11 = new Assignment("CS300 P11", 12, 13, 22); Assignment fe = new
     * Assignment("CS300 Final Exam", 12, 20, 17); Assignment HW240 = new Assignment("CS240 HW10",
     * 12, 06, 17); aq.enqueue(p10); aq.enqueue(p11); aq.enqueue(fe); aq.enqueue(HW240);
     * aq.toString();
     */
  }
}
