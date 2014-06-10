package com.eggwall.Touch.data;


import android.test.InstrumentationTestCase;

import java.util.List;

/**
 * Created by viki on 6/6/14.
 */
public class FriendTest extends InstrumentationTestCase {

    private final Friend first = new Friend("First", 2*30, 1);
    private final Friend second = new Friend("Second", 3*30, 2);

    public FriendTest() {
    }

    public void testSingleSerialization() {
        String serial = first.serialize();
        List<Friend> collection = Friend.deSerialize(serial);
        assertTrue(collection != null && collection.size() == 1);
        assertTrue(collection.get(0).equals(first));
    }

    public void testListSerialization() {
        String serial = first.serialize();
        List<Friend> collection = Friend.deSerialize(serial);
        assertTrue(collection != null && collection.size() == 1);
        assertTrue(collection.get(0).equals(first));
    }
}