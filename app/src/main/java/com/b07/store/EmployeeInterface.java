package com.b07.store;

import java.sql.SQLException;
import com.b07.exceptions.FailedToCreateUserException;
import com.b07.inventory.Item;
import com.b07.users.Employee;

public interface EmployeeInterface {
	/**
	 * This is an interface to show the employee
	 */
  public void setCurrentEmployee(Employee employee);

  public boolean hasCurrentEmployee();

  public boolean restockInventory(Item item, int quantity) throws SQLException;

  public int createCustomer(String name, int age, String address, String password)
      throws FailedToCreateUserException;

  public int createEmployee(String name, int age, String address, String password)
      throws FailedToCreateUserException;
}
