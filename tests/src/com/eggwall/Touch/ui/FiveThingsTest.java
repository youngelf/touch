package com.eggwall.Touch.ui;

import android.annotation.TargetApi;
import android.os.Build;
import android.test.ActivityInstrumentationTestCase2;

/**
 * This is a simple framework for a test of an Application.  See
 * {@link android.test.ApplicationTestCase ApplicationTestCase} for more information on
 * how to write and extend Application tests.
 * <p/>
 * To run this test, you can type:
 * adb shell am instrument -w \
 * -e class com.eggwall.Touch.ui.FiveThingsTest \
 * com.eggwall.Touch.tests/android.test.InstrumentationTestRunner
 */
@TargetApi(Build.VERSION_CODES.CUPCAKE)
public class FiveThingsTest extends ActivityInstrumentationTestCase2<FiveThings> {

    public FiveThingsTest() {
        super("com.eggwall.Touch.ui", FiveThings.class);
    }

    public void testPreConditions() {
    }
}
