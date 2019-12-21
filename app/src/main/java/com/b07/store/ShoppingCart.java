package com.b07.store;

import android.content.Context;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.b07.exceptions.InvalidInputException;
import com.b07.inventory.Item;
import com.b07.users.Customer;

public interface ShoppingCart extends Serializable {
	/**
	 * This is an interface to show the shopping cart
	 */
  public void addItem(Item item, int quantity);

  public void removeItem(Item item, int quantity);

  public List<Item> getItems();

  public int getQuantity(int itemId);

  public Customer getCustomer();

  public BigDecimal getTotal();

  public BigDecimal getTaxRate();

  public boolean checkOut(Context contect);

  public void clearcart();

  public void setAssociatedAccount(int id);

  public int getAssociatedAccount();
}
