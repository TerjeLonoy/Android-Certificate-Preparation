package com.acp.terjelonoy.androidcertificationpreparation.test;
import android.database.Cursor;
import android.net.Uri;
import android.support.test.InstrumentationRegistry;
import android.test.ProviderTestCase2;
import android.util.Log;

import com.acp.terjelonoy.androidcertificationpreparation.providers.ContactProvider;

import org.junit.Test;

public class ExampleProviderTest extends ProviderTestCase2{
    public ExampleProviderTest(){
        super(ContactProvider.class, ContactProvider.PROVIDER_NAME);
    }

    @Test
    public void testQuery() {
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
