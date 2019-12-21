package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.users.User;
import com.example.myapp.popUp;
import java.util.ArrayList;
import java.util.List;

public class HistoricAccountsButton implements View.OnClickListener {
  private Context context;
  private DatabaseSelectAndroidHelper select;
  private int userId;

  public HistoricAccountsButton(Context context, int userId) {
    this.context = context;
    this.select = new DatabaseSelectAndroidHelper(context);
    this.userId = userId;
  }

  @Override
  public void onClick(View view) {
    List<User> allUsers = select.getUsersDetailsAndroid();
    List<Integer> historicAcc = new ArrayList<>();
    String toPrint = "";
    if (allUsers.size() == 0) {
      toPrint = "There are no users";
    } else {
      for (User user : allUsers) {
        List<Integer> userHistoricAcc = select.getUserInactiveAccountsAndroid(user.getId());
        historicAcc.addAll(userHistoricAcc);
      }

      for (int num : historicAcc) {
        toPrint = toPrint + "\n" + num;
      }
    }

    if (historicAcc.size() == 0) {
      toPrint = "No historic Accounts!";
    }

    Intent intent = new Intent(context, popUp.class);
    intent.putExtra("title", "Historic Accounts");
    intent.putExtra("data", toPrint);
    context.startActivity(intent);
  }

}
