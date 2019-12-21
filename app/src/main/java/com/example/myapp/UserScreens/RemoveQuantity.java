package com.example.myapp.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import com.b07.store.ShoppingCartImpl;
import com.example.myapp.R;

public class RemoveQuantity extends AppCompatActivity {
  private ShoppingCartImpl myCart;
  private Button confirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_remove_quantity);
    myCart = (ShoppingCartImpl) getIntent().getExtras().getSerializable("cart");
    confirm = (Button) findViewById(R.id.confirmQuan);
    confirm.setOnClickListener(new RemoveButtonController(this, myCart));
  }
}
