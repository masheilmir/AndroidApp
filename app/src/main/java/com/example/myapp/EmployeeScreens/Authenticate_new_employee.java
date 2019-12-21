package com.example.myapp.EmployeeScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.b07.database.DatabaseDriverAndroid;
import com.example.myapp.InitialScreens.LogInButtonController;
import com.example.myapp.R;

public class Authenticate_new_employee extends AppCompatActivity {



  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_authenticate_new_employee);
    Button button = (Button) findViewById(R.id.ConfirmButton);
    button.setOnClickListener(new AuthenticateButtonController(this));



  }

  protected void onResume() {
    super.onResume();
    Button button = (Button) findViewById(R.id.ConfirmButton);
    button.setOnClickListener(new AuthenticateButtonController(this));
  }



}
