package com.b07.database.helper;

import com.b07.database.DatabaseDriverAndroid;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.b07.exceptions.DatabaseInsertException;
import com.b07.inventory.Inventory;
import com.b07.inventory.InventoryImpl;
import com.b07.inventory.Item;
import com.b07.inventory.ItemImpl;
import com.b07.security.PasswordHelpers;

import com.b07.exceptions.DatabaseInsertException;
import com.b07.exceptions.InvalidInputException;
import com.b07.store.Sale;
import com.b07.store.SaleImpl;
import com.b07.store.SalesLog;
import com.b07.store.SalesLogImpl;
import com.b07.users.Account;
import com.b07.users.AccountImpl;
import com.b07.store.ShoppingCartImpl;
import com.b07.users.Admin;
import com.b07.users.Customer;
import com.b07.users.Employee;
import com.b07.users.Roles;
import com.b07.users.User;

import java.math.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class DatabaseSelectAndroidHelper extends DatabaseDriverAndroid {

    public DatabaseSelectAndroidHelper(Context context){
        super(context);

    }

    public List<Integer> getRoleIdsAndroid(){
        try {
            Cursor results = this.getRoles();
            List<Integer> ids = new ArrayList<>();
            while (results.moveToNext()) {
                ids.add(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))));
            }
            results.close();

            return ids;
        } catch(CursorIndexOutOfBoundsException e) {
            return new ArrayList<Integer>();
        }
    }

    /**
     * get each role's name.
     *
     * @param roleId the role's id.
     * @return role's name.
     */
    public String getRoleNameAndroid(int roleId) {
        try {
            String role = this.getRole(roleId);

            return role;
        } catch(CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * get each role's id.
     *
     * @param roleName the role's name.
     * @return role's id
     * @throws InvalidInputException on failure
     */
    public int getRoleIdAndroid(String roleName) throws InvalidInputException {
        try {
            List<Integer> roles = this.getRoleIdsAndroid();

            for (int id : roles) {
                String name = getRoleNameAndroid(id);

                if (name.equals(roleName)) {
                    return id;
                }
            }

            throw new InvalidInputException("User role not found!");
        } catch (CursorIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * get the role of the given user.
     *
     * @param userId the id of the user.
     * @return the roleId for the user.
     */
    public int getUserRoleIdAndroid(int userId) {
        try {
            int roleId = this.getUserRole(userId);

            return roleId;
        } catch(CursorIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * get the role of the given user.
     *
     * @param roleId the id of the role.
     * @return the roleId for the user.
     */
    public List<Integer> getUsersByRoleAndroid(int roleId) {
        try {
            Cursor results = this.getUsersByRole(roleId);
            List<Integer> userIds = new ArrayList<>();
            while (results.moveToNext()) {
                userIds.add(Math.toIntExact(results.getLong(results.getColumnIndex("USERID"))));
            }
            results.close();

            return userIds;
        } catch(CursorIndexOutOfBoundsException e) {
            return new ArrayList<Integer>();
        }
    }

    /**
     * create a new user
     *
     * @param id the id of the user.
     * @param name the name of the user.
     * @param age the age of the user.
     * @param address the address of the user.
     * @return the created new user.

     */
    private User createUserAndroid(int id, String name, int age, String address){
        int roleId = this.getUserRoleIdAndroid(id);
        String role = this.getRoleNameAndroid(roleId);
        User user = null;

        if (role.equals(Roles.ADMIN.name())) {
            user = new Admin(id, name, age, address);
        } else if (role.equals(Roles.CUSTOMER.name())) {
            user = new Customer(id, name, age, address);
        } else if (role.equals(Roles.EMPLOYEE.name())) {
            user = new Employee(id, name, age, address);
        }

        return user;
    }

    /**
     * get all the users.
     *
     * @return a list for the users.

     */
    public List<User> getUsersDetailsAndroid(){
        try {
            Cursor results = this.getUsersDetails();
            List<User> users = new ArrayList<>();
            while (results.moveToNext()) {
                users.add(createUserAndroid(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))),
                        results.getString(results.getColumnIndex("NAME")),
                        Math.toIntExact(results.getLong(results.getColumnIndex("AGE"))),
                        results.getString(results.getColumnIndex("ADDRESS"))));
            }
            results.close();

            return users;
        } catch (CursorIndexOutOfBoundsException e) {
            return new ArrayList<User>();
        }
    }

    /**
     * get all the users.
     *
     * @param userId the id of the user.
     * @return the corresponding user.
     */
    public User getUserDetailsAndroid(int userId) {
        try {
            Cursor results = this.getUserDetails(userId);
            User user = null;
            while (results.moveToNext()) {
                user = createUserAndroid(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))),
                        results.getString(results.getColumnIndex("NAME")),
                        Math.toIntExact(results.getLong(results.getColumnIndex("AGE"))),
                        results.getString(results.getColumnIndex("ADDRESS")));
            }
            results.close();
            return user;
        } catch (CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * get the hashed version of the password.
     *
     * @param userId the user's id.
     * @return the hashed password to be checked against given password.
     */
    public String getPasswordAndroid(int userId){

        String password = this.getPassword(userId);
        return password;
    }

    /**
     * get all rows from the items table.
     *
     * @return a result set of all items and associated values
     */
    public  List<Item> getAllItemsAndroid(){
        try {
            Cursor results = this.getAllItems();
            List<Item> items = new ArrayList<>();
            while (results.moveToNext()) {
                items.add(new ItemImpl
                        (Math.toIntExact(results.getLong(results.getColumnIndex("ID"))),
                                results.getString(results.getColumnIndex("NAME")),
                                new BigDecimal(results.getString(results.getColumnIndex("PRICE")))));
            }
            results.close();

            return items;
        } catch (CursorIndexOutOfBoundsException e){
            return new ArrayList<Item>();
        }
    }

    /**
     * get a specific item and it's details from the database.
     *
     * @param itemId the item of interest.
     * @return result set containing the row in it.
     */
    public Item getItemAndroid(int itemId) {
        try {
            Cursor results = this.getItem(itemId);
            Item item = null;
            while (results.moveToNext()) {
                item = new ItemImpl
                        (Math.toIntExact(results.getLong(results.getColumnIndex("ID"))),
                                results.getString(results.getColumnIndex("NAME")),
                                new BigDecimal(results.getString(results.getColumnIndex("PRICE"))));
            }
            results.close();

            return item;
        } catch (CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * get all rows from the inventory table.
     *
     * @return result set containing all rows from the table inventory.
     */
    public Inventory getInventoryAndroid() {
        try {
            Cursor results = this.getInventory();
            Inventory inventory = new InventoryImpl(new HashMap<Item, Integer>(), 0);
            Item item;

            while (results.moveToNext()) {
                item = getItemAndroid
                        (Math.toIntExact(results.getLong(results.getColumnIndex("ITEMID"))));
                inventory.updateMap(item, Math.toIntExact
                        (results.getLong(results.getColumnIndex("QUANTITY"))));
                inventory.setTotalItems(inventory.getTotalItems()
                        + Math.toIntExact(results.getLong(results.getColumnIndex("QUANTITY"))));
            }
            results.close();

            return inventory;
        } catch(CursorIndexOutOfBoundsException e) {
            return new InventoryImpl(new HashMap<Item, Integer>(), 0);
        }
    }

    /**
     * get the quantity on hand of the given item.
     *
     * @param itemId the id of the give item.
     * @return the quantity of the given item available.
     */
    public int getInventoryQuantityAndroid(int itemId){
        try {
            int quantity = this.getInventoryQuantity(itemId);

            return quantity;
        } catch (CursorIndexOutOfBoundsException e) {
            return -1;
        }
    }

    /**
     * return all sales.
     *
     * @return all sales.
     */
    public SalesLog getSalesAndroid(){

        try {
            Cursor results = this.getSales();
            Sale tempSale;
            SalesLog salesLog = new SalesLogImpl();
            User user;

            while (results.moveToNext()) {
                user = getUserDetailsAndroid
                        (Math.toIntExact(results.getLong(results.getColumnIndex("USERID"))));
                tempSale =
                        new SaleImpl((Math.toIntExact(results.getLong(results.getColumnIndex("ID")))),
                                user,
                                new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE"))));

                salesLog.addSale(tempSale);
            }
            results.close();

            return salesLog;
        } catch (CursorIndexOutOfBoundsException e) {
            return  new SalesLogImpl();
        }
    }

    /**
     * get the details of a given sale.
     *
     * @param saleId the id of the given sale.
     * @return the details of a given sale.
     */
    public Sale getSaleByIdAndroid(int saleId) {
        try {
            Cursor results = this.getSaleById(saleId);
            User user;
            Sale tempSale = null;

            while (results.moveToNext()) {
                user = getUserDetailsAndroid
                        (Math.toIntExact(results.getLong(results.getColumnIndex("USERID"))));
                tempSale =
                        new SaleImpl((Math.toIntExact(results.getLong(results.getColumnIndex("ID")))),
                                user,
                                new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE"))));
            }
            results.close();

            return tempSale;
        } catch(CursorIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * get all sales for a given user.
     *
     * @param userId the id of the user.
     * @return a result set of all sales made to the given user.
     */
    public List<Sale> getSalesToUserAndroid(int userId){

        Cursor results = this.getSalesToUser(userId);
        List<Sale> sales = new ArrayList<>();
        User user = getUserDetailsAndroid(Math.toIntExact(results.getLong(results.getColumnIndex("USERID"))));
        Sale sale;

        try {
            while (results.moveToNext()) {
                sale =
                        new SaleImpl((Math.toIntExact(results.getLong(results.getColumnIndex("ID")))),
                                user,
                                new BigDecimal(results.getString(results.getColumnIndex("TOTALPRICE"))));
                sales.add(sale);
            }
            results.close();

            return sales;
        } catch (CursorIndexOutOfBoundsException e) {
            return sales;
        }
    }

    /**
     * get the items sold in the given sale and the quantity of each.
     *
     * @param saleId the id of the sale of interest.
     * @return the itemized sale.
     */
    public Sale getItemizedSaleByIdAndroid(int saleId) {

        Cursor results = this.getItemizedSaleById(saleId);
        HashMap<Item, Integer> itemMap = new HashMap<>();
        Sale sale = new SaleImpl(saleId, new HashMap<Item, Integer>());

        try {
            while (results.moveToNext()) {
                Item item = getItemAndroid(
                        Math.toIntExact(results.getLong(results.getColumnIndex("ITEMID"))));

                itemMap.put(item,
                        Math.toIntExact(results.getLong(results.getColumnIndex("QUANTITY"))));
            }
            sale.setItemMap(itemMap);
            results.close();

            return sale;
        } catch (CursorIndexOutOfBoundsException e) {
            return sale;
        }
    }


    /**
     * check whether the sale exists or not.
     *
     * @param sale the sale of interest.
     * @param salesLog saleslog of interest.
     * @return whether the sale exists or not.
     */
    private boolean saleExistsAndroid(Sale sale, SalesLog salesLog) {
        List<Sale> sales = salesLog.getSales();
        for (Sale currentSale : sales) {
            if (currentSale.getId() == sale.getId()) {
                return true;
            }
        }

        return false;
    }

    /**
     * get all itemized sales from the itemizedsales table.
     *
     * @return all itemized sales.
     */
    public SalesLog getItemizedSalesAndroid() {
        Cursor results = this.getItemizedSales();
        SalesLog salesLog = new SalesLogImpl();
        Sale sale;
        try {
            while (results.moveToNext()) {
                sale = getItemizedSaleByIdAndroid
                        (Math.toIntExact(results.getLong(results.getColumnIndex("SALEID"))));
                if (!saleExistsAndroid(sale, salesLog)) {
                    salesLog.addSale(sale);
                }
            }
            results.close();

            return salesLog;
        } catch (CursorIndexOutOfBoundsException e) {
            return salesLog;
        }
    }

    /**
     * Get the accounts assigned to a given user.
     *
     * @param userId the id of the user.
     * @return a result set containing the id's of the accounts
     */
    public List<Integer> getUserAccountsAndroid(int userId) {

        Cursor results = this.getUserAccounts(userId);
        List<Integer> accounts = new ArrayList<Integer>();
        try {
            while (results.moveToNext()) {
                accounts.add(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))));
            }
            return accounts;
        } catch (CursorIndexOutOfBoundsException e) {
            return accounts;
        }
    }

    /**
     * Get the details of a given account.
     *
     * @param accountId the ID of the account.
     * @return the details associated to the given account.

     */
    public Account getAccountDetailsAndroid(int accountId){

        Cursor results = this.getAccountDetails(accountId);
        HashMap<Item, Integer> cart = new HashMap<Item, Integer>();
        Account account = new AccountImpl(accountId, cart);

        try {
            while (results.moveToNext()) {
                account.addItemToCart(getItemAndroid(
                        Math.toIntExact(results.getLong(results.getColumnIndex("ITEMID")))),
                        Math.toIntExact(results.getLong(results.getColumnIndex("QUANTITY"))));
            }
            return account;
        } catch (CursorIndexOutOfBoundsException e) {
            return account;
        }
    }

    public List<Integer> getUserActiveAccountsAndroid(int userId) {
        Cursor results = this.getUserActiveAccounts(userId);
        List<Integer> activeAccounts = new ArrayList<Integer>();
        try {
            while (results.moveToNext()) {
                activeAccounts.add(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))));
            }
            return activeAccounts;
        } catch(CursorIndexOutOfBoundsException e) {
            return activeAccounts;
        }
    }

    public List<Integer> getUserInactiveAccountsAndroid(int userId) {
        Cursor results = this.getUserInactiveAccounts(userId);
        List<Integer> inactiveAccounts = new ArrayList<Integer>();
        try {
            while (results.moveToNext()) {
                inactiveAccounts.add(Math.toIntExact(results.getLong(results.getColumnIndex("ID"))));
            }
            return inactiveAccounts;
        } catch (CursorIndexOutOfBoundsException e) {
            return inactiveAccounts;
        }
    }
    



}
