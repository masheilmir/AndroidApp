package com.b07.store;

import java.util.List;


public interface SalesLog {
	/**
	 * This is an interface to show the saleslog
	 */
  public void addSale(Sale sale);

  public void addSales(List<Sale> sales);

  public List<Sale> getSales();

  public Sale getSale(int id);

  public List<Sale> getSalesByItem(int itemId);

  public List<Sale> getSalesByUser(int userId);
}
