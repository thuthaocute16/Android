package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.FoodOfStore;
import hcmute.edu.vn.foody_28.Domain.VoucherOfStore;
import hcmute.edu.vn.foody_28.Domain.Store;

public class VoucherOfStoreDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public VoucherOfStoreDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"VoucherOfStore\" (\"VoucherID\" INTEGER NOT NULL , \"StoreID\" INTEGER NOT NULL , \"Active\" INTEGER, PRIMARY KEY (\"VoucherID\", \"StoreID\"))");
    }
    public List<Store> get_list(int VoucherID)
    {
        List<Store> storeList = new ArrayList<>();
        String sql_query = "select * from VoucherOfStore,Store where VoucherOfStore.StoreID = Store .StoreID and VoucherID = " + VoucherID;
        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Store store = new Store();
                store.setStoreID(cursor.getInt(3));
                store.setStoreName(cursor.getString(4));
                store.setDate(cursor.getString(5));
                store.setTimeOn(cursor.getString(6));
                store.setTimeOff(cursor.getString(7));
                store.setAddress(cursor.getString(8));
                store.setOwner(cursor.getString(9));
                store.setAmountDivideFood(cursor.getInt(10));
                store.setRate(cursor.getDouble(11));
                storeList.add(store);
            }
            cursor.close();
        }
        return storeList;
    }

    public boolean insertVoucherOfStore(VoucherOfStore voucherOfStore)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("VoucherID",voucherOfStore.getVoucherID());
        contentValues.put("StoreID",voucherOfStore.getStoreID());
        contentValues.put("Active",voucherOfStore.getActive());

        return sqLiteDatabase.insert("VoucherOfStore", null, contentValues) > 0;
    }
    public boolean updateVoucherOfStore(VoucherOfStore voucherOfStore)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("VoucherID",voucherOfStore.getVoucherID());
        contentValues.put("StoreID",voucherOfStore.getStoreID());
        contentValues.put("Active",voucherOfStore.getActive());

        return sqLiteDatabase.update("VoucherOfStore", contentValues, "VoucherID=" + voucherOfStore.getVoucherID(), null) > 0;
    }

    public boolean deleteVoucherOfStore(VoucherOfStore voucherOfStore)
    {
        return sqLiteDatabase.delete("VoucherOfStore", "Voucher=" + voucherOfStore.getVoucherID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
    
}
