package com.example.myapp.UserScreens;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import com.example.myapp.UserScreens.SaveCartController;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.exceptions.UserNotLoggedInException;
import com.b07.store.ShoppingCart;
import com.b07.store.ShoppingCartImpl;
import com.b07.users.Customer;
import com.example.myapp.EmployeeScreens.ViewInventoryButtonController;
import com.example.myapp.R;

public class userOptions extends AppCompatActivity {

  ShoppingCart cart;
  Customer customer;
  int id;
  String pass;
  DatabaseSelectAndroidHelper select = new DatabaseSelectAndroidHelper(this);
  ShoppingCart tempCart;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_user_options);

    id = getIntent().getIntExtra("userId", -1);
    pass = getIntent().getStringExtra("pass");

    if (id != -1 && pass != null) {
      Customer customer = (Customer) select.getUserDetailsAndroid(id);
      customer.authenticate(pass, this);
      try {
        cart = new ShoppingCartImpl(customer);
      } catch (UserNotLoggedInException e) {
        Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
      }
    }

    Bundle bundle = getIntent().getExtras();
    tempCart = null;
    if (bundle != null) {
      tempCart = (ShoppingCart) bundle.getSerializable("cart");
    }
    if (tempCart != null) {
      cart = tempCart;
    }

    // list items
    Button listItems = (Button) findViewById(R.id.list_items);
    listItems.setOnClickListener(new ListItemsButtonController(this, cart));

    // add quantity button
    Button addItems = (Button) findViewById(R.id.add_items);;
    addItems.setOnClickListener(new AddItemsButtonController(this, cart));

    // remove items button
    Button removeItems = (Button) findViewById(R.id.remove_items);
    removeItems.setOnClickListener(new RemoveQuantityButtonController(this, cart));

    // load cart button
    Button loadCart = (Button) findViewById(R.id.loadCart);
    loadCart.setOnClickListener(new LoadCartButtonController(this, cart));

    // checkout
    Button checkout = (Button) findViewById(R.id.checkout);
    checkout.setOnClickListener(new checkoutButtonController(this, cart));


    // exit button
    Button exit = (Button) findViewById(R.id.exit_button);
    exit.setOnClickListener(new exitButtonController(this));

    Button checkTotal = (Button) findViewById(R.id.check_total);
    checkTotal.setOnClickListener(new TotalPriceButtonController(this, cart));

    Button saveCart = (Button) findViewById((R.id.saveCart));
    saveCart.setOnClickListener(new SaveCartController(this, cart));

  }

  @Override
  protected void onResume() {
    super.onResume();

    id = getIntent().getIntExtra("userId", -1);
    pass = getIntent().getStringExtra("pass");

    if (id != -1 && pass != null) {
      Customer customer = (Customer) select.getUserDetailsAndroid(id);
      customer.authenticate(pass, this);
      try {
        cart = new ShoppingCartImpl(customer);
      } catch (UserNotLoggedInException e) {
        Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
      }
    }
    try {
      Bundle bundle = getIntent().getExtras();
      if (bundle != null) {
        tempCart = (ShoppingCart) bundle.getSerializable("cart");
      }
    } catch (NullPointerException e) {
    }

    if (tempCart != null) {
      cart = tempCart;
    }

    // list items
    Button listItems = (Button) findViewById(R.id.list_items);
    listItems.setOnClickListener(new ListItemsButtonController(this, cart));

    // add quantity button
    Button addItems = (Button) findViewById(R.id.add_items);;
    addItems.setOnClickListener(new AddItemsButtonController(this, cart));

    // remove items button
    Button removeItems = (Button) findViewById(R.id.remove_items);
    removeItems.setOnClickListener(new RemoveQuantityButtonController(this, cart));

    // load cart button
    Button loadCart = (Button) findViewById(R.id.loadCart);
    loadCart.setOnClickListener(new LoadCartButtonController(this, cart));

    // checkout
    Button checkout = (Button) findViewById(R.id.checkout);
    checkout.setOnClickListener(new checkoutButtonController(this, cart));


    // exit button
    Button exit = (Button) findViewById(R.id.exit_button);
    exit.setOnClickListener(new exitButtonController(this));
  }
}
