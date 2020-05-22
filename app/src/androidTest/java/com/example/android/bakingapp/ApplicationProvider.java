package com.example.android.bakingapp;




import android.content.Context;
import android.support.test.InstrumentationRegistry;


public final class ApplicationProvider {

    private ApplicationProvider() {}

    /**
     * Returns the application {@link Context} for the application under test.
     *
     * @see {@link Context#getApplicationContext()}
     */
    @SuppressWarnings("unchecked")
    public static <T extends Context> T getApplicationContext() {
        return (T)
                InstrumentationRegistry.getInstrumentation().getTargetContext().getApplicationContext();
    }
}