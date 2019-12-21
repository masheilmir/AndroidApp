package com.b07.users;

import android.content.Context;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.exceptions.InvalidInputException;

public class Admin extends User {
  /**
   * This is an Admin class to extend User
   * 
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   */

  public Admin(int id, String name, int age, String address) {
    super(id, name, age, address);
  }

  
  /**
   * This is a class to extend User
   * 
   * @param id the user's id
   * @param name the user's name
   * @param age the user's age
   * @param address the user's address
   * @param authenticated authenticate users
   */
  public Admin(int id, String name, int age, String address, boolean authenticated) {
    super(id, name, age, address, authenticated);
  }

  /**
   * find the location of role's id in the database
   * 
   * @return the index of the role's id
   * @throws SQLException on failure
   */
  private static int findRoleId(Context context) throws SQLException {

    /*
     * for(int id: DatabaseSelectHelper.getRoleIds()) { String role =
     * DatabaseSelectHelper.getRoleName(id); if(role.equals(Roles.ADMIN.name())) { return id; } }
     * return -1;
     */
    DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(context);

    List<Integer> roleIds = new ArrayList<>();
    roleIds = select.getRoleIdsAndroid();
    String role;
    int index = -1;
    for (int i = 0; i < roleIds.size(); i++) {
      role = select.getRoleNameAndroid(roleIds.get(i));
      if (role.equalsIgnoreCase("Admin")) {
        index = i;
        break;
      }
    }
    return roleIds.get(index);
  }

  /**
   * 
   * @param employee the employee
   * @return true if the employee is promoted, false if not
   */
  public boolean promoteEmployee(Employee employee, Context context) {
    DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(context);
    DatabaseUpdateAndroidHelper update = new DatabaseUpdateAndroidHelper(context);
    try {
      int roleid = select.getRoleIdAndroid(Roles.EMPLOYEE.name());
      int adminId = select.getRoleIdAndroid(Roles.ADMIN.name());
      int roleIdtoUser = select.getUserRoleIdAndroid(employee.getId());
      if (roleid == roleIdtoUser) {
        update.updateUserRoleAndroid(adminId, employee.getId());
        return true;
      }
      return false;
    } catch (Exception e) {
      return false;
    }
  }
}
