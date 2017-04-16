package com.hussain.savehuman;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by HUSSAIN on 28-Mar-17.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bloodbank1.db";
    private static final String TABLE_NAME = "DONOR";
    private static final String COL_id = "ID";
    private static final String COL_name = "NAME";
    private static final String COL_discipline = "DISCIPLINE";
    private static final String COL_group = "BLOOD_GROUP";
    private static final String COL_mobile = "MOBILE_NO";
    private static final String COL_email = "EMAIL";
    private static final String COL_password = "PASSWORD";
    private static final String COL_date = "GIVENDATE";
    //-------------second table----------------

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String qurey = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY, NAME TEXT not null,DISCIPLINE TEXT not null,BLOOD_GROUP TEXT not null,MOBILE_NO INTEGER not null,EMAIL TEXT not null,PASSWORD TEXT not null,GIVENDATE TEXT not null)";
        db.execSQL(qurey);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String qurey2 = "DROP TABLE IF EXITS " + TABLE_NAME;
        db.execSQL(qurey2);
        onCreate(db);
    }

    public boolean insertData(int idStr, String nameStr, String disciplineStr, String bloodStr, int mobileStr, String emailStr, String passwordStr) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_id, idStr);
        values.put(COL_name, nameStr);
        values.put(COL_discipline, disciplineStr);
        values.put(COL_group, bloodStr);
        values.put(COL_mobile, mobileStr);
        values.put(COL_email, emailStr);
        values.put(COL_password, passwordStr);
        long result = db.insert(TABLE_NAME, null, values);
        if (result == -1) {
            return false;
        } else
            return true;
    }

    /*public boolean insertNew(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("StudentId",id);
        long result = db.insert("DONATE_DATE",null,values);
        if(result==-1) return false;
        return true;
    }*/
    public Cursor searchPassword() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);
        return res;
    }

    public Cursor getAllData(String group) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where BLOOD_GROUP='" + group + "';", null);
        //JOIN DONATE_DATE ON DONOR.ID=DONATE_DATE.StudentId
        return res;
    }

    public boolean updateData(int id_update, String name_update, int mobile_update, String email_update, String password_update) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String q = "UPDATE DONOR SET (NAME,MOBILE_NO,EMAIL,PASSWORD,GIVENDATE)=("+"'"+name_update+"',"+
//                "'"+mobile_update+"',"+"'"+email_update+"',"+"'"+password_update+"'"+") ID="+id_update;
//        db.execSQL(q);
        //  return true;
        ContentValues values = new ContentValues();
        values.put(COL_id,id_update);
        values.put(COL_name, name_update);
        values.put(COL_mobile, mobile_update);
        values.put(COL_email, email_update);
        values.put(COL_password, password_update);


        db.update(TABLE_NAME, values, "ID=?", new String[]{String.valueOf(id_update)});


        return true;
    }

    /*public boolean updateDate(int id,String date)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("StudnetId",id);
        values.put("GIVENDATE",date);
        db.update("DONATE_DATE",values,"StudentId= ?",new String[]{String.valueOf(id)});
        return true;
    }*/

}
