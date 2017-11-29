package com.example.maupi.parkking;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private UiSettings mUiSettings;
    private String selectedMarkerID = "111111";
    private HashMap<String, Integer> markerHM = new HashMap<String, Integer>();
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Sets up map settings
        mMap = googleMap;
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);

        //Sets up markers
        ArrayList<ParkingMeterData> meters = new ArrayList<>();
        ArrayList<Marker> meterMarkers = new ArrayList<>();
        int numberOfMeters = 5;
        for(int i = 0; i < numberOfMeters; i++){
            meters.add(putDataIntoMeter(i));
        }

        //Puts the data into the markers
        Marker currentMarker;
        for(ParkingMeterData p : meters){
            db.insertMeter(p);
            currentMarker = mMap.addMarker(new MarkerOptions()
                    .position(p.getLatlng())
                    .title("Currently Available: " + ( p.isAvailable()? "YES" : "NOPE" ))
                    .snippet("Minutes until available: " + p.getTimeTillAvailble()));
            markerHM.put(currentMarker.getId(), p.getId());
            meterMarkers.add(currentMarker);
        }

        //Puts markers on the map
        for(int i = 0; i < meterMarkers.size(); i++){
            mMap.addMarker(new MarkerOptions()
                    .position(meterMarkers.get(i).getPosition())
                    .title(meterMarkers.get(i).getTitle())
                    .snippet(meterMarkers.get(i).getSnippet()));

        }
        //Sets default location of the map
        //TODO change to user's location
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(meters.get(3).getLatlng(), 14.0f));

        //Make the Marker's infoWindow clickable
        mMap.setOnInfoWindowClickListener(this);
    }

    //TODO: finish updating meters
    public ParkingMeterData putDataIntoMeter(int meterNumber){
        ParkingMeterData pmd = new ParkingMeterData();
        switch (meterNumber){
            case 0: pmd.setId(111111);  //My address
                pmd.setAvailable(true);
                pmd.setTimeTillAvailble(0);
                pmd.setPrice(5.2);
                pmd.setTimePerUse(45);
                pmd.setTimeLastUsed("12");  //DEMO: Set to about 10 mins ago
                pmd.setTimePerLastUsed(15);
                pmd.setLatlng(new LatLng(31.7942, -85.9452));
                pmd.setAddress("205 S Franklin Dr\n36081\nTroy\nAlabama");
                break;
            case 1: pmd.setId(111112);  //University Avenue
                pmd.setAvailable(false);
                pmd.setTimeTillAvailble(15);
                pmd.setPrice(10);
                pmd.setTimePerUse(45);
                pmd.setTimeLastUsed("12");    //DEMO: FREE never used
                pmd.setTimePerLastUsed(5);
                pmd.setLatlng(new LatLng(31.7988, -85.957));
                pmd.setAddress("205 S Franklin Dr\n36081\nTroy\nAlabama");  //TODO: update address
                break;
            case 2: pmd.setId(111113);  //Walmart
                pmd.setAvailable(true);
                pmd.setTimeTillAvailble(0);
                pmd.setPrice(4.55);
                pmd.setTimePerUse(30);
                pmd.setTimeLastUsed("12");    //DEMO: Set to ~15secs ago
                pmd.setTimePerLastUsed(5);
                pmd.setLatlng(new LatLng(31.7789, -85.9411));
                pmd.setAddress("1420 Highway 231 S\n36081\nTroy\nAlabama");
                break;
            case 3: pmd.setId(111114);  //TC
                pmd.setAvailable(false);
                pmd.setTimeTillAvailble(30);
                pmd.setPrice(3);
                pmd.setTimePerUse(60);
                pmd.setTimeLastUsed("12");    //DEMO: FREE, used like an hour ago
                pmd.setTimePerLastUsed(30);
                pmd.setLatlng(new LatLng(31.8018, -85.9554));
                pmd.setAddress("205 S Franklin Dr\n36081\nTroy\nAlabama");  //TODO update address
                break;
            case 4: pmd.setId(111115);  //Troy Utilities Department
                pmd.setAvailable(false);    //TODO: make sure this reflects the actual availability
                pmd.setTimeTillAvailble(28);
                pmd.setPrice(2.5);
                pmd.setTimePerUse(29);
                pmd.setTimeLastUsed(
                    (String)new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss")
                        .format(new Date()));    //DEMO: RESERVED JUST NOW
                pmd.setTimePerLastUsed(30);
                pmd.setLatlng(new LatLng(31.8100,-85.9692));
                pmd.setAddress("306 E Academy St\n36081\nTroy\nAlabama");  //TODO update address
                break;
        }
        return pmd;
    }

    public static void putMeterIntoDB(ParkingMeterData pmd){
        pmd.getId();
    }
    @Override
    public void onInfoWindowClick(Marker marker) {
        //Toast.makeText(this, marker.getSnippet(), Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(MapsActivity.this, MeterActivity.class);
        int markerID = markerHM.get(marker.getId());
        intent.putExtra("markerID", markerID);
        startActivity(intent);
        //finish();
        //setContentView(R.layout.activity_meter);
    }
}
