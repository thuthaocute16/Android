package hcmute.edu.vn.foody_28.DatabaseConnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.foody_28.Domain.Voucher;

public class VoucherDB
{
    private final SQLiteDatabase sqLiteDatabase;


    public VoucherDB(Context context)
    {
        String databaseName = "Foody28.sqlite";
        sqLiteDatabase = context.openOrCreateDatabase(databaseName,Context.MODE_PRIVATE,null);
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS \"Voucher\" (\"VoucherID\" INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL , \"VoucherName\" VARCHAR, \"VoucherImage\" VARCHAR, \"Active\" INTEGER, \"Date\" VARCHAR, \"Amount\" INTEGER, \"RetireDate\" VARCHAR, \"AmountUsed\" INTEGER)");
    }

    public List<Voucher> get_list()
    {
        List<Voucher> voucherList = new ArrayList<>();
        String sql_query = "Select * from Voucher";

        Cursor cursor = sqLiteDatabase.rawQuery(sql_query,null);
        if (cursor.getCount()>0)
        {
            while (cursor.moveToNext())
            {
                Voucher voucher = new Voucher();
                voucher.setVoucherID(cursor.getInt(0));
                voucher.setVoucherName(cursor.getString(1));
                voucher.setVoucherImage(cursor.getString(2));
                voucher.setActive(cursor.getInt(3));
                voucher.setDate(cursor.getString(4));
                voucher.setRetireDate(cursor.getString(5));
                voucher.setAmount(cursor.getInt(6));
                voucher.setAmountUsed(cursor.getInt(7));


                voucherList.add(voucher);
            }
            cursor.close();
        }
        return voucherList;

    }

    public boolean insertVoucher(Voucher voucher)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("VoucherID",voucher.getVoucherID());
        contentValues.put("VoucherName",voucher.getVoucherID());
        contentValues.put("Active",voucher.getActive());
        contentValues.put("Date",voucher.getDate());
        contentValues.put("RetireDate",voucher.getRetireDate());
        contentValues.put("Amount",voucher.getAmount());
        contentValues.put("AmountUsed",voucher.getAmountUsed());

        return sqLiteDatabase.insert("Voucher", null, contentValues) > 0;
    }
    public boolean updateVoucher(Voucher voucher)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("VoucherID",voucher.getVoucherID());
        contentValues.put("VoucherName",voucher.getVoucherID());
        contentValues.put("Active",voucher.getActive());
        contentValues.put("Date",voucher.getDate());
        contentValues.put("RetireDate",voucher.getRetireDate());
        contentValues.put("Amount",voucher.getAmount());
        contentValues.put("AmountUsed",voucher.getAmountUsed());

        return sqLiteDatabase.update("Voucher", contentValues, "VoucherID=" + voucher.getVoucherID(), null) > 0;
    }

    public boolean deleteVoucher(Voucher voucher)
    {
        return sqLiteDatabase.delete("Voucher", "VoucherID=" + voucher.getVoucherID(), null) > 0;
    }
    public void close()
    {
        sqLiteDatabase.close();
    }

}
