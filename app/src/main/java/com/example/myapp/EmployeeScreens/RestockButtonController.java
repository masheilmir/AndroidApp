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
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.b07.store.EmployeeInterfaceImpl;
import com.b07.users.Employee;
import com.example.myapp.R;

public class RestockButtonController implements View.OnClickListener {
  private Context appContext;
  private DatabaseSelectAndroidHelper select;
  private DatabaseUpdateAndroidHelper update;

  private Intent intent;

  public RestockButtonController(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
    update = new DatabaseUpdateAndroidHelper(context);
  }



  @Override
  public void onClick(View v) {
    EditText itid = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.editText4);
    EditText itquan = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.quantityPrompt);

    try {
      int itemid = Integer.parseInt(itid.getText().toString());
      int itemquan = Integer.parseInt(itquan.getText().toString())
          + select.getInventoryQuantityAndroid(itemid);

      Item newitem = select.getItemAndroid(itemid);
      if (newitem == null) {
        Toast.makeText(appContext, "Invalid Item Id", Toast.LENGTH_SHORT).show();
      } else {
        boolean upcheck = update.updateInventoryQuantityAndroid(itemquan, itemid);
        if (upcheck == true) {
          Toast.makeText(appContext, "Update Successful", Toast.LENGTH_SHORT).show();
          intent = new Intent(appContext, employeeOptions.class);
          this.activityStarter(intent);
        } else {
          Toast.makeText(appContext, "Update Failed, Invalid information", Toast.LENGTH_LONG)
              .show();
        }


      }
    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "Invalid Information", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, "Invalid Information", Toast.LENGTH_SHORT).show();
    }

  }

  private void activityStarter(Intent intent) {
    appContext.startActivity(intent);
    Activity activityTransition = (Activity) appContext;
    activityTransition.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
  }


}
