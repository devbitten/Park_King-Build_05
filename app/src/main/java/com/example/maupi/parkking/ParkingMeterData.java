package com.example.maupi.parkking;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by WINTER on 11/6/2017.
 */

public class ParkingMeterData implements Parcelable {
    /*
    Possible things to add:
    id
    isAvailable
    timeTillAvailable
    price
    timePerUse
    address
    LatLng
     */

    private int id;
    private boolean isAvailable;
    private int timeTillAvailble;    //In seconds
    private double price;       //price in USD to use the meter
    private int timePerUse;     //Time, in minutes, the meter is used for
    private LatLng latlng;      //Latitude, and Longitude of meter
    private String address;     //Gives the address in the format "LINE_1\nZIP\nCITY\nSTATE"
    private String timeLastUsed;    //When the meter was last reserved
    private int timePerLastUsed;    //How long the meter was last used for

    public ParkingMeterData(){

    }

    public ParkingMeterData(Parcel in){
        String[] data = new String[10];
        in.readStringArray(data);

        this.id = Integer.parseInt(data[0]);
        this.isAvailable = Boolean.parseBoolean(data[1]);
        this.timeTillAvailble = Integer.parseInt(data[2]);
        this.price = Double.parseDouble(data[3]);
        this.timePerUse = Integer.parseInt(data[4]);
        this.latlng = new LatLng(Double.parseDouble(data[5]), Double.parseDouble(data[6]));
        this.address = data[7];
        this.timeLastUsed = data[8];
        this.timePerLastUsed = Integer.parseInt(data[9]);
    }

    public static final Creator<ParkingMeterData> CREATOR = new Creator<ParkingMeterData>() {
        @Override
        public ParkingMeterData createFromParcel(Parcel in) {
            return new ParkingMeterData(in);
        }

        @Override
        public ParkingMeterData[] newArray(int size) {
            return new ParkingMeterData[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getTimeTillAvailble() {
        return timeTillAvailble;
    }

    public void setTimeTillAvailble(int timeTillAvailble) {
        this.timeTillAvailble = timeTillAvailble;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getTimePerUse() {
        return timePerUse;
    }

    public void setTimePerUse(int timePerUse) {
        this.timePerUse = timePerUse;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getLatlng() {
        return latlng;
    }

    public void setLatlng(LatLng latlng) {
        this.latlng = latlng;
    }

    public String getTimeLastUsed() {   return timeLastUsed;    }

    public void setTimeLastUsed(String timeLastUsed) {  this.timeLastUsed = timeLastUsed;   }

    public String getTimePerLastUsed() {   return Integer.toString(timePerLastUsed); }

    public void setTimePerLastUsed(int timePerLastUsed) {   this.timePerLastUsed = timePerLastUsed; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeStringArray(new String[]
                {Integer.toString(this.id),
                        Boolean.toString(this.isAvailable),
                        String.valueOf(this.timeTillAvailble),
                        Double.toString(this.price),
                        Integer.toString(this.timePerUse),
                        Double.toString(this.latlng.latitude),
                        Double.toString(this.latlng.longitude),
                        this.address,
                        this.timeLastUsed,
                        Integer.toString(this.timePerLastUsed)});
    }
}
