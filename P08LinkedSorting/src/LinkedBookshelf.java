//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P08 Linked Bookshelf
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
 * Linked BookShelf Class, which is a linked list with nodes that store Books. It provides getter
 * methods for head and tail nodes as well as the size of the bookshelf. It also allows for
 * insertion according to a sorting attribute, and appending to the end of the shelf. It can be
 * sorted according to different attributes.
 * 
 * @author Roy Wang
 *
 */

public class LinkedBookshelf {

  private LinkedNode<Book> front; // a private instance field indicating the LinkedNode<Book>
                                  // currently at the front of the list
  private LinkedNode<Book> back; // a private instance field indicating the LinkedNode<Book>
                                 // currently at the end of the list
  private int size; // a private instance field containing the number of Books currently on the
                    // bookshelf
  private Attribute sortedBy; // a private instance field containing the Attribute by which the list
                              // is currently sorted; defaults to Attribute.ID

  /**
   * Default constructor for LinkedBookshelf, which is initialized with a null head and tail
   * pointers, size 0, and the the ID attribute.
   */
  public LinkedBookshelf() {
    front = null;
    back = null;
    size = 0;
    sortedBy = Attribute.ID;
  }

  /**
   * Getter for the size of this LinkedBookshelf
   * 
   * @return size of this LinkedBookshelf
   */
  public int size() {
    return this.size;
  }


  /**
   * Returns true if LinkedBookshelf is empty, false otherwise.
   * 
   * @return true if LinkedBookshelf is empty, false otherwise.
   */
  public boolean isEmpty() {
    return this.size == 0;
  }


  /**
   * Returns String representation of this LinkedBookshelf
   */
  @Override
  public String toString() {
    StringBuffer str = new StringBuffer(sortedBy.toString() + "\n");
    LinkedNode<Book> currNode = front;

    // add the toString representation of each node in the bookshelf to the StringBuffer with
    // correct formatting
    while (currNode != null) {
      str.append(currNode.toString());
      str.append("\n");
      currNode = currNode.getNext();
    }
    return str.toString();
  }

  /**
   * Returns the linked node at the specified index.
   * 
   * @param index - index of node to get from the list, if index is valid
   * @return LinkedNode at the given index
   * @throws IndexOutOfBoundsException if the index is not valid
   */
  public LinkedNode<Book> getNode(int index) {
    // if index is invalid, throw exception
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException("given index is invalid");
    }

    if (index == size - 1) {
      return back;
    }
    // iterate through the bookshelf while the index of the current node doesn't match the given
    // index
    int count = 0;
    LinkedNode<Book> node = front;
    while (count != index) {
      node = node.getNext();
      count++;
    }
    return node;
  }

  /**
   * Returns the book at the linked node at the specified index.
   * 
   * @param index - index of node in the list to get the book from, if index is valid
   * @return Book stored in the node at the given index
   * @throws IndexOutOfBoundsException if the index is not valid
   */
  public Book get(int index) {
    LinkedNode<Book> node = getNode(index);
    return node.getData();
  }

  /**
   * Getter for the head of the list
   * 
   * @return the head of list, if it exists
   * @throws NullPointerException if the head does not exist, (head is null)
   */
  public Book getFirst() {
    if (front == null) {
      throw new NullPointerException("head node null");
    }
    return this.front.getData();
  }

  /**
   * Getter for the tail of the list
   * 
   * @return the tail of list, if it exists
   * @throws NullPointerException if the tail does not exist, (tail is null)
   */
  public Book getLast() {
    if (back == null) {
      throw new NullPointerException("tail node null");
    }
    return this.back.getData();
  }

  /**
   * Clear the bookshelf. Set the size to 0, and head and tail nodes to null
   */
  public void clear() {
    front = null;
    back = null;
    size = 0;
  }

  /**
   * Append the given book to the end of the LinkdBookshelf.
   * 
   * @param toAdd - Book to add to the end of the LinkedBookshelf
   */
  public void appendBook(Book toAdd) {
    LinkedNode<Book> add = new LinkedNode<>(toAdd);
    // if bookshelf is empty, the node to be added is both the head and tail
    // otherwise, append the new node to the end of the list.
    // update size accordingly.
    if (front == null) {
      front = add;
      back = add;
    } else {
      this.back.setNext(add);
      this.back = add;
    }
    size++;
  }

  /**
   * Insert the given book to the LinkedBookshelf to the appropriate index according to the current
   * Attribute sortedBy.
   * 
   * @param toAdd - Book to insert into the LinkedBookshelf
   */
  public void insertBook(Book toAdd) {
    LinkedNode<Book> add = new LinkedNode<>(toAdd);
    // if bookshelf is empty, the node to be added is both the head and tail
    // otherwise, iterate through the bookshelf while the node to be added is greater than the
    // current node, and add the node once the correct place has been found. update the next
    // pointers and size.
    if (front == null) {
      front = add;
      back = add;
    } else {
      LinkedNode<Book> currNode = front;
      while (toAdd.compareTo(currNode.getData(), sortedBy) > 0) {
        currNode = currNode.getNext();
      }
      add.setNext(currNode.getNext());
      currNode.setNext(add);
    }
    size++;
  }

  /**
   * Sort a given LinkedBookshelf according to the given attribute.
   * 
   * @param b        - the LinkedBookshelf to sort
   * @param sortedBy - attribute to sort the bookshelf by
   */
  public static void sort(LinkedBookshelf b, Attribute sortedBy) {
    b.sortedBy = sortedBy;
    if (b.size == 0 || b.size == 1) {
      return;
    }
    int j;
    for (int i = 1; i < b.size; i++) {
      j = i;
      LinkedNode<Book> toSort = b.getNode(j);
      while (j > 0 && toSort.getData().compareTo(b.get(j - 1), sortedBy) < 0) {
        // while the node to be sorted is less than the nodes to the left of it, swap the two nodes
        LinkedNode<Book> toSwap = b.getNode(j - 1);

        // if the node to be swapped with the current node is the head node, update head and next
        // pointers accordingly
        if (toSwap.equals(b.front)) {
          b.front.setNext(toSort.getNext());
          b.front = toSort;
          b.front.setNext(toSwap);
        } else if (toSort.equals(b.back)) { // if the current node is the tail node, update tail and
                                            // next pointers accordingly
          LinkedNode<Book> temp = b.getNode(j - 2);
          toSort.setNext(toSwap);
          toSwap.setNext(null);
          temp.setNext(toSort);
          b.back = toSwap;
        } else { // otherwise, update the to be swapped and to be sorted node and next pointers
                 // accordingly
          LinkedNode<Book> temp = b.getNode(j - 2);
          toSwap.setNext(toSort.getNext());
          toSort.setNext(toSwap);
          temp.setNext(toSort);
        }
        j--;
      }
    }
  }
}
