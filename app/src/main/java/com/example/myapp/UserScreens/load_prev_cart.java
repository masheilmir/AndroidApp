package com.example.myapp.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.b07.store.ShoppingCartImpl;
import com.example.myapp.EmployeeScreens.makeNewUserButtonController;
import com.example.myapp.R;

public class load_prev_cart extends AppCompatActivity {

  private ShoppingCartImpl myCart;
  private Button confirm_button;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_load_prev_cart);
    myCart = (ShoppingCartImpl) getIntent().getExtras().getSerializable("cart");
    confirm_button = (Button) findViewById(R.id.EnterButton);
    confirm_button.setOnClickListener(new LoadPrevCartController(this, myCart));

  }
}
