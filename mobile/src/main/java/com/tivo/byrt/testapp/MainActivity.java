package com.tivo.byrt.testapp;


import android.arch.persistence.room.Room;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.NumberPicker;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.tivo.byrt.testapp.MESSAGE";
    private NumberPicker mMassPicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMassPicker = findViewById(R.id.massPicker);
        mMassPicker.setMinValue(0);
        mMassPicker.setMaxValue(2);
        mMassPicker.setWrapSelectorWheel(true);
        mMassPicker.setDisplayedValues(new String[] {"121.01", "224.22", "244.32"});

        AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "db-ore").allowMainThreadQueries().build();
        OreDAO oreDb = db.getOreDAO();
        initializeDatabase(oreDb);

        TextView view = findViewById(R.id.resultsTotalValue);
        view.setText(oreDb.getOres().get(0).getName());
    }

    private void initializeDatabase(OreDAO oreDb) {


        Ore ore = new Ore();
        ore.setName("Quartz");
        ore.setPrice(2.4);
        ore.setInvDensity(1.2);

        oreDb.insert(ore);
    }

}