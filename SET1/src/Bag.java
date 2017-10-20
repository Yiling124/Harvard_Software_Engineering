/* 
 * Bag.java
 * 
 * Author:          Computer Science E-22 staff
 * Modified by:     <Yiling Jiang>, <Yiling124@gmail.com>
 * Date modified:   <09/18/2017
 * >
 */

/**
 * An interface for a Bag ADT.
 */
public interface Bag {
  /**
   * adds the specified item to the Bag.  Returns true on success
   * and false if there is no more room in the Bag.
   */
  boolean add(Object item);

  /**
   * removes one occurrence of the specified item (if any) from the
   * Bag.  Returns true on success and false if the specified item
   * (i.e., an object equal to item) is not in the Bag.
   */
  boolean remove(Object item);

  /**
   * returns true if the specified item is in the Bag, and false
   * otherwise.
   */
  boolean contains(Object item);

  /**
   * returns true if the calling object contain all of the items in
   * otherBag, and false otherwise.  Also returns false if otherBag
   * is null or empty.
   */
  boolean containsAll(Bag otherBag);

  /**
   * returns the number of items in the Bag.
   */
  int numItems();

  /**
   * grab - returns a reference to a randomly chosen in the Bag.
   */
  Object grab();

  /**
   * toArray - return an array containing the current contents of the bag
   */
  Object[] toArray();


  // Add new methods:

  /**
   * capacity - return an integer  showing the maximum number of items that the
   * ArrayBag is able to hold
   */
  int capacity();

  /**
   * isFull - returns an boolean showing true if the ArrayBag is full, and false
   * otherwise
   */
  boolean isFull();

  /**
   * increases the maximum capacity of the bag by
   * the specified amount
   */
  void increaseCapacity(int increment);

  /**
   * attempts to remove from the called ArrayBag all occurrences of the items
   * found in the parameter other
   */
  boolean removeItems(Bag other);

  /**
   * creates and returns an ArrayBag containing one occurrence of any item
   * that is found in either the called object or the parameter
   */
  Bag unionWith(Bag other);
}

