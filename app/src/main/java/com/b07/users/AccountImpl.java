package com.b07.users;

import java.util.HashMap;
import com.b07.inventory.Item;
import com.b07.store.ShoppingCart;

public class AccountImpl implements Account {
  private User user;
  private HashMap<Item, Integer> cart;
  private int accountId;

  /**
   * This is a class to implement Account
   * 
   * @param accountId the account's id
   * @param user the user
   * @param cart the shopping cart
   */
  public AccountImpl(int accountId, User user, HashMap<Item, Integer> cart) {
    if (user.isAuthenticated()) {
      this.user = user;
    }
    this.cart = cart;
    this.accountId = accountId;
  }

  /**
   * This is a class to implement Account
   * 
   * @param accountId the account's id
   * @param cart the shopping cart
   */
  public AccountImpl(int accountId, HashMap<Item, Integer> cart) {
    this.cart = cart;
    this.accountId = accountId;
  }

  /**
   * @return the user
   */
  @Override
  public User getUser() {
    // TODO Auto-generated method stub
    return this.user;
  }

  /**
   * 
   * @param user the new user
   */
  @Override
  public void setUser(User user) {
    // TODO Auto-generated method stub
    if (user.isAuthenticated()) {
      this.user = user;
    }
  }

  /**
   * 
   * @return the cart
   */
  @Override
  public HashMap<Item, Integer> getCart() {
    // TODO Auto-generated method stub
    return cart;
  }

  /**
   * 
   * @param cart the new cart
   */
  @Override
  public void setCart(HashMap<Item, Integer> cart) {
    // TODO Auto-generated method stub
    this.cart = cart;
  }

  /**
   * add the item to the shopping cart
   * 
   * @param item the item in shopping cart
   * @param quantity the quantity of the item in shopping cart
   */
  @Override
  public void addItemToCart(Item item, int quantity) {
    boolean found = false;
    for (Item cartItem : cart.keySet()) {
      if (cartItem.getId() == item.getId()) {
        cart.put(cartItem, cart.get(cartItem) + quantity);
        found = true;
      }
    }

    if (!found) {
      cart.put(item, quantity);
    }
  }

  /**
   * remove item from shopping cart
   * 
   * @param item the item in shopping cart
   * @param quantity the quantity of the item in shopping cart
   */
  @Override
  public void removeItemFromCart(Item item, int quantity) {
    boolean found = false;
    for (Item cartItem : cart.keySet()) {
      if (cartItem.getId() == item.getId() && cart.get(cartItem) > quantity) {
        cart.put(cartItem, cart.get(cartItem) - quantity);
      } else if (cartItem.getId() == item.getId()) {
        found = true;
      }
    }

    if (found) {
      cart.put(item, quantity);
    }
  }

  /**
   * check whether the user exists
   * 
   * @return true if the user exists, false if the user does not exist
   */
  @Override
  public boolean hasUser() {
    return this.user == null;
  }

}
