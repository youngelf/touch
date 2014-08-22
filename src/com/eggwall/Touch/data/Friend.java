package com.eggwall.Touch.data;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A description of a person you wish to keep in touch with.
 * This class is immutable.
 */
public class Friend {
    public final int ID;
    /** Name of the person to call. */
    public final String NAME;
    /** How frequently to call this person */
    public final int CALL_FREQUENCY;
    /** When was the last call made to this person. Never null. */
    public final DateTime LAST_CALLED;

    private static final DateTimeFormatter FMT =
            DateTimeFormat.forPattern("HH:mm:ss, dddd, DDDD, MMMM, yyyy, ZZZ");

    /** Create a database without assigning a database ID yet. */
    public Friend(String name, int frequency) {
        ID = -1;
        NAME = name;
        CALL_FREQUENCY = frequency;
        // Assume the person was never called.
        LAST_CALLED = new DateTime(1970, 1, 1, 1, 1);
    }

    /** The database creates a friend by assigning the ID. */
    Friend(int id, String name, int frequency, String lastCalled) {
        ID = id;
        NAME = name;
        CALL_FREQUENCY = frequency;
        LAST_CALLED = FMT.parseDateTime(lastCalled);
    }

    /** The database creates a friend by assigning the ID. */
    private Friend(int id, String name, int frequency, DateTime lastCalled) {
        ID = id;
        NAME = name;
        CALL_FREQUENCY = frequency;
        // Assume the person was never called.
        LAST_CALLED = lastCalled;
    }

    public Friend updateFrequency(int newFrequency) {
        return new Friend(ID, NAME, newFrequency, LAST_CALLED);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Friend)) return false;

        Friend f = (Friend)o;
        return (f.NAME.equals(NAME) && f.CALL_FREQUENCY == CALL_FREQUENCY
                && f.LAST_CALLED.equals(LAST_CALLED));
    }

    @Override
    public String toString() {
        return "Friend(" + ID + "): " + NAME
                + ", freq=" + CALL_FREQUENCY
                + ", days=" + FMT.print(LAST_CALLED);
    }

    /** The last time this person was called */
    public String lastCalled() {
        return FMT.print(LAST_CALLED);
    }

    /** Return a new friend, who was just called now */
    public Friend calledNow() {
        return new Friend(ID, NAME, CALL_FREQUENCY, DateTime.now());
    }
}
