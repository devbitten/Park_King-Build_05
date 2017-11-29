package com.example.maupi.parkking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MeterActivity extends AppCompatActivity {
    //Initializes the TextViews of the values in the activity_meter.xml
    TextView addressTV;
    TextView idTV;
    TextView priceTV;
    TextView timePerUseTV;
    TextView currentAvailabilityTV;
    TextView timeUntilAvailableTV;
    TextView timeLastUsedTV;
    TextView timePerLastUseTV;

    String timeLastUsed;
    String timePerLastUsed; //TODO change to int
    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meter);

        Bundle extras = getIntent().getExtras();
        //Sets up the values TextViews
        addressTV = (TextView) findViewById(R.id.address_label_value);
        idTV = (TextView) findViewById(R.id.id_label_value);
        priceTV = (TextView) findViewById(R.id.price_label_value);
        timePerUseTV = (TextView) findViewById(R.id.time_per_use_label_value);
        currentAvailabilityTV = (TextView) findViewById(R.id.availability_label_value);
        timeUntilAvailableTV = (TextView) findViewById(R.id.time_til_available_label_value);
        timeLastUsedTV = (TextView) findViewById(R.id.time_last_reserved_label_value);
        timePerLastUseTV = (TextView) findViewById(R.id.time_per_last_use_label_value);


        int meterID = -1;
        if(extras != null){
            meterID = extras.getInt("markerID");
        }
        System.out.println("meter ID!!!" + meterID);


        Log.d("FROM DB", db.getAddress("" + meterID));
        Log.d("FROM DB", db.getPrice("" + meterID));
        timeLastUsed = db.getTimeLastUsed("" + meterID);
        timePerLastUsed = db.getTimePerOfLastUse("" + meterID);
        //Displays the values from the database
        addressTV.setText(db.getAddress("" + meterID));
        idTV.setText(Integer.toString(meterID));
        priceTV.setText(db.getPrice("" + meterID));
        timePerUseTV.setText(db.getTimePerUse("" + meterID));
        currentAvailabilityTV.setText(db.getPrice("" + meterID));
        timeUntilAvailableTV.setText(""/*Add function to display time until available*/);
        timeLastUsedTV.setText(db.getTimeLastUsed("" + meterID));
        timePerLastUseTV.setText(db.getTimePerOfLastUse("" + meterID));

    }

    public String getTimeTilAvailbleDisplay()

}
