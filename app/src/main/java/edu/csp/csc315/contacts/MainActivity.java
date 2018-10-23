package edu.csp.csc315.contacts;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ContactInfo> myContactInfos = new ArrayList<>();
    private DatabaseHelper dbHelper;
    public RecyclerView adapter;

    public final static int ADD_CONTACT = 998;
    public final static int UPDATE_CONTACT = 997;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper = new DatabaseHelper(this);
        myContactInfos = getAllFromDB();

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AddContact.class);
                startActivityForResult(intent, ADD_CONTACT);
            }
        });

        android.support.v7.widget.RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerView(this, myContactInfos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setOnClickListener(new OnItemClickAction());
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == ADD_CONTACT && resultCode == RESULT_OK){
            String contactName = data.getStringExtra("contact_name");
            String contactNumber = data.getStringExtra("contact_number");
            String contactAddress = data.getStringExtra("contact_address");
            String contactNotes = data.getStringExtra("contact_notes");

            dbHelper.add(contactName, contactNumber, contactAddress, contactNotes);
            myContactInfos.add(dbHelper.getContact());
            adapter.notifyDataSetChanged();
        }

        else if(requestCode == UPDATE_CONTACT && resultCode == RESULT_OK){
            String contactName = data.getStringExtra("contact_name");
            String contactNumber = data.getStringExtra("contact_number");
            String contactAddress = data.getStringExtra("contact_address");
            String contactNotes = data.getStringExtra("contact_notes");
            String contactPosition = data.getStringExtra("contact_position");
            int position = Integer.parseInt(contactPosition);
            int dbPosition = (position+1);

            myContactInfos.set(position, dbHelper.updateContact(contactName, contactNumber, contactAddress, contactNotes, dbPosition));
            adapter.notifyDataSetChanged();
        }
    }

    class OnItemClickAction implements android.support.v7.widget.RecyclerView.OnClickListener{
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, AddContact.class);
            startActivityForResult(intent, ADD_CONTACT);
        }
    }

    private ArrayList<ContactInfo> getAllFromDB(){
        ArrayList<ContactInfo> contactInfos = dbHelper.getAll();
        return contactInfos;
    }
}
