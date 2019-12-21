package com.example.myapp.InitialScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Fade;
import android.view.Window;
import com.b07.database.DatabaseDriverAndroid;
import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.Roles;
import com.example.myapp.R;

public class Initial_Page extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_initial_page);
    final InitialPageDatabaseInitialize createDatabase = new InitialPageDatabaseInitialize();
    createDatabase.initializeDatabase(this);
    final Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        createDatabase.nextScreen(Initial_Page.this);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        finish();
      }
    }, 1000);

  }

}
