package com.example.nguyen__le.googlemaps;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> arrNames;
    private ArrayList<String> arrNumbers;
    private ArrayList<String> arrAddresses;
    private ArrayList<String> arrNotes;
    private Context arrContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> contactName, ArrayList<String> contactNumber, ArrayList<String> address, ArrayList<String> notes) {

        arrNames = contactName;
        arrNumbers = contactNumber;
        arrAddresses = address;
        arrNotes = notes;
        arrContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {

        holder.contactName.setText(arrNames.get(position));
        holder.contactNumber.setText(arrNumbers.get(position));
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(arrContext, UpdateContact.class);
                intent.putExtra("contact_name", arrNames.get(position));
                intent.putExtra("contact_number", arrNumbers.get(position));
                intent.putExtra("contact_address", arrAddresses.get(position));
                intent.putExtra("contact_notes", arrNotes.get(position));
                ((Activity) arrContext).startActivityForResult(intent, MainActivity.UPDATE_CONTACT);
                removeAt(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

    TextView contactName;
    TextView contactNumber;
    RelativeLayout parentLayout;

    public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.add_contact_name);
            contactNumber = itemView.findViewById(R.id.contact_number);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public void removeAt(int position){
        arrNames.remove(position);
        arrAddresses.remove(position);
        arrNumbers.remove(position);
        arrNotes.remove(position);
    }
}
