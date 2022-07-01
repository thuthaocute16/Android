package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Bill;

public class BillDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public BillDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Bill\" (\"BillID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"OrderID\" INTEGER, \"VoucherID\" INTEGER, \"Active\" INTEGER, \"DriverID\" INTEGER, \"Note\" VARCHAR, \"RiceRoot\" DOUBLE, \"RiceSale\" DOUBLE, \"Date\" VARCHAR, \"TimeBill\" VARCHAR, \"TimeComplete\" VARCHAR, Status VARCHAR)");
    }

    public List<Bill> get_list()
    {
        List<Bill> billList = new ArrayList<>();
        String sql_query = "Select * from Bill";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Bill bill = new Bill();
                bill.setBillID(cursor.getInt(0));
                bill.setOrderID(cursor.getInt(1));
                bill.setVoucherID(cursor.getInt(2));
                bill.setActive(cursor.getInt(3));
                bill.setDriverID(cursor.getInt(4));
                bill.setNote(cursor.getString(5));
                bill.setRiceRoot(cursor.getDouble(6));
                bill.setRiceSale(cursor.getDouble(7));
                bill.setDate(cursor.getString(8));
                bill.setTimeBill(cursor.getString(9));
                bill.setTimeComplete(cursor.getString(10));
                bill.setStatus(cursor.getString(11));
                billList.add(bill);
            }
            cursor.close();
        }
        return billList;
    }

    public boolean insertBill(Bill bill)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderID",bill.getOrderID());
        contentValues.put("VoucherID",bill.getVoucherID());
        contentValues.put("Active",bill.getActive());
        contentValues.put("DriverID",bill.getDriverID());
        contentValues.put("Note",bill.getNote());
        contentValues.put("RiceRoot",bill.getRiceRoot());
        contentValues.put("RiceSale",bill.getRiceSale());
        contentValues.put("Date",bill.getDate());
        contentValues.put("TimeBill",bill.getTimeBill());
        contentValues.put("TimeComplete",bill.getTimeComplete());
        contentValues.put("Status",bill.getStatus());

        return sqLiteDatabase.insert("Bill", null, contentValues) > 0;
    }
    public boolean updateBill(Bill bill)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("OrderID",bill.getOrderID());
        contentValues.put("VoucherID",bill.getVoucherID());
        contentValues.put("Active",bill.getActive());
        contentValues.put("DriverID",bill.getDriverID());
        contentValues.put("Note",bill.getNote());
        contentValues.put("RiceRoot",bill.getRiceRoot());
        contentValues.put("RiceSale",bill.getRiceSale());
        contentValues.put("Date",bill.getDate());
        contentValues.put("TimeBill",bill.getTimeBill());
        contentValues.put("TimeComplete",bill.getTimeComplete());
        contentValues.put("Status",bill.getStatus());

        return sqLiteDatabase.update("Bill", contentValues, "BillID=" + bill.getBillID(), null) > 0;
    }

    public boolean deleteBill(Bill bill)
    {
        return sqLiteDatabase.delete("Bill", "BillID=" + bill.getBillID(), null) > 0;
    }


    public void close()
    {
        sqLiteDatabase.close();
    }
}
