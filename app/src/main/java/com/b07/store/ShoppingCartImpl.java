package com.b07.store;

import android.content.Context;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.exceptions.InvalidInputException;
import com.b07.exceptions.UserNotLoggedInException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.users.Customer;

public class ShoppingCartImpl implements ShoppingCart {
  private HashMap<Item, Integer> items = new HashMap<>();
  private Customer customer;
  private final BigDecimal TAXRATE = new BigDecimal(1.13);
  private int account = -1;

  
  /**
   * This is a class to implement ShoppingCart
   * 
   * @param customer the customer
   * @throws UserNotLoggedInException on failure
   */
  public ShoppingCartImpl(Customer customer) throws UserNotLoggedInException {
    if (customer.isAuthenticated()) {
      this.customer = customer;
    } else {
      throw new UserNotLoggedInException();
    }
  }

  /**
   * add items into the shopping cart
   * 
   * @param item the item in shopping cart
   * @param quantity the quantity of the item
   */
  // Add the given item and quantity to the item
  @Override
  public void addItem(Item item, int quantity) {
    boolean found = false;

    if (item != null && quantity != 0 && items != null) {
      for (Item currentItem : items.keySet()) {
        if (currentItem.getId() == item.getId()) {
          items.put(currentItem, items.get(currentItem) + quantity);
          found = true;
        }
      }
    }

    if (!found && item != null && quantity != 0) {
      items.put(item, quantity);
    }
  }

  /**
   * remove items into the shopping cart
   * 
   * @param item the item in shopping cart
   * @param quantity the quantity of the item
   */
  // Remove the item
  @Override
  public void removeItem(Item item, int quantity) {
    boolean remove = false;
    if (item != null) {
      for (Item currentItem : items.keySet()) {
        if (currentItem.getId() == item.getId() && items.get(currentItem) > quantity) {
          items.put(currentItem, items.get(currentItem) - quantity);
        } else if (currentItem.getId() == item.getId()) {
          remove = true;
          item = currentItem;
        }
      }

      if (remove) {
        items.remove(item);
      }
    }
  }

  /**
   * 
   * @return the list of all items
   */
  // Get the item list
  @Override
  public List<Item> getItems() {
    List<Item> allItems = new ArrayList<>();

    if (items == null) {
      return allItems;
    }

    for (Item item : items.keySet()) {
      allItems.add(item);
    }

    return allItems;
  }

  /**
   * 
   * @return the customer
   */
  // Get the customer
  @Override
  public Customer getCustomer() {
    // TODO Auto-generated method stub
    return customer;
  }

  /**
   * 
   * @return the total number of items
   */
  // Get the total price of all items
  @Override
  public BigDecimal getTotal() {
    BigDecimal cartTotal = new BigDecimal(0);
    for (Item item : this.getItems()) {
      cartTotal =
          cartTotal.add(item.getPrice().multiply(new BigDecimal(items.get(item)))).setScale(2);
    }
    cartTotal = cartTotal.multiply(TAXRATE).setScale(2, BigDecimal.ROUND_HALF_UP);
    return cartTotal;
  }

  /**
   * 
   * @return the tax rate
   */
  // Get the tax rate
  @Override
  public BigDecimal getTaxRate() {
    // TODO Auto-generated method stub
    return TAXRATE;
  }

  /**
   * 
   * @return true if the input is valid, false if not
   * @throws InvalidInputException on failure
   */
  // Checkout if the input is valid
  @Override
  public boolean checkOut(Context context) {
    DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(context);
    DatabaseInsertAndroidHelper insert = new DatabaseInsertAndroidHelper(context);
    DatabaseUpdateAndroidHelper update = new DatabaseUpdateAndroidHelper(context);

    if (customer == null) {
      return false;
    }
    try {
      Inventory inventory = select.getInventoryAndroid();
      HashMap<Item, Integer> itemMap = inventory.getItemMap();

      if (inventory.getTotalItems() == 0 || items.size() == 0) {
        return false;
      }

      for (Item item : itemMap.keySet()) {
        for (Item cartItem : items.keySet()) {
          if ((item.getId() == cartItem.getId() && itemMap.get(item) < items.get(cartItem))
                  || select.getInventoryQuantityAndroid(cartItem.getId()) == 0) {
            return false;
          }
        }
      }

      long saleId = insert.insertSaleAndroid(customer.getId(), this.getTotal());

      for (Item invItem : itemMap.keySet()) {
        for (Item cartItem : items.keySet()) {
          if (cartItem.getId() == invItem.getId()) {
            insert.insertItemizedSaleAndroid((int) saleId, cartItem.getId(), items.get(cartItem));
            update.updateInventoryQuantityAndroid(itemMap.get(invItem) - items.get(cartItem),
                    cartItem.getId());
          }
        }
      }
    } catch (InvalidInputException e) {
      return false;
    }

    this.clearcart();

    return true;
  }


  // Clear the shopping cart
  @Override
  public void clearcart() {
    items.clear();
  }

  /**
   * 
   * @param itemId the item's id
   * @return the quantity of the given item
   */
  @Override
  public int getQuantity(int itemId) {
    for (Item item : items.keySet()) {
      if (item.getId() == itemId) {
        return items.get(item);
      }
    }
    return 0;
  }

  @Override
  public void setAssociatedAccount(int id) {
    this.account = id;
  }

  @Override
  public int getAssociatedAccount() {
    return account;
  }

}
