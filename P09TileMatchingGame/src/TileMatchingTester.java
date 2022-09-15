//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P09 Tile Matching Tester
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
import java.util.NoSuchElementException;

/**
 * Tile Matching Tester is a tester class for various methods and classes in the P09 Project. It
 * tests the functionality of the Tile.equals() method, TileListIterator Class, TileStack class, and
 * TileMachingGame class.
 * 
 * @author Roy Wang
 *
 */
public class TileMatchingTester {

  /**
   * Tests the functionality of the Tile.equals() method. Returns true if it works as intended,
   * false otherwise.
   * 
   * @return true if all tests return no bugs, false otherwise
   */
  public static boolean tileEqualsTester() {

    try {
      Tile black = new Tile(Color.BLACK);
      Tile sameColor = new Tile(Color.BLACK);
      Tile blue = new Tile(Color.BLUE);
      String str = "String";

      // Scenario 1 - Try to compare a tile to an object which is NOT instance of the class Tile
      if (black.equals(str) != false) {
        System.out.println("Comparing a tile to an non-tile object returned true");
        return false;
      }

      // Scenario 2 - Try to compare a tile to another tile of the same color.
      if (black.equals(sameColor) != true) {
        System.out.println("Comparing two tiles with the same color returned false");
        return false;
      }

      // Scenario 3 - Try to compare a tile to another tile of a different color
      if (black.equals(blue) != false) {
        System.out.println("Comparing two tiles with the different colors returned true");
        return false;
      }

      // Scenario 4 - Try to compare a tile with itself
      if (black.equals(black) != true) {
        System.out.println("Comparing a tile with itself returned false");
        return false;
      }

      // Scenario 5 - Try to compare a tile with a null reference
      if (black.equals(null) != false) {
        System.out.println("Comparing a tile with itself returned true");
        return false;
      }

    } catch (Exception e) {
      System.out.println("Problem detected: Your toEquals() method in the Tile class has thrown"
          + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of the TileListIterator class. Returns true if it works as intended,
   * false otherwise.
   * 
   * @return true if all tests return true, and Tile List Iterator works as intended.
   */
  public static boolean tileListIteratorTester() {

    try {
      // Scenario 1 - Iterate over one tile
      Node oneNode = new Node(new Tile(Color.BLUE));
      TileListIterator oneIt = new TileListIterator(oneNode);

      // oneIt hasn't iterated over oneNode, so oneIt still hasNext
      if (oneIt.hasNext() != true) {
        System.out.println(
            "This iterator has yet to iterate over any node, so hasNext() should return true");
        return false;
      }
      // oneIt.next() should return oneNode
      if (!oneIt.next().equals(oneNode.getTile())) {
        System.out.println("This iterator should return the first node, oneNode");
        return false;
      }
      // oneIt has no more nodes to iterate over, shouldn't have next
      if (oneIt.hasNext() != false) {
        System.out.println("No nodes left to iterate over, hasNext() should return false");
        return false;
      }

      // Scenario 2 - Try next() when no more nodes to iterate over, NSEE expected
      try {
        oneIt.next();
        System.out.println("Did not throw NoSuchElementException");
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }

      // Scenario 3 - Iterate over multiple tiles
      Tile black = new Tile(Color.BLACK);
      Tile orange = new Tile(Color.ORANGE);
      Tile blue = new Tile(Color.BLUE);
      Tile yellow = new Tile(Color.YELLOW);

      Node head = new Node(black, new Node(orange, new Node(blue, new Node(yellow, null))));

      TileListIterator tli = new TileListIterator(head);

      // tli has yet to iterate over any node, should have next.
      if (tli.hasNext() != true) {
        System.out.println("Should have next");
        return false;
      }
      // first call to next
      if (!tli.next().equals(black)) {
        System.out.println("first call to next() should return the head node's tile, black");
        return false;
      }
      // tli's next should now point to tile1, should still have next
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to orange, should still have next");
        return false;
      }

      // second call to next
      if (!tli.next().equals(orange)) {
        System.out.println("second call to next() should return the second node's tile, orange");
        return false;
      }
      // tli's next should now point to tile2, should still have next
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to blue, should still have next");
        return false;
      }

      // third call to next
      if (!tli.next().equals(blue)) {
        System.out.println("third call to next() should return the third node's tile, blue");
        return false;
      }
      // tli's next should now point to tile3, hasNext() should not return true
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to yellow, hasNext() true");
        return false;
      }
      // fourth and final (valid) call to next
      if (!tli.next().equals(yellow)) {
        System.out.println("fourth call to next() should return the fourth node's tile, yellow");
        return false;
      }

      // tli's next should now point to null, hasNext() should not return true
      if (tli.hasNext() != false) {
        System.out.println("tli's next should now point to null, hasNext() should not return true");
        return false;
      }
      // Try next() when no more nodes to iterate over, NSEE expected
      try {
        tli.next();
        System.out.println("Did not throw NoSuchElementException");
      } catch (NoSuchElementException e) {
        // expected behavior
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your TileListIterator() class has thrown"
          + " a non expected exception.");
      return false;
    }

    return true;
  }

