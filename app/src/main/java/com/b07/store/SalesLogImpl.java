package com.b07.store;

import java.util.List;
import com.b07.inventory.Item;
import java.util.ArrayList;

public class SalesLogImpl implements SalesLog {
  private ArrayList<Sale> sales = new ArrayList<Sale>();

  /**
   * This is a class to implement SalesLog
   */
  public SalesLogImpl() {
    this.sales = new ArrayList<>();
  }

  /**
   * This is a class to implement SalesLog
   * 
   * @param sales the sales
   */
  public SalesLogImpl(ArrayList<Sale> sales) {
    this.sales = sales;
  }

  /**
   * 
   * @param sale the new sale
   */
  // Add the sale
  @Override
  public void addSale(Sale sale) {
    sales.add(sale);
  }

  /**
   * 
   * @return the list of sales
   */
  // Get the sale
  @Override
  public List<Sale> getSales() {
    return this.sales;
  }

  /**
   * add the list of sales
   * 
   * @param sales the new list of sales
   */
  // Add the sale list
  @Override
  public void addSales(List<Sale> sales) {
    this.sales.addAll(sales);
  }

  
  /**
   * 
   * @param id the sale's id
   * @return the sale corresponding to the id
   */
  // Get the sale
  @Override
  public Sale getSale(int id) {
    for (Sale sale : sales) {
      if (sale.getId() == id) {
        return sale;
      }
    }
    return null;
  }

  /**
   * 
   * @param itemId the item's id
   * @return the list of sales by the given item id
   */
  // Get the sale by the given itemid
  @Override
  public List<Sale> getSalesByItem(int itemId) {
    List<Sale> salesByItem = new ArrayList<>();

    for (Sale sale : sales) {
      for (Item item : sale.getItemMap().keySet()) {
        if (item.getId() == itemId) {
          salesByItem.add(sale);
          break;
        }
      }
    }
    return salesByItem;
  }

  /**
   * 
   * @param userId the user's id
   * @return the list of sales by the given user's id
   */
  // Get the sale by the given userid
  @Override
  public List<Sale> getSalesByUser(int userId) {
    List<Sale> salesByUser = new ArrayList<>();

    for (Sale sale : sales) {
      if (sale.getUser().getId() == userId) {
        salesByUser.add(sale);
      }
    }
    return salesByUser;
  }

}
