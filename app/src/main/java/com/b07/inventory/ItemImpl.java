package com.b07.inventory;

import java.math.BigDecimal;

public class ItemImpl implements Item {

  private int id;
  private String name;
  private BigDecimal price;

  /**
   * This is a class to implement the Item
   * 
   * @param id the id about the item
   * @param name the name of the item
   * @param price the price of the item
   */
  public ItemImpl(int id, String name, BigDecimal price) {
    this.id = id;
    this.name = name;
    this.price = price;
  }


  /**
   * @return the item id
   */
  // Get the id
  @Override
  public int getId() {
    // TODO Auto-generated method stub
    return id;
  }

  /**
   * @param id the new item id
   */
  // Set the id
  @Override
  public void setId(int id) {
    // TODO Auto-generated method stub
    this.id = id;
  }

  /**
   * @return the item name
   */
  // Get the name
  @Override
  public String getName() {
    // TODO Auto-generated method stub
    return name;
  }

  /**
   * @param name the new item name
   */
  // Set the name
  @Override
  public void setName(String name) {
    // TODO Auto-generated method stub
    this.name = name;
  }

  /**
   * @return the item price
   */
  // Get the price
  @Override
  public BigDecimal getPrice() {
    // TODO Auto-generated method stub
    return price;
  }

  /**
   * @param price the new item price
   */
  // Set the price
  @Override
  public void setPrice(BigDecimal price) {
    // TODO Auto-generated method stub
    this.price = price;
  }

}
