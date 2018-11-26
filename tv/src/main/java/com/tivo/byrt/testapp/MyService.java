package com.tivo.byrt.testapp;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

public class MyService extends AccessibilityService {
    public MyService() {
    }

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        Toast.makeText(this, R.string.app_name,Toast.LENGTH_SHORT);
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    public void onCreate() {

    }


}
