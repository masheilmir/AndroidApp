package com.example.myapp.AdminScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.users.Admin;
import com.b07.users.User;
import com.example.myapp.AdminScreens.AdminClickListeners.ActiveAccountsButton;
import com.example.myapp.AdminScreens.AdminClickListeners.ExitButton;
import com.example.myapp.AdminScreens.AdminClickListeners.HistoricAccountsButton;
import com.example.myapp.AdminScreens.AdminClickListeners.PromoteEmployeeButton;
import com.example.myapp.AdminScreens.AdminClickListeners.ViewBooksButton;
import com.example.myapp.R;

public class adminOptions extends AppCompatActivity {
  int id;
  String pass;
  User admin;
  DatabaseSelectAndroidHelper select;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin_options);
    select = new DatabaseSelectAndroidHelper(this);
    id = getIntent().getIntExtra("userId", -1);
    pass = getIntent().getStringExtra("pass");

    Button promoteEmployee = (Button) findViewById(R.id.promoteEmpButton);
    Button viewBooks = (Button) findViewById(R.id.viewBooksButton);
    Button historicAcc = (Button) findViewById(R.id.historicAccounts);
    Button activeAcc = (Button) findViewById(R.id.activeAccounts);

    if (id != -1 && pass != null) {
      admin = select.getUserDetailsAndroid(id);
      admin.authenticate(pass, this);
      promoteEmployee.setOnClickListener(new PromoteEmployeeButton((Admin) admin, this));
    }

    if (id != -1) {
      viewBooks.setOnClickListener(new ViewBooksButton(this));
      historicAcc.setOnClickListener(new HistoricAccountsButton(this, id));
      activeAcc.setOnClickListener(new ActiveAccountsButton(this, id));
    }
  }

  @Override
  protected void onResume() {
    super.onResume();
    int temp = getIntent().getIntExtra("userId", -1);
    String tempPass = getIntent().getStringExtra("pass");

    Button exit = (Button) findViewById(R.id.exit_button);

    exit.setOnClickListener(new ExitButton(this, "login"));

    if (temp != -1) {
      Button historicAcc = (Button) findViewById(R.id.historicAccounts);
      Button activeAcc = (Button) findViewById(R.id.activeAccounts);

      historicAcc.setOnClickListener(new HistoricAccountsButton(this, temp));
      activeAcc.setOnClickListener(new ActiveAccountsButton(this, temp));
    }

    if (temp != 1 && tempPass != null) {
      admin = select.getUserDetailsAndroid(id);
      admin.authenticate(pass, this);

      Button promoteEmployee = (Button) findViewById(R.id.promoteEmpButton);
      promoteEmployee.setOnClickListener(new PromoteEmployeeButton((Admin) admin, this));
    }
  }
}
