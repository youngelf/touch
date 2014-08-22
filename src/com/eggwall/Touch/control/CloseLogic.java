package com.eggwall.Touch.control;

import com.eggwall.Touch.data.Friend;
import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.List;

/**
 * Decides which Friends are close to you and need
 * to be contacted.
 */
public class CloseLogic {
    /**
     * Find the best friends to contact right now.
     * @param list
     * @return
     */
    public static List<Friend> findBest(List<Friend> list) {
        ArrayList<Friend> best = new ArrayList<Friend>(5);
        DateTime now = DateTime.now();
        // Go through the list and find the people you haven't contacted
        // that you should have. Put them in the list.
        for (final Friend f : list) {
            // Haven't contacted this person for a while, add them.
            if (Days.daysBetween(f.LAST_CALLED, now).getDays() > f.CALL_FREQUENCY) {
                // Allow the user to deselect this person or turn down
                // the call frequency.
                best.add(f);
            }
            // TODO(viki) Need some notion of ranking to pick out the best.
            if (best.size() == 5) {
                break;
            }
        }
        return best;
    }
}
