package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.users.Admin;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.example.myapp.R;

public class PromoteEmployeeConfirmButton implements View.OnClickListener {
  private Admin admin;
  private Context context;
  private DatabaseSelectAndroidHelper select;
  private DatabaseUpdateAndroidHelper update;

  public PromoteEmployeeConfirmButton(Admin admin, Context context) {
    this.admin = admin;
    this.context = context;
    this.update = new DatabaseUpdateAndroidHelper(context);
    this.select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    EditText empId = (EditText) ((AppCompatActivity) context).findViewById(R.id.employeeId);
    String tempId = empId.getText().toString();

    try {
      int employeeId = Integer.parseInt(tempId);
      int roleId = select.getUserRoleIdAndroid(employeeId);
      String roleName = select.getRoleNameAndroid(roleId);
      if (roleName.equals(Roles.EMPLOYEE.name())) {
        Employee employee = (Employee) select.getUserDetailsAndroid(employeeId);
        boolean completed = admin.promoteEmployee(employee, context);
        if (!completed) {
          Toast.makeText(context, "Could not promote Employee", Toast.LENGTH_SHORT).show();
        } else {
          Toast.makeText(context, "Promoted Employee!", Toast.LENGTH_SHORT).show();
        }
      } else {
        Toast.makeText(context, "Id is not of an employee's", Toast.LENGTH_SHORT).show();
      }
    } catch (NumberFormatException e) {
      Toast.makeText(context, "Incorrect Id", Toast.LENGTH_SHORT).show();
    }
  }
}
