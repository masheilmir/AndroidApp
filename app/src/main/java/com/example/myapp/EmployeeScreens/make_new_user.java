package com.example.myapp.EmployeeScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.exceptions.InvalidInputException;
import com.b07.inventory.Inventory;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;
import com.example.myapp.AdminScreens.adminOptions;
import com.example.myapp.R;
import com.example.myapp.UserScreens.userOptions;

public class make_new_user extends AppCompatActivity {

  private Button confirm_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_make_new_user);
    confirm_button = (Button) findViewById(R.id.confirmUser);
    confirm_button.setOnClickListener(new makeNewUserButtonController(this));

  }

}

