package com.example.myapp.InitialScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import com.example.myapp.R;

public class LogInActivity extends AppCompatActivity {
  int variable;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_log_in);
    Button button = (Button) findViewById(R.id.logInButton);
    button.setOnClickListener(new LogInButtonController(this));
  }
}
