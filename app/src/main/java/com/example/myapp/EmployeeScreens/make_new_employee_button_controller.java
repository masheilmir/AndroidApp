package com.example.myapp.EmployeeScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.b07.exceptions.FailedToCreateUserException;
import com.b07.inventory.Inventory;
import com.b07.store.EmployeeInterface;
import com.b07.store.EmployeeInterfaceImpl;
import com.b07.users.*;
import com.b07.*;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.Roles;
import com.b07.users.User;
import com.example.myapp.R;
import android.content.Context;

public class make_new_employee_button_controller implements View.OnClickListener {

  private Context appContext;
  private DatabaseSelectAndroidHelper select;
  private int id;
  private Intent intent;

  public make_new_employee_button_controller(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
    this.id = select.getUsersDetailsAndroid().get(1).getId();
  }

  public void onClick(View view) {
    EditText raw_name = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText4);
    EditText raw_age = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText5);
    EditText raw_address = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText6);
    EditText raw_password =
        (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText7);
    try {
      String name = raw_name.getText().toString();
      int age = Integer.parseInt(raw_age.getText().toString());
      String address = raw_address.getText().toString();
      String password = raw_password.getText().toString();

      Employee employee = (Employee) select.getUserDetailsAndroid(id);
      Inventory inventory = select.getInventoryAndroid();
      EmployeeInterfaceImpl me = new EmployeeInterfaceImpl(employee, inventory, appContext);

      int newId = me.createEmployee(name, age, address, password);
      Toast.makeText(appContext, "Success, The new Employee Id is " + String.valueOf(newId),
          Toast.LENGTH_LONG).show();
      intent = new Intent(appContext, employeeOptions.class);
      this.activityStarter(intent);

    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "Invalid Age", Toast.LENGTH_SHORT).show();
    } catch (FailedToCreateUserException e) {
      Toast.makeText(appContext, "Invalid User Details", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }


  private void activityStarter(Intent intent) {

    appContext.startActivity(intent);
    Activity activityTransition = (Activity) appContext;
    activityTransition.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
  }


}


