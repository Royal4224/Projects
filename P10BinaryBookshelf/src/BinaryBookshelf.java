//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P10 Binary Bookshelf
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
 * Binary Bookshelf, which represents a Binary Search Tree that stores TreeNode<Book>. Can search
 * the bookshelf for nodes, can insert books to nodes, can get the attribute order and return all
 * books with the same author name, etc. Also has a toString() method and getter for size and its
 * empty status.
 * 
 * @author Roy Wang
 *
 */

public class BinaryBookshelf {
  private TreeNode<Book> root; // The root node of the BST.
  private int size; // The number of nodes currently contained in the BST.
  private Attribute[] sortList; // The ordered array of attributes by which the BST nodes are
                                // sorted.

  /**
   * One-argument constructor, initializes an empty BinaryBookshelf.
   * 
   * @param sortList - an ordered array of Attributes, must begin with AUTHOR and contain exactly
   *                 one copy of each Attribute in the enum
   * 
   * @throws IllegalArgumentException
   */
  public BinaryBookshelf(Attribute[] sortList) {
    // if sortList is invalid, throw IllegalArgumentException
    if (sortList == null || sortList.length != 4 || sortList[0] != Attribute.AUTHOR) {
      throw new IllegalArgumentException(
          "sortList is null, length of Array is not 4, or the first entry is not the author attribute");
    }

    // if there are duplicate attributes, throw IllegalArgumentException
    for (int i = 1; i < sortList.length; i++) {
      if (sortList[i] == Attribute.AUTHOR) {
        throw new IllegalArgumentException("duplicate author attribute");
      }
      for (int j = 1; i < sortList.length; i++) {
        if (j != i && sortList[i] == sortList[j]) {
          throw new IllegalArgumentException("duplicate attributes");
        }
      }
    }

    this.sortList = sortList;
  }

  /**
   * Resets the BST to be empty
   */
  public void clear() {
    size = 0;
    root = null;
  }

  /**
   * OPTIONAL: helper method to compare two Book objects according to the sortList of attributes.
   * 
   * @param one - the first book
   * @param two - the second book
   * @return a negative value if one < two, a positive value if one > two, and 0 if they are equal
   */
  protected int compareToHelper(Book one, Book two) {
    int attributeCount = 0;
    // compare the Books one and two attributes in sortList, return when there is a difference
    while (attributeCount < sortList.length) {
      if (one.compareTo(two, sortList[attributeCount]) > 0) {
        return 1;
      } else if (one.compareTo(two, sortList[attributeCount]) < 0) {
        return -1;
      }
      attributeCount++;
    }
    return 0;
  }

  /**
   * Searches for the input book in the bookshelf.
   * 
   * COMPLEXITY ANALYSIS: O(log(n))
   * 
   * @param book - the book to search for
   * @return - true if the book is present in the shelf, false otherwise
   */
  public boolean contains(Book book) {
    if (book == null || isEmpty() || root == null) {
      return false;
    }

    // if book to insert is less than the root, search through the left subtree
    if (compareToHelper(book, root.getData()) < 0) {
      return containsHelper(book, root.getLeft());
    }

    // if book to insert is greater than the root, search through the right subtree
    if (compareToHelper(book, root.getData()) > 0) {
      return containsHelper(book, root.getRight());
    }
    return true;
  }

  /**
   * Recursive helper method; searches for the input book in the subtree rooted at current.
   * 
   * @param book    - the query book to search for
   * @param current - the root of the current subtree
   * @return true if the book is contained in this subtree, false otherwise
   */
  protected boolean containsHelper(Book book, TreeNode<Book> current) {
    if (current == null) {
      return false;
    }

    // if the current node's data is the book, the bookshelf contains the book, return true;
    if (compareToHelper(current.getData(), book) == 0) {
      return true;
    }

    // if the current node has no children and it's data is not the book, this subtree does not
    // contain the book, return false;
    if (current.getLeft() == null && current.getRight() == null) {
      return false;
    }

    // if the book is less than the current node's book and the left node exists, search through the
    // left subtree
    if (compareToHelper(book, current.getData()) < 0 && current.getLeft() != null) {
      return containsHelper(book, current.getLeft());
    }

    // if the book is greater than the current node's book and the right node exists, search through
    // the right subtree
    if (compareToHelper(book, current.getData()) > 0 && current.getRight() != null) {
      return containsHelper(book, current.getRight());
    }

    return false;
  }

  /**
   * Provides a String-formatted list of the attributes in the order in which they are used, for
   * example: "1:AUTHOR 2:PAGECOUNT 3:TITLE 4:ID".
   * 
   * @return a String-formatted list of the sorting attributes
   */
  public String getAttributeOrder() {
    StringBuffer str = new StringBuffer();
    // append all attributes in sortList, return the string
    for (int i = 0; i < sortList.length; i++) {
      str.append(i + ":" + sortList[i]);
    }
    return str.toString();
  }

