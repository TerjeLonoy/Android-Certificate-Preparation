package com.acp.terjelonoy.androidcertificationpreparation.providers;
import com.acp.terjelonoy.androidcertificationpreparation.database.DatabaseHandler;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;


public class ContactProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.acp.terjelonoy.androidcertificationpreparation.providers.ContactProvider";

    static final int CONTACTS = 1;
    static final int CONTACT_ID = 2;

    static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "contacts", CONTACTS);
        uriMatcher.addURI(PROVIDER_NAME, "contacts/#", CONTACT_ID);
    }


    @Override
    public boolean onCreate() {
        // true if the provider was successfully loaded, false otherwise
        Context context = getContext();
        DatabaseHandler db = DatabaseHandler.getInstance(context);

        return (db.getReadableDatabase() != null);
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d("URI", uri.toString());
        DatabaseHandler db = DatabaseHandler.getInstance(getContext());

        System.out.println("Matcher: " + String.valueOf(uriMatcher.match(uri)));

        if (uriMatcher.match(uri) == CONTACT_ID) {
            int contactId = Integer.parseInt(uri.getLastPathSegment());
            return db.getContactCursor(contactId);
        }

        if (uriMatcher.match(uri) == CONTACTS) {
            return db.getAllContactsCursor();
        }

        return null;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        throw new UnsupportedOperationException("getType is not supported");
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        throw new UnsupportedOperationException("Insert is not supported");
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        throw new UnsupportedOperationException("Delete is not supported");
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        throw new UnsupportedOperationException("Update is not supported");
    }
}
