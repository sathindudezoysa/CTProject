package com.example.ctproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String USER_TABLE = "user_table";
    public static final String USER_NAME = "user_name";
    public static final String EMAIL = "user_email";
    public static final String NUMBER = "user_number";
    public static final String USER_PASSWORD = "user_password";
    public static final String USER_ID = "user_id";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "alert.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                                                                        + USER_NAME + " TEXT, "
                                                                        + EMAIL + " TEXT, "
                                                                        + NUMBER + " TEXT, "
                                                                        + USER_PASSWORD + " TEXT)";
        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean addOne(String name, String email, String number, String password ){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(USER_NAME, name);
        cv.put(EMAIL,email);
        cv.put(NUMBER, number);
        cv.put(USER_PASSWORD, password);

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1){
            return false;
        }else{
            return true;
        }
    }

    public String getpass(String usename){
        String pass = "";

        String queryString = "SELECT * FROM " + USER_TABLE;
                //+ " WHERE "+ USER_NAME + " = '"+usename+"'";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()){
            do{
                pass = cursor.getString(3);
            }while (cursor.moveToNext());
        }else{
            pass = "No values";
        }
        cursor.close();
        db.close();

        return pass;
    }

}
