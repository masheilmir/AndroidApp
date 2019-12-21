package com.b07.inventory;

import java.util.HashMap;

public class InventoryImpl implements Inventory {

  private HashMap<Item, Integer> itemMap;
  private int total;

  /**
   * This is a class to implement the Inventory
   * 
   * @param itemMap the hashmap with Item and Integer
   * @param total the total number of items
   */
  public InventoryImpl(HashMap<Item, Integer> itemMap, int total) {
    this.itemMap = itemMap;
    this.total = total;
  }


  /**
   * @return current itemMap
   */
  // Get the ItemMap
  @Override
  public HashMap<Item, Integer> getItemMap() {
    // TODO Auto-generated method stub
    return itemMap;
  }

  /**
   * @param itemMap the new HashMap to be set
   */
  // Set the ItemMap
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // TODO Auto-generated method stub
    this.itemMap = itemMap;
  }


  /**
   * @param item the item need to be updated
   * @param value the value need to be updated
   */
  // Update the ItemMap with given item and value
  @Override
  public void updateMap(Item item, Integer value) {
    boolean found = false;
    // TODO Auto-generated method stub
    for (Item inventoryItem : itemMap.keySet()) {
      if (inventoryItem.getId() == item.getId()) {
        itemMap.put(inventoryItem, itemMap.get(inventoryItem) + value);
        found = true;
      }
    }

    if (!found) {
      itemMap.put(item, value);
    }
  }

  /**
   * @return total the total number of items
   */
  // Get the total items
  @Override
  public int getTotalItems() {
    // TODO Auto-generated method stub
    return total;
  }

  /**
   * @param total the total number of items
   */
  // Set the total items
  @Override
  public void setTotalItems(int total) {
    // TODO Auto-generated method stub
    this.total = total;
  }


}