  /**
   * Returns a list of books in the bookshelf written by the given author.
   * 
   * @param authorName - the author name to filter on
   * @return a list of books by the author
   */
  public ArrayList<Book> getBooksByAuthor(String authorName) {
    ArrayList<Book> books = new ArrayList<>();
    // if the BST is empty, return an empty ArrayList
    // else, call recursive helper
    if (isEmpty()) {
      return books;
    }
    return getBooksByAuthorHelper(authorName, root);
  }

  /**
   * Recursive helper method; returns a list of books in the subtree rooted at current which were
   * written by the given author.
   * 
   * @param authorName - the author name to filter on
   * @param current    - the root of the current subtree
   * @return a list of books by the author in the current subtree
   */
  protected ArrayList<Book> getBooksByAuthorHelper(String authorName, TreeNode<Book> current) {
    ArrayList<Book> books = new ArrayList<>();
    // if current node is null, return empty AL;
    if (current == null) {
      return books;
    }

    // if current node's book has same author as the given authorName, add current's book to the AL
    if (current.getData().getAuthor().equals(authorName)) {
      books.add(current.getData());
    }

    // if no left or right children, we're done, return AL
    if (current.getLeft() == null && current.getRight() == null) {
      return books;
    }

    // if authorName is less than the current book's author, add all books in the left subtree with
    // the given authorName
    // else, add all books in the right subtree with the given authorName
    if (authorName.compareTo(current.getData().getAuthor()) < 0) {
      books.addAll(getBooksByAuthorHelper(authorName, current.getLeft()));
    } else {
      books.addAll(getBooksByAuthorHelper(authorName, current.getRight()));
    }

    return books;
  }

  /**
   * Returns a shallow copy of the root node so that test tree structures may be constructed
   * manually rather than by using the insertBook() method.
   * 
   * @return a reference to the current root node
   */
  protected TreeNode<Book> getRoot() {
    return this.root;
  }

  /**
   * Adds a new Book to the BST in sorted order, relative to this BST's sortList attributes Note:
   * you may wish to write helper methods for comparing Books according to the sortList, as well as
   * for inserting Books in a recursive manner.
   * 
   * @param book - a reference to the current root node
   * 
   * @throws IllegalArgumentException
   */
  public void insertBook(Book book) {
    // if root is null, new node with book is the root
    if (root == null) {
      root = new TreeNode<>(book);
      size++;
      return;
    }
    // if the bookshelf contains the book, throw IllegalArgumentException
    if (contains(book)) {
      throw new IllegalArgumentException("cannot insert duplicate book to binary bookshelf");
    }
    // call recursive helper method, increment size appropriately
    insertBookHelper(book, root);
    size++;
  }

  /**
   * Recursive helper method; adds the given Book to the subtree rooted at current.
   * 
   * @param book    - a reference to the current root node
   * @param current - the root of the current subtree
   */
  protected void insertBookHelper(Book book, TreeNode<Book> current) {
    TreeNode<Book> toInsert = new TreeNode<>(book);
    // if current node is null, we've found the appropriate node, and insert the book to this
    // position
    if (current == null) {
      current = toInsert;
      return;
    }

    // if book to insert is less than current's book and there is a left node, search left subtree
    // if left node is null, insert book at current's left
    if (compareToHelper(book, current.getData()) < 0) {
      if (current.getLeft() != null) {
        insertBookHelper(book, current.getLeft());
      } else {
        current.setLeft(toInsert);
      }
    } else {
      // if book to insert is greater than current's book and there is a right node, search
      // right subtree
      // if right node is null, insert book at current's right
      if (current.getRight() != null) {
        insertBookHelper(book, current.getRight());
      } else {
        current.setRight(toInsert);
      }
    }
  }

  /**
   * Determine whether the BST is empty.
   * 
   * 
   * COMPLEXITY ANALYSIS: O(1)
   * 
   * @return true if the BST is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Get the number of nodes currently in the BST.
   * 
   * COMPLEXITY ANALYSIS: O(1)
   * 
   * @return the number of nodes currently in the BST
   */
  public int size() {
    return size;
  }

  /**
   * Creates and returns an in-order traversal of the BST, with each Book on a separate line.
   * 
   * COMPLEXITY ANALYSIS: O(n)
   * 
   * @return an in-order traversal of the BST, with each Book on a separate line
   */
  public String toString() {
    return toStringHelper(root);
  }

  /**
   * Recursive helper method; creates and returns the String representation of the subtree rooted at
   * the current node.
   * 
   * @param current - the root of the current subtree
   * @return the string representation of this subtree
   */
  protected String toStringHelper(TreeNode<Book> current) {
    StringBuffer str = new StringBuffer("");
    // if current is null, return empty string
    if (current == null) {
      return str.toString();
    }
    // append left node's string and its subtree's string
    str.append(toStringHelper(current.getLeft()) + "\n");
    // append this node's string
    str.append(current.getData().toString() + "\n");
    // append right node's string and its subtree's string
    str.append(toStringHelper(current.getRight()) + "\n");

    return str.toString().trim();
  }

}
