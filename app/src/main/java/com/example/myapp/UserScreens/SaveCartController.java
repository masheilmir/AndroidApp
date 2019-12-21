package com.example.myapp.UserScreens;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.b07.database.helper.DatabaseInsertAndroidHelper;
import com.b07.exceptions.InvalidInputException;
import com.b07.inventory.Item;
import com.b07.store.ShoppingCart;

public class SaveCartController implements View.OnClickListener {
  Context context;
  ShoppingCart cart;
  DatabaseInsertAndroidHelper insert;

  public SaveCartController(Context context, ShoppingCart cart) {
    this.context = context;
    this.cart = cart;
    insert = new DatabaseInsertAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    if (cart.getItems().size() == 0) {
      Toast.makeText(context, "Cannot have empty cart", Toast.LENGTH_LONG).show();
    } else {
      try {
        long accountid = insert.insertAccountAndroid(cart.getCustomer().getId());
        for (Item item : cart.getItems()) {
          long store = insert.insertAccountLineCAndroid(Math.toIntExact(accountid), item.getId(),
              cart.getQuantity(item.getId()));
        }
        Toast.makeText(context, "Save Successful", Toast.LENGTH_SHORT).show();
      } catch (InvalidInputException e) {
        Toast.makeText(context, "Invalid inputs", Toast.LENGTH_SHORT).show();
      }
    }
  }
}
