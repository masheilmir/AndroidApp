package com.b07.database.helper;

import com.b07.database.DatabaseDriverAndroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.security.PasswordHelpers;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidInputException;
import com.b07.users.Roles;
import java.math.BigDecimal;
import android.content.Context;

public class DatabaseUpdateAndroidHelper extends DatabaseDriverAndroid {
    DatabaseSelectAndroidHelper db;
    public DatabaseUpdateAndroidHelper(Context context) {

        super(context);
        db = new DatabaseSelectAndroidHelper(context);
    }

    private static boolean checkRoles(String message) {
        try {
            Roles.valueOf(message.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    public boolean updateRoleNameAndroid(String name, int id)
            throws InvalidInputException {

        if (!(checkRoles(name) == true)) {
            return false;
        }

        boolean complete = this.updateRoleName(name, id);
        return complete;
    }

    public boolean updateUserNameAndroid(String name, int userId)
            throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            return false;
        }
        if (db.getUserDetailsAndroid(userId) == null) {
            return false;
        }
        boolean complete = this.updateUserName(name, userId);
        return complete;

    }
    public  boolean updateUserAgeAndroid(int age, int userId)
             {
        if (age < 0) {
            return false;
        }
                 if (db.getUserDetailsAndroid(userId) == null) {
                     return false;
                 }

                boolean complete = this.updateUserAge(age, userId);

                return complete;


        }
    public  boolean updateUserAddressAndroid(String address, int userId)
             {
        if (address.length() > 100) {
            return false;
        }
        if (db.getUserDetailsAndroid(userId) == null) {
            return false;
                 }
        boolean complete = this.updateUserAddress(address, userId);
        return complete;
            }

    public  boolean updateUserRoleAndroid(int roleId, int userId)  {

        if (db.getRoleNameAndroid(roleId) == null) {
            return false;
        }
        if (db.getUserDetailsAndroid(userId) == null) {
            return false;
        }

            boolean complete = this.updateUserRole(roleId, userId);

            return complete;

        }
    public boolean updateItemNameAndroid(String name, int itemId){
        if (name == null || name.trim().isEmpty() || name.length() >= 64) {
            return false;
        }

        if (db.getItemAndroid(itemId) == null) {
            return false;
        }
            boolean complete = this.updateItemName(name, itemId);
            return complete;
        }
    public boolean updateItemPriceAndroid(BigDecimal price, int itemId)  {
        if (price.compareTo(BigDecimal.ZERO) > 0 || price.scale() != 2) {
            return false;
        }
        if (db.getItemAndroid(itemId) == null) {
            return false;
        }
            boolean complete = this.updateItemPrice(price, itemId);
            return complete;
        }
    public boolean updateInventoryQuantityAndroid(int quantity, int itemId)  {
        if (quantity < 0) {
            return false;
        }

        if (db.getItemAndroid(itemId) == null) {
            return false;
        }
            boolean complete = this.updateInventoryQuantity(quantity, itemId);

            return complete;

    }

    public boolean updateAccountStatus(int accountId, boolean active) {
        if(db.getAccountDetailsAndroid(accountId).getCart().size() == 0) {
            return false;
        }

        boolean complete = this.updateAccountStatus(accountId, active);

        return complete;
    }
}














