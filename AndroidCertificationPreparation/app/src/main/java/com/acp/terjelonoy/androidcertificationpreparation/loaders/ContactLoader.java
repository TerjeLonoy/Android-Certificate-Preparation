package com.acp.terjelonoy.androidcertificationpreparation.loaders;

import android.Manifest;
import android.app.ListFragment;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.SimpleCursorAdapter;

import com.acp.terjelonoy.androidcertificationpreparation.R;

import static android.support.v4.content.ContextCompat.checkSelfPermission;

/**
 * Created by terjelonoy on 03/02/2017.
 */

public class ContactLoader extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{
    private static final int CONTACT_LOADER = 991;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    static final String[] CONTACTS_DATA = new String[] {
            ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME,
            ContactsContract.CommonDataKinds.Phone.NUMBER
    };

    SimpleCursorAdapter adapter;
    String filter;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Show that no phonenumbers are present
        setEmptyText(getString(R.string.no_phonenumbers));
        showContacts();
    }

    private void showContacts() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(getContext(), Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // Create empty adapter
            adapter = new SimpleCursorAdapter(getActivity(),
                    R.layout.listitem_contacts,
                    null,
                    new String[] {ContactsContract.Contacts.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER},
                    new int[] {R.id.contact_name, R.id.contact_phone});

            // Set adapter
            setListAdapter(adapter);

            // Start or reconnect to loader
            getLoaderManager().initLoader(CONTACT_LOADER, null, this);
        }
    }

    @Override
    public Loader onCreateLoader(int i, Bundle bundle) {
        Uri contactUri;

        // Get URI for Contacts
        if (filter != null) {
            contactUri = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, Uri.encode(filter));
        } else {
            contactUri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        }

        // Select all contacts that has a displayname that is not empty, and has a phonenumber
        String select = "((" + ContactsContract.Contacts.DISPLAY_NAME + " NOTNULL) AND ("
                + ContactsContract.Contacts.HAS_PHONE_NUMBER + "=1) AND ("
                + ContactsContract.Contacts.DISPLAY_NAME + " != '' ))";

        return new CursorLoader(getActivity(), contactUri,
                CONTACTS_DATA, select, null,
                ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
    }

    @Override
    public void onLoaderReset(Loader loader) {
        // Make sure we do not reuse a completed cursor
        adapter.swapCursor(null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }
}
