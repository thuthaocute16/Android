package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Strategy;

public class StrategyDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public StrategyDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Strategy\" (\"StrategyID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"StrategyName\" VARCHAR, \"Active\" INTEGER, \"Date\" VARCHAR)");
    }

    public List<Strategy> get_list()
    {
        List<Strategy>  strategyList = new ArrayList<>();
        String sql_query = "Select * from Strategy";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Strategy strategy = new Strategy();
                strategy.setStrategyID(cursor.getInt(0));
                strategy.setStrategyName(cursor.getString(1));
                strategy.setActive(cursor.getInt(2));
                strategy.setDate(cursor.getString(3));

                strategyList.add(strategy);
            }
            cursor.close();
        }
        return strategyList;
    }

    public boolean insertStrategy(Strategy strategy)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("StrategyID",strategy.getStrategyID());
        contentValues.put("StrategyName",strategy.getStrategyName());
        contentValues.put("Active",strategy.getActive());
        contentValues.put("Date",strategy.getDate());

        return sqLiteDatabase.insert("Strategy", null, contentValues) > 0;
    }
    public boolean updateStrategy(Strategy strategy)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("StrategyID",strategy.getStrategyID());
        contentValues.put("StrategyName",strategy.getStrategyName());
        contentValues.put("Active",strategy.getActive());
        contentValues.put("Date",strategy.getDate());

        return sqLiteDatabase.update("Strategy", contentValues, "StrategyID=" + strategy.getStrategyID(), null) > 0;
    }

    public boolean deleteStrategy(Strategy strategy)
    {
        return sqLiteDatabase.delete("Strategy", "StrategyID=" + strategy.getStrategyID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
