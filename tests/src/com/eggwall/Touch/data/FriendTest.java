package com.eggwall.Touch.data;


import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.List;

/**
 * Created by viki on 6/6/14.
 */
public class FriendTest extends InstrumentationTestCase {

    private static final Friend first =
            new Friend(1, "First person", 60, 1);

    private FriendDbHelper mHelper;
    public FriendTest() {
    }

    @Override
    protected void setUp() throws Exception {
        mHelper = new FriendDbHelper(getInstrumentation().getTargetContext());
        super.setUp();
    }

    public void testLookup() {
        // Store something in the database and expect it is there.
        List<Friend> v = mHelper.lookup();
        // Anything non-null is good.
        assertTrue(v != null && v.size() > 0);
        for (final Friend f : v) {
            Log.d("FriendTest", "Got friend " + f);
        }
    }


}