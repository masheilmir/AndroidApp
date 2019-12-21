package com.example.myapp.EmployeeScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.Toast;
import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.inventory.Inventory;
import com.b07.store.EmployeeInterface;
import com.b07.store.EmployeeInterfaceImpl;
import com.example.myapp.R;
import com.b07.users.*;
import com.b07.store.*;

public class employeeOptions extends AppCompatActivity {
  private Button authenticate_button;
  private Button make_user_button;
  private Button view_inventory_button;
  private Button make_employee_button;
  private Button restock_inventory_button;
  private Button exit_button;
  private Inventory inventory;
  private EmployeeInterface myInterface;
  private DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(this);

  @Override
  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_employee_options);



    inventory = select.getInventoryAndroid();



    authenticate_button = (Button) findViewById(R.id.auth_emp_button);

    authenticate_button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        openAuthenticateEmployee();
      }
    });


    make_employee_button = (Button) findViewById(R.id.new_emp_button);

    make_employee_button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {


        openNewEmp();
      }
    });


    make_user_button = (Button) findViewById(R.id.new_user_button);

    make_user_button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {

        openNewUser();
      }
    });


    restock_inventory_button = (Button) findViewById(R.id.restock_button);
    restock_inventory_button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        openRestock();
      }
    });


    exit_button = (Button) findViewById(R.id.exit_button);
    exit_button.setOnClickListener(new View.OnClickListener() {

      @Override
      public void onClick(View v) {
        openLogIn();
      }
    });

    view_inventory_button = (Button) findViewById(R.id.view_inventory_button);
    view_inventory_button.setOnClickListener(new ViewInventoryButtonController(this));
  }



  public void openLogIn() {
    Intent intent = new Intent(this, com.example.myapp.InitialScreens.LogInActivity.class);
    startActivity(intent);
  }

  public void openRestock() {
    Intent intent = new Intent(this, restock_inventory.class);
    // intent.putExtra("myId",id);
    startActivity(intent);
  }


  public void openNewUser() {
    Intent intent = new Intent(this, make_new_user.class);
    // intent.putExtra("myId",id);
    startActivity(intent);
  }

  public void openNewEmp() {
    Intent intent = new Intent(this, make_new_employee.class);
    // intent.putExtra("myId",this.id);
    startActivity(intent);
  }


  public void openAuthenticateEmployee() {
    Intent intent = new Intent(this, Authenticate_new_employee.class);
    startActivity(intent);
  }
}


