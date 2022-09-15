//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf Tester
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

import java.util.ArrayList;

/**
 * Binary Bookshelf Tester, a tester class for the BinaryBookshelf and TreeNode classes. It tests
 * various methods in BinaryBookshelf and TreeNode and tests their functionality.
 * 
 * @author Roy Wang
 *
 */

public class BinaryBookshelfTester {

  /**
   * This method tests the TreeNode class. Returns true if all tests finish with no bugs, false
   * otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  public static boolean testTreeNode() {
    Book.resetGenerator();
    try {

      // Scenario 1: a single TreeNode with no children
      TreeNode<Integer> intNode = new TreeNode<>(1);

      if (intNode.getLeft() != null || intNode.getRight() != null) {
        System.out.println("Left or right node should be null, but isn't");
        return false;
      }

      if (intNode.getData() != 1) {
        System.out.println("data should be equal to int 1, but isn't");
        return false;
      }

      if (!intNode.toString().equals("1")) {
        System.out.println("String representation of data should be \"1\", but isn't");
        return false;
      }
      // Scenario 2: a simple collection of TreeNodes
      TreeNode<Integer> one = new TreeNode<>(1);
      TreeNode<Integer> two = new TreeNode<>(2);
      one.setLeft(two);

      if (one.getRight() != null || one.getLeft() == null || one.getLeft().getData() != 2) {
        System.out.println("error with setters or getters for left and right (or both)");
        return false;
      }
      one.setLeft(null);

      if (intNode.getLeft() != null || intNode.getRight() != null) {
        System.out.println("left or right node should be null, but isn't");
        return false;
      }

      // Scenario 3: multiple-arg constructor
      TreeNode<Integer> three = new TreeNode<>(3);
      one = new TreeNode<>(1, two, three);

      if (one.getLeft().getData() != 2 || one.getRight().getData() != 3) {
        System.out
            .println("left node should be 2, right node should be 3, but one (or both) aren't");
        return false;
      }
    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your Tree Node class has thrown" + " a non expected exception.");
    }

    return true;
  }

  /**
   * This method tests the scenario of an empty BinaryBookshelf. Returns true if all tests finish
   * with no bugs, false otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  public static boolean testEmptyTree() {
    Book.resetGenerator();
    try {
      // Scenario 1: invalid constructor inputs
      BinaryBookshelf bb;
      Attribute[] empty = new Attribute[0];
      try {
        bb = new BinaryBookshelf(empty);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      Attribute[] invalid = new Attribute[5];
      try {
        bb = new BinaryBookshelf(invalid);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      Attribute[] duplicate =
          {Attribute.AUTHOR, Attribute.AUTHOR, Attribute.ID, Attribute.PAGECOUNT};
      try {
        bb = new BinaryBookshelf(duplicate);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      Attribute[] authorNotFirst =
          {Attribute.ID, Attribute.AUTHOR, Attribute.TITLE, Attribute.PAGECOUNT};
      try {
        bb = new BinaryBookshelf(authorNotFirst);
      } catch (IllegalArgumentException e) {
        // expected behavior
      }
      // Scenario 2: valid input
      Attribute[] valid = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      bb = new BinaryBookshelf(valid);
      if (!emptyTreeScenario2(bb)) {
        System.out.println("Problem detected: Scenario 2 on an empty tree has thrown"
            + " a non expected exception.");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: Your methods on an empty tree has thrown"
          + " a non expected exception.");
    }

    return true;
  }


  /**
   * Helper method that tests a certain scenario for BinaryBookshelf. Returns true if all tests
   * finish with no bugs, false otherwise.
   * 
   * @param bb - BinaryBookshelf to be tested on
   * @return true if all tests finish with no bugs, false otherwise.
   */
  private static boolean emptyTreeScenario2(BinaryBookshelf bb) {
    Book.resetGenerator();
    try {
      // Scenario 2 - valid inputs
      if (!bb.isEmpty() || bb.size() != 0) {
        System.out.println("Should be empty");
        return false;
      }

      if (!bb.toString().equals("")) {
        System.out.println("Should return empty string");
        return false;
      }

      if (bb.getRoot() != null) {
        System.out.println("root should be null");
        return false;
      }

      Book book = new Book("Thanatos", 15);
      if (bb.contains(book) || bb.contains(null)) {
        System.out.println("bb shouldn't contain anything");
        return false;
      }

      if (bb.getBooksByAuthor("Thanatos").size() != 0) {
        System.out.println("returned AL should be empty");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Scenario 2 on an empty tree has thrown"
          + " a non expected exception.");
    }
    return true;
  }

