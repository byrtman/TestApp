package com.tivo.byrt.testapp;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tivo.byrt.testapp.MESSAGE";
    private NumberPicker mMassPicker;
    private OreData[] mTableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMassPicker = findViewById(R.id.massPicker);
        mMassPicker.setMinValue(0);
        mMassPicker.setMaxValue(2);
        mMassPicker.setWrapSelectorWheel(true);
        mMassPicker.setDisplayedValues(new String[] {"121.01", "224.22", "244.32"});

        mTableData = initData();


        AppDatabase db = Room.inMemoryDatabaseBuilder(this, AppDatabase.class).allowMainThreadQueries().build();
        OreDAO oreDb = db.getOreDAO();
        initializeDatabase(oreDb, mTableData);

        TextView view = findViewById(R.id.resultsTotalValue);
        view.setText(oreDb.getOres().get(oreDb.getOres().size()-1).getName());
    }

    private void initializeDatabase(OreDAO oreDb, OreData[] data) {

        for (OreData currentData : data) {
            Ore ore = new Ore();
            ore.setName(currentData.name);
            ore.setPrice(currentData.price);
            ore.setInvDensity(currentData.invDensity);
            oreDb.insert(ore);
        }
    }

    private OreData[] initData() {
        return new OreData[]{
                new OreData("Quartz", 2.4, 1.2),
                new OreData("Bexalite", 25.2, 1.52),
                new OreData("Copper", 4.2, 1.15),
                new OreData("Agricium", 23.1, 1.3),

        };
    }


    private static class OreData {
        private String name;
        private double price;
        private double invDensity;

        public OreData(String name, double price, double invDensity) {
            this.name = name;
            this.price = price;
            this.invDensity = invDensity;
        }
    }

}