package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.foody_28.Domain.*;

public class StoreOfUserDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public StoreOfUserDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Menu\" (\"MenuID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"Username\" VARCHAR, \"Password\" VARCHAR, \"Role\" VARCHAR, \"Active\" INTEGER, \"Date\" DATETIME)");
    }

    public String find_background_store(int StoreID)
    {
        StoreOfUser storeOfUser = new StoreOfUser();
        String sql_query = "Select * from StoreOfUser where StoreID = " + StoreID;

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {

                storeOfUser.setUserID(cursor.getInt(0));
                storeOfUser.setStoreID(cursor.getInt(1));
                storeOfUser.setBackGroundStore(cursor.getString(2));
                storeOfUser.setActive(cursor.getInt(3));
            }
            cursor.close();
        }
        return storeOfUser.getBackGroundStore();
    }
}
