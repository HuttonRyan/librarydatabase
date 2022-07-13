package com.example.librarydb;

import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class LibDatabase {

    DBHelper myDb;
    Context context;
    EditText editEvent, serialNbr, appId, userId, loc, route, day, logger, eventNbr, addtDesc, addtNbr;
    Button btnAddData, btnViewAll, btnViewUpdate, btnDelete;

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted = myDb.insertData(editEvent.getText().toString(),serialNbr.getText().toString(),appId.getText().toString(),
                        userId.getText().toString(),loc.getText().toString(),route.getText().toString(),
                        day.getText().toString(),logger.getText().toString(),eventNbr.getText().toString(),
                        addtDesc.getText().toString(),addtNbr.getText().toString());

                if (isInserted){
                    Toast.makeText(context, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll(){
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();

                if(res.getCount() == 0){
                    showMessage("Error", "Nothing found");
                    return;
                }
                else{
                    StringBuffer buffer = new StringBuffer();
                    // EVENT_TIME, SERIAL_NBR, APP_ID, USER_ID, LOC, RTE, DAY, LOGGER, EVENT_NBR, ADDT_DESC, ADDT_NBR
                    while (res.moveToNext()){
                        buffer.append("EventTime :" + res.getString(0) + "\n");
                        buffer.append("SerialNbr :" + res.getString(1) + "\n");
                        buffer.append("AppId :" + res.getString(2) + "\n");
                        buffer.append("UserId :" + res.getString(3) + "\n");
                        buffer.append("Loc :" + res.getString(4) + "\n");
                        buffer.append("Rte :" + res.getString(5) + "\n");
                        buffer.append("Day :" + res.getString(6) + "\n");
                        buffer.append("Logger :" + res.getString(7) + "\n");
                        buffer.append("EventNbr :" + res.getString(8) + "\n");
                        buffer.append("AddtDesc :" + res.getString(9) + "\n");
                        buffer.append("AddtNbr :" + res.getString(10) + "\n\n");
                    }

                    showMessage("Data", buffer.toString());
                }
            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void UpdateData(){
        btnViewUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdated = myDb.updateData(editEvent.getText().toString(),serialNbr.getText().toString(),appId.getText().toString(),
                        userId.getText().toString(),loc.getText().toString(),route.getText().toString(),
                        day.getText().toString(),logger.getText().toString(),eventNbr.getText().toString(),
                        addtDesc.getText().toString(),addtNbr.getText().toString());

                if (isUpdated){
                    Toast.makeText(context, "Data Updated", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Data not Updated", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void DeleteData(){
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editEvent.getText().toString());

                if (deletedRows > 0){
                    Toast.makeText(context, "Data Deleted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "Data not Deleted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
