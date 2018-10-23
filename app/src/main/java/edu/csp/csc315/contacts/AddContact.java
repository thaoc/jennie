package edu.csp.csc315.contacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {

    Button cancelButton;
    Button saveButton;

    EditText addNameText;
    EditText addPhoneText;
    EditText addAddressText;
    EditText addNoteText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_contact);

        cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish_activity();
            }
        });
    }

    private void finish_activity(){
        addNameText = (EditText) findViewById(R.id.addNameText);
        addPhoneText = (EditText) findViewById(R.id.addPhoneText);
        addAddressText = (EditText) findViewById(R.id.addAddressText);
        addNoteText = (EditText) findViewById(R.id.addNoteText);
        Intent data = new Intent();
        data.putExtra("contact_name", addNameText.getText().toString());
        data.putExtra("contact_number", addPhoneText.getText().toString());
        data.putExtra("contact_address", addAddressText.getText().toString());
        data.putExtra("contact_notes", addNoteText.getText().toString());
        setResult(RESULT_OK, data);
        super.finish();
    }
}
