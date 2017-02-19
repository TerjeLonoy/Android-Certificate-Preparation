package com.acp.terjelonoy.androidcertificationpreparation.activities;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.acp.terjelonoy.androidcertificationpreparation.R;
import com.acp.terjelonoy.androidcertificationpreparation.database.Contact;
import com.acp.terjelonoy.androidcertificationpreparation.database.DatabaseHandler;

import java.util.List;

public class DatabaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);
        populateDatabase();
        emptyDatabase();
    }

    private void emptyDatabase() {
        DatabaseHandler db = DatabaseHandler.getInstance(this);
        db.deleteAllContacts();
        Log.d("empty", String.valueOf(db.getContactsCount()));
    }

    private void populateDatabase() {

        DatabaseHandler db = DatabaseHandler.getInstance(this);

        for (int i = 1; i < 5; i++) {
            db.addContact(new Contact("Person no" + i, "470000000" + i));
        }

        List<Contact> contacts = db.getAllContacts();

        for (Contact contact : contacts) {
            String log = "id: " + contact.getID() + ", name: " + contact.getName() + ", phone_number: " + contact.getPhoneNumber();
            Log.d("Contact:", log);
        }
    }

}


