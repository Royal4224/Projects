//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: P07 Folder Explorer
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
import java.util.NoSuchElementException;

/**
 * Folder Explorer that can get the contents of a given directory and get the contents of a given
 * directory and its sub-directories. It also has methods that recursively search through a given
 * directory/sub-directories for files based on name, keyword, and size.
 * 
 * @author Roy Wang
 */

public class FolderExplorer {
  /**
   * Returns a list of the names of all files and directories in the the given folder
   * currentDirectory. Throws NotDirectoryException with a description error message if the provided
   * currentDirectory does not exist or if it is not a directory
   * 
   * @param currentDirectory - given directory
   * @return names of all files and directories in the given folder
   * @throws NotDirectoryException
   */
  public static ArrayList<String> getContents(File currentDirectory) throws NotDirectoryException {
    // if current directory is not a directory, throw exception
    if (!currentDirectory.isDirectory())
      throw new NotDirectoryException("Given file is not a directory");

    ArrayList<String> contents = new ArrayList<>();

    // Add every file name that exists in the current directory to the AL
    for (String fileName : currentDirectory.list()) {
      contents.add(fileName);
    }

    return contents;
  }

  /**
   * Recursive method that lists the names of all the files (not directories) in the given directory
   * and its sub-directories. Throws NotDirectoryException with a description error message if the
   * provided currentDirectory does not exist or if it is not a directory
   * 
   * @param currentDirectory - given directory
   * @return names of all files in the given directory/sub directories
   * @throws NotDirectoryException
   */
  public static ArrayList<String> getDeepContents(File currentDirectory)
      throws NotDirectoryException {
    // if current directory is not directory, throw exception
    if (!currentDirectory.isDirectory())
      throw new NotDirectoryException("Given file is not a directory");

    ArrayList<String> contents = new ArrayList<>();
    // recursively parse through every sub-directory in our current directory and add all files
    // within those sub-directories to the AL. if not a sub-directory, add file to the AL
    for (File file : currentDirectory.listFiles()) {
      if (file.isDirectory()) {
        contents.addAll(getDeepContents(file));
      } else {
        contents.add(file.getName());
      }
    }
    return contents;
  }

  /**
   * Searches the given directory and all of its sub-directories for an exact match to the provided
   * fileName. This method returns a path to the file, if it exists. Throws NoSuchElementException
   * with a descriptive error message if the search operation returns with no results found
   * (including the case if fileName is null or currentDirectory does not exist, or was not a
   * directory)
   * 
   * @param currentDirectory - given directory
   * @param fileName         - name of the file being looked up
   * @return Path of given fileName in current directory if it exists. Throws exception otherwise.
   * @throws NoSuchElementException
   */
  public static String lookupByName(File currentDirectory, String fileName)
      throws NoSuchElementException {

    // if given directory doesn't exist, is not a directory, or the fileName is null, throw a NoSuchElementException.
    if (!currentDirectory.exists()) {
      throw new NoSuchElementException("Given directory doesn't exist");
    }
    if (!currentDirectory.isDirectory())
      throw new NoSuchElementException("Given file is not a directory");

    if (fileName == null)
      throw new NoSuchElementException("fileName is null");

    String currentPath = currentDirectory.getName();
    // get the path of the desired file with fileName in the currentDirectory
    String resultPath = lookupByNameHelper(currentPath, currentDirectory, fileName);
    
    // if no file matching the fileName is found, throw a NoSuchElementException.
    if (resultPath.equals("")) {
      throw new NoSuchElementException("File " + fileName + " not found.");
    }

    return resultPath;
  }


  /**
   * Recursive helper method for lookupByName. Recursively searches a provided directory and returns
   * the path of the first file that matches the provided fileName.
   * 
   * @param currentDirectory - given directory
   * @param fileName         - name of the file being looked up
   * @return Path of given fileName in current directory if it exists.
   */
  private static String lookupByNameHelper(String currentPath, File currentDirectory,
      String fileName) {

    // base case: if file name equals desired file name, append name to path and return
    // recursive case: if file is sub-directory, call helper again and search through that
    // sub-directory
    // if the resulting path exists/isn't blank, return it
    for (File file : currentDirectory.listFiles()) {
      if (file.getName().equals(fileName)) {
        return currentPath + File.separator + file.getName();
      }
      
      if (file.isDirectory()) {
        String path =
            lookupByNameHelper(currentPath + File.separator + file.getName(), file, fileName);
        if (!path.equals("")) {
          return path;
        }
      }
    }
    return "";
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files that
   * contain the given key in part of their name. Returns an ArrayList of all the names of files
   * that match and an empty ArrayList when the operation returns with no results found (including
   * the case where currentDirectory is not a directory).
   * 
   * @param currentDirectory - given directory
   * @param key              - key used to look files up
   * @return An ArrayList of files that contain the given key in part of their name
   */
  public static ArrayList<String> lookupByKey(File currentDirectory, String key) {
    ArrayList<String> fileNames = new ArrayList<>();

    // if given directory doesn't exist or isn't a directory, return empty ArrayList.
    if (!currentDirectory.exists() || !currentDirectory.isDirectory() || key == null) {
      return fileNames;
    }

    // base case: if file name contains key, add it to the AL
    // recursive case: recursively search for and add all files in sub-directories that contain the key to the AL
    for (File file : currentDirectory.listFiles()) {
      if (file.getName().contains(key)) {
        fileNames.add(file.getName());
      }
      
      if (file.isDirectory()) {
        fileNames.addAll(lookupByKey(file, key));
      }
    }

    return fileNames;
  }

  /**
   * Recursive method that searches the given folder and its sub-directories for ALL files whose
   * size is within the given max and min values, inclusive. Returns an ArrayList of the names of
   * all files whose size are within the boundaries and an empty ArrayList if the search operation
   * returns with no results found (including the case where currentDirectory is not a directory).
   * 
   * @param currentDirectory - given directory
   * @param sizeMin          - minimum size of the file
   * @param sizeMax          - maximum size of the file
   * @return an ArrayList of files whose size are within the given boundaries
   */
  public static ArrayList<String> lookupBySize(File currentDirectory, long sizeMin, long sizeMax) {
    ArrayList<String> fileNames = new ArrayList<>();
    
    // if given directory doesn't exist or isn't a directory, return empty ArrayList.
    if (!currentDirectory.exists() || !currentDirectory.isDirectory()) {
      return fileNames;
    }

    // base case: if file length is within bounds, add it to the AL
    // recursive case: recursively search for and add all files in sub-directories that are within bounds to the AL
    for (File file : currentDirectory.listFiles()) {
      if (file.length() >= sizeMin && file.length() <= sizeMax) {
        fileNames.add(file.getName());
      }

      if (file.isDirectory()) {
        fileNames.addAll(lookupBySize(file, sizeMin, sizeMax));
      }
    }

    return fileNames;
  }
}
