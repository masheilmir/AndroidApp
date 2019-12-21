package com.b07.users;

import java.util.HashMap;
import com.b07.inventory.Item;

public interface Account {
	/**
	 * This is an interface to show the users' account
	 */
  public User getUser();

  public void setUser(User user);

  public boolean hasUser();

  public HashMap<Item, Integer> getCart();

  public void setCart(HashMap<Item, Integer> cart);

  public void addItemToCart(Item item, int quantity);

  public void removeItemFromCart(Item item, int quantity);
}

