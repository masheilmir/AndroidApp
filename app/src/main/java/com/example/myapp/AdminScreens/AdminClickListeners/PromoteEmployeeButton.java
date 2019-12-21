package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.b07.users.Admin;
import com.b07.users.User;
import com.example.myapp.AdminScreens.PromoteEmployee;

public class PromoteEmployeeButton implements View.OnClickListener {
  private User admin;
  private Context context;

  public PromoteEmployeeButton(Admin admin, Context context) {
    this.admin = admin;
    this.context = context;
  }

  @Override
  public void onClick(View view) {
    Intent intent = new Intent(context, PromoteEmployee.class);
    Bundle bundle = new Bundle();

    bundle.putSerializable("admin", admin);
    intent.putExtras(bundle);

    context.startActivity(intent);
  }
}
