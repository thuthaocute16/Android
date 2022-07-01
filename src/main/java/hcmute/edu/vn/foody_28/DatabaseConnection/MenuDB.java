package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Menu;

public class MenuDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public MenuDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Menu\" (\"MenuID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"Username\" VARCHAR, \"Password\" VARCHAR, \"Role\" VARCHAR, \"Active\" INTEGER, \"Date\" DATETIME)");
    }

    public List<Menu> get_list()
    {
        List<Menu> menuList = new ArrayList<>();
        String sql_query = "Select * from Menu";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Menu menu = new Menu();
                menu.setMenuID(cursor.getInt(0));
                menu.setMenuName(cursor.getString(1));
                menu.setMenuImage(cursor.getString(2));
                menu.setActive(cursor.getInt(3));
                menu.setDate(cursor.getString(4));
                menuList.add(menu);
            }
            cursor.close();
        }
        return menuList;
    }

    public boolean insertMenu(Menu menu)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MenuName",menu.getMenuName());
        contentValues.put("MenuImage",menu.getMenuImage());
        contentValues.put("Active",menu.getActive());
        contentValues.put("Date",menu.getDate());

        return sqLiteDatabase.insert("Menu", null, contentValues) > 0;
    }
    public boolean updateMenu(Menu menu)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("MenuName",menu.getMenuName());
        contentValues.put("MenuImage",menu.getMenuImage());
        contentValues.put("Active",menu.getActive());
        contentValues.put("Date",menu.getDate());

        return sqLiteDatabase.update("Menu", contentValues, "MenuID=" + menu.getMenuID(), null) > 0;
    }

    public boolean deleteMenu(Menu menu)
    {
        return sqLiteDatabase.delete("Menu", "MenuID=" + menu.getMenuID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}

