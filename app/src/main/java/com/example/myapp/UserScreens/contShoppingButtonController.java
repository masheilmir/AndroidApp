package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.example.myapp.InitialScreens.LogInActivity;

public class contShoppingButtonController implements View.OnClickListener {

  private Context appContext;

  public contShoppingButtonController(Context context) {
    this.appContext = context;
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(appContext, userOptions.class);
    appContext.startActivity(intent);
  }
}

