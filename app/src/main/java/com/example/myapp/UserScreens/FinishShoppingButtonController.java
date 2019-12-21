package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import com.b07.database.helper.DatabaseUpdateAndroidHelper;
import com.b07.store.ShoppingCart;

public class FinishShoppingButtonController implements View.OnClickListener {
  private ShoppingCart cart;
  private Context context;
  private DatabaseUpdateAndroidHelper update;

  public FinishShoppingButtonController(Context context, ShoppingCart cart) {
    this.cart = cart;
    this.context = context;
    this.update = new DatabaseUpdateAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    if (cart.getItems().size() == 0) {
      Toast.makeText(context, "Cannot  Checkout Empty Cart!", Toast.LENGTH_LONG).show();
    } else {
      boolean changed = false;
      boolean completed = cart.checkOut(context);
      if (completed) {
        if (cart.getAssociatedAccount() > 0) {
          changed = update.updateAccountStatus(cart.getAssociatedAccount(), false);
        }
        if (!changed) {
          Toast.makeText(context, "Could not remove account", Toast.LENGTH_LONG).show();
        } else {
          Toast.makeText(context, "Successfully Checked out", Toast.LENGTH_LONG).show();
          Intent intent = new Intent(context, userOptions.class);
        }
      } else {
        Toast.makeText(context, "Could not process checkout", Toast.LENGTH_LONG).show();
      }
    }
  }
}
