package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.User;
import com.example.myapp.popUp;
import java.util.ArrayList;
import java.util.List;

public class ActiveAccountsButton implements View.OnClickListener {
  private Context context;
  private DatabaseSelectAndroidHelper select;
  private int userId;

  public ActiveAccountsButton(Context context, int userId) {
    this.context = context;
    this.select = new DatabaseSelectAndroidHelper(context);
    this.userId = userId;
  }

  @Override
  public void onClick(View view) {
    List<User> allUsers = select.getUsersDetailsAndroid();
    List<Integer> activeAcc = new ArrayList<>();
    String toPrint = "";
    if (allUsers.size() == 0) {
      toPrint = "There are no users";
    } else {
      for (User user : allUsers) {
        List<Integer> userActiveAcc = select.getUserActiveAccountsAndroid(user.getId());
        activeAcc.addAll(userActiveAcc);
      }

      for (int num : activeAcc) {
        toPrint = toPrint + "\n" + num;
      }
    }

    if (activeAcc.size() == 0) {
      toPrint = "No active accounts";
    }

    Intent intent = new Intent(context, popUp.class);
    intent.putExtra("title", "Active Accounts");
    intent.putExtra("data", toPrint);
    context.startActivity(intent);
  }

}
