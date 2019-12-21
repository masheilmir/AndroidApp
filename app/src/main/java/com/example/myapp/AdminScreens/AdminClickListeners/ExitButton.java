package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.example.myapp.AdminScreens.adminOptions;
import com.example.myapp.InitialScreens.LogInActivity;

public class ExitButton implements View.OnClickListener {
  Intent intent;
  private Context context;
  private String where;

  public ExitButton(Context context, String where) {
    this.context = context;
    this.where = where;
  }

  @Override
  public void onClick(View view) {
    if (where.equals("login")) {
      intent = new Intent(context, LogInActivity.class);
    } else {
      intent = new Intent(context, adminOptions.class);
    }

    context.startActivity(intent);
  }
}
