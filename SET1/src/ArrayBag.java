/*
 * ArrayBag.java
 *
 * Author:          Computer Science E-22 staff
 * Modified by:     <Yiling Jiang>, <yiling124@gmail.com>
 * Date modified:   <09/18/2017>
 */

import java.util.*;

/**
 * An implementation of a Bag ADT using an array.
 */
public class ArrayBag implements Bag {
  /**
   * The array used to store the items in the bag.
   */
  private Object[] items;

  /**
   * The number of items in the bag.
   */
  private int numItems;

  public static final int DEFAULT_MAX_SIZE = 50;

  /**
   * Default, no-arg constructor - creates a new, empty ArrayBag with
   * the default maximum size.
   */
  public ArrayBag() {
    items = new Object[DEFAULT_MAX_SIZE];
    numItems = 0;
  }

  /**
   * A constructor that creates a new, empty ArrayBag with the specified
   * maximum size.
   */
  public ArrayBag(int maxSize) {
    if (maxSize <= 0)
      throw new IllegalArgumentException("maxSize must be > 0");
    items = new Object[maxSize];
    numItems = 0;
  }

  /**
   * add - adds the specified item to the Bag.  Returns true on
   * success and false if there is no more room in the Bag.
   */
  public boolean add(Object item) {
    if (item == null)
      throw new IllegalArgumentException("item must be non-null");
    if (numItems == items.length)
      return false;              // no more room!
    else {
      items[numItems] = item;
      numItems++;
      return true;
    }
  }

  /**
   * remove - removes one occurrence of the specified item (if any)
   * from the Bag.  Returns true on success and false if the
   * specified item (i.e., an object equal to item) is not in the Bag.
   */
  public boolean remove(Object item) {
    for (int i = 0; i < numItems; i++) {
      if (items[i].equals(item)) {
        // Shift the remaining items left by one.
        for (int j = i; j < numItems - 1; j++)
          items[j] = items[j + 1];
        items[numItems - 1] = null;

        numItems--;
        return true;
      }
    }

    return false;  // item not found
  }

  /**
   * contains - returns true if the specified item is in the Bag, and
   * false otherwise.
   */
  public boolean contains(Object item) {
    for (int i = 0; i < numItems; i++) {
      if (items[i].equals(item))
        return true;
    }

    return false;
  }

  /**
   * containsAll - does this ArrayBag contain all of the items in
   * otherBag?  Returns false if otherBag is null or empty.
   */
  public boolean containsAll(Bag otherBag) {
    if (otherBag == null || otherBag.numItems() == 0)
      return false;

    Object[] otherItems = otherBag.toArray();
    for (int i = 0; i < otherItems.length; i++) {
      if (!contains(otherItems[i]))
        return false;
    }

    return true;
  }

  /**
   * numItems - returns the number of items in the Bag.
   */
  public int numItems() {
    return numItems;
  }

  /**
   * grab - returns a reference to a randomly chosen in the Bag.
   */
  public Object grab() {
    if (numItems == 0)
      throw new NoSuchElementException("the bag is empty");
    int whichOne = (int) (Math.random() * numItems);
    return items[whichOne];
  }

  /**
   * toArray - return an array containing the current contents of the bag
   */
  public Object[] toArray() {
    Object[] copy = new Object[numItems];

    for (int i = 0; i < numItems; i++)
      copy[i] = items[i];

    return copy;
  }

  /**
   * toString - converts this ArrayBag into a readable String object.
   * Overrides the Object version of this method.
   */
  public String toString() {
    String str = "{";

    for (int i = 0; i < numItems; i++)
      str = str + " " + items[i];
    str = str + " }";

    return str;
  }

  /**
   * returns the maximum number of items that the ArrayBag is able to hold
   */
  public int capacity() {
    return items.length;
  }

  /**
   * returns if the ArrayBag is full
   */
  public boolean isFull() {
    return items.length == numItems();
  }

  /**
   * increases the maximum capacity of the bag by the specified amount
   */
  public void increaseCapacity(int increment) {
    if (increment == 0) {
      return;
    }
    if (increment < 0) {
      throw new IllegalArgumentException("increment is not valid!");
    }
    Object[] new_items = new Object[items.length + increment];
    System.arraycopy(items, 0, new_items, 0, items.length);
    items = new_items;
  }

