/*
 * Median.java
 *
 * Starter code by Computer Science E-22 Staff
 *
 * Modifed by:      <Yiling Jiang>, <yiling124@gmail.com>
 * Date modified:   <09/24/2017>
 */

import java.util.Arrays;

public class Median {
  /*
   * swap - swap the values of the array elements at
   * position a and position b of the array to which arr refers.
   * Used by the partition method below.
   */
  private static void swap(int[] arr, int a, int b) {
    int temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
  }

  /* partition - helper method for your recursive median-finding method */
  private static int partition(int[] arr, int first, int last) {
    int pivot = arr[(first + last)/2];
    int i = first - 1;  // index going left to right
    int j = last + 1;   // index going right to left

    while (true) {
      do {
        i++;
      } while (arr[i] < pivot);
      do {
        j--;
      } while (arr[j] > pivot);

      if (i < j)
        swap(arr, i, j);
      else
        return j;   // index of last element in the left subarray
    }
  }

  /*
   * findMedian - "wrapper" method for your recursive median-finding method.
   * It just makes the initial call to that method, passing it
   * whatever initial parameters make sense.
   */
  public static void findMedian(int[] arr) {
    if (arr.length == 0 || arr.length == 1) {
      return;
    }
    // add an appropriate call to your recursive method
    fMedian(arr, 0, arr.length - 1);
  }

  /*
   * Put the definition of your recursive median-finding method below.
   */
  private static void fMedian(int[] arr, int first, int last) {
    double split = partition(arr, first, last);
    int median = arr.length / 2;
    if (arr.length % 2 != 0) {
      if (split == median) {
        return;
      }
      if (split > median) {
        fMedian(arr, 0, (int)split - 1);
      }
      if (split < median) {
        fMedian(arr, (int)split + 1, last);
      }
    } else {
      if (first == median - 1 && last == median) {
        return;
      }
      if (split <= median - 1) {
        fMedian(arr, (int)split, last);
      } else {
        fMedian(arr, first, (int)split);
      }
    }
  }

  private static double getMedian(int[] arr) {
    findMedian(arr);
    if (arr.length % 2 != 0) {
      return arr[arr.length / 2];
    }
    return (double)(arr[arr.length/ 2] + arr [(arr.length / 2) - 1])/ 2;
  }

  public static void main(String[] args) {
    // the median of this array is 15
    int[] oddLength = {4, 18, 12, 34, 7, 42, 15, 22, 5};

    // the median of this array is the average of 15 and 18 = 16.5
    int[] evenLength = {4, 18, 12, 34, 7, 42, 15, 22, 5, 27};

    int[] testCaseThree = {};

    int[] testCaseFour = {1};

    int[] testCaseFive = {6,6,6,6,6};


        /* Put code to test your method here. */
    System.out.println("median of [4, 18, 12, 34, 7, 42, 15, 22, 5] is 15: "
      + (getMedian(oddLength) == 15));
    System.out.println();

    System.out.println("median of [4, 18, 12, 34, 7, 42, 15, 22, 5, 27] is " +
      "16.5: " + (getMedian(evenLength) == 16.5));
    System.out.println();

    findMedian(testCaseThree);
    System.out.println(testCaseThree.length == 0);
    System.out.println();

    System.out.println("median of [1] is 1: " + (getMedian
      (testCaseFour) ==
      1));
    System.out.println();

    System.out.println("median of [6, 6, 6, 6, 6] is 6: " + (getMedian
      (testCaseFive) == 6) );

  }
}
