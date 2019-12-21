package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.store.ShoppingCart;

public class AddItemsButtonController implements View.OnClickListener {

  private Context appContext;
  private DatabaseSelectAndroidHelper select;
  private ShoppingCart cart;

  public AddItemsButtonController(Context context, ShoppingCart cart) {
    this.appContext = context;
    this.cart = cart;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View v) {
    Intent intent = new Intent(appContext, AddQuantityofItem.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("cart", cart);
    intent.putExtras(bundle);
    appContext.startActivity(intent);
  }
}
