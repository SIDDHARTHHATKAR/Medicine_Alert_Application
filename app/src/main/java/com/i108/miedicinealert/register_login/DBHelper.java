package com.i108.miedicinealert.register_login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TAG = DBHelper.class.getSimpleName();
    public static final String DB_NAME = "singin.db";

    public static final String USER_TABLE = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_EMAILID = "EMAIL_ID";//EMAIL ID
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_CNFRMPASS = "cnfrmpassword";

    public SQLiteDatabase db ;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + USER_TABLE +
                "(" + COLUMN_NAME + " TEXT, " + COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAILID + " TEXT, " +COLUMN_PASSWORD + " TEXT, " + COLUMN_CNFRMPASS + " TEXT " +")");    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        onCreate(db);
    }

    /* Storing User details*/

    public void addUser(String name, String username, String email_id, String password, String cnfrmpassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAILID, email_id);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_CNFRMPASS, cnfrmpassword);

        db.insert(USER_TABLE, null, values);
        db.close();
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + USER_TABLE, null);
    }

    public void updatePassword(String email, String password) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_EMAILID, email);
        values.put(COLUMN_PASSWORD, password);
        db.update(USER_TABLE, values, COLUMN_EMAILID+" = ?", new String[] { email });
        db.close();
    }

    public boolean checkUser(String email){
        String[] columns = {COLUMN_USERNAME};
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAILID + " = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }

    public boolean checkUser(String email, String password){
        String[] columns = {
                COLUMN_NAME
        };
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_EMAILID + " = ?" + " AND " + COLUMN_PASSWORD + " =?";
        String[] selectionArgs = { email, password };

        Cursor cursor = db.query(USER_TABLE,
                columns,
                selection,
                selectionArgs,
                null,
                null,
                null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0){
            return true;
        }
        return false;
    }


    public int getDataCount(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        String query="select COUNT(*) from "+USER_TABLE;
        Cursor cursor=sqLiteDatabase.rawQuery(query,null);
        int count=cursor.getCount();
        cursor.close();
        return count;

    }

}
