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


public class ExampleProviderTest extends ProviderTestCase2 {

    public ExampleProviderTest(Class providerClass, String providerAuthority) {
        super(providerClass, providerAuthority);
    }

    @Test
    public void testQuery() throws Exception {
        ContactProvider provider = new ContactProvider();
        Uri uri = ContactProvider.CONTENT_URI;
        Cursor cursor = provider.query(uri, null, null, null, null);
        Log.d("cursor", cursor.getCount()+"");
        assertNotNull(cursor);
    }

    @Override
    protected void setUp() throws Exception {
        setContext(InstrumentationRegistry.getTargetContext());
        super.setUp();
    }

}
