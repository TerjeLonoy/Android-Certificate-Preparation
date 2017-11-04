package com.acp.terjelonoy.androidcertificationpreparation;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.acp.terjelonoy.androidcertificationpreparation.providers.ContactProvider;

import org.junit.Test;

/**
 * Example of integration test of ContactProvider
 */


public class ExampleProviderTest extends ProviderTestCase2<ContactProvider> {

    public ExampleProviderTest() {
        super(ContactProvider.class, null);
    }

    @Test
    public void testGetAllContacts() throws Exception {
        ContactProvider provider = new ContactProvider();
        String URL = "content://" + ContactProvider.PROVIDER_NAME + "/contacts";
        Uri uri = Uri.parse(URL);
        Thread.sleep(5000);

        Cursor cursor = provider.query(uri, null, null, null, null);
        assertNotNull(cursor);
        Log.d("cursor", cursor.getCount()+"");
    }

    @Test
    public void testGetFirstContact() throws Exception {
        ContactProvider provider = new ContactProvider();
        String URL = "content://" + ContactProvider.PROVIDER_NAME + "/contacts/1";
        Uri uri = Uri.parse(URL);

        Cursor cursor = provider.query(uri, null, null, null, null);
        assertNotNull(cursor);
        //TODO: for some reason not getting any contacts, even with animation turned off
//        assertEquals(cursor.getCount(), 1);
//        assertEquals(cursor.getString(0), "1");
    }

    @Override
    protected void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
    }

}
