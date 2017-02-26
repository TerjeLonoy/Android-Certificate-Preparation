package com.acp.terjelonoy.androidcertificationpreparation.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {

    private static DatabaseHandler _instance;
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "contactsManager";
    public static final String TABLE_CONTACTS = "contacts";

    public static synchronized DatabaseHandler getInstance(Context context) {
        if (_instance == null) {
            _instance = new DatabaseHandler(context.getApplicationContext());
        }
        return _instance;
    }

    private DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_PH_NO = "phone_number";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH_NO + " TEXT" + ")";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(sqLiteDatabase);
    }

    public void addContact(Contact contact) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());
        db.insert(TABLE_CONTACTS, null, values);
        db.close();
    }

    public Contact getContact(int id) {

        Cursor cursor = getContactCursor(id);

        if (cursor != null) {
            new Contact(Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1), cursor.getString(2));
        }
        return null;
    }

    public Cursor getContactCursor(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
                        KEY_NAME, KEY_PH_NO }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList();
        Cursor cursor = getAllContactsCursor();

        if (cursor.moveToFirst()) {
            do {

                Contact contact = new Contact(
                    Integer.parseInt(cursor.getString(0)), // id
                    cursor.getString(1), // name
                    cursor.getString(2)  // phone_number
                );

                contactList.add(contact);

            } while (cursor.moveToNext());
        }

        return contactList;
    }

    public Cursor getAllContactsCursor() {
        String selectQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery(selectQuery, null);
    }

    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        cursor.close();

        return count;
    }

    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, contact.getName());
        values.put(KEY_PH_NO, contact.getPhoneNumber());

        return db.update(TABLE_CONTACTS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
    }

    public void deleteContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
                new String[] { String.valueOf(contact.getID()) });
        db.close();
    }

    public void deleteAllContacts() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, null, null);
        db.close();
    }

}
