package com.example.myapp.EmployeeScreens;

import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.*;
import com.b07.store.*;
import com.b07.inventory.*;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.*;
import android.widget.Toast;
import android.widget.*;
import com.example.myapp.R;

public class make_new_employee extends AppCompatActivity {


  private Inventory inventory;
  private DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(this);
  private Button confirm;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_make_new_employee);

    confirm = (Button) findViewById(R.id.ConfirmButton);
    confirm.setOnClickListener(new make_new_employee_button_controller(this));

  }


}
