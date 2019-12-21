package com.example.myapp.EmployeeScreens;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.b07.database.helper.DatabaseSelectAndroidHelper;
import com.b07.inventory.Inventory;
import com.b07.inventory.Item;
import com.example.myapp.popUp;
import java.util.HashMap;

public class ViewInventoryButtonController implements View.OnClickListener {
  private DatabaseSelectAndroidHelper select;
  private Context context;

  public ViewInventoryButtonController(Context context) {
    this.context = context;
    select = new DatabaseSelectAndroidHelper(context);
  }

  @Override
  public void onClick(View view) {
    String toPrint = "";

    Inventory inventory = select.getInventoryAndroid();
    HashMap<Item, Integer> inventoryMap = inventory.getItemMap();

    if (inventoryMap.size() == 0) {
      toPrint = "Inventory is empty";
    } else {
      for (Item item : inventoryMap.keySet()) {
        toPrint = toPrint + "\n" + item.getName() + " " + inventoryMap.get(item);
      }
    }

    Intent intent = new Intent(context, popUp.class);
    intent.putExtra("title", "View Inventory");
    intent.putExtra("data", toPrint);
    context.startActivity(intent);
  }
}
