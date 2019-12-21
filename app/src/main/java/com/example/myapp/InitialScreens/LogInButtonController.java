package com.example.myapp.InitialScreens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.Roles;
import com.b07.users.User;
import com.example.myapp.AdminScreens.adminOptions;
import com.example.myapp.EmployeeScreens.employeeOptions;
import com.example.myapp.R;
import com.example.myapp.UserScreens.userOptions;
import static android.app.PendingIntent.getActivity;

public class LogInButtonController implements View.OnClickListener {
  private Context appContext;
  private DatabaseSelectAndroidHelper select;

  public LogInButtonController(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    EditText userId = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.useridEditText);
    EditText password =
        (EditText) ((AppCompatActivity) appContext).findViewById(R.id.passwordEditText);
    try {
      int id = Integer.parseInt(userId.getText().toString());
      String pass = password.getText().toString();

      User user = select.getUserDetailsAndroid(id);
      if (user == null) {
        Toast.makeText(appContext, "Invalid User Id", Toast.LENGTH_SHORT).show();
      } else {
        boolean loggedIn = user.authenticate(pass, appContext);
        if (!loggedIn) {
          Toast.makeText(appContext, "Invalid Password", Toast.LENGTH_SHORT).show();
        } else {
          String roleName = select.getRoleNameAndroid(select.getUserRoleIdAndroid(id));
          Intent intent;
          if (roleName.equals(Roles.ADMIN.name())) {
            intent = new Intent(appContext, adminOptions.class);
            this.activityStarter(intent, id, pass);
          } else if (roleName.equals(Roles.EMPLOYEE.name())) {
            intent = new Intent(appContext, employeeOptions.class);
            this.activityStarter(intent, id, pass);
          } else if (roleName.equals(Roles.CUSTOMER.name())) {
            intent = new Intent(appContext, userOptions.class);
            this.activityStarter(intent, id, pass);
          }
        }
      }
    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "Invalid User Id", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }

  private void activityStarter(Intent intent, int id, String password) {
    intent.putExtra("userId", id);
    intent.putExtra("pass", password);
    appContext.startActivity(intent);
    Activity activityTransition = (Activity) appContext;
    activityTransition.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
  }
}
