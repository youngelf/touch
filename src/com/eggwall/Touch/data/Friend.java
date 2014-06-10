package com.eggwall.Touch.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * A description of a person you wish to keep in touch with.
 * This class is immutable.
 */
public class Friend {
    private static final String SEPARATOR = "#$#";
    private static final String FIELD_DELIMITER = "#:#";

    public final String NAME;
    public final int CALL_FREQUENCY;
    public final int DAYS_TO_CALL;

    public Friend(String name, int frequency, int daysToCall) {
        NAME = name;
        CALL_FREQUENCY = frequency;
        DAYS_TO_CALL = daysToCall;
    }

    /**
     * Return a serialized representation of a list of friends.
     * @param in
     * @return
     */
    public static String serialize(Collection<Friend> in) {
        StringBuilder b = new StringBuilder();
        for (final Friend i : in) {
            b.append(i.serialize());
            b.append(SEPARATOR);
        }
        return b.toString();
    }

    String serialize() {
        return NAME
                + FIELD_DELIMITER + CALL_FREQUENCY
                + FIELD_DELIMITER + DAYS_TO_CALL;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof Friend)) return false;

        Friend f = (Friend)o;
        return (f.NAME.equals(NAME) && f.CALL_FREQUENCY == CALL_FREQUENCY
                && f.DAYS_TO_CALL == DAYS_TO_CALL);
    }

    /**
     * Read in a string containing a collection of friends and return that.
     * The string should have been constructed with {@link #serialize()}
     * @param in
     * @return
     */
    public static List<Friend> deSerialize(String in) {
        if (in == null) {
            return Collections.emptyList();
        }
        String[] pieces = in.split(SEPARATOR);
        if (pieces.length <= 0) {
            return Collections.emptyList();
        }
        ArrayList<Friend> out = new ArrayList<Friend>(pieces.length);
        for (final String i : pieces){
            String[] fields = i.split(FIELD_DELIMITER);
            if (fields.length == 3) {
                Friend f = new Friend(fields[0], Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
                out.add(f);
            }
        }
        return out;
    }
}
