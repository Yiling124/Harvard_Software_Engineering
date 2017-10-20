import java.util.Arrays;
/**
 * Shifts all of the array’s elements one position right, with the original
 * last element
 * wrapping around to become the new first element.
 */
public class P2 {
  public static void shiftRight(int[] arr) {
    if (arr == null) {
      throw new IllegalArgumentException("parameter other is not valid");
    } else if (arr.length > 1) {
      int new_first_element = arr[arr.length - 1];
      for (int i = arr.length - 1; i >= 1; i--) {
        arr[i] = arr[i - 1];
      }
      arr[0] = new_first_element;
    }
  }

  /**
   * returns the index of the first occurrence of the first list in the
   * second list
   */
  public static int indexOf(int[] arr1, int[] arr2) {
    for (int i = 0; i <= arr2.length - arr1.length; i++) {
      int index_arr1 = 0, index_arr2 = i;
      while (index_arr1 < arr1.length) {
        if (arr1[index_arr1] != arr2[index_arr2]) {
          break;
        }
        ++index_arr1;
        ++index_arr2;
      }
      if (index_arr1 == arr1.length) {
        return i;
      }
    }
    return -1;
  }

  public static void main (String[] args) {
    int[] testone = {1,2,4,6,8,10};
    shiftRight(testone);
    System.out.println(Arrays.toString(testone));
  }
}

