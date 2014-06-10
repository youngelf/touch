package com.eggwall.Touch.data;

import java.util.Collection;

/**
 * A description of a person you wish to keep in touch with.
 * This class is immutable.
 */
public class Friend {
    private static final String SEPARATOR = "#$#";
    private static final String FIELD_DELIMITER = "#:#";

    public final int ID;
    public final String NAME;
    public final int CALL_FREQUENCY;
    public final int DAYS_TO_CALL;

    public Friend(int id, String name, int frequency, int daysToCall) {
        ID = id;
        NAME = name;
        CALL_FREQUENCY = frequency;
        DAYS_TO_CALL = daysToCall;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Friend)) return false;

        Friend f = (Friend)o;
        return (f.NAME.equals(NAME) && f.CALL_FREQUENCY == CALL_FREQUENCY
                && f.DAYS_TO_CALL == DAYS_TO_CALL);
    }

    @Override
    public String toString() {
        return "Friend(" + ID + "): " + NAME + ", freq=" + CALL_FREQUENCY + ", days=" + DAYS_TO_CALL;
    }
}
