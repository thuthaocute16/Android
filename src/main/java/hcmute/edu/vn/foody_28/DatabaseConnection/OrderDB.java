package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.OrderD;

public class OrderDB
{
    private final SQLiteDatabase sqLiteDatabase;
    //public static List<Order>  orderList;

    public OrderDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"OrderD\" (\"OrderID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"UserID\" INTEGER NOT NULL , \"StoreID\" INTEGER, \"Active\" INTEGER)");
    }

    public List<OrderD> get_list()
    {
        List<OrderD> orderDS = new ArrayList<>();
        String sql_query = "Select * from OrderD";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                OrderD orderD = new OrderD();
                orderD.setOrderID(cursor.getInt(0));
                orderD.setStoreID(cursor.getInt(2));
                orderD.setUserID(cursor.getInt(1));
                orderD.setActive(cursor.getInt(3));
                orderDS.add(orderD);
            }
            cursor.close();
        }
        return orderDS;
    }

    public OrderD get_Order(int user_id , int store_id , int active)
    {
        OrderD orderD = new OrderD();
        String sql_query = "SELECT * FROM OrderD where UserID = " + user_id + " and StoreID = "  + store_id + " and Active = " + active ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                orderD.setOrderID(cursor.getInt(0));
                orderD.setStoreID(cursor.getInt(2));
                orderD.setUserID(cursor.getInt(1));
                orderD.setActive(cursor.getInt(3));
            }
            cursor.close();
        }
        return orderD;
    }

    public boolean checkOrder(int user_id , int store_id , int active)
    {
        String sql_query = "SELECT * FROM OrderD where UserID = " + user_id + " and StoreID = "  + store_id + " and Active = " + active ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if(cursor.getCount() > 0)
        {
            cursor.close();
            return true;
        }
        return false;
    }

    public int getOrderID(int user_id , int store_id , int active)
    {
        OrderD orderD = new OrderD();
        String sql_query = "SELECT * FROM OrderD where UserID = " + user_id + " and StoreID = "  + store_id + " and Active = " + active ;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if(cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                orderD.setOrderID(cursor.getInt(0));
            }
            cursor.close();
        }
        return orderD.getOrderID();
    }
    public boolean insertOrder(OrderD orderD)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID", orderD.getUserID());
        contentValues.put("StoreID", orderD.getStoreID());
        contentValues.put("Active", orderD.getActive());

        return sqLiteDatabase.insert("OrderD", null, contentValues) > 0;
    }

    public boolean check_order_complete(int store_id)
    {
        String sql_query = "SELECT * FROM OrderD where Active = 0 and StoreID is not " + store_id;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if(cursor.getCount()>0)
        {
            cursor.close();
            return false;
        }
        return true;
    }

    public int find_store_id()
    {
        int orderID = -1;
        String sql_query = "SELECT * FROM OrderD where Active = 0";
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if(cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                orderID = cursor.getInt(2);
            }
            cursor.close();
        }
        return orderID;
    }
    public boolean update_order(OrderD orderD)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID", orderD.getUserID());
        contentValues.put("StoreID", orderD.getOrderID());
        contentValues.put("Active", orderD.getActive());

        return sqLiteDatabase.update("OrderD",contentValues,"OrderID = " + find_store_id() , null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
