package com.eggwall.Touch.data;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.List;

/**
 * Test out the friend class and its database interaction.
 */
public class FriendDbTest extends InstrumentationTestCase {

    private static final Friend first =
            new Friend(1, "First person", 60, 1);

    private FriendDbHelper mHelper;

    public FriendDbTest() {
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
            Log.d("FriendDbTest", "Got friend " + f);
        }
    }

    public void testUpdate() {
        List<Friend> v = mHelper.lookup();
        assertTrue(v != null && v.size() > 0);
        Friend one = v.get(0);
        Friend updatedOne = new Friend(one.ID, "Strange name", one.CALL_FREQUENCY, one.DAYS_TO_CALL);
        assertTrue(mHelper.update(updatedOne));
    }
}