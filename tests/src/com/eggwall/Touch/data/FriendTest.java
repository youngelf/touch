package com.eggwall.Touch.data;

import android.test.InstrumentationTestCase;
import android.util.Log;
import org.joda.time.Days;

/**
 * Test the {@link com.eggwall.Touch.data.Friend} class
 */
public class FriendTest extends InstrumentationTestCase {

    public FriendTest () {}

    @Override
    protected void setUp() throws Exception {
    }

    public void testDateSerialization() {
        Friend toTest = new Friend("Something", 5);
        // Now pretend to call this friend
        Friend justCalled = toTest.calledNow();
        String dateCalled = justCalled.lastCalled();
        Log.d("Viki", "Called at " + dateCalled);
        Friend newWithSameInfo = new Friend(justCalled.ID, justCalled.NAME,
                justCalled.CALL_FREQUENCY, dateCalled);

        // Make sure those two objects do NOT match.
        assertFalse(newWithSameInfo.equals(justCalled));

        // However, they are within a day of each other
        assertTrue(Days.daysBetween(newWithSameInfo.LAST_CALLED, justCalled.LAST_CALLED).getDays() < 1);
    }
}
