package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.store.ShoppingCart;
import com.example.myapp.popUp;
import java.math.BigDecimal;

public class TotalPriceButtonController implements View.OnClickListener {
  private Context context;
  private ShoppingCart cart;

  public TotalPriceButtonController(Context context, ShoppingCart cart) {
    this.cart = cart;
    this.context = context;
  }

  @Override
  public void onClick(View view) {
    BigDecimal totalPrice = cart.getTotal();
    String toPrint = totalPrice.toString();
    Intent intent = new Intent(context, popUp.class);
    intent.putExtra("title", "Your Total");
    intent.putExtra("data", toPrint);
    context.startActivity(intent);
  }
}
