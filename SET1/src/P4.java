// Question 4 / 1:
public class P4 {
  /**
   * searches for an item an array that can contain any type of object
   */
  public static <T> boolean search(T item, T[] arr) {
    for (int i = 0; i < arr.length; i++) {
      if ((arr[i]).equals(item)) {
        return true;
      }
    }
    return false;
  }


  /**
   * searches for an item an array that can contain any type of object, using
   * recursion instead of iteration
   */
  public static <T> boolean search(T item, T[] arr, int idx) {
    if (idx > arr.length - 1) {
      return false;
    }
    return arr[idx].equals(item) || search(item, arr, idx + 1);
  }

  public static void main(String[] args) {
    String[] testone = {"new","hella", "world"};
    boolean result = search("hello", testone, 0);
    System.out.println(result);
  }
}

