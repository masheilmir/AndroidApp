package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.store.ShoppingCart;
import com.example.myapp.InitialScreens.LogInActivity;

public class LoadCartButtonController implements View.OnClickListener {

  Context appContext;
  DatabaseSelectAndroidHelper select;
  private ShoppingCart cart;

  public LoadCartButtonController(Context context, ShoppingCart cart) {
    this.appContext = context;
    this.cart = cart;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View v) {

    Intent intent = new Intent(appContext, load_prev_cart.class);
    Bundle bundle = new Bundle();
    bundle.putSerializable("cart", cart);
    intent.putExtras(bundle);
    appContext.startActivity(intent);

  }

}
