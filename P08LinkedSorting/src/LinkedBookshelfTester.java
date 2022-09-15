//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Bookshelf Tester
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
 * Linked BookShelf Tester Class, which has several methods that test implementations of the
 * LinkedBookshelf and LinkedNode classes.
 * 
 * @author Roy Wang
 *
 */
public class LinkedBookshelfTester {

  /**
   * Tester method for the Linked Node class
   * 
   * @return true if all tests for Linked Node class pass (if Linked Node class works as expected)
   */
  public static boolean testLinkedNode() {

    try {

      // Scenario 1 - Call constructor that takes data only, call data getter and next getter
      int data = 1;
      LinkedNode<Integer> test = new LinkedNode<>(data);

      if (test.getData() != 1) {
        System.out.println("Problem detected: node must contain int 1 as data");
        return false;
      }
      if (test.getNext() != null) {
        System.out.println("Problem detected: node has a next node but shouldn't");
        return false;
      }

      // Scenario 2 - Call constructor that takes both data and a next node, call next getter

      LinkedNode<Integer> beforeTestNode = new LinkedNode<>(3, test);

      if (beforeTestNode.getNext() == null || !beforeTestNode.getNext().equals(test)) {
        System.out
            .println("Problem detected: node beforeTestNode should point to node test but doesn't");
        return false;
      }

      // Scenario 3 - Set a node's next node
      LinkedNode<Integer> afterTestNode = new LinkedNode<>(1);
      test.setNext(afterTestNode);

      if (test.getNext() == null) {
        System.out.println("Problem detected: next node should not be null");
        return false;
      }

      if (!test.getNext().equals(afterTestNode)) {
        System.out.println("Problem detected: node test does not point to node afterTestNode");
        return false;
      }
    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your LinkedNode class has thrown" + " a non expected exception.");
      return false;
    }

    return true;
  }



