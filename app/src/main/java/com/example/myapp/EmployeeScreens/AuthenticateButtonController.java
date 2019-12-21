package com.example.myapp.EmployeeScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.b07.users.*;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.Roles;
import com.b07.users.User;
import com.example.myapp.AdminScreens.adminOptions;
import com.example.myapp.R;
import com.example.myapp.UserScreens.userOptions;

public class AuthenticateButtonController implements View.OnClickListener {
  private Context appContext;
  private DatabaseSelectAndroidHelper select;

  public AuthenticateButtonController(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  public void onClick(View view) {
    EditText userId = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText);
    EditText password = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText2);
    try {
      int id = Integer.parseInt(userId.getText().toString());
      String pass = password.getText().toString();

      Employee user = (Employee) select.getUserDetailsAndroid(id);
      if (user == null) {
        Toast.makeText(appContext, "Invalid User Id", Toast.LENGTH_SHORT).show();
      } else {
        boolean loggedIn = user.authenticate(pass, appContext);
        if (!loggedIn) {
          Toast.makeText(appContext, "Invalid Password", Toast.LENGTH_SHORT).show();
        } else {
          String roleName = select.getRoleNameAndroid(select.getUserRoleIdAndroid(id));
          Intent intent;
          if (roleName.equals(Roles.EMPLOYEE.name())) {
            Toast.makeText(appContext, "Correct Information", Toast.LENGTH_SHORT).show();
            intent = new Intent(appContext, employeeOptions.class);

            this.activityStarter(intent, id);
          } else {
            Toast.makeText(appContext, "Invalid Information", Toast.LENGTH_SHORT).show();
          }
        }
      }
    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "Invalid User Id", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, "error", Toast.LENGTH_SHORT).show();
    }
  }

  private void activityStarter(Intent intent, int id) {
    intent.putExtra("employeeIdSend", id);
    appContext.startActivity(intent);
    Activity activityTransition = (Activity) appContext;
    activityTransition.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
  }
}

