/*
 * StringRecursion.java
 *
 * Author:          Computer Science E-22 staff
 * Modified by:     Yiling Jiang, yiling124@gmail.com
 * Date modified:   09/17/2017
 */


import java.util.ArrayList;

// Question 6 / 1:

/**
 * use recursion to print the individual characters in the string str,
 * separated by commas
 */
public class StringRecursion {
  public static void printLetters(String str) {
    if (str != null && !str.isEmpty()) {
      System.out.print(str.charAt(0));
      if (str.length() > 1) {
        System.out.print(", ");
      }
      if (str.length() > 1) {
        printLetters(str.substring(1));
      }
    }
  }


  // Question 6 / 2:

  /**
   * use recursion to return a String that is formed by replacing all
   * occurrences of the character oldChar in the string str with the
   * character newChar
   */
  public static String replace(String str, char oldChar, char newChar) {
    if (str == null) {
      return null;
    }
    if (str.isEmpty()) {
      return "";
    }
    StringBuilder replaced = new StringBuilder();
    replaced.append((str.charAt(0) == oldChar) ? newChar : str.charAt(0));
    String nextPart = replace(str.substring(1), oldChar, newChar);
    return (replaced.append(nextPart)).toString();
  }


  // Question 6 / 3:

  /**
   * use recursion to find and return the index of the first occurrence of
   * the character ch in the string str, or -1 if ch does not occur in str.
   */
  public static int indexOf(char ch, String str) {
    if (str == null || str.isEmpty()) return -1;
    if (str.charAt(0) == ch) return 0;
    int resultFromSubString = indexOf(ch, str.substring(1));
    return (resultFromSubString == -1) ? -1 : 1 + resultFromSubString;
  }


  public static void main(String[] args) {
    System.out.println("printLetters test cases:");
    System.out.println("printLetters(null)): ");
    printLetters(null);

    System.out.println("printLetters(\"\"): ");
    printLetters("");

    System.out.println("printLetters('Hello'): ");
    printLetters("Hello");
    System.out.println();
    System.out.println("----------------------------------");


    System.out.println("replaces test cases:");
    System.out.println("replace(null, 'e', 'y') == null: " + (replace(null,
      'e', 'y') == null ? "passed" : "failed"));
    System.out.println("replace(\"\", 'e', 'y') == \"\": " + (replace("",
      'e', 'y').equals("") ? "passed" : "failed"));
    System.out.println("replace(\"base case\", 'e', 'y') == \"basy casy\": " +
      (replace
        ("base case", 'e', 'y').equals("basy casy") ? "passed" : "failed"));
    System.out.println("----------------------------------");


    System.out.println("indexOf test cases:");
    System.out.println("indexOf('e', null) == -1: " + (indexOf('e',
      null) == -1 ? "passed" : "failed"));
    System.out.println("indexOf('e', \"\") == -1: " + (indexOf('e',
      "") == -1 ? "passed" : "failed"));
    System.out.println("indexOf('m', \"animals\") == 3: " + (indexOf('m',
      "animal") == 3 ? "passed" : "failed"));
    System.out.println("indexOf('q', \"going to the zoo\") == -1: " +
      (indexOf('q', "going to the zoo") == -1 ? "passed" : "failed"));
  }
}
