package com.b07.store;

import java.math.BigDecimal;
import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.users.User;

public class SaleImpl implements Sale {

  private int id;
  private User user;
  private BigDecimal price;
  private HashMap<Item, Integer> itemMap;

  /**
   * This is a class to implement Sale
   * 
   * @param id the sale id
   * @param user the user
   * @param price the price
   */
  public SaleImpl(int id, User user, BigDecimal price) {
    this.id = id;
    this.user = user;
    this.price = price;
  }

  /**
   * This is a class to implement Sale
   * 
   * @param id the sale id
   * @param itemMap the hashmap
   */
  public SaleImpl(int id, HashMap<Item, Integer> itemMap) {
    this.id = id;
    this.itemMap = itemMap;
  }

  /**
   * @return sale id
   */
  // Get the id
  @Override
  public int getId() {
    // TODO Auto-generated method stub
    return this.id;
  }

  
  /**
   * @param id the new sale id
   */
  // Set the id
  @Override
  public void setId(int id) {
    // TODO Auto-generated method stub
    this.id = id;
  }

  /**
   * @return user
   */
  // Get the user
  @Override
  public User getUser() {
    // TODO Auto-generated method stub
    return this.user;
  }

  
  /**
   * @param user the new user
   */
  // Set the user
  @Override
  public void setUser(User user) {
    // TODO Auto-generated method stub
    this.user = user;
  }

  /**
   * @return price
   */
  // Get the total price
  @Override
  public BigDecimal getTotalPrice() {
    // TODO Auto-generated method stub
    return this.price;
  }

  
  /**
   * @param price the new total price
   */
  // Set the total price
  @Override
  public void setTotalPrice(BigDecimal price) {
    // TODO Auto-generated method stub
    this.price = price;
  }

  /**
   * @return itemMap
   */
  // Get the ItemMap
  @Override
  public HashMap<Item, Integer> getItemMap() {
    // TODO Auto-generated method stub
    return this.itemMap;
  }

  /**
   * @param itemMap the new itemMap
   */
  // Set the ItemMap
  @Override
  public void setItemMap(HashMap<Item, Integer> itemMap) {
    // TODO Auto-generated method stub
    this.itemMap = itemMap;
  }

}
