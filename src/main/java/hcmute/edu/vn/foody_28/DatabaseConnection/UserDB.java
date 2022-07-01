package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import hcmute.edu.vn.foody_28.Domain.Account;
import hcmute.edu.vn.foody_28.Domain.User;

public class UserDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public UserDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Voucher\" (\"VoucherID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"VoucherName\" VARCHAR, \"VoucherImage\" VARCHAR, \"Active\" INTEGER, \"Date\" VARCHAR, \"Amount\" INTEGER, \"RetireDate\" VARCHAR, \"AmountUsed\" INTEGER)");
    }

    public boolean add_User_with_Account(Account account)
    {

        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",account.getUsername());

        return sqLiteDatabase.insert("User", null, contentValues) > 0;
    }

    public int find_user_with_username(String user_name)
    {
        int user_id = 0;
        String sql_query = "Select * from User where Username = '" + user_name + "'";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                user_id = cursor.getInt(0);
            }

            cursor.close();
        }
        return user_id;
    }
}
