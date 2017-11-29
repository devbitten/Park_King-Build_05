package com.example.maupi.parkking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private Button AccBtn;
    private Button Login;
   // private Button MapBtn;
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AccBtn = (Button) findViewById(R.id.AccBtn);

        AccBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToCreateAccount();
            }
        });

        Login = (Button) findViewById(R.id.login);

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                checkLogin();
            }
        });

      //  MapBtn = (Button) findViewById(R.id.MapBtn);

        /*MapBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                goToMap();
            }
        });*/

    }
    public void goToMap()
    {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
    public void goToCreateAccount()
    {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    public void checkLogin(){

        EditText uname = (EditText)findViewById(R.id.user);
        String userName = uname.getText().toString();

        EditText pass = (EditText)findViewById(R.id.password);
        String password = pass.getText().toString();

        String checkedPass = helper.searchInfo(userName);

        if(userName.isEmpty() && password.isEmpty()) {
            Toast noInfo = Toast.makeText(MainActivity.this, "Please input the username and password", Toast.LENGTH_SHORT);
            noInfo.show();
        } else if(checkedPass.equals(password)){
            Toast success =  Toast.makeText(MainActivity.this, "congratulations, logged in successfully", Toast.LENGTH_SHORT);
            success.show();
            helper.userName = userName;
            goToMap();
        } else{
            Toast failure =  Toast.makeText(MainActivity.this, "Sorry, wrong username or password", Toast.LENGTH_SHORT);
            failure.show();
        }


    }


  /*  public void getPaymentInfo(){
        Intent intent = new Intent(this, getPaymentInfo.class);
        startActivity(intent);
    }*/




}

