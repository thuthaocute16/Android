package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Banner;
import hcmute.edu.vn.foody_28.Domain.Store;

public class StoreDB {
    private final SQLiteDatabase sqLiteDatabase;

    public StoreDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Store\" (\"StoreID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"StoreName\" VARCHAR, \"StoreImage\" VARCHAR, \"Date\" VARCHAR, \"TimeOn\" VARCHAR, \"TimeOff\" VARCHAR, \"Address\" VARCHAR, \"Owner\" VARCHAR, AmountDivideFood int, Rate DOUBLE)");
    }

    public List<Store> get_list()
    {
        List<Store> storeList = new ArrayList<>();
        String sql_query = "Select * from Store";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Store store = new Store();
                store.setStoreID(cursor.getInt(0));
                store.setStoreName(cursor.getString(1));
                store.setStoreImage(cursor.getString(2));
                store.setDate(cursor.getString(3));
                store.setTimeOn(cursor.getString(4));
                store.setTimeOff(cursor.getString(5));
                store.setAddress(cursor.getString(6));
                store.setOwner(cursor.getString(7));
                store.setAmountDivideFood(cursor.getInt(8));
                store.setRate(cursor.getDouble(9));

                storeList.add(store);
            }
            cursor.close();
        }
        return storeList;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}