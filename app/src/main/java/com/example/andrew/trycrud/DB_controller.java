package com.example.andrew.trycrud;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

/**
 * Created by andrew on 09/12/2018.
 */

public class DB_controller extends SQLiteOpenHelper {

    public DB_controller(android.content.Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE STUDENTS(ID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT UNIQUE, LASTNAME TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS STUDENTS;");
        onCreate(sqLiteDatabase);
    }

    public void insert_student(String firstname, String lastname) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("FIRSTNAME", firstname);
        contentValues.put("LASTNAME", lastname);
        this.getWritableDatabase().insertOrThrow("STUDENTS", "", contentValues);
    }

    public void delete_student(String firstname) {
        this.getWritableDatabase().delete("STUDENTS", "FIRSTNAME='" + firstname + "'", null);
    }

    public void update_student(String old_firstname, String new_firstname) {
        this.getWritableDatabase().execSQL("UPDATE STUDENTS SET FIRSTNAME='" + new_firstname + "' WHERE FIRSTNAME='" + old_firstname + "'");
    }

    public void list_all_students(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM STUDENTS", null);
        textView.setText("");
        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + " " + cursor.getString(2));
        }
    }
}
