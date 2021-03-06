package com.example.medet.android_project1.databaseconnection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.medet.android_project1.formOfLoginAndRegist.Contact;

public class DBHelper extends SQLiteOpenHelper {

    SQLiteDatabase db;

    private static final int DATABASE_VERSION=1;

    private static final String DATABASE_NAME="accounts.db";
    private static final String TABLE_NAME="accounts";
    private static final String COLUMN_ID="id";
    private static final String COLUMN_EMAIL="email";
    private static final String COLUMN_UNAME="uname";
    private static final String COLUMN_PASS="pass";

    private static final String TABLE_CREATE="create table accounts (id integer primary key not null, "+
            "email text not null, uname text not null, pass text not null);";

    public DBHelper(Context ctx) {
        super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;

    }

    public void inserContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        int count = cursor.getCount();

        values.put(COLUMN_ID,count);
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_UNAME,c.getUname());
        values.put(COLUMN_PASS,c.getPass());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }
    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query  = "select "+COLUMN_UNAME+", pass from "+ TABLE_NAME;
        Cursor cursor =db.rawQuery(query,null);

        String a,b;
        b = "Not found";
        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);

                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;

                }
            }while (cursor.moveToNext());
        }
        return b;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int nerVersion) {
        String query = "DROP TABLE IF EXICTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
