package com.example.librarydb;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

public class DBHelper extends SQLiteOpenHelper {

    /*public static final String KEY = "ID";
    public static final String DATABASE_NAME = "Enterprise Logging";
    public static final String TABLE_NAME = "application_table";
    public static final String EVENT_TIME = "Event_Time";
    public static final String SERIAL_NBR  = "Serial_Nbr";
    public static final String APP_ID = "App_ID";
    public static final String USER_ID = "User_ID";
    public static final String LOC = "Location_Nbr";
    public static final String RTE = "Route_Nbr";
    public static final String DAY = "Day";
    public static final String LOGGER = "Logger";
    public static final String EVENT_NBR = "Event_Nbr";
    public static final String ADDT_DESC = "Addit_Description";
    public static final String ADDT_NBR = "Addit_Nbr";

     */

    public static final String TABLE_NAME = "login_table";
    public static final String NAME = "Name";
    public static final String EMAIL = "Email";
    public static final String PASSWORD = "Password";
    public static final String DOB = "DOB";
    public static final String GENDER = "Gender";

    public DBHelper(Context context){

        super(context, "SQLiteLogin", null, 1);
        //SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*String query = "create table " + TABLE_NAME + " (id integer primary key, Event_Time text, Serial_Nbr text, App_ID text, User_ID text, Location_Nbr text, " +
                "Route_Nbr text, Day text, Logger text, Event_Nbr text, Addit_Description text, Addit_Nbr text)";

         */
        String newquery = "create table " + TABLE_NAME + "(id integer primary key, Name text, Email text, Password text, DOB text, Gender text)";
        db.execSQL(newquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop Table if Exists " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addNewEntry(String name, String email, String password, String dob, String gender){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, name);
        contentValues.put(EMAIL, email);
        contentValues.put(PASSWORD, password);
        contentValues.put(DOB, dob);
        contentValues.put(GENDER, gender);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    @SuppressLint("Range")
    public String retrieveData(String email, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME + " where " + EMAIL + " = " + email + " and " + PASSWORD + " = " + password, null);

        if(res.getCount() == 0) {
            return null;
        }

        res.moveToFirst();

        if (res.getString(1 ).equals(null)){
            return "";
        }

        res.close();

        return res.getString(1);
    }

    /*
    public boolean insertData(String eventTime, String serialNum, String appId, String userId, String location,
                              String route, String day, String logger, String eventNbr, String addtDesc, String addtNbr){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(KEY, id);
        contentValues.put(EVENT_TIME, eventTime);
        contentValues.put(SERIAL_NBR, serialNum);
        contentValues.put(APP_ID, appId);
        contentValues.put(USER_ID, userId);
        contentValues.put(LOC, location);
        contentValues.put(RTE, route);
        contentValues.put(DAY, day);
        contentValues.put(LOGGER, logger);
        contentValues.put(EVENT_NBR, eventNbr);
        contentValues.put(ADDT_DESC, addtDesc);
        contentValues.put(ADDT_NBR, addtNbr);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from " + TABLE_NAME, null);

        /*if(res.getCount() == 0){
           // showMessage("Error", "Nothing found", context);
        }
        else{
            StringBuffer buffer = new StringBuffer();
            // EVENT_TIME, SERIAL_NBR, APP_ID, USER_ID, LOC, RTE, DAY, LOGGER, EVENT_NBR, ADDT_DESC, ADDT_NBR
            while (res.moveToNext()){
                buffer.append("ID :" + res.getString(0) + "\n");
                buffer.append("EventTime :" + res.getString(1) + "\n");
                buffer.append("SerialNbr :" + res.getString(2) + "\n");
                buffer.append("AppId :" + res.getString(3) + "\n");
                buffer.append("UserId :" + res.getString(4) + "\n");
                buffer.append("Loc :" + res.getString(5) + "\n");
                buffer.append("Rte :" + res.getString(6) + "\n");
                buffer.append("Day :" + res.getString(7) + "\n");
                buffer.append("Logger :" + res.getString(8) + "\n");
                buffer.append("EventNbr :" + res.getString(9) + "\n");
                buffer.append("AddtDesc :" + res.getString(10) + "\n");
                buffer.append("AddtNbr :" + res.getString(11) + "\n\n");
            }

         //   showMessage("Data", buffer.toString());
        }

         */

        //return res;

    /*public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(DBHelper.this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

     */
/*
    public boolean updateData(String id, String eventTime, String serialNum, String appId, String userId, String location,
                              String route, String day, String logger, String eventNbr, String addtDesc, String addtNbr){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(KEY, id);
        contentValues.put(EVENT_TIME, eventTime);
        contentValues.put(SERIAL_NBR, serialNum);
        contentValues.put(APP_ID, appId);
        contentValues.put(USER_ID, userId);
        contentValues.put(LOC, location);
        contentValues.put(RTE, route);
        contentValues.put(DAY, day);
        contentValues.put(LOGGER, logger);
        contentValues.put(EVENT_NBR, eventNbr);
        contentValues.put(ADDT_DESC, addtDesc);
        contentValues.put(ADDT_NBR, addtNbr);

        db.update(TABLE_NAME, contentValues, "ID = ?", new String[] {KEY});
        return true;
    }

    public long deleteData(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        /*if (db.delete(TABLE_NAME, "ID = ?", new String[] {id}) > 0){
            Toast.makeText(this, "Data Deleted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Data not Deleted", Toast.LENGTH_LONG).show();
        }

        return db.delete(TABLE_NAME, "ID = " + id, null );// new String[] {Integer.toString(id)});
         */

}

