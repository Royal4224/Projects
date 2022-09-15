//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Folder Explorer Tester
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

import java.io.File;
import java.nio.file.NotDirectoryException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Folder Explorer Tester that tests the functionality of Folder Explorer. Has tester methods for
 * the getContents, getDeepContents, lookupByName, and lookupByKey methods in Folder Explorer.
 * 
 * @author Roy Wang
 */

public class FolderExplorerTester {

  /**
   * Tester method for getContents
   * 
   * @param folder - given directory to test getContents on.
   * @return true if getContents functions as expected. false otherwise.
   */
  public static boolean testGetContents(File folder) {
    try {

      // Scenario 1
      // list the basic contents of the cs300 folder
      ArrayList<String> listContent = FolderExplorer.getContents(folder);
      // expected output must contain "exams preparation", "grades",
      // "lecture notes", "programs", "reading notes", "syllabus.txt",
      // and "todo.txt" only.
      String[] contents = new String[] {"exams preparation", "grades", "lecture notes", "programs",
          "reading notes", "syllabus.txt", "todo.txt"};
      List<String> expectedList = Arrays.asList(contents);
      // check the size and the contents of the output
      if (listContent.size() != 7) {
        System.out.println("Problem detected: cs300 folder must contain 7 elements.");
        return false;
      }
      for (int i = 0; i < expectedList.size(); i++) {
        if (!listContent.contains(expectedList.get(i))) {
          System.out.println("Problem detected: " + expectedList.get(i)
              + " is missing from the output of the list contents of cs300 folder.");
          return false;
        }
      }
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }
      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tester method for getDeepContents. Returns true if getDeepContents works as intended, false
   * otherwise.
   * 
   * @param folder - given directory to test getDeepContents on.
   * @return true if getDeepContents functions as expected. false otherwise.
   */
  public static boolean testGetDeepContents(File folder) {
    try {

      ArrayList<String> listContent = new ArrayList<>();
      // Scenario 2 - list the contents of the grades folder
      File f = new File(folder.getPath() + File.separator + "grades");
      listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 0) {
        System.out.println("Problem detected: grades folder must be empty.");
        return false;
      }
      // Scenario 3 - list the contents of the p02 folder
      f = new File(folder.getPath() + File.separator + "programs" + File.separator + "p02");
      listContent = FolderExplorer.getDeepContents(f);
      if (listContent.size() != 1 || !listContent.contains("FishTank.java")) {
        System.out.println(
            "Problem detected: p02 folder must contain only " + "one file named FishTank.java.");
        return false;
      }
      // Scenario 4 - List the contents of a file
      f = new File(folder.getPath() + File.separator + "todo.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if it is provided an input which is not"
            + "a directory.");
        return false;
      } catch (NotDirectoryException e) { // catch only the expected exception
        // no problem detected
      }
      // Scenario 5 - List the contents of not found directory/file
      f = new File(folder.getPath() + File.separator + "music.txt");
      try {
        listContent = FolderExplorer.getDeepContents(f);
        System.out.println("Problem detected: Your FolderExplorer.getDeepContents() must "
            + "throw a NotDirectoryException if the provided File does not exist.");
        return false;
      } catch (NotDirectoryException e) {
        // behavior expected
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.getDeepContents() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Tester method for lookupByName. Returns true if lookupByName works as intended, false
   * otherwise.
   * 
   * @param folder - given directory to test lookupByName on.
   * @return true if lookupByName functions as expected. false otherwise.
   */
  public static boolean testLookupByFileName(File folder) {

    try {
      // Scenario 1
      // Find a file that exists
      String file = FolderExplorer.lookupByName(folder, "outline.txt");

      if (!file.equals("cs300/exams preparation/exam1/outline.txt")) {
        return false;
      }

      // Scenario 2
      // Find a file in a directory that doesn't exist
      try {
        file = FolderExplorer.lookupByName(new File("ca200"), "todo.txt");
      } catch (NoSuchElementException e) {
        // expected behavior
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
            + " a non expected exception.");
        e.printStackTrace();
        return false;
      }

      // Scenario 3
      // Find a file with null file name
      try {
        file = FolderExplorer.lookupByName(folder, null);
      } catch (NoSuchElementException e) {
        // expected behavior
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
            + " a non expected exception.");
        e.printStackTrace();
        return false;
      }

      // Scenario 4
      // Find a file in a given directory that isn't actually a directory
      try {
        file = FolderExplorer.lookupByName(new File("FishTank.java"), "todo.txt");
      } catch (NoSuchElementException e) {
        // expected behavior
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
            + " a non expected exception.");
        e.printStackTrace();
        return false;
      }

      // Scenario 5
      // Find a file that isn't in a given directory
      try {
        file = FolderExplorer.lookupByName(folder, ".txt");
      } catch (NoSuchElementException e) {
        // expected behavior
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
            + " a non expected exception.");
        e.printStackTrace();
        return false;
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.lookupByName() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }

    return true;
  }


  /**
   * Tester method for lookupByKey. Returns true if lookupByKey works as intended, false otherwise.
   * 
   * @param folder - given directory to test lookupByKey on.
   * @return true if lookupByKey functions as expected. false otherwise.
   */
  public static boolean testLookupByKeyBaseCase(File folder) {

    try {
      // Scenario 1
      // base case
      try {
        ArrayList<String> contents = FolderExplorer.lookupByKey(folder, ".pdf");

        if (contents.size() != 3 || !contents.contains("Program01.pdf")
            || !contents.contains("Program02.pdf") || !contents.contains("Program03.pdf")) {
          return false;
        }
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByKey() has thrown"
            + " a non expected exception.");
      }
      
      // Scenario 2  
      // key is null
      try {
        ArrayList<String> contents = FolderExplorer.lookupByKey(folder, null);
        if (!contents.isEmpty()) {
          return false;
        }
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByKey() has thrown"
            + " a non expected exception.");
      }
      
      // Scenario 3
      // given directory isn't a directory
      try {
        ArrayList<String> contents = FolderExplorer.lookupByKey(new File("todo.txt"), ".pdf");
        if (!contents.isEmpty()) {
          return false;
        }
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByKey() has thrown"
            + " a non expected exception.");
      }
      
      // Scenario 4
      // no results found with valid key
      try {
        ArrayList<String> contents = FolderExplorer.lookupByKey(folder, "flashtest.");
        if (!contents.isEmpty()) {
          return false;
        }
      } catch (Exception e) {
        System.out.println("Problem detected: Your FolderExplorer.lookupByKey() has thrown"
            + " a non expected exception.");
      }
    } catch (Exception e) {
      System.out.println("Problem detected: Your FolderExplorer.lookupByKey() has thrown"
          + " a non expected exception.");
      e.printStackTrace();
      return false;
    }
    return true;
  }

  /**
   * Main method, get outputs of the tester methods.
   * 
   * @param args - not used
   * @throws NotDirectoryException
   */
  public static void main(String[] args) throws NotDirectoryException {
    File folder = new File("cs300");
    System.out.println("testGetContents: " + testGetContents(folder));
    System.out.println("testGetDeepContents: " + testGetDeepContents(folder));
    // System.out.println(FolderExplorer.getContents(new File ("cs300")));
    System.out.println("testLookupByName: " + testLookupByFileName(folder));
    System.out.println("testLookupByKeyBaseCase: " + testLookupByKeyBaseCase(folder));
    System.out.println(FolderExplorer.lookupByKey(folder, ".pdf"));
  }
}