  /**
   * Tester method for the LinkedBookshelf's appendBook(Book toAdd) method
   * 
   * @return true if all tests for appendBook() method pass (if the method works as expected)
   */
  public static boolean testAddBooks() {
    Book.resetGenerator();
    try {
      LinkedBookshelf lb = new LinkedBookshelf();
      // Scenario 1 - add Book to an empty bookshelf
      Book test1 = new Book("test1", 1);
      lb.appendBook(test1);

      if (!lb.getFirst().getTitle().equals("test1") || !lb.getLast().getTitle().equals("test1")
          || lb.size() != 1) {
        System.out.println("Problem detected: front or back node has not been set to node test");
        return false;
      }

      // Scenario 2 - add Book to a non-empty bookshelf
      LinkedNode<Book> test2 = new LinkedNode<>(new Book("test2", 2));

      lb.appendBook(test2.getData());

      if (!lb.getFirst().getTitle().equals("test1") || !lb.getLast().getTitle().equals("test2")
          || lb.size() != 2) {
        System.out.println("Problem detected: back node does not point to node test2,"
            + " or front node was changed (does not point to node test1)");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your LinkedBookshelf.appendBook() method has thrown"
          + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Tester method for LinkedBookshelf's clear() method
   * 
   * @return true if all tests for appendBook() method pass (if the method works as expected)
   */
  public static boolean testClear() {
    Book.resetGenerator();
    try {
      LinkedBookshelf lb = new LinkedBookshelf();
      // Scenario 1 - clear a non-empty bookshelf
      LinkedNode<Book> test1 = new LinkedNode<>(new Book("test1", 1));
      LinkedNode<Book> test2 = new LinkedNode<>(new Book("test2", 2));

      lb.appendBook(test1.getData());
      lb.appendBook(test2.getData());
      lb.clear();

      try {
        lb.getFirst();
        if (lb.getFirst() != null) {
          return false;
        }
      } catch (NullPointerException npe) {
        // expected behavior
      }

      try {
        lb.getLast();
        if (lb.getLast() != null) {
          return false;
        }
      } catch (NullPointerException npe) {
        // expected behavior
      }

      if (lb.size() != 0) {
        System.out.println("Non-Empty bookshelf was not cleared");
        return false;
      }

      // Scenario 2 - clear an empty bookshelf
      LinkedBookshelf empty = new LinkedBookshelf();
      empty.clear();

      try {
        lb.getFirst();
        if (lb.getFirst() != null) {
          return false;
        }
      } catch (NullPointerException npe) {
        // expected behavior
      }

      try {
        lb.getLast();
        if (lb.getLast() != null) {
          return false;
        }
      } catch (NullPointerException npe) {
        // expected behavior
      }
      if (empty.size() != 0) {
        System.out.println("empty bookshelf is not empty after being cleared");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your LinkedBookshelf.clear() method has thrown"
          + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Tester method for the LinkedBookshelf.sortBooks() method
   * 
   * @return true if all tests for sortBooks() method pass (if the method works as expected)
   */
  public static boolean testSortBooks() {
    Book.resetGenerator();
    try {
      LinkedBookshelf lb = new LinkedBookshelf();

      /*
       * LinkedNode<Book> goodOmens = new LinkedNode<Book>(new Book("Good Omens", 288, "Gaman",
       * "Neil")); LinkedNode<Book> feed = new LinkedNode<Book>(new Book("FEED", 608, "Grant",
       * "Mira")); LinkedNode<Book> snowCrash = new LinkedNode<Book>(new Book("Snow Crash", 468,
       * "Stephenson", "Neal")); LinkedNode<Book> two001 = new LinkedNode<Book>(new Book("2001",
       * 296, "Clarke", "Arthur C"));
       */
      
      LinkedBookshelf.sort(lb, Attribute.ID);
      if (!lb.toString().equals("ID" + "\n")) {
        return false;
      }
      
      Book GoodOmens = new Book("Good Omens", 288, "Gaman", "Neil");
      Book FEED = new Book("FEED", 608, "Grant", "Mira");
      Book SnowCrash = new Book("Snow Crash", 468, "Stephenson", "Neal");
      Book twoZero01 = new Book("2001", 296, "Clarke", "Arthur C");

      lb.appendBook(GoodOmens);
      lb.appendBook(FEED);
      lb.appendBook(SnowCrash);
      lb.appendBook(twoZero01);

      LinkedBookshelf.sort(lb, Attribute.TITLE);

      String titleSort = "TITLE" + "\n" + twoZero01.toString() + "\n" + FEED.toString() + "\n"
          + GoodOmens.toString() + "\n" + SnowCrash.toString() + "\n";

      if (!lb.toString().equals(titleSort)) {
        System.out.println("Sorting by title did not produce desired result");
        return false;
      }

      LinkedBookshelf.sort(lb, Attribute.PAGECOUNT);
      if (!lb.toString().contains("PAGECOUNT")) {
        return false;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: Your LinkedBookshelf.clear() method has thrown"
          + " a non expected exception.");
      return false;
    }

    return true;
  }

  /**
   * Runs all tester methods in this class
   * 
   * @return true if all tests return true (all methods work as intended)
   */
  public static boolean runAllTests() {

    return testLinkedNode() && testAddBooks() && testClear() && testSortBooks();
  }

  public static void main(String[] args) {
    /*
     * Book GoodOmens = new Book("Good Omens", 288, "Gaman", "Neil"); Book FEED = new Book("FEED",
     * 608, "Grant", "Mira"); Book SnowCrash = new Book("Snow Crash", 468, "Stephenson", "Neal");
     * Book twoZero01 = new Book("2001", 296, "Clarke", "Arthur C"); String titleSort = "TITLE" +
     * "\n" + twoZero01.toString() + "\n" + FEED.toString() + "\n" + GoodOmens.toString() + "\n" +
     * SnowCrash.toString(); System.out.println(titleSort); LinkedNode<Book> test1 = new
     * LinkedNode<>(new Book("test1", 1)); LinkedNode<Book> test2 = test1;
     * System.out.println(test1.equals(test2));
     */
    System.out.println(runAllTests());
  }

}
