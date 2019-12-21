package com.example.myapp.AdminScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.b07.users.Admin;
import com.example.myapp.AdminScreens.AdminClickListeners.ExitButton;
import com.example.myapp.AdminScreens.AdminClickListeners.PromoteEmployeeConfirmButton;
import com.example.myapp.R;

public class PromoteEmployee extends AppCompatActivity {
  private Admin admin;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_promote_employee);
    admin = (Admin) getIntent().getSerializableExtra("admin");
    Button promote = (Button) findViewById(R.id.promoteEmpConfirm);
    promote.setOnClickListener(new PromoteEmployeeConfirmButton(admin, this));
    Button exit = (Button) findViewById(R.id.promoteEmpExit);
    exit.setOnClickListener(new ExitButton(this, "login"));
  }

  @Override
  protected void onResume() {
    super.onResume();
    Admin tempAdmin = (Admin) getIntent().getSerializableExtra("admin");
    if (tempAdmin != null) {
      Button promote = (Button) findViewById(R.id.promoteEmpConfirm);
      promote.setOnClickListener(new PromoteEmployeeConfirmButton(tempAdmin, this));
    }
  }
}
