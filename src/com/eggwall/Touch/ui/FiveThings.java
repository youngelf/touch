package com.eggwall.Touch.ui;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.eggwall.Touch.R;
import com.eggwall.Touch.data.Friend;
import com.eggwall.Touch.data.FriendDbHelper;

/*
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

        FriendDbHelper helper = new FriendDbHelper(this);
        helper.insertFriend(new Friend(1, "First", 60, 1));
    }
}
