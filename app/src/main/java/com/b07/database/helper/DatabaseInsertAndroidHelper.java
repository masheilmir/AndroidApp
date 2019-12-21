package com.b07.database.helper;

import com.b07.database.DatabaseDriverAndroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.b07.exceptions.DatabaseInsertException;
import com.b07.security.PasswordHelpers;
import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidInputException;
import com.b07.users.Roles;
import java.math.BigDecimal;
public class DatabaseInsertAndroidHelper extends DatabaseDriverAndroid{
    DatabaseSelectAndroidHelper db;
    public DatabaseInsertAndroidHelper(Context context){

        super(context);
        db = new DatabaseSelectAndroidHelper(context);
    }
    private static boolean checkRoles(String name) {
        try {
            Roles.valueOf(name.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }

    }

    // catch invalidInput and databaseInsert in main
    public long insertRoleAndroid(String name)  throws InvalidInputException {
        if (!checkRoles(name)) {
            throw new InvalidInputException("Invalid input: Role name does not exist!");
        }
        long roleId;
        roleId = this.insertRole(name);
        return roleId;
    }

    public long insertNewUserAndroid(String name, int age, String address, String password)
            throws InvalidInputException{

        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Invalid input: Name cannot be null or empty!");
        } else if (age < 0) {
            throw new InvalidInputException("Invalid input: Age cannot be negative!");
        } else if (address.length() > 100 || address.trim().isEmpty()) {
            throw new InvalidInputException("Invalid input: Address exceeds 100 character limit!");
        } else if (password == null || password.trim().isEmpty()) {
            throw new InvalidInputException("Invalid input: Password cannot be null or empty!");
        }
        long userId;
        userId = this.insertNewUser(name, age, address, password);
        return userId;
    }
    public long insertUserRoleAndroid(int userId, int roleId)
            throws InvalidInputException{
        // TODO Implement this method as stated on the assignment sheet
        // hint: You should be using these three lines in your final code
        if (db.getPasswordAndroid(userId) == null) {
            throw new InvalidInputException("Invalid input: userId cannot be found in database!");
        }
        if (db.getRoleNameAndroid(roleId) == null) {
            throw new InvalidInputException("Invalid input: roleId cannot be found in database!");
        }

        long userRoleId = this.insertUserRole(userId, roleId);
        return userRoleId;
    }
    public long insertItemAndroid(String name, BigDecimal price)
            throws InvalidInputException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidInputException("Invalid input: Item name cannot be null or empty!");
        } else if (name.length() >= 64) {
            throw new InvalidInputException("Invalid input: Item name exceeds 63 character limit!");
        } else if (price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidInputException("Invalid input: Price must be greater than 0!");
        } else if (price.scale() != 2) {
            throw new InvalidInputException("Invalid input: Price must be precise to 2 decimal places!");
        }

        long itemId = this.insertItem(name, price);

        return itemId;
    }
    public long insertInventoryAndroid(int itemId, int quantity)
            throws InvalidInputException{
        // TODO Implement this method as stated on the assignment sheet
        // hint: You should be using these three lines in your final code
        if (db.getItemAndroid(itemId) == null) {
            throw new InvalidInputException("Invalid input: itemId cannot be found in database!");
        }
        if (quantity < 0) {
            throw new InvalidInputException("Invalid input: Quantity must be greater than or equal to 0!");
        }
        long inventoryId = this.insertInventory(itemId, quantity);
        return inventoryId;
    }
    public long insertSaleAndroid(int userId, BigDecimal totalPrice)
            throws InvalidInputException {
        // TODO Implement this method as stated on the assignment sheet
        // hint: You should be using these three lines in your final code
        if (db.getUserDetailsAndroid(userId) == null) {
            throw new InvalidInputException("Invalid input: userId cannot be found in database!");
        }
        if (totalPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new InvalidInputException("Invalid input: totalPrice cannot be negative!");
        }

        long saleId = this.insertSale(userId, totalPrice);
        return saleId;
    }
    public long insertItemizedSaleAndroid(int saleId, int itemId, int quantity)
            throws InvalidInputException {
        // TODO Implement this method as stated on the assignment sheet
        // hint: You should be using these three lines in your final code
        if (db.getSaleByIdAndroid(saleId) == null) {
            throw new InvalidInputException("Invalid input: saleId cannot be found in database!");
        }
        if (db.getItemAndroid(itemId) == null) {
            throw new InvalidInputException("Invalid input: itemId cannot be found in database!");
        }
        if (quantity < 0) {
            throw new InvalidInputException("Invalid input: Quantity must be greater than or equal to 0!");
        }
        long itemizedId = this.insertItemizedSale(saleId, itemId, quantity);
        return itemizedId;
    }

    public long insertAccountAndroid(int userId)
            throws InvalidInputException {
        if (db.getUserDetailsAndroid(userId) == null) {
            throw new InvalidInputException("Invalid input: userId cannot be found in database");
        }

        long accountId = this.insertAccount(userId, true);
        return accountId;
    }
    public long insertAccountLineCAndroid(int accountId, int itemId, int quantity)
            throws InvalidInputException {
        if (db.getItemAndroid(itemId) == null) {
            throw new InvalidInputException("Invalid input: itemId cannot be found in database!");
        }
        if (db.getAccountDetailsAndroid(accountId) == null) {
            throw new InvalidInputException("Invalid input: accountId cannot be found in database!");
        }
        if (quantity < 0) {
            throw new InvalidInputException("Invalid input: Quantity must be greater than or equal to 0!");
        }

       long accountLineId = this.insertAccountLine(accountId, itemId, quantity);

        return accountLineId;
    }


}
