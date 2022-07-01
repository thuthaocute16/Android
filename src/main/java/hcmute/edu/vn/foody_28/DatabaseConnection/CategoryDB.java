package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Category;

public class CategoryDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public CategoryDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Category\" (\"CategoryID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"CategoryName\" VARCHAR, \"CategoryRoot\" INTEGER, \"CategoryImage\" VARCHAR, \"Date\" VARCHAR, \"Active\" INTEGER)");
    }

    public List<Category> get_list()
    {
        List<Category> categoryList = new ArrayList<>();
        String sql_query = "Select * from Category";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Category category = new Category();
                category.setCategoryID(cursor.getInt(0));
                category.setCategoryName(cursor.getString(1));
                category.setCategoryRoot(cursor.getInt(2));
                category.setCategoryImage(cursor.getString(3));
                category.setDate(cursor.getString(4));
                category.setActive(cursor.getInt(5));

                categoryList.add(category);
            }
            cursor.close();
        }
        return categoryList;
    }

    public boolean insertCategory(Category category)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CategoryName",category.getCategoryName());
        contentValues.put("CategoryRoot",category.getCategoryRoot());
        contentValues.put("CategoryImage",category.getCategoryImage());
        contentValues.put("Date",category.getDate());
        contentValues.put("Active",category.getActive());


        return sqLiteDatabase.insert("Category", null, contentValues) > 0;
    }
    public boolean updateCategory(Category category)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("CategoryName",category.getCategoryName());
        contentValues.put("CategoryRoot",category.getCategoryRoot());
        contentValues.put("CategoryImage",category.getCategoryImage());
        contentValues.put("Date",category.getDate());
        contentValues.put("Active",category.getActive());

        return sqLiteDatabase.update("Category", contentValues, "CategoryID=" + category.getCategoryID(), null) > 0;
    }

    public boolean deleteCategory(Category category)
    {
        return sqLiteDatabase.delete("Category", "CategoryID=" + category.getCategoryID(), null) > 0;
    }

    public void close()
    {
        sqLiteDatabase.close();
    }
}