  /**
   * Tests the functionality of the TileStack class. Returns true if it works as intended, false
   * otherwise.
   * 
   * @return true if all tests return true, and Tile Stack works as intended.
   */
  public static boolean tileStackTester() {

    return testPeekPopPushSize() && testIsEmpty() && testIterator();
  }

  /**
   * Tests the functionality of Tile Stacks' peek, pop, push, and size methods. Returns true if it
   * works as intended, false otherwise.
   * 
   * @return true if all tests return no bugs, and all methods work as intended. false otherwise
   */
  private static boolean testPeekPopPushSize() {
    try {
      TileStack ts = new TileStack();
      // Test if size is 0 at initialization
      if (ts.size() != 0) {
        System.out.println("Size should be 0");
        return false;
      }
      Tile black = new Tile(Color.BLACK);
      Tile orange = new Tile(Color.ORANGE);
      Tile blue = new Tile(Color.BLUE);
      Tile yellow = new Tile(Color.YELLOW);
      // Scenario 1 - pop and peek on an empty stack, ES exceptions expected.
      try {
        ts.pop();
        return false;
      } catch (EmptyStackException e) {
        // expected behavior
      }
      try {
        ts.peek();
        return false;
      } catch (EmptyStackException e) {
        // expected behavior
      }
      // Scenario 2 - Push tiles onto stack, and then pop and peek them from the stack. If this
      // series of operations does not work as intended, return false.
      ts.push(black);
      ts.push(orange);
      ts.push(blue);
      ts.push(yellow);


      if (ts.size() != 4 || !ts.peek().equals(yellow)) {
        System.out
            .println("top tile wasn't the expected tile, or size was not incremented properly");
        return false;
      }

      ts.pop();
      if (ts.size() != 3 || !ts.peek().equals(blue)) {
        System.out
            .println("popped tile wasn't the expected tile, or size was not decremented properly");
      }

      ts.pop();
      if (ts.size() != 2 || !ts.peek().equals(orange)) {
        System.out
            .println("popped tile wasn't the expected tile, or size was not decremented properly");
      }

      ts.pop();
      if (ts.size() != 1 || !ts.peek().equals(black)) {
        System.out
            .println("popped tile wasn't the expected tile, or size was not decremented properly");
      }

      ts.pop();
      if (ts.size() != 0) {
        System.out
            .println("popped tile wasn't the expected tile, or size was not decremented properly");
      }
      // try pop and peek on what should be an empty stack again, ES exceptions expected.
      try {
        ts.pop();
        return false;
      } catch (EmptyStackException e) {
        // expected behavior
      }
      try {
        ts.peek();
        return false;
      } catch (EmptyStackException e) {
        // expected behavior
      }

    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your peek, pop, push(), or size() method in the Tile Stack class has thrown"
              + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Tests Tile Stacks' iterator() method. Returns true if iterator() works as intended
   * 
   * @return true if all tests return no bugs, iterator() works as intended.
   */
  private static boolean testIterator() {
    try {
      TileStack ts = new TileStack();

      // Scenario 1 - iterate over an empty stack, call next() and hasNext().
      TileListIterator tli = (TileListIterator) ts.iterator();
      if (tli.hasNext() != false) {
        System.out.println("No nodes to iterate over, hasNext() should return false");
        return false;
      }
      try {
        tli.next();
        System.out.println("NoSuchElementException expected");
        return false;
      } catch (NoSuchElementException e) {
        // expected behavior
      }
      Tile black = new Tile(Color.BLACK);
      Tile orange = new Tile(Color.ORANGE);
      Tile blue = new Tile(Color.BLUE);
      Tile yellow = new Tile(Color.YELLOW);

      // Scenario 2 - iterate over a nonempty stack, and call hasNext() and next() to test
      // functionality. If not working as expected, return false.
      ts.push(black);
      ts.push(orange);
      ts.push(blue);
      ts.push(yellow);

      tli = (TileListIterator) ts.iterator();

      if (tli.hasNext() != true) {
        System.out.println("Should have next");
        return false;
      }
      // first call to next
      if (!tli.next().equals(yellow)) {
        System.out.println("first call to next() should return yellow");
        return false;
      }
      // tli's next should now point to blue, should still have next
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to blue, should still have next");
        return false;
      }

      // second call to next
      if (!tli.next().equals(blue)) {
        System.out.println("second call to next() should return blue");
        return false;
      }
      // tli's next should now point to orange, should still have next
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to orange, should still have next");
        return false;
      }

      // third call to next
      if (!tli.next().equals(orange)) {
        System.out.println("third call to next() should return orange");
        return false;
      }
      // tli's next should now point to black
      if (tli.hasNext() != true) {
        System.out.println("tli's next should now point to black, hasNext() true");
        return false;
      }
      // fourth and final (valid) call to next
      if (!tli.next().equals(black)) {
        System.out.println("fourth call to next() should return black");
        return false;
      }

      // tli's next should now point to null, hasNext() should not return true
      if (tli.hasNext() != false) {
        System.out.println("tli's next should now point to null, hasNext() should not return true");
        return false;
      }
      try {
        tli.next();
        System.out.println("Did not throw NoSuchElementException");
      } catch (NoSuchElementException e) {
        // expected behavior
      }
    } catch (Exception e) {
      System.out
          .println("Problem detected: Your TileStack.iterator() method in the Tile class has thrown"
              + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Test Tile Stacks' isEmpty() method. Returns true if isEmpty() works as intended.
   * 
   * @return true if all tests return no bugs, method works as intended.
   */
  private static boolean testIsEmpty() {
    try {
      TileStack ts = new TileStack();

      Tile black = new Tile(Color.BLACK);

      // Scenario 1 - call isEmpty on an empty stack, isEmpty should return true
      if (!ts.isEmpty()) {
        System.out.println("The TileStack should be empty");
        return false;
      }

      // Scenario 2 - call isEmpty after pushing a tile onto stack, isEmpty should return false
      ts.push(black);
      if (ts.isEmpty()) {
        System.out.println("The TileStack shouldn't be empty, size should be 1");
        return false;
      }

      // Scenario 3 - call isEmpty after peeking a tile, isEmpty status should not change.
      ts.peek();
      if (ts.isEmpty()) {
        System.out.println("The TileStack shouldn't be empty, size shouldn't have changed");
        return false;
      }


      // Scenario 4 - call isEmpty after popping last tile off stack, isEmpty should return true
      ts.pop();
      if (!ts.isEmpty()) {
        System.out.println("The TileStack should be empty");
        return false;
      }
    } catch (Exception e) {
      System.out
          .println("Problem detected: Your isEmpty() method in the Tile Stack class has thrown"
              + " a non expected exception.");
      return false;
    }
    return true;
  }

  /**
   * Tests the functionality of the TileMatchingGame class. Returns true if it works as intended,
   * false otherwise.
   * 
   * @return true if TileMatchingGame works as intended, false otherwise.
   */
  public static boolean tileMatchingGameTester() {
    try {
      TileMatchingGame game;
      // Scenario 1 - Try initializing game with invalid inputs to the constructor.
      try {
        game = new TileMatchingGame(0);
        System.out.println("Should throw exception for invalid columns, but does not");
        return false;
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      try {
        game = new TileMatchingGame(-1);
        System.out.println("Should throw exception for invalid columns, but does not");
        return false;
      } catch (IllegalArgumentException e) {
        // expected behavior
      }

      // Scenario 1 - Test the getColumnsNumber() method.
      game = new TileMatchingGame(2);

      if (game.getColumnsNumber() != 2) {
        System.out.println("Expected number of columns is 2");
        return false;
      }
    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your TileMatchingGame constructor or getColumnsNumber method has thrown"
              + " a non expected exception.");
      return false;
    }

    return testDropTileColumnAndToString() && testClearColumnAndRestart();
  }

  /**
   * Tests TileMatchingGame's dropTile(), column(), and toString() methods. Returns true if they
   * work as intended, false otherwise.
   * 
   * @return true if all methods work as intended, false otherwise.
   */
  private static boolean testDropTileColumnAndToString() {
    try {
      TileMatchingGame game = new TileMatchingGame(4);
      Tile black = new Tile(Color.BLACK);
      String gameState = "GAME COLUMNS:\n0:\n1:\n2:\n3:";

      // Scenario 1 - Test column and toString representations on an "empty" game
      for (int i = 0; i < game.getColumnsNumber(); i++) {
        if (!game.column(i).equals("")) {
          System.out.println("column string should be empty");
          return false;
        }
      }
      if (!game.toString().trim().equals(gameState)) {
        System.out.println("toString should represent an empty game");
        return false;
      }

      // Scenario 2 - Test column and dropTile with invalid inputs, should throw IOOB Exception
      try {
        game.column(-1);
        System.out.println("Should throw IOOB exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }
      try {
        game.column(4);
        System.out.println("Should throw IOOB exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }

      try {
        game.dropTile(black, -1);
        System.out.println("Should throw IOOB exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }
      try {
        game.dropTile(black, 80);
        System.out.println("Should throw IOOB exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }

      // Scenario 3 - Test dropTile, column, and toString() on nonempty game.
      game.dropTile(new Tile(Color.BLACK), 0);
      String column0State = "BLACK";

      if (!column0State.equals(game.column(0))) {
        System.out.println(
            "column string doesn't return as expected, either dropTile or column() is incorrect (or both)");
        return false;
      }
      for (int i = 1; i < game.getColumnsNumber(); i++) {
        if (!game.column(i).equals("")) {
          System.out.println("column string should be empty");
          return false;
        }
      }

      gameState = "GAME COLUMNS:\n0: BLACK\n1:\n2:\n3:";

      if (!game.toString().trim().equals(gameState)) {
        System.out.println("toString doesn't return as expected");
        return false;
      }

      // Scenario 4 - Drop tile of same color on tile of same color, should remove both.
      game.dropTile(black, 2);
      game.dropTile(new Tile(Color.ORANGE), 0);
      game.dropTile(new Tile(Color.ORANGE), 0);

      gameState = "GAME COLUMNS:\n0: BLACK\n1:\n2: BLACK\n3:";

      if (!game.toString().trim().equals(gameState)) {
        System.out.println("toString doesn't return as expected");
        return false;
      }

    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your dropTile(), column(), or toString() method in the TileMatchingGame class has thrown"
              + " a non expected exception.");
      return false;
    }
    return true;
  }

  private static boolean testClearColumnAndRestart() {
    try {
      TileMatchingGame game = new TileMatchingGame(4);
      String clearedColumn = "";
      String gameState = "GAME COLUMNS:\n0:\n1:\n2:\n3:";

      // Scenario 1 - Try clearColumn() and restartGame() on an "empty" game. Make sure that
      // clearColumn does not modify other columns besides the one specified.
      game.clearColumn(0);
      if (!game.column(0).equals(clearedColumn)) {
        System.out.println("clearColumn does not work as intended");
        return false;
      }
      if (!game.toString().trim().equals(gameState)) {
        System.out.println("clearColumn does not work as intended");
        return false;
      }

      game.restartGame();
      if (!game.toString().trim().equals(gameState)) {
        System.out.println("restartGame does not work as intended");
        return false;
      }

      // Scenario 2 - Try clearColumn() with invalid index inputs.
      game.clearColumn(0);
      try {
        game.clearColumn(-1);
        System.out.println("Should throw IOOF exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }
      try {
        game.clearColumn(4);
        System.out.println("Should throw IOOF exception, invalid index given");
        return false;
      } catch (IndexOutOfBoundsException e) {
        // expected behavior
      }

      // Scenario 3 - Try clearColumn() and restartGame on nonempty columns. Make sure that
      // clearColumn does not modify other columns besides the one specified.

      Tile black = new Tile(Color.BLACK);
      game.dropTile(black, 0);
      game.dropTile(black, 1);
      game.dropTile(black, 2);
      game.dropTile(black, 3);

      game.clearColumn(0);
      if (!game.column(0).equals(clearedColumn)) {
        System.out.println("clearColumn does not work as intended");
        return false;
      }
      game.clearColumn(1);
      if (!game.column(1).equals(clearedColumn)) {
        System.out.println("clearColumn does not work as intended");
        return false;
      }
      gameState = "GAME COLUMNS:\n0:\n1:\n2: BLACK\n3: BLACK";
      if (!game.toString().trim().equals(gameState)) {
        System.out.println("clearColumn does not work as intended");
        return false;
      }

      gameState = "GAME COLUMNS:\n0:\n1:\n2:\n3:";
      game.restartGame();
      if (!game.toString().trim().equals(gameState)) {
        System.out.println("restartGame does not work as intended");
        return false;
      }

    } catch (Exception e) {
      System.out.println(
          "Problem detected: Your dropTile() method in the TileMatchingGame class has thrown"
              + " a non expected exception.");
      return false;
    }
    return true;
  }

  private static boolean runAllTests() {
    System.out.println(tileEqualsTester());
    System.out.println(tileListIteratorTester());
    System.out.println(tileStackTester());
    System.out.println(tileMatchingGameTester());
    return tileEqualsTester() && tileListIteratorTester() && tileStackTester()
        && tileMatchingGameTester();
  }

  public static void main(String[] args) {
    System.out.println(runAllTests());
  }
}
