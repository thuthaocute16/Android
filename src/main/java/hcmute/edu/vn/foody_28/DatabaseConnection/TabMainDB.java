package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.TabMain;
import hcmute.edu.vn.foody_28.Domain.TabMain;

public class TabMainDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public TabMainDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"TabMain\" (\"TabMainID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"TabMainName\" VARCHAR, Active INTERGER)");
    }

    public List<TabMain> get_list()
    {
        List<TabMain>  tabMainList = new ArrayList<>();
        String sql_query = "Select * from TabMain";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                TabMain tabMain = new TabMain();
                tabMain.setTabMainID(cursor.getInt(0));
                tabMain.setTabMainName(cursor.getString(1));

                tabMainList.add(tabMain);
            }
            cursor.close();
        }
        return tabMainList;
    }

    public boolean insertTabMain(TabMain tabMain)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TabMainName",tabMain.getTabMainName());
        contentValues.put("Active",tabMain.getActive());

        return sqLiteDatabase.insert("TabMain", null, contentValues) > 0;
    }
    public boolean updateTabMain(TabMain tabMain)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TabMainName",tabMain.getTabMainName());
        contentValues.put("Active",tabMain.getActive());

        return sqLiteDatabase.update("TabMain", contentValues, "TabMainID=" + tabMain.getTabMainID(), null) > 0;
    }

    public boolean deleteTabMain(TabMain tabMain)
    {
        return sqLiteDatabase.delete("TabMain", "TabMainID=" + tabMain.getTabMainID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