  /**
   * attempts to remove from the called ArrayBag all occurrences of the items
   * found in the parameter other
   */
  public boolean removeItems(Bag other) {
    if (other == null) {
      throw new IllegalArgumentException("parameter other is not valid");
    }
    if (other.numItems() == 0) {
      return false;
    }
    Object[] otherArr = other.toArray();
    boolean hasRemoved = false;
    for (int i = 0; i < otherArr.length; ++i) {
      while (this.remove(otherArr[i])) {
        hasRemoved = true;
      }
    }
    return hasRemoved;
  }

  /**
   * creates and returns an ArrayBag containing one occurrence of any item
   * that is found in either the called object or the parameter other
   */
  public Bag unionWith(Bag other) {
    if (other == null) {
      throw new IllegalArgumentException("parameter other is not valid");
    }
    Bag unionBag = new ArrayBag(this.capacity() + other.capacity());
    Set<Object> union_set = new HashSet<>();
    for (Object elem : items) {
      if (elem != null) {
        union_set.add(elem);
      }
    }
    for (Object elem : other.toArray()) {
      if (elem != null) {
        union_set.add(elem);
      }
    }
    for (Object elem : union_set) {
      unionBag.add(elem);
    }
    return unionBag;
  }

  /* Test the ArrayBag implementation. */
  public static void main(String[] args) {
    // Create a Scanner object for user input.
    Scanner in = new Scanner(System.in);

    // Create an ArrayBag named bag1.
    System.out.print("Size of bag 1: ");
    int size = in.nextInt();
    Bag bag1 = new ArrayBag(size);
    in.nextLine();    // consume the rest of the line

    // Read in strings, add them to bag1, and print out bag1.
    String itemStr;
    for (int i = 0; i < size; i++) {
      System.out.print("item " + i + ": ");
      itemStr = in.nextLine();
      bag1.add(itemStr);
    }
    System.out.println("bag 1 = " + bag1);
    System.out.println();

    // Select a random item and print it.
    Object item = bag1.grab();
    System.out.println("grabbed " + item);
    System.out.println();

    // Iterate over the objects in bag1, printing them one per
    // line.
    Object[] items = bag1.toArray();
    for (int i = 0; i < items.length; i++)
      System.out.println(items[i]);
    System.out.println();

    // Get an item to remove from bag1, remove it, and reprint the bag.
    System.out.print("item to remove: ");
    itemStr = in.nextLine();
    if (bag1.contains(itemStr))
      bag1.remove(itemStr);
    System.out.println("bag 1 = " + bag1);
    System.out.println();

    // Get the capacity of the bag.
    System.out.print("Bag capacity: " + bag1.capacity());
    System.out.println();

    // Check if the bag is full
    System.out.print("Bag is full: " + bag1.isFull());
    System.out.println();

    // Increase the bag capacity
    System.out.print("Capacity increment: ");
    int increment = in.nextInt();
    bag1.increaseCapacity(increment);
    System.out.print("Bag capacity after increment: " + bag1.capacity());
    System.out.println();

    // build a second bag
    System.out.print("Size of the second bag: ");
    int otherSize = in.nextInt();
    Bag other = new ArrayBag(otherSize);
    in.nextLine();    // consume the rest of the line

    String otherBagItem;
    for (int i = 0; i < otherSize; i++) {
      System.out.print("item " + i + ": ");
      otherBagItem = in.nextLine();
      other.add(otherBagItem);
    }

    // remove from ArrayBag all occurrences of the items found in the
    // parameter other
    bag1.removeItems(other);

    Object[] itemsLeft = bag1.toArray();
    System.out.println("bag items left after removed:");
    for (int i = 0; i < itemsLeft.length; i++)
      System.out.println(itemsLeft[i]);
    System.out.println();

    // creates and returns an ArrayBag containing one occurrence of any item
    // that is found in either the called object or the parameter other
    Object[] unionItems = bag1.unionWith(other).toArray();
    System.out.println("bag items left after union:");
    for (int i = 0; i < unionItems.length; i++)
      System.out.println(unionItems[i]);
    System.out.println();
  }
}


