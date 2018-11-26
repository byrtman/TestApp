package com.tivo.byrt.testapp;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tivo.byrt.testapp.MESSAGE";
    private NumberPicker mPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPicker = findViewById(R.id.numberPicker);
        mPicker.setMinValue(0);
        mPicker.setMaxValue(2);
        mPicker.setWrapSelectorWheel(true);
        mPicker.setDisplayedValues(new String[] {"121.01", "224.22", "244.32"});
    }


}
