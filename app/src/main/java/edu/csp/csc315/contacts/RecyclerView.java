package edu.csp.csc315.contacts;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.ArrayList;

public class RecyclerView extends android.support.v7.widget.RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<ContactInfo> myContactInfos;
    private Context myContext;

    public RecyclerView(Context context, ArrayList<ContactInfo> contactInfos){

        this.myContactInfos = contactInfos;
        this.myContext = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position){

        holder.contactName.setText(myContactInfos.get(position).getName());
        holder.contactNumber.setText(myContactInfos.get(position).getPhoneNumber());
        holder.parentLayout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                Intent intent = new Intent(myContext, UpdateContact.class);
                intent.putExtra("contact_name", myContactInfos.get(position).getName());
                intent.putExtra("contact_number", myContactInfos.get(position).getPhoneNumber());
                intent.putExtra("contact_address", myContactInfos.get(position).getAddress());
                intent.putExtra("contact_notes", myContactInfos.get(position).getNotes());
                intent.putExtra("contact_position", String.valueOf(position));
                ((Activity) myContext).startActivityForResult(intent, MainActivity.UPDATE_CONTACT);
            }
        });
    }

    @Override
    public int getItemCount(){
        return myContactInfos.size();
    }

    public class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

        TextView contactName;
        TextView contactNumber;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            contactName = itemView.findViewById(R.id.addContactName);
            contactNumber = itemView.findViewById(R.id.designNumberText);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }

    public void removeAt(int position){
        myContactInfos.remove(position);
    }
}
