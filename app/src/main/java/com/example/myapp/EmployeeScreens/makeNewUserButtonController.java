package com.example.myapp.EmployeeScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.exceptions.FailedToCreateUserException;
import com.b07.inventory.Inventory;
import com.b07.store.EmployeeInterfaceImpl;
import com.b07.users.Employee;
import com.b07.users.User;
import com.example.myapp.R;

public class makeNewUserButtonController implements View.OnClickListener {

  private Context appContext;
  private DatabaseSelectAndroidHelper select;
  private int id;
  private Intent intent;

  public makeNewUserButtonController(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
    this.id = select.getUsersDetailsAndroid().get(1).getId();
  }



  @Override
  public void onClick(View v) {
    EditText rName = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.nameEntered);
    EditText rAge = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.ageEntered);
    EditText rAddress =
        (EditText) ((AppCompatActivity) appContext).findViewById(R.id.addressEntered);
    EditText rPassword =
        (EditText) ((AppCompatActivity) appContext).findViewById(R.id.passwordEntered);
    try {
      String name = rName.getText().toString();
      int age = Integer.parseInt(rAge.getText().toString());
      String address = rAddress.getText().toString();
      String password = rPassword.getText().toString();

      Employee employee = (Employee) select.getUserDetailsAndroid(id);
      Inventory inventory = select.getInventoryAndroid();
      EmployeeInterfaceImpl emp = new EmployeeInterfaceImpl(employee, inventory, appContext);

      int customerId = emp.createCustomer(name, age, address, password);
      Toast.makeText(appContext, "Success, New Customer Id is " + customerId, Toast.LENGTH_LONG)
          .show();
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
