package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.CheckIn;

public class CheckInDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public CheckInDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"CheckIn\" (\"CheckInID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"UserID\" INTEGER, \"StoreID\" INTEGER, \"Content\" VARCHAR, \"CheckInImage\" VARCHAR, \"Time\" VARCHAR, \"Date\" VARCHAR, \"Active\" INTEGER)");
    }

    public List<CheckIn> get_list()
    {
        List<CheckIn> checkInList = new ArrayList<>();
        String sql_query = "Select * from CheckIn";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                CheckIn checkIn = new CheckIn();
                checkIn.setCheckInID(cursor.getInt(0));
                checkIn.setUserID(cursor.getInt(1));
                checkIn.setStoreID(cursor.getInt(2));
                checkIn.setContent(cursor.getString(3));
                checkIn.setCheckInImage(cursor.getString(4));
                checkIn.setTime(cursor.getString(5));
                checkIn.setDate(cursor.getString(6));
                checkIn.setActive(cursor.getInt(7));
                checkInList.add(checkIn);
            }
            cursor.close();
        }
        return checkInList;
    }

    public boolean insertCheckIn(CheckIn checkIn)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID",checkIn.getUserID());
        contentValues.put("StoreID",checkIn.getStoreID());
        contentValues.put("Content",checkIn.getContent());
        contentValues.put("CheckInImage",checkIn.getCheckInImage());
        contentValues.put("Time",checkIn.getTime());
        contentValues.put("Date",checkIn.getDate());
        contentValues.put("Active",checkIn.getActive());


        return sqLiteDatabase.insert("CheckIn", null, contentValues) > 0;
    }
    public boolean updateCheckIn(CheckIn checkIn)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("UserID",checkIn.getUserID());
        contentValues.put("StoreID",checkIn.getStoreID());
        contentValues.put("Content",checkIn.getContent());
        contentValues.put("CheckInImage",checkIn.getCheckInImage());
        contentValues.put("Time",checkIn.getTime());
        contentValues.put("Date",checkIn.getDate());
        contentValues.put("Active",checkIn.getActive());

        return sqLiteDatabase.update("CheckIn", contentValues, "CheckInID=" + checkIn.getCheckInID(), null) > 0;
    }

    public boolean deleteCheckIn(CheckIn checkIn)
    {
        return sqLiteDatabase.delete("CheckIn", "CheckInID=" + checkIn.getCheckInID(), null) > 0;
    }

    public void close()
    {
        sqLiteDatabase.close();
    }
}
