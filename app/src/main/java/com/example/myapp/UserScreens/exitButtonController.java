package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.example.myapp.InitialScreens.LogInActivity;

public class exitButtonController implements View.OnClickListener {

  Context appContext;
  DatabaseSelectAndroidHelper select;

  public exitButtonController(Context context) {
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(appContext, LogInActivity.class);
    appContext.startActivity(intent);
  }
}
