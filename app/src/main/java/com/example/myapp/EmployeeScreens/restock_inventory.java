package com.example.myapp.EmployeeScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.example.myapp.R;

public class restock_inventory extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_restock_inventory);
    Button button = (Button) findViewById(R.id.EnterButton);
    button.setOnClickListener(new RestockButtonController(this));
  }
}
