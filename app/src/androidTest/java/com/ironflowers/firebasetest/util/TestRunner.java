package com.ironflowers.firebasetest.util;

import android.app.Application;
import android.content.Context;
import android.support.test.runner.AndroidJUnitRunner;

import com.ironflowers.firebasetest.MainTestApplication;

public class TestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MainTestApplication.class.getName(), context);
    }
}