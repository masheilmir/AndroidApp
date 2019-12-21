package com.b07.store;

import android.content.Context;

import java.sql.SQLException;
import java.util.List;

import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.FailedToCreateUserException;
import com.b07.exceptions.InvalidInputException;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.inventory.ItemTypes;
import com.b07.users.Employee;
import com.b07.users.Roles;

public class EmployeeInterfaceImpl implements EmployeeInterface {
  private Inventory inventory;
  private Employee employee;
  DatabaseSelectAndroidHelper select;
  DatabaseInsertAndroidHelper insert;
  DatabaseUpdateAndroidHelper update;

  /**
   * This is a class to implement EmployeeInterface
   * 
   * @param employee the employee
   * @param inventory the inventory
   */
  public EmployeeInterfaceImpl(Employee employee, Inventory inventory, Context context) {
    select = new DatabaseSelectAndroidHelper(context);
    insert = new DatabaseInsertAndroidHelper(context);
    update = new DatabaseUpdateAndroidHelper(context);
    if (employee.isAuthenticated()) {
      this.employee = employee;
    }
    this.inventory = inventory;
  }

  /**
   * This is a class to implement EmployeeInterface
   * 
   * @param inventory the inventory
   */
  public EmployeeInterfaceImpl(Inventory inventory) {
    this.inventory = inventory;
  }

  
  /**
   * @param employee the new employee
   */
  // Set the current employee
  @Override
  public void setCurrentEmployee(Employee employee) {
    if (employee.isAuthenticated()) {
      this.employee = employee;
    }
  }

  /**
   * @return whether employee exists or not
   */
  // Check if the current emplyee exists
  @Override
  public boolean hasCurrentEmployee() {
    return employee != null;
  }

  /**
   * @param item the item
   * @param quantity the quantity of items
   * @return whether the invemtory has been restocked
   */
  // Restock the inventory with given item and quantity
  @Override
  public boolean restockInventory(Item item, int quantity) {
    for (ItemTypes itemName : ItemTypes.values()) {
      if (itemName.name().equals(item.getName()) && quantity >= 0) {
        if(select.getInventoryQuantityAndroid(item.getId()) == -1) {
          try {
            insert.insertInventoryAndroid(item.getId(), quantity);
          } catch (InvalidInputException e) {
            return false;
          }
        } else {
          update.updateInventoryQuantityAndroid(
                  select.getInventoryQuantityAndroid(item.getId()) + quantity, item.getId());
        }
        return true;
      }
    }
    return false;
  }

  /**
   * @param name the name of the customer
   * @param age the age of the customer
   * @param address the address of the customer
   * @param password each customer's password
   * @return user's id
   * @throws FailedToCreateUserException on failure
   */
  // create a customer
  @Override
  public int createCustomer(String name, int age, String address, String password)
      throws FailedToCreateUserException {
    try {
      long userId = insert.insertNewUserAndroid(name, age, address, password);
      int roleId = select.getRoleIdAndroid(Roles.CUSTOMER.name());
      long user = insert.insertUserRoleAndroid((int) userId, roleId);
      return (int) userId;

    } catch (InvalidInputException e) {
      // TODO Auto-generated catch block
      throw new FailedToCreateUserException();
    }
  }

  
  /**
   * @param name the name of the employee
   * @param age the age of the employee
   * @param address the address of the employee
   * @param password each employee's password
   * @return user's id
   * @throws FailedToCreateUserException on failure
   */
  // create an employee
  @Override
  public int createEmployee(String name, int age, String address, String password)
      throws FailedToCreateUserException {
    try {
      long userId = insert.insertNewUserAndroid(name, age, address, password);
      int roleId = select.getRoleIdAndroid(Roles.EMPLOYEE.name());
      long user = insert.insertUserRoleAndroid((int) userId, roleId);
      return (int) userId;
    } catch (InvalidInputException e) {
      throw new FailedToCreateUserException();
    }
  }

}
