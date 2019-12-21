package com.example.myapp.UserScreens;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.inventory.Item;
import com.b07.store.ShoppingCartImpl;
import com.example.myapp.R;

public class RemoveButtonController implements View.OnClickListener {
  private ShoppingCartImpl myCart;
  private Context appContext;
  private DatabaseSelectAndroidHelper select;

  public RemoveButtonController(Context context, ShoppingCartImpl myCart) {
    this.myCart = myCart;
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
  }


  @Override
  public void onClick(View view) {
    try {
      EditText raw_id = (EditText) ((AppCompatActivity) appContext).findViewById(R.id.faizan1);
      EditText raw_quantity =
          (EditText) ((AppCompatActivity) appContext).findViewById(R.id.faizan2);

      int id = Integer.parseInt(raw_id.getText().toString());
      int quantity = Integer.parseInt(raw_quantity.getText().toString());

      Item item = select.getItemAndroid(id);
      if (item == null) {
        Toast.makeText(appContext, "Item Id Invalid", Toast.LENGTH_SHORT).show();
      } else if (quantity <= 0) {
        Toast.makeText(appContext, "Quantity must be greater than 0", Toast.LENGTH_SHORT).show();
      } else {
        myCart.removeItem(item, quantity);
        Intent intent = new Intent(appContext, userOptions.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", myCart);
        intent.putExtras(bundle);
        appContext.startActivity(intent);
      }


    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "ID and Quantity must be numerical", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }

}

