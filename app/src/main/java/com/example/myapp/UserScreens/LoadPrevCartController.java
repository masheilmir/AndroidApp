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
import com.b07.users.Account;
import com.b07.users.AccountImpl;
import com.b07.users.Customer;
import com.example.myapp.R;
import java.io.IOException;
import java.util.List;

public class LoadPrevCartController implements View.OnClickListener {

  private ShoppingCartImpl myCart;
  private Context appContext;
  private DatabaseSelectAndroidHelper select;

  public LoadPrevCartController(Context context, ShoppingCartImpl myCart) {
    this.myCart = myCart;
    this.appContext = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  public void onClick(View view) {
    try {
      EditText raw_act_num =
          (EditText) ((AppCompatActivity) appContext).findViewById(R.id.actNumEntered);
      int accountNum = Integer.parseInt(raw_act_num.getText().toString());

      Customer me = myCart.getCustomer();
      int custId = me.getId();
      List<Integer> acctIds = select.getUserAccountsAndroid(custId);

      if (acctIds.size() == 0) {
        Toast.makeText(appContext, "No Active Accounts", Toast.LENGTH_SHORT).show();
      } else {
        for (int accountId : acctIds) {
          if (select.getAccountDetailsAndroid(accountId).getCart().size() > 0) {
            myCart.clearcart();
            Account account = select.getAccountDetailsAndroid(accountNum);
            myCart.setAssociatedAccount(accountNum);
            for (Item cartItem : account.getCart().keySet()) {
              if (select.getItemAndroid(cartItem.getId()) != null) {

                myCart.addItem(cartItem, account.getCart().get(cartItem));


              }

            }
          } else {
            Toast.makeText(appContext, "No Saved Carts", Toast.LENGTH_SHORT).show();
          }
        }
        Intent intent = new Intent(appContext, userOptions.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cart", myCart);
        intent.putExtras(bundle);
        appContext.startActivity(intent);

        Toast.makeText(appContext, "Success!", Toast.LENGTH_SHORT).show();
      }

    } catch (NumberFormatException e) {
      Toast.makeText(appContext, "Invalid Id", Toast.LENGTH_SHORT).show();
    } catch (Exception e) {
      Toast.makeText(appContext, e.getMessage(), Toast.LENGTH_SHORT).show();
    }
  }



}
