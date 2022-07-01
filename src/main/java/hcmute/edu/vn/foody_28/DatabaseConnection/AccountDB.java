package hcmute.edu.vn.foody_28.DatabaseConnection;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Account;

public class AccountDB
{
    private final SQLiteDatabase sqLiteDatabase;
    public static List<Account> check_account = null;

    public AccountDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Account\" (\"AccountID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"Username\" VARCHAR, \"Password\" VARCHAR, \"Role\" VARCHAR, \"Active\" INTEGER, \"Date\" DATETIME)");
    }

    public List<Account> get_list()
    {
        List<Account> accounts = new ArrayList<>();
        String sql_query = "Select * from Account";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Account account = new Account();
                account.setAccountID(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setRole(cursor.getString(3));
                account.setActive(cursor.getInt(4));
                account.setDate(cursor.getString(5));

                accounts.add(account);
            }
            cursor.close();
        }
        return accounts;
    }

    public boolean insertAccount(Account account)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",account.getUsername());
        contentValues.put("Password",account.getPassword());
        contentValues.put("Role",account.getRole());
        contentValues.put("Active",account.getActive());
        contentValues.put("Date",account.getDate());

        return sqLiteDatabase.insert("Account", null, contentValues) > 0;
    }
    public boolean updateAccount(Account account)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("Username",account.getUsername());
        contentValues.put("Password",account.getPassword());
        contentValues.put("Role",account.getRole());
        contentValues.put("Active",account.getActive());
        contentValues.put("Date",account.getDate());

        return sqLiteDatabase.update("Account", contentValues, "AccountID=" + account.getAccountID(), null) > 0;
    }

    public boolean deleteAccount(Account account)
    {
        return sqLiteDatabase.delete("Account", "AccountID=" + account.getAccountID(), null) > 0;
    }

    public boolean checkAccount(String username,String password)
    {
        check_account = new ArrayList<>();
        String sql = "SELECT * FROM Account where Username = '" + username + "'and Password = '" + password + "'";

        @SuppressLint("Recycle") Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        if(cursor.getCount() > 0)
        {
            while (cursor.moveToNext())
            {
                Account account = new Account();
                account.setAccountID(cursor.getInt(0));
                account.setUsername(cursor.getString(1));
                account.setPassword(cursor.getString(2));
                account.setRole(cursor.getString(3));
                account.setActive(cursor.getInt(4));
                account.setDate(cursor.getString(5));

                check_account.add(account);
            }
            return true;
        }
        return false;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }
}
