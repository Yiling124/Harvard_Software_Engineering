/* 
 * MagicSquare.java
 * 
 * Author:          Computer Science E-22 staff
 * Modified by:     Yiling Jiang, yiling124@gmail.com
 * Date modified:   09/17/2017
 */

import java.util.*;

public class MagicSquare {
  // the current contents of the cells of the puzzle values[r][c]
  // gives the value in the cell at row r, column c
  private int[][] values;

  // the order (i.e., the dimension) of the puzzle
  private int order;

  /**
   * Creates a MagicSquare object for a puzzle with the specified
   * dimension/order.
   */
  public MagicSquare(int order) {
    values = new int[order][order];
    this.order = order;
  }

  /**
   * A helper class representing a position in the square.
   * Both 'row' and col' are 0-based indices.
   */
  private class Position {
    public int row;
    public int col;

    public Position(int row, int col) {
      this.row = row;
      this.col = col;
    }
  }

  /**
   * A helper function that get the next position to fill for Problem 8.
   *
   * The next position is obtained as per the algorithm proposed in Problem
   * 8, i.e first fill the first row, then fill the remainder of the first
   * column, then fill the remainder of the second row, then fill the
   * remainder of the second column, etc.
   *
   */
  public Position getNextPosition(Position position) {
    if (position.col == order - 1) {
      return new Position(position.row + 1, position.row);
    }
    if (position.row == order - 1) {
      return new Position(position.col + 1, position.col + 1);
    }
    if (position.col >= position.row) {
      return new Position(position.row, position.col + 1);
    }
    return new Position(position.row + 1, position.col);
  }

  /**
   * This method should call the separate recursive-backtracking method
   * that you will write, passing it the appropriate initial parameter(s).
   * It should return true if a solution is found, and false otherwise.
   */
  public boolean solve() {
    // 'used_number' which number has been used in square.
    boolean[] used_number = new boolean[order * order];
    return recursiveHelper(new Position(0, 0), used_number);
  }

  /**
   * Returns true if the square can be filled to become a magic square.
   *
   * The square is filled in the order proposed in Problem 8 (see
   * getNextPosition for more details). The positions before 'position' have
   * been filled and this function is to answer the question of whether the
   * square can possibly end up be a magic square, with previously positions
   * filled as in 'values'.
   *
   */
  public boolean recursiveHelper(Position position, boolean[] used_number) {
    int magic_sum = ((int) Math.pow(order, 3) + order) / 2;
    int row = position.row;
    int col = position.col;

    // If position to be filled is in the last column, we can use 'magic_sum'
    // calculate which number should be filled.
    if (col == order - 1) {
      int current_sum = 0;
      for (int i = 0; i <= order - 2; ++i) {
        current_sum += values[row][i];
      }
      int target_num = magic_sum - current_sum;
      if (target_num > 0 && target_num <= order * order &&
        !used_number[target_num - 1]) {
        values[row][col] = target_num;
        // If this position happens to be last row as well, check whether the
        // sum of last row is 'magic_sum'.
        if (row == order - 1) {
          int last_row_sum = 0;
          for (int i = 0; i <= order - 1; ++i) {
            last_row_sum += values[row][i];
          }
          return last_row_sum == magic_sum;
        }
        used_number[target_num - 1] = true;
        if (recursiveHelper(getNextPosition(position), used_number)) {
          return true;
        }
        used_number[target_num - 1] = false;
        return false;
      }
      return false;
    }

    // If position to be filled is in the last row.
    if (row == order - 1) {
      int current_sum = 0;
      for (int i = 0; i <= order - 2; ++i) {
        current_sum += values[i][col];
      }
      int target_num = magic_sum - current_sum;
      if (target_num > 0 && target_num <= order * order &&
        !used_number[target_num - 1]) {
        values[row][col] = target_num;
        used_number[target_num - 1] = true;
        if (recursiveHelper(getNextPosition(position), used_number)) {
          return true;
        }
        used_number[target_num - 1] = false;
        return false;
      }
      return false;
    }

    // Otherwise, we try every available number as per 'used_number'.
    for (int number_index = 0; number_index < used_number.length;
         ++number_index) {
      if (!used_number[number_index]) {
        values[row][col] = number_index + 1;
        used_number[number_index] = true;
        if (recursiveHelper(getNextPosition(position), used_number)) {
          return true;
        }
        used_number[number_index] = false;
      }
    }
    return false;
  }

  /**
   * Displays the current state of the puzzle.
   * You should not change this method.
   */
  public void display() {
    for (int r = 0; r < order; r++) {
      printRowSeparator();
      for (int c = 0; c < order; c++) {
        System.out.print("|");
        if (values[r][c] == 0)
          System.out.print("   ");
        else {
          if (values[r][c] < 10) {
            System.out.print(" ");
          }
          System.out.print(" " + values[r][c] + " ");
        }
      }
      System.out.println("|");
    }
    printRowSeparator();
  }

  // A private helper method used by display()
  // to print a line separating two rows of the puzzle.
  private void printRowSeparator() {
    for (int i = 0; i < order; i++)
      System.out.print("-----");
    System.out.println("-");
  }

  public static void main(String[] args) {
    /*******************************************************
     **** You should NOT change any code in this method ****
     ******************************************************/

    Scanner console = new Scanner(System.in);
    System.out.print("What order Magic Square would you like to solve? ");
    int order = console.nextInt();

    MagicSquare puzzle = new MagicSquare(order);
    if (puzzle.solve()) {
      System.out.println("Here's the solution:");
      puzzle.display();
    } else {
      System.out.println("No solution found.");
    }
  }
}