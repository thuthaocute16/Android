package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import hcmute.edu.vn.foody_28.Activity.Home.StoreActivity;
import hcmute.edu.vn.foody_28.Domain.DivideFood;
import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.Store;


public class DivideFoodDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public DivideFoodDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"DevideFood\" (\"DivideFoodID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"DivideFoodName\" VARCHAR)");
    }

    public List<FoodOfStore> get_list(int StoreID)
    {
        List<FoodOfStore> foodOfStoreList = new ArrayList<>();
        String sql_query = "Select * from DevideFood,FoodOfStore where DevideFood.DivideFoodID = FoodOfStore.DivideFoodID and StoreID = " + StoreID;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                FoodOfStore foodOfStore = new FoodOfStore();
                foodOfStore.setFoodID(cursor.getInt(2));
                foodOfStore.setStoreID(cursor.getInt(3));
                foodOfStore.setDivideFoodID(cursor.getInt(4));
                foodOfStore.setActive(cursor.getInt(5));
                foodOfStore.setFoodImage(cursor.getString(6));
                foodOfStore.setPriceRoot(cursor.getDouble(7));
                foodOfStore.setPriceSale(cursor.getDouble(8));
                foodOfStore.setIsSale(cursor.getInt(9));

                foodOfStoreList.add(foodOfStore);
            }
            cursor.close();
        }
        return foodOfStoreList;
    }

    public List<DivideFood> get_list_divide_food(int StoreID)
    {
        List<DivideFood> divideFoodList = new ArrayList<>();
        String sql_query = "Select DISTINCT FoodOfStore.DivideFoodID, DivideFoodName from DevideFood,FoodOfStore where DevideFood.DivideFoodID = FoodOfStore.DivideFoodID and FoodOfStore.StoreID = " + StoreID;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                DivideFood divideFood = new DivideFood();
                divideFood.setDivideFoodID(cursor.getInt(0));
                divideFood.setDivideFoodName(cursor.getString(1));

                divideFoodList.add(divideFood);
                System.out.println(divideFood.getDivideFoodName());
            }
            cursor.close();
        }
        return divideFoodList;
    }

    public void close()
    {
        sqLiteDatabase.close();
    }

}