  /**
   * This method tests the insertBook method in BinaryBookshelf. Returns true if all tests finish
   * with no bugs, false otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  public static boolean testInsertBook() {
    Book.resetGenerator();
    try {
      BinaryBookshelf bb;
      // Scenario 1: inserting into an empty tree
      Attribute[] valid = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      bb = new BinaryBookshelf(valid);

      if (!emptyTreeScenario2(bb)) {
        System.out.println("Problem detected: Scenario 2 on an empty tree has thrown"
            + " a non expected exception.");
        return false;
      }

      Book tlotr = new Book("The Lord of The Rings", 500, "Tolkein", "JRR");



      bb.insertBook(tlotr);
      try {
        BinaryBookshelf insertFalseCopy = new BinaryBookshelf(valid);
        Book tlotrCopy = new Book("LordOfTheRings", 400, "Tolkein", "JRR");
        insertFalseCopy.insertBook(tlotr);
        insertFalseCopy.insertBook(tlotrCopy);
      } catch (Exception e) {
        return false;
      }

      if (bb.isEmpty() || bb.size() != 1) {
        System.out.println("size should be 1");
        return false;
      }

      if (!bb.getRoot().getData().equals(tlotr)) {
        System.out.println("root should be node with data equal to the book just added");
        return false;
      }



      // Scenario 2: inserting a unique, smaller value into a non-empty tree
      Book tlo = new Book("Queen Archer", 350, "Riordan", "Rick");
      bb.insertBook(tlo);

      if (bb.getRoot().getRight() != null || !bb.getRoot().getLeft().getData().equals(tlo)) {
        System.out.println("root's right node should be the node with book TLO");
        return false;
      }

      if (bb.size() != 2) {
        System.out.println("size not properly incremented");
        return false;
      }
      // Scenario 3: inserting a value with a shared author attribute
      Book zlb = new Book("ZeLastBook", 800, "Tolkein", "JRR");
      bb.insertBook(zlb);

      if (!bb.getRoot().getRight().getData().equals(zlb)) {
        System.out.println("root's left node should be the node with book ZLB");
        return false;
      }
      if (bb.size() != 3) {
        System.out.println("size not properly incremented");
        return false;
      }
      // Scenario 4: inserting a duplicate value

      try {
        bb.insertBook(tlotr);
        System.out.println("Did not throw IAE");
        return false;
      } catch (IllegalArgumentException e) {
        // expected behavior
      }
      if (bb.size() != 3) {
        System.out.println("size not properly incremented");
        return false;
      }
      /*
       * You may also wish to test more complicated scenarios, like building a tree of 5 or more
       * nodes. You might also want to try building this tree by inserting Books in different
       * orders, and test that the resulting structure changes correctly based on the order in which
       * you inserted them. Draw pictures to help figure out what the correct structure will look
       * like!
       */

    } catch (Exception e) {
      System.out.println("Problem detected: Your BinaryBookshelf.insertBook() method has thrown"
          + " a non expected exception.");
    }
    return true;
  }

  /**
   * This method tests the contains method of BinaryBookshelf. Returns true if all tests finish
   * with no bugs, false otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  public static boolean testContains() {
    Book.resetGenerator();
    try {
      BinaryBookshelf bb;
      Attribute[] valid = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      bb = new BinaryBookshelf(valid);
      // Scenario 1: non-recursive case
      Book tlotr = new Book("The Lord of The Rings", 500, "Tolkein", "JRR");
      bb.insertBook(tlotr);

      if (!bb.contains(tlotr)) {
        System.out.println("Bookshelf should contain the book tlotr");
        return false;
      }

      Book tlo = new Book("The Last Olympian", 350, "Riordan", "Rick");
      if (bb.contains(tlo)) {
        System.out.println("Bookshelf shouldn't contain the book tlo");
        return false;
      }


      // Scenario 2: recursive case

      Book ttt = new Book("The Two Towers", 500, "Tolkein", "JRR");
      Book nb = new Book("New Blood", 50, "Archer", "Queen");
      Book ct =
          new Book("Chaos Theory: A Butterfly Flaps its Wings in China", 150, "Zapata", "Felippe");

      Book notInBookshelf = new Book("DNE", 150, "Doe", "Jane");

      TreeNode<Book> root = bb.getRoot();
      TreeNode<Book> theLastO = new TreeNode<>(tlo);
      TreeNode<Book> theTwoT = new TreeNode<>(ttt);
      TreeNode<Book> newBlood = new TreeNode<>(nb);
      TreeNode<Book> chaosTheory = new TreeNode<>(ct);


      root.setLeft(theLastO);
      root.setRight(theTwoT);
      theLastO.setLeft(newBlood);
      theTwoT.setRight(chaosTheory);

      // root
      if (!bb.contains(root.getData())) {
        System.out.println("Bookshelf should contain the root node with book tlotr");
        return false;
      }

      // leaf
      if (!bb.contains(newBlood.getData())) {
        System.out.println("Bookshelf should contain a leaf node with book nb");
        return false;
      }

      // internal
      if (!bb.contains(theLastO.getData())) {
        System.out.println("Bookshelf should contain an internal node with book tlo");
        return false;
      }

      // not in Bookshelf
      if (bb.contains(notInBookshelf)) {
        System.out.println("Bookshelf shouldn't contain a node with book notInBookshelf");
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your BinaryBookshelf.contains() method has thrown"
          + " a non expected exception.");
    }

    return true;
  }
  
  /**
   * This method tests te getBooksByAuthor() method in BinaryBookshelf. Returns true if all tests finish
   * with no bugs, false otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  public static boolean testGetBooksByAuthor() {
    Book.resetGenerator();
    try {
      // Scenario 1: non-recursive case
      BinaryBookshelf bb;
      Attribute[] valid = {Attribute.AUTHOR, Attribute.TITLE, Attribute.ID, Attribute.PAGECOUNT};
      bb = new BinaryBookshelf(valid);
      Book tlotr = new Book("The Lord of The Rings", 500, "Tolkein", "JRR");
      bb.insertBook(tlotr);

      if (bb.getBooksByAuthor("Tolkein, JRR").size() != 1) {
        System.out.println("getBooksByAuthor() did not return an ArrayList of size 1");
        return false;
      }

      if (!bb.getBooksByAuthor("Tolkein, JRR").contains(tlotr)) {
        System.out.println("getBooksByAuthor() does not contain the only book in the bookshelf");
        return false;
      }

      if (bb.getBooksByAuthor("Author, Invalid").size() != 0) {
        System.out.println("getBooksByAuthor() did not return an ArrayList of size 0");
        return false;
      }

      // Scenario 2: recursive case

      Book tlo = new Book("The Last Olympian", 350, "Riordan", "Rick");
      Book ttt = new Book("The Two Towers", 500, "Tolkein", "JRR");
      Book nb = new Book("New Blood", 50, "Archer", "Queen");
      Book ct =
          new Book("Chaos Theory: A Butterfly Flaps its Wings in China", 150, "Zapata", "Felippe");

      TreeNode<Book> root = bb.getRoot();
      TreeNode<Book> theLastO = new TreeNode<>(tlo);
      TreeNode<Book> theTwoT = new TreeNode<>(ttt);
      TreeNode<Book> newBlood = new TreeNode<>(nb);
      TreeNode<Book> chaosTheory = new TreeNode<>(ct);

      root.setLeft(theLastO);
      root.setRight(theTwoT);
      theLastO.setLeft(newBlood);
      theTwoT.setRight(chaosTheory);

      ArrayList<Book> booksByAuthor = bb.getBooksByAuthor("Riordan, Rick");
      if (booksByAuthor.size() != 1 || !booksByAuthor.contains(theLastO.getData())) {
        System.out.println("getBooksByAuthor() did not return an ArrayList of size 1 "
            + "or doesn't contain the right book");
        return false;
      }

      booksByAuthor = bb.getBooksByAuthor("Tolkein, JRR");
      if (booksByAuthor.size() != 2 || !booksByAuthor.contains(theTwoT.getData())
          || !booksByAuthor.contains(root.getData())) {
        System.out.println("getBooksByAuthor() did not return an ArrayList of size 2 or "
            + "doesn't contain the right books");
        return false;
      }

      booksByAuthor = bb.getBooksByAuthor("Author, Invalid");
      if (booksByAuthor.size() != 0) {
        System.out.println("getBooksByAuthor() did not return an ArrayList of size 0, scenario 2");
        return false;
      }

    } catch (Exception e) {
      System.out
          .println("Problem detected: Your BinaryBookshelf.getBooksByAuthor() method has thrown"
              + " a non expected exception.");
    }
    return true;
  }

  /**
   * Runs all tester methods in this class. Returns true if all tests finish
   * with no bugs, false otherwise
   * 
   * @return true if all tests finish with no bugs, false otherwise
   */
  private static boolean runAllTests() {
    Book.resetGenerator();
    testTreeNode();
    testEmptyTree();
    testContains();
    testInsertBook();
    testGetBooksByAuthor();
    return testTreeNode() && testEmptyTree() && testContains() && testInsertBook()
        && testGetBooksByAuthor();
  }

  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}
