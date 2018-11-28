package com.tivo.byrt.testapp;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.NumberPicker;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tivo.byrt.testapp.MESSAGE";
    private NumberPicker mMassPicker;
    private OreData[] mTableData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMassPicker = findViewById(R.id.massPicker);
        mMassPicker.setWrapSelectorWheel(true);
        mMassPicker.setDisplayedValues(getResources().getStringArray(R.array.chunkSizes));
        mMassPicker.setMinValue(0);
        mMassPicker.setMaxValue(mMassPicker.getDisplayedValues().length-1);

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

        private OreData(String name, double price, double invDensity) {
            this.name = name;
            this.price = price;
            this.invDensity = invDensity;
        }
    }

}