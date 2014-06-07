package com.eggwall.Touch;

import android.app.Activity;
import android.os.Bundle;

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
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}
