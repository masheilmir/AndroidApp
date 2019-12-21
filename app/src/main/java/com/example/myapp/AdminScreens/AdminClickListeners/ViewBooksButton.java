package com.example.myapp.AdminScreens.AdminClickListeners;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.inventory.Item;
import com.b07.store.Sale;
import com.b07.store.SalesLog;
import com.b07.store.SalesLogImpl;
import com.b07.users.User;
import com.example.myapp.R;
import com.example.myapp.popUp;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewBooksButton implements View.OnClickListener {
  private Context context;
  private DatabaseSelectAndroidHelper select;

  public ViewBooksButton(Context context) {
    this.context = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    Intent intent = new Intent(context, popUp.class);
    intent.putExtra("title", "View Books");

    ArrayList<String> printing = new ArrayList<>();

    SalesLog salesLog = select.getSalesAndroid();
    List<Sale> allSales = salesLog.getSales();
    HashMap<Item, Integer> numberSold = new HashMap<>();
    BigDecimal totalPrice = new BigDecimal(0);

    for (Sale sale : allSales) {
      Sale itemizedSale = select.getItemizedSaleByIdAndroid(sale.getId());
      User saleUser = sale.getUser();
      HashMap<Item, Integer> allItems = itemizedSale.getItemMap();
      printing.add("Customer: " + saleUser.getName());
      printing.add("Purchase Number " + sale.getId());
      printing.add("Total Purchase Price: " + sale.getTotalPrice());
      totalPrice = totalPrice.add(sale.getTotalPrice());

      int i = 0;
      for (Item saleItem : allItems.keySet()) {
        if (i == 0) {
          printing.add("Itemized Breakdown: " + saleItem.getName() + ": " + allItems.get(saleItem));
        } else {
          printing.add("                    " + saleItem.getName() + ": " + allItems.get(saleItem));
        }

        boolean found = false;
        for (Item itemSold : numberSold.keySet()) {
          if (itemSold.getId() == saleItem.getId()) {
            numberSold.put(itemSold, numberSold.get(itemSold) + allItems.get(saleItem));
            found = true;
          }
        }

        if (!found) {
          numberSold.put(saleItem, allItems.get(saleItem));
        }
        i = i + 1;
      }
      printing.add("----------------------------------------------------------------");

      for (Item itemSold : numberSold.keySet()) {
        printing.add("Number of " + itemSold.getName() + " Sold: " + numberSold.get(itemSold));
      }

      printing.add("TOTAL SALES: " + totalPrice);
    }

    String toPrint = "";

    for (String value : printing) {
      toPrint = toPrint + "\n" + value;
    }

    if (toPrint.length() == 0) {
      toPrint = "No sales to show!";
    }

    intent.putExtra("data", toPrint);
    TextView test = ((AppCompatActivity) context).findViewById(R.id.welcomeAdmin);
    context.startActivity(intent);
  }
}
