package com.example.myapp.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.*;
import android.os.Bundle;
import android.widget.EditText;
import com.b07.store.ShoppingCartImpl;
import com.example.myapp.R;

public class AddQuantityofItem extends AppCompatActivity {
  private ShoppingCartImpl myCart;
  private Button confirm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_add_quantityof_item);
    myCart = (ShoppingCartImpl) getIntent().getExtras().getSerializable("cart");
    confirm = (Button) findViewById(R.id.confirmAdd);
    confirm.setOnClickListener(new AddQuantityOfItemButtonController(this, myCart));



  }


}
