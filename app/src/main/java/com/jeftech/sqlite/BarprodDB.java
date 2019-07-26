package com.jeftech.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

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
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;

    public BarprodDB(Context ctx) {

        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
        db = DBHelper.getWritableDatabase();
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
    public String searchUser(String name) {
        String u = new String();
        db = DBHelper.getWritableDatabase();
        Cursor c = db.rawQuery("select * from " + DATABASE_TABLE+" where productname =?", new String[]{name});
        Log.i(TAG, "searchUser: "+c);
        if (c.moveToLast()) {
            u=c.getString(1);
            Log.i(TAG, "searchUser: q"+u);
            return u;


        }else {
            Log.e("error not found", "user can't be found or database empty");
            return u;
        }

    }


    public boolean insertdata(){
        db = DBHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PRODUCTNAME,"fizzy");
        contentValues.put(PRODUCTCODE,"1213");
        contentValues.put(PRODUCTPRICE,"67");
        contentValues.put(PRODUCTNAME,"fizzy");
        contentValues.put(PRODUCTCODE,"1213");
        contentValues.put(PRODUCTPRICE,"67");
        contentValues.put(PRODUCTNAME,"fizzy");
        contentValues.put(PRODUCTCODE,"1213");
        contentValues.put(PRODUCTPRICE,"67");

        long result =db.insert(DATABASE_TABLE,null,contentValues);

        if (result== -1){
            return false;
        }
        else {

            return true;
        }



}}
