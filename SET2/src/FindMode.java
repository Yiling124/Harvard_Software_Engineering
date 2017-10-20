import java.util.HashMap;

public class FindMode {
//  public static int updatedFindMode(int[] arr) {
//    if (arr.length == 1) {
//      return arr[0];
//    }
//    Sort.mergeSort(arr);
//    int[] sortedArr = arr;
//
//    int mode = -1;
//    int modeFreq = 0;
//
//    int count = 1;
//    for (int i = 1; i <= arr.length - 1; i++) {
//      if (arr[i] == arr[i - 1]) {
//        count ++;
//        if (count > modeFreq) {
//          mode = arr[i];
//          modeFreq = count;
//        }
//      } else {
//        count = 1;
//      }
//    }
//    return mode;
//  }
  public static int updatedFindMode(int[] arr) {
    if (arr.length == 0) {
      return -1;
    }
    int count = 0;
    int mode = Integer.MAX_VALUE;
    HashMap <Integer, Integer> myMap = new HashMap<>();
    for (int num : arr) {
      if (myMap.containsKey(num)) {
        myMap.put(num, myMap.get(num) + 1);
      } else {
        myMap.put(num, 1);
      }
    }
    for (int key : myMap.keySet()) {
      if (myMap.get(key) > count) {
        count = myMap.get(key);
        mode = key;
      } else if (myMap.get(key) == count && key < mode) {
        mode = key;
      }
    }
    return mode;
  }

  public static void main(String[] args) {
    int resultOne = updatedFindMode(new int[]{2,3,4,5,7,3,7,5,7,9,9});
    System.out.println(resultOne);

    int resultTwo = updatedFindMode(new int[]{3});
    System.out.println(resultTwo);

    int resultThree = updatedFindMode(new int[]{});
    System.out.println(resultThree);

    int resultFour = updatedFindMode(new int[]{8,8,8});
    System.out.println(resultFour);

  }
}

