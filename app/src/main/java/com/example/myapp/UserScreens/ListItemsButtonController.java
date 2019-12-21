package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.store.ShoppingCart;
import com.example.myapp.popUp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListItemsButtonController implements View.OnClickListener {

  private Context appContext;
  private ShoppingCart cart;

  public ListItemsButtonController(Context context, ShoppingCart cart) {
    this.appContext = context;
    this.cart = cart;
  }

  @Override
  public void onClick(View v) {
    List<Item> itemMap = cart.getItems();
    String toPrint = "";

    if (itemMap.size() == 0) {
      toPrint = "This Cart is empty!";
    } else {
      for (Item item : itemMap) {
        toPrint = toPrint + "\n" + item.getName() + ": " + cart.getQuantity(item.getId());
      }
    }

    Intent intent = new Intent(appContext, popUp.class);
    intent.putExtra("title", "Your Cart");
    intent.putExtra("data", toPrint);
    appContext.startActivity(intent);
  }
}
