package com.example.librarydb;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LibDatabase {

    DBHelper myDb;
    Context context;
    EditText editEvent, serialNbr, appId, userId, loc, route, day, logger, eventNbr, addtDesc, addtNbr;
    Button btnAddData;

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
}
