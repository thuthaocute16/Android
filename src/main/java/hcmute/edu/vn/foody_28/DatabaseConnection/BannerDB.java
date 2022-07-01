package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Banner;

public class BannerDB
{
    private final SQLiteDatabase sqLiteDatabase;

    public BannerDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Banner\" (\"BannerID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"BannerName\" VARCHAR, \"BannerLink\" VARCHAR)");
    }

    public List<Banner> get_list()
    {
        List<Banner> bannerList = new ArrayList<>();
        String sql_query = "Select * from Banner";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Banner banner = new Banner();
                banner.setBannerID(cursor.getInt(0));
                banner.setName(cursor.getString(1));
                banner.setBannerLink(cursor.getString(2));
                bannerList.add(banner);
            }
            cursor.close();
        }
        return bannerList;
    }
    public boolean insertBanner(Banner banner)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("BannerName",banner.getName());
        contentValues.put("BannerLink",banner.getBannerLink());

        return sqLiteDatabase.insert("Banner", null, contentValues) > 0;
    }
    public boolean updateBanner(Banner banner)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("BannerName",banner.getName());
        contentValues.put("BannerLink",banner.getBannerLink());

        return sqLiteDatabase.update("Banner", contentValues, "BannerID=" + banner.getBannerID(), null) > 0;
    }

    public boolean deleteBanner(Banner banner)
    {
        return sqLiteDatabase.delete("Banner", "BannerID=" + banner.getBannerID(), null) > 0;
    }

    public void close()
    {
        sqLiteDatabase.close();
    }
}
