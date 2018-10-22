package com.example.nguyen__le.googlemaps;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateContact extends AppCompatActivity {

    Button updateButton;
    Button callButton;

    EditText updateName;
    EditText updatePhone;
    EditText updateAddress;
    EditText updateNotes;

    TextView updatePosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        getIncomingIntent();

        updateButton = (Button) findViewById(R.id.saveButton);
        updateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish_activity();
            }
        });

        callButton = (Button) findViewById(R.id.callButton);
        callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(getPhoneNumber()));
                startActivity(intent);
            }
        });
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("contact_name") && getIntent().hasExtra("contact_number")
                && getIntent().hasExtra("contact_address") && getIntent().hasExtra("contact_notes")
                && getIntent().hasExtra("contact_position")){

            String contactName = getIntent().getStringExtra("contact_name");
            String contactNumber = getIntent().getStringExtra("contact_number");
            String contactAddress = getIntent().getStringExtra("contact_address");
            String contactNotes = getIntent().getStringExtra("contact_notes");
            String contactPosition = getIntent().getStringExtra("contact_position");
            setContact(contactName, contactNumber, contactAddress, contactNotes, contactPosition);
        }
    }
    private void setContact(String contactName, String contactNumber, String contactAddress,
                            String contactNotes, String contactPosition){
        updateName = (EditText) findViewById(R.id.updateNameText);
        updateName.setText(contactName);

        updatePhone = (EditText)findViewById(R.id.updatePhoneText);
        updatePhone.setText(contactNumber);

        updateAddress = (EditText) findViewById(R.id.updateAddressText);
        updateAddress.setText(contactAddress);

        updateNotes = (EditText)findViewById(R.id.updateNotesText);
        updateNotes.setText(contactNotes);

        updatePosition = (EditText)findViewById(R.id.updatePosition);
        updatePosition.setText(contactPosition);

    }

    private void finish_activity(){
        updateName = (EditText) findViewById(R.id.updateNameText);
        updatePhone = (EditText)findViewById(R.id.updatePhoneText);
        updateAddress = (EditText) findViewById(R.id.updateAddressText);
        updateNotes = (EditText)findViewById(R.id.updateNotesText);
        updatePosition = (TextView) findViewById(R.id.updateRow);

        Intent data = new Intent(UpdateContact.this, MainActivity.class);
        data.putExtra("contact_name", updateName.getText().toString());
        data.putExtra("contact_number", updatePhone.getText().toString());
        data.putExtra("contact_address", updateAddress.getText().toString());
        data.putExtra("contact_notes", updateNotes.getText().toString());
        data.putExtra("contact_position", updatePosition.getText().toString());
        setResult(RESULT_OK, data);
        super.finish();
    }
    private String getPhoneNumber(){
        String phone;
        if(getIntent().hasExtra("contact_number")){
            phone = "tel:" + getIntent().getStringExtra("contact_number");
        }
        else{
            phone = "tel:6512323213";
        }
        return phone;
    }
}
