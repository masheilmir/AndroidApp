package com.example.myapp.InitialScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.Roles;
import java.math.BigDecimal;
import com.b07.inventory.*;
import java.util.logging.LogRecord;

public class InitialPageDatabaseInitialize {
  public void initializeDatabase(Context context) {
    DatabaseDriverAndroid database = new DatabaseDriverAndroid(context);
    database.getWritableDatabase();
    DatabaseInsertAndroidHelper insert = new DatabaseInsertAndroidHelper(context);
    DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(context);
    try {
      if (select.getRoleIdsAndroid().size() == 0) {
        long adminUserId = insert.insertNewUserAndroid("Bob", 45, "23 Valley Grove", "12345");
        long employeeUserId =
            insert.insertNewUserAndroid("Bobster", 45, "23 Valley Grove", "12345");
        long customerUserId = insert.insertNewUserAndroid("Bobby", 45, "23 Valley Grove", "12345");
        for (Roles role : Roles.values()) {
          insert.insertRoleAndroid(role.name());
        }

        int adminRoleID = select.getRoleIdAndroid(Roles.ADMIN.name());
        int employeeRoleId = select.getRoleIdAndroid(Roles.EMPLOYEE.name());
        int customerRoleId = select.getRoleIdAndroid(Roles.CUSTOMER.name());
        insert.insertUserRoleAndroid(Math.toIntExact(adminUserId), adminRoleID);
        insert.insertUserRoleAndroid(Math.toIntExact(employeeUserId), employeeRoleId);
        insert.insertUserRoleAndroid(Math.toIntExact(customerUserId), customerRoleId);
        BigDecimal price;

        for (ItemTypes item : ItemTypes.values()) {
          price = new BigDecimal(Math.random() * 5);
          long itemId = insert.insertItemAndroid(item.name(),
              price.multiply(new BigDecimal(2)).setScale(2, BigDecimal.ROUND_HALF_UP));

          insert.insertInventoryAndroid(Math.toIntExact(itemId), 0);
        }

      }
    } catch (Exception e) {
      e.printStackTrace();

    }
  }

  public void nextScreen(Context context) {
    Intent intent = new Intent(context, LogInActivity.class);
    context.startActivity(intent);
  }
}
