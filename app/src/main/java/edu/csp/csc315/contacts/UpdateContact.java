package edu.csp.csc315.contacts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContact extends AppCompatActivity {

    Button updateButton;
    Button callButton;

    EditText updateNameText;
    EditText updatePhoneText;
    EditText updateAddressText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_contact);
        getIncomingIntent();

        updateButton = (Button) findViewById(R.id.updateButton);
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
                && getIntent().hasExtra("contact_address")){

            String contactName = getIntent().getStringExtra("contact_name");
            String contactNumber = getIntent().getStringExtra("contact_number");
            String contactAddress = getIntent().getStringExtra("contact_address");
            setContact(contactName, contactNumber, contactAddress);
        }
    }

    private void setContact(String contactName, String contactNumber, String contactAddress){
        updateNameText = (EditText) findViewById(R.id.updateNameText);
        updateNameText.setText(contactName);

        updatePhoneText = (EditText) findViewById(R.id.updatePhoneText);
        updatePhoneText.setText(contactNumber);

        updateAddressText = (EditText) findViewById(R.id.updateAddressText);
        updateAddressText.setText(contactAddress);
    }

    private void finish_activity(){
        updateNameText = (EditText) findViewById(R.id.updateNameText);
        updatePhoneText = (EditText) findViewById(R.id.updatePhoneText);
        updateAddressText = (EditText) findViewById(R.id.updateAddressText);


        Intent data = new Intent(UpdateContact.this, MainActivity.class);
        data.putExtra("contact_name", updateNameText.getText().toString());
        data.putExtra("contact_number", updatePhoneText.getText().toString());
        data.putExtra("contact_address", updateAddressText.getText().toString());
        setResult(RESULT_OK, data);
        super.finish();
    }

    private String getPhoneNumber(){
        String phone;
        if(getIntent().hasExtra("contact_number")){
            phone = "tel:" + getIntent().getStringExtra("contact_number");
        }
        else{
            phone = "tel:6512433242";
        }
        return phone;
    }

}