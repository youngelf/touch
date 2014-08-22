package com.eggwall.Touch.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import com.eggwall.Touch.control.CloseLogic;
import com.eggwall.Touch.R;
import com.eggwall.Touch.data.Friend;
import com.eggwall.Touch.data.FriendDbHelper;

import java.util.List;

/*
 * TODO(viki) Enter names somehow as a first cut.
   TODO(viki) Then test out calling a person. Mark the person as called.
   TODO(viki) Allow emailing someone a quick response with a trigger text.


 * TODO(viki) Show a list of people from the contact list
 * TODO(viki) Allow selecting people from the list and remember them.
 * TODO(viki) Prompt to allow calling someone
 * TODO(viki) Prompt to allow mailing someone
 * TODO(viki) Screen to stop interacting with the user.
 */

/**
 * Activity to allow the user to keep in touch with their friends.
 */
public class FiveThings extends Activity {
    private static final String KEY_FRIENDS = "key-friends";
    public static final String FRIENDS_SEPARATOR = ";";

    FriendDbHelper mHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHelper = new FriendDbHelper(this);
        setContentView(R.layout.main);

        FriendDbHelper helper = new FriendDbHelper(this);
//        Get a list of all friends.
        List<Friend> allFriends = helper.lookup();
//        Find the top five that you can contact
        List<Friend> toContact = CloseLogic.findBest(allFriends);
//        Show these options to the user.
        
    }

    /** Show an activity to call suggested friends. */
    public void callFriends(View view) {
        // For now, just show a list of people to call with a frequency on when
        // to call them.

    }

    /** Select the friends to suggest later. */
    public void selectFriends(View view) {
        // Show the list of friends from the contacts database
        // and allow picking one out to call. (Better still, look
        // at the previously called people to list when you should
        // call them again.)

        // For now, just populate it with some people
        // Min just called me, so call her 17 days from now.
        mHelper.insertFriend(new Friend("Min Roh", 21));

        // Haven't talked to Brian for a while, so call soon.
        mHelper.insertFriend(new Friend("Brian Drawert", 50));

    }
}
