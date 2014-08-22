package com.eggwall.Touch.data;

import android.test.InstrumentationTestCase;
import android.util.Log;

import java.util.List;

/**
 * Test out the friend class and its database interaction.
 */
public class FriendDbTest extends InstrumentationTestCase {

    private static final Friend first = new Friend("A person", 60);
    private FriendDbHelper mHelper;

    /** Name of the test database on disk */
    public static final String TEST_DB_NAME = "TESTfriends.sqlite";

    public FriendDbTest() {
    }

    @Override
    protected void setUp() throws Exception {
        mHelper = new FriendDbHelper(getInstrumentation().getTargetContext(), TEST_DB_NAME);
        List<Friend> v = mHelper.lookup();
        // Even if there is anything, delete it to reset state for the next tests
        for (final Friend f : v) {
            Log.d("FriendDbTest", "Got friend " + f);
            mHelper.deleteFriend(f);
        }
        super.setUp();
    }

    public void testStartingHasEmptyDatabase() {
        // The default test database is totally empty.
        // Store something in the database and expect it is there.
        List<Friend> v = mHelper.lookup();
        assertTrue(v == null || v.size() == 0);
    }

    public void testInsertLookupAndDelete() {
        // Store something in the database and expect it is there.
        mHelper.insertFriend(first);
        List<Friend> v = mHelper.lookup();
        // I have just one friend at this point.
        assertTrue(v != null && v.size() == 1);
        for (final Friend f : v) {
            Log.d("FriendDbTest", "Got friend " + f);
        }
        // And remove this friend. You cannot delete the original friend because you don't have
        // the ID required to delete the friend.
        assertTrue(mHelper.deleteFriend(v.get(0)));
    }

    public void testUpdate() {
        // Put a single user in the database.
        mHelper.insertFriend(first);
        List<Friend> v = mHelper.lookup();
        assertTrue(v != null && v.size() > 0);
        Friend one = v.get(0);
        Friend updatedOne = one.updateFrequency(55);
        assertTrue(mHelper.update(updatedOne));
        // Now we are allowed to delete this user.
        assertTrue(mHelper.deleteFriend(updatedOne));
    }


    // Adding a person

}