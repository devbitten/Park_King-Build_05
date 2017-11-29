package com.example.maupi.parkking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class getPaymentInfo extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_payment_info);

    }
    public void goToMap()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }

    public void onSubmit(View v){

        if(v.getId() == R.id.SubmitPayment) {

            EditText address = (EditText) findViewById(R.id.address);
            EditText zip = (EditText) findViewById(R.id.zip);
            EditText cityState = (EditText) findViewById(R.id.cityState);
            EditText securityCode = (EditText) findViewById(R.id.securityCode);
            EditText expirationDate = (EditText) findViewById(R.id.expirationDate);
            EditText cardNum = (EditText) findViewById(R.id.cardNum);
            EditText name = (EditText) findViewById(R.id.name);
            EditText country = (EditText) findViewById(R.id.country);

            String addressString = address.getText().toString();
            String zipString = zip.getText().toString();
            String cityStateString = cityState.getText().toString();
            String securityCodeString = securityCode.getText().toString();
            String expirationDateString = expirationDate.getText().toString();
            String cardNumString = cardNum.getText().toString();
            String nameString = name.getText().toString();
            String countryString = country.getText().toString();

            // Make sure the user enters all the necessary fields for the payment
            if(addressString.isEmpty() || zipString.isEmpty() || cityStateString.isEmpty() || securityCodeString.isEmpty() ||
                    expirationDateString.isEmpty() || cardNumString.isEmpty() || nameString.isEmpty() || countryString.isEmpty()){
                if(addressString.isEmpty())
                    address.setError("Address is required");
                else if(zipString.isEmpty())
                    zip.setError("Zip code is required");
                else if(cityStateString.isEmpty())
                    cityState.setError("State and city are required");
                else if(securityCodeString.isEmpty())
                    securityCode.setError("Security code is required");
                else if(expirationDateString.isEmpty())
                    expirationDate.setError("Expiration date is required");
                else if(cardNumString.isEmpty())
                    cardNum.setError("Card number is required");
                else if(nameString.isEmpty())
                    name.setError("Name on card is required");
                else
                    country.setError("Country is required");
                return;
            }

            if(helper.uniquePayment(cardNumString , securityCodeString)){

                PaymentInfo p = new PaymentInfo();
                p.setAddress(addressString);
                p.setCardNum(cardNumString);
                p.setCityState(cityStateString);
                p.setCountry(countryString);
                p.setExpDate(expirationDateString);
                p.setNameOnCard(nameString);
                p.setSecurityCode(securityCodeString);
                p.setZip(zipString);

                helper.insertPayment(p);
                Toast c = Toast.makeText(getPaymentInfo.this, "payment info accepted", Toast.LENGTH_SHORT);
                c.show();
                goToMap();
            } else{

                Toast unique =  Toast.makeText(getPaymentInfo.this, "Payment already exists", Toast.LENGTH_SHORT);
                unique.show();
            }
        }
    }
}


