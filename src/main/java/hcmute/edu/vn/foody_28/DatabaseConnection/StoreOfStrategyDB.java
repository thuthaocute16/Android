package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Store;
import hcmute.edu.vn.foody_28.Domain.StoreOfStrategy;
import hcmute.edu.vn.foody_28.Domain.VoucherOfStore;

public class StoreOfStrategyDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public StoreOfStrategyDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"StoreOfStrategy\" (\"StrategyID\" INTEGER NOT NULL , \"StoreID\" INTEGER NOT NULL , \"Date\" VARCHAR, \"RetireDate\" VARCHAR, \"Active\" INTEGER, PRIMARY KEY (\"StrategyID\", \"StoreID\"))");
    }

    public List<Store> get_list(int StrategyID)
    {
        List<Store> storeList = new ArrayList<>();
        String sql_query = "select * from StoreOfStrategy,Store where StoreOfStrategy.StoreID = Store .StoreID and StrategyID = " + StrategyID;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Store store = new Store();
                store.setStoreID(cursor.getInt(5));
                store.setStoreName(cursor.getString(6));
                store.setStoreImage(cursor.getString(7));
                store.setDate(cursor.getString(8));
                store.setTimeOn(cursor.getString(9));
                store.setTimeOff(cursor.getString(10));
                store.setAddress(cursor.getString(11));
                store.setOwner(cursor.getString(12));
                store.setAmountDivideFood(cursor.getInt(13));
                store.setRate(cursor.getDouble(14));

                storeList.add(store);
            }
            cursor.close();
        }
        return storeList;
    }
    public boolean insertVoucherOfStore(StoreOfStrategy storeOfStrategy)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("StrategyID",storeOfStrategy.getStrategyID());
        contentValues.put("StoreID",storeOfStrategy.getStoreID());
        contentValues.put("Date",storeOfStrategy.getDate());
        contentValues.put("RetireDate",storeOfStrategy.getRetireDate());
        contentValues.put("Active",storeOfStrategy.getActive());


        return sqLiteDatabase.insert("VoucherOfStore", null, contentValues) > 0;
    }
    public boolean updateVoucherOfStore(StoreOfStrategy storeOfStrategy)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("StrategyID",storeOfStrategy.getStrategyID());
        contentValues.put("StoreID",storeOfStrategy.getStoreID());
        contentValues.put("Date",storeOfStrategy.getDate());
        contentValues.put("RetireDate",storeOfStrategy.getRetireDate());
        contentValues.put("Active",storeOfStrategy.getActive());

        return sqLiteDatabase.update("VoucherOfStore", contentValues, "VoucherID=" + storeOfStrategy.getStrategyID(), null) > 0;
    }

    public boolean deleteVoucherOfStore(StoreOfStrategy storeOfStrategy)
    {
        return sqLiteDatabase.delete("VoucherOfStore", "Voucher=" + storeOfStrategy.getStrategyID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
