package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.OrderDetail;

public class OrderDetailDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public OrderDetailDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"OrderDetail\" (\"OrderID\" INTEGER NOT NULL , \"FoodID\" INTEGER NOT NULL , \"Amount\" INTEGER, \"Price\" DOUBLE, PRIMARY KEY (\"OrderID\", \"FoodID\"))");
    }

    public boolean check_food_existed(int food_id , int order_id)
    {
        String sql_query = "Select * from OrderDetail where OrderID = " + order_id + " and FoodID = " + food_id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if(cursor.getCount()>0)
        {
            cursor.close();
            return true;
        }
        return false;
    }

    public int get_amount_current_food(int food_id , int order_id)
    {
        int amount = 0;
        String sql_query = "Select * from OrderDetail where OrderID = " + order_id + " and FoodID = " + food_id;

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                amount = cursor.getInt(2);
            }
        }
        return amount;
    }

    public boolean update(OrderDetail orderDetail)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("Amount",orderDetail.getAmount());
        contentValues.put("Price",orderDetail.getPrice());
        return sqLiteDatabase.update("OrderDetail", contentValues, "OrderID = " + orderDetail.getOrderID() + " and FoodID = " + orderDetail.getFoodID(), null) > 0;
    }

    public boolean delete(OrderDetail orderDetail)
    {
        return sqLiteDatabase.delete("OrderDetail", "OrderID = " + orderDetail.getOrderID() + " and FoodID = " + orderDetail.getFoodID() , null) > 0;

    }


    public boolean insert_order(OrderDetail orderDetail)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put("OrderID",orderDetail.getOrderID());
        contentValues.put("FoodID",orderDetail.getFoodID());
        contentValues.put("Amount",orderDetail.getAmount());
        contentValues.put("Price",orderDetail.getPrice());

        return sqLiteDatabase.insert("OrderDetail", null , contentValues) > 0;
    }

    // have active = 0
    public List<OrderDetail> get_list(int order_id)
    {
        List<OrderDetail> orderDetailList = new ArrayList<>();
        String sql_query = "Select * from OrderDetail where OrderID  = " + order_id;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);

        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrderID(cursor.getInt(0));
                orderDetail.setFoodID(cursor.getInt(1));
                orderDetail.setAmount(cursor.getInt(2));
                orderDetail.setPrice(cursor.getDouble(3));
                orderDetailList.add(orderDetail);
            }
            cursor.close();
        }
        return orderDetailList;
    }

    public double getAllPrice(int orderID)
    {
        double all_price = 0;
        String sql_query = "select sum(Price) from OrderDetail where OrderID = " + orderID;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);

        if(cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                all_price = cursor.getDouble(0);
            }
            cursor.close();
        }

        return all_price;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
