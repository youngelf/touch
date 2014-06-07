package com.eggwall.Touch.data;

/**
 * A description of a person you wish to keep in touch with.
 * This class is immutable.
 */
public class Friend {
    public final String NAME;
    public final int CALL_FREQUENCY;
    public final int DAYS_TO_CALL;

    public Friend(String name, int frequency, int daysToCall) {
        NAME = name;
        CALL_FREQUENCY = frequency;
        DAYS_TO_CALL = daysToCall;
    }
}
