package com.acp.terjelonoy.androidcertificationpreparation.activities;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.acp.terjelonoy.androidcertificationpreparation.R;
import com.acp.terjelonoy.androidcertificationpreparation.database.Contact;
import com.acp.terjelonoy.androidcertificationpreparation.database.DatabaseHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class StorageActivity extends AppCompatActivity {

    private final String ACTIVITY_ID = "STORAGE_ACTIVITY";
    private final String REMEMBER = "REMEMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);

        Button populateBtn = (Button) findViewById(R.id.populateBtn);
        populateBtn.setOnClickListener(view -> populateDatabase());
        Button emptyBtn = (Button) findViewById(R.id.emptyBtn);
        emptyBtn.setOnClickListener(view -> emptyDatabase());

        CheckBox checkBox = (CheckBox) findViewById(R.id.checkBox);
        checkBox.setChecked(getValue());

        readRawFile();

        checkBox.setOnCheckedChangeListener((compoundButton, b) -> commitChange(b));
    }

    private void readRawFile() {
        try {
            Resources res = getResources();
            InputStream is = res.openRawResource(R.raw.emergency);
            byte[] b = new byte[is.available()];
            is.read(b);
            Log.d("readRawFile", new String(b));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void commitChange(boolean checked) {
        SharedPreferences preferences = getSharedPreferences(ACTIVITY_ID, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(REMEMBER, checked);
        editor.commit();
    }

    private boolean getValue() {
        SharedPreferences preferences = getSharedPreferences(ACTIVITY_ID, MODE_PRIVATE);
        return preferences.getBoolean(REMEMBER, false);
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


