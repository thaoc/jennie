package com.example.nguyen__le.googlemaps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_contact);

        Button cancel = findViewById(R.id.add_button_cancel);
        cancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button save = findViewById(R.id.add_button_save);
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish_activity();
            }
        });
    }

    private void finish_activity(){
        EditText name = findViewById(R.id.addname);
        EditText phone = findViewById(R.id.addPhone);
        EditText address = findViewById(R.id.addAddress);
        EditText notes = findViewById(R.id.addNote);
        Intent data = new Intent();
        data.putExtra("contact_name", name.getText().toString());
        data.putExtra("contact_number", phone.getText().toString());
        data.putExtra("contact_address", address.getText().toString());
        data.putExtra("contact_notes", notes.getText().toString());
        setResult(RESULT_OK, data);
        super.finish();
    }
}
