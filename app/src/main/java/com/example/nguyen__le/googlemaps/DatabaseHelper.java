package com.example.nguyen__le.googlemaps;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final private static String DB_NAME = "contact_db";

    final private static String TBL_CONTACT = "contact_tbl";

    final private static String CONTACT_NAME = "name";
    final private static String CONTACT_PHONE = "phone";
    final private static String CONTACT_ADDRESS = "address";
    final private static String CONTACT_NOTES = "notes";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TBL_CONTACT + "(" + CONTACT_NAME + " TEXT,"
                + CONTACT_PHONE + " TEXT," + CONTACT_ADDRESS + " TEXT," + CONTACT_NOTES + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "CREATE TABLE " + TBL_CONTACT + "(" + CONTACT_NAME + " TEXT,"
                + CONTACT_PHONE + " TEXT," + CONTACT_ADDRESS + " TEXT," + CONTACT_NOTES + " TEXT)";
        db.execSQL(sql);
    }

    public void add(String name, String phone, String address, String notes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, name);
        values.put(CONTACT_PHONE, phone);
        values.put(CONTACT_ADDRESS, address);
        values.put(CONTACT_NOTES, notes);
        db.insert(TBL_CONTACT, null, values);
    }

    public Contact getContact(){
        SQLiteDatabase db = this.getReadableDatabase();
        long rowCount = DatabaseUtils.queryNumEntries(db, TBL_CONTACT);
        String selectQuery = "SELECT * FROM " + TBL_CONTACT + " WHERE rowid = " + rowCount;


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
        String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
        String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
        String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

        Contact contact = new Contact(contactName, contactNumber, contactAddress, contactNotes);
        return contact;
    }
    public void deleteContact(int contactPosition){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TBL_CONTACT, "rowid" + " = ?", new String[] {String.valueOf(contactPosition)});
        db.close();
    }

    public ArrayList<Contact> getAll(){
        ArrayList<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        long rowCount = DatabaseUtils.queryNumEntries(db, TBL_CONTACT);

        for(int j=1; j <= rowCount; j++) {
            String selectQuery = "SELECT * FROM " + TBL_CONTACT + " WHERE rowid = " + Long.valueOf(j);
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
            String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
            String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
            String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
            String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

            Contact contact = new Contact(contactName, contactNumber, contactAddress, contactNotes);
            contactList.add(contact);
        }
        return contactList;
    }
    public Contact updateContact(String name, String phone, String address, String notes, int position){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, name);
        values.put(CONTACT_PHONE, phone);
        values.put(CONTACT_ADDRESS, address);
        values.put(CONTACT_NOTES, notes);
        db.update(TBL_CONTACT, values, "rowid" + " = ?", new String[] {String.valueOf(position)});

        String selectQuery = "SELECT * FROM " + TBL_CONTACT + " WHERE rowid = " + Long.valueOf(position);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
        String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
        String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
        String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

        Contact contact = new Contact(contactName, contactNumber, contactAddress, contactNotes);
        return contact;
    }
}

