package edu.csp.csc315.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    final private static String DB_NAME = "contact_db";
    final private static String TABLE_CONTACT = "contact_tbl";
    final private static String CONTACT_NAME = "name";
    final private static String CONTACT_PHONE = "phone";
    final private static String CONTACT_ADDRESS = "address";
    final private static String CONTACT_NOTES = "notes";

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, 8);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_CONTACT + "(" + CONTACT_NAME + " TEXT,"
        + CONTACT_PHONE + " TEXT," + CONTACT_ADDRESS + " TEXT," + CONTACT_NOTES + " TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "CREATE TABLE " + TABLE_CONTACT + "(" + CONTACT_NAME + " TEXT,"
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
        db.insert(TABLE_CONTACT, null, values);
    }

    public ContactInfo getContact(){
        SQLiteDatabase db = this.getReadableDatabase();
        long rowCount = DatabaseUtils.queryNumEntries(db, TABLE_CONTACT);
        String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE rowid = " + rowCount;


        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
        String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
        String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
        String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

        ContactInfo contactInfo = new ContactInfo(contactName, contactNumber, contactAddress, contactNotes);
        return contactInfo;
    }

    public void deleteContact(int contactPosition){
        SQLiteDatabase db = this.getReadableDatabase();
        db.delete(TABLE_CONTACT, "rowid" + " = ?", new String[] {String.valueOf(contactPosition)});
        db.close();
    }

    public ArrayList<ContactInfo> getAll(){
        ArrayList<ContactInfo> contactInfoList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        long rowCount = DatabaseUtils.queryNumEntries(db, TABLE_CONTACT);

        for(int j=1; j <= rowCount; j++) {
            String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE rowid = " + Long.valueOf(j);
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor != null) {
                cursor.moveToFirst();
            }
                String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
                String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
                String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
                String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

                ContactInfo contactInfo = new ContactInfo(contactName, contactNumber, contactAddress, contactNotes);
                contactInfoList.add(contactInfo);
            }
            return contactInfoList;
    }
    public ContactInfo updateContact(String name, String phone, String address, String notes, int position){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CONTACT_NAME, name);
        values.put(CONTACT_PHONE, phone);
        values.put(CONTACT_ADDRESS, address);
        values.put(CONTACT_NOTES, notes);
        db.update(TABLE_CONTACT, values, "rowid" + " = ?", new String[] {String.valueOf(position)});

        String selectQuery = "SELECT * FROM " + TABLE_CONTACT + " WHERE rowid = " + Long.valueOf(position);
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        String contactName = cursor.getString(cursor.getColumnIndex(CONTACT_NAME));
        String contactNumber = cursor.getString(cursor.getColumnIndex(CONTACT_PHONE));
        String contactAddress = cursor.getString(cursor.getColumnIndex(CONTACT_ADDRESS));
        String contactNotes = cursor.getString(cursor.getColumnIndex(CONTACT_NOTES));

        ContactInfo contactInfo = new ContactInfo(contactName, contactNumber, contactAddress, contactNotes);
        return contactInfo;
    }
}
