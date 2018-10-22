package com.example.nguyen__le.googlemaps;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Contact> arrContacts = new ArrayList<>();
    public RecyclerViewAdapter adapter;
    private DatabaseHelper dbHelper;

    public final static int NEW_CONTACT = 998;
    public final static int UPDATE_CONTACT = 997;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        listContacts = getAllFromDB();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent, NEW_CONTACT);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(this, arrContacts);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(new OnItemClickAction());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){

        if (requestCode == NEW_CONTACT && resultCode == RESULT_OK){
            String contactName = data.getStringExtra("contact_name");
            String contactNumber = data.getStringExtra("contact_number");
            String contactAddress = data.getStringExtra("contact_address");
            String contactNotes = data.getStringExtra("contact_notes");

            dbHelper.add(contactName, contactNumber, contactAddress, contactNotes);
            arrContacts.add(dbHelper.getContact());
            adapter.notifyDataSetChanged();
        }

        else if(requestCode == UPDATE_CONTACT && resultCode == RESULT_OK){
            String contactName = data.getStringExtra("contact_name");
            String contactNumber = data.getStringExtra("contact_umber");
            String contactAddress = data.getStringExtra("contact_address");
            String contactNotes = data.getStringExtra("contact_notes");
            String contactPosition = data.getStringExtra("contact_position");
            int position = Integer.parseInt(contactPosition);
            int dbPosition = (position+1);

            arrContacts.set(position, dbHelper.updateContact(contactName, contactNumber, contactAddress, contactNotes, dbPosition));
            adapter.notifyDataSetChanged();
        }
    }

    class OnItemClickAction implements RecyclerView.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddContact.class);
            startActivityForResult(intent, NEW_CONTACT);
        }
    }

    private ArrayList<Contact> getAllFromDB(){
        ArrayList<Contact> contacts = dbHelper.getAll();
        return contacts;
    }
}
