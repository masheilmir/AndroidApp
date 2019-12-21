package com.example.myapp.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import com.b07.store.ShoppingCart;
import com.example.myapp.R;

public class checkout extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_checkout);

    Button contShopping = (Button) findViewById(R.id.yesCont);
    contShopping.setOnClickListener(new contShoppingButtonController(this));

    ShoppingCart cart = (ShoppingCart) getIntent().getExtras().getSerializable("cart");

    TextView total = (TextView) findViewById(R.id.actualTotal);
    total.setText(cart.getTotal().toString());

    Button finishShopping = (Button) findViewById(R.id.noCont);
    finishShopping.setOnClickListener(new FinishShoppingButtonController(this, cart));
  }
}
