package com.eggwall.Touch.ui;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
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
        List<Friend> all = mHelper.lookup();
        // Show all the friends
        for (Friend f : all) {
            Log.d("viki", "Another friend: " + f);
        }
        return;

    }

    public void fetchContacts() {
        String phoneNumber = null;
        String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        String _ID = ContactsContract.Contacts._ID;
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        String TIMES_CONTACTED = ContactsContract.Contacts.TIMES_CONTACTED;

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;

        Uri EmailCONTENT_URI =  ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;

        StringBuffer output = new StringBuffer();

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, TIMES_CONTACTED);

        // Loop for every contact in the phone
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String contact_id = cursor.getString(cursor.getColumnIndex( _ID ));
                String name = cursor.getString(cursor.getColumnIndex( DISPLAY_NAME ));

                // For some stupid reason, the phone application does not increment this number.
                int timesContacted = cursor.getInt(cursor.getColumnIndex(TIMES_CONTACTED));
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex( HAS_PHONE_NUMBER )));

                if (hasPhoneNumber > 0) {
                    output.append("\n First Name:" + name + ", contacted " + timesContacted + " times");

                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(
                            PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[] { contact_id }, null);
                    while (phoneCursor.moveToNext()) {
                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);
                    }
                    phoneCursor.close();

                    // Query and loop for every email of the contact
                    Cursor emailCursor = contentResolver.query(
                            EmailCONTENT_URI, null, EmailCONTACT_ID+ " = ?", new String[] { contact_id }, null);
                    while (emailCursor.moveToNext()) {
                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));
                        output.append("\nEmail:" + email);

                    }
                    emailCursor.close();
                }
                output.append("\n");
            }

            //outputText.setText(output);
            Log.d("viki", "Debug log is " + output);
        }
    }

    /** Select the friends to suggest later. */
    public void selectFriends(View view) {
        Log.d("viki", "selectFriends called");
        // Show the list of friends from the contacts database
        // and allow picking one out to call. (Better still, look
        // at the previously called people to list when you should
        // call them again.)

        fetchContacts();
       }
}
