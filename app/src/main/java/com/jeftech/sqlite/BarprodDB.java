package com.jeftech.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import com.jeftech.sqlite.model.Data;

/**
 * Created by ajith on 30/12/15.
 */
public class BarprodDB {

    public static final String DATABASE_TABLE = "barcode1";
    private static final String PRODUCTNAME = "productname";
    private static final String PRODUCTPRICE = "productprice";
    private static final String PRODUCTCODE = "productcode";
    public static final String DATABASE_CREATE =
        "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE
            + " (" + PRODUCTCODE + " INTEGER NOT NULL, "
            + PRODUCTNAME + " TEXT NOT NULL, "
            + PRODUCTPRICE + " TEXT "
            + ");";
    private static String TAG = "BarprodDB";
    private final Context context;
    private static DatabaseHelper DBHelper;
    private static SQLiteDatabase db;

    public BarprodDB(Context ctx) {

        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
      /*  db = DBHelper.getWritableDatabase();*/
    }

    //---open SQLite DB---
    public BarprodDB open() throws SQLException {
        db = DBHelper.getWritableDatabase();
        return this;
    }

    //---close SQLite DB---
    public void close() {
        DBHelper.close();
    }

    //---Delete All Data from table in SQLite DB---
    public void deleteAll() {
        this.open();
        db.delete(DATABASE_TABLE, null, null);
        this.close();
    }

    public Data getNote(String id) {
        // get readable database as we are not inserting anything
        Data data = new Data();
        Cursor cursor;
        this.open();

        cursor = db.query(DATABASE_TABLE,
            new String[]{PRODUCTNAME,PRODUCTCODE,PRODUCTPRICE },
            PRODUCTCODE + "=?",
            new String[]{String.valueOf(id)}, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            data.setProductname(cursor.getString(0));
            data.setProductcode(cursor.getString(1));
            data.setProductprice(cursor.getString(2));
            cursor.moveToNext();
        }




        // close the db connection
        cursor.close();

        return data;
    }



    public static boolean insertdata(String name, String code, String price){
        db = DBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        {

            contentValues.put(PRODUCTNAME,name );
            contentValues.put(PRODUCTCODE,code );
            contentValues.put(PRODUCTPRICE,price );


        }

        long result =db.insert(DATABASE_TABLE,null,contentValues);
        db.close();

        if (result== -1){
            return false;
        }
        else {

            return true;
        }



    }

}
