package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.Store;

public class FoodOfStoreDB {
    private final SQLiteDatabase sqLiteDatabase;

    public FoodOfStoreDB(Context context) {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName, Context.MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS  \"FoodOfStore\" (\"FoodID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"StoreID\" INTEGER, \"DivideFoodID\" INTEGER, \"Active\" INTEGER, \"FoodImage\" VARCHAR, \"PriceRoot\" DOUBLE, \"PriceSale\" DOUBLE, \"IsSale\" INTEGER, \"FoodName\" VARCHAR, \"Description\" TEXT )");
    }

    public List<FoodOfStore> get_list(int store_id, int divide_id) {
        List<FoodOfStore> foodOfStoreList = new ArrayList<>();
        String sql_query = "Select * from FoodOfStore where StoreID = " + store_id + " and DivideFoodID = " + divide_id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                FoodOfStore foodOfStore = new FoodOfStore();
                foodOfStore.setFoodID(cursor.getInt(0));
                foodOfStore.setStoreID(cursor.getInt(1));
                foodOfStore.setDivideFoodID(cursor.getInt(2));
                foodOfStore.setActive(cursor.getInt(3));
                foodOfStore.setFoodImage(cursor.getString(4));
                foodOfStore.setPriceRoot(cursor.getDouble(5));
                foodOfStore.setPriceSale(cursor.getDouble(6));
                foodOfStore.setIsSale(cursor.getInt(7));
                foodOfStore.setFoodName(cursor.getString(8));
                foodOfStore.setDes(cursor.getString(9));

                foodOfStoreList.add(foodOfStore);
            }
            cursor.close();
        }
        return foodOfStoreList;
    }

    public FoodOfStore find_food_image(int orderID)
    {
        FoodOfStore foodOfStore = new FoodOfStore();

        String sql_query = "Select * from FoodOfStore, OrderDetail where OrderDetail.FoodID = FoodOfStore.FoodID and OrderDetail.OrderID = " + orderID;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                foodOfStore.setFoodID(cursor.getInt(0));
                foodOfStore.setStoreID(cursor.getInt(1));
                foodOfStore.setDivideFoodID(cursor.getInt(2));
                foodOfStore.setActive(cursor.getInt(3));
                foodOfStore.setFoodImage(cursor.getString(4));
                foodOfStore.setPriceRoot(cursor.getDouble(5));
                foodOfStore.setPriceSale(cursor.getDouble(6));
                foodOfStore.setIsSale(cursor.getInt(7));
                foodOfStore.setFoodName(cursor.getString(8));
                foodOfStore.setDes(cursor.getString(9));
            }
            cursor.close();
        }
        return foodOfStore;

    }

    public FoodOfStore getFood(int food_id) {
        FoodOfStore foodOfStore = new FoodOfStore();
        String sql_query = "SELECT * FROM FoodOfStore where FoodID = " + food_id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query, null);

        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                foodOfStore.setFoodID(cursor.getInt(0));
                foodOfStore.setStoreID(cursor.getInt(1));
                foodOfStore.setDivideFoodID(cursor.getInt(2));
                foodOfStore.setActive(cursor.getInt(3));
                foodOfStore.setFoodImage(cursor.getString(4));
                foodOfStore.setPriceRoot(cursor.getDouble(5));
                foodOfStore.setPriceSale(cursor.getDouble(6));
                foodOfStore.setIsSale(cursor.getInt(7));
                foodOfStore.setFoodName(cursor.getString(8));
                foodOfStore.setDes(cursor.getString(9));
            }
            cursor.close();
        }
        return foodOfStore;
    }

    public double get_price(int food_id, int store_id) {
        double cost = 0;
        String sql_query = "SELECT * FROM FoodOfStore where FoodID = " + food_id + " and StoreID = " + store_id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query, null);
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                cost = cursor.getDouble(5);
            }
        }
        return cost;
    }

    public void close() {
        sqLiteDatabase.close();
    }

}
