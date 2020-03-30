package com.example.foodapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;

import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Stack;


public class prataOrdering extends AppCompatActivity implements View.OnClickListener {

    double total_amount = 0.0;
    int pratanumber;
    int number = 87119056;
    Stack<String> k = new Stack<String>();

    String name;
    String numberOrderer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prata_ordering);


        pratanumber = 0;

        TextView prataNum = findViewById(R.id.prataquantity);
        prataNum.setText(String.valueOf(pratanumber));

        FloatingActionButton deleteprata = findViewById(R.id.deletebutton);
        Button order  =  findViewById(R.id.buttonorder);
        Button add = findViewById(R.id.addbutton);

        order.setOnClickListener(this);
        add.setOnClickListener(this);
        deleteprata.setOnClickListener(this);

        sendsms();


    }


    protected void sendsms() {

        final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }


    }



    @Override
    public void onClick(View v) {

        TextView totalprice = findViewById(R.id.totalamount);
        TextView prataNum = findViewById(R.id.prataquantity);


        switch (v.getId()) {

            case R.id.buttonorder:

                ordering();
                break;


            case R.id.addbutton:

                additem();

                prataNum.setText(String.valueOf(k.size()));
                totalprice.setText("Total: " + String.valueOf(total_amount));

                break;


            case R.id.deletebutton:

                final LinearLayout linearLayout =  findViewById(R.id.orderlist);


                try {

                    linearLayout.removeAllViewsInLayout();

                    k.clear();
                    prataNum.setText(String.valueOf(0));

                    total_amount = 0;
                    totalprice.setText("Total: ");

               }

               catch (Exception e){

                   Toast.makeText(getApplicationContext(),"error", Toast.LENGTH_SHORT).show();

               }

                break;
        }

    }


    public void ordering() {

        AlertDialog.Builder areusure = new AlertDialog.Builder(this);

        areusure.setMessage("Are you sure you want to order?").setCancelable(true)

                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })


                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(String.valueOf(number), null, "Order items: " + k, null, null);

                        Toast.makeText(getApplicationContext(), "Food Ordered", Toast.LENGTH_SHORT).show();


                    }
                });



        AlertDialog alert = areusure.create();
        alert.show();





    }


    public void additem(){

        TextView textViewcheese = new TextView(this);
        TextView textViewchocolate = new TextView(this);
        TextView textViewpepper = new TextView(this);


        CheckBox checkBox1 = findViewById(R.id.cheese);
        CheckBox checkBox2 = findViewById(R.id.chocolate);
        CheckBox checkBox3 = findViewById(R.id.pepper);

        String cheese = "Cheese";
        String chocolate = "Chocolate";
        String pepper = "Pepper";

        final LinearLayout linearLayout =  findViewById(R.id.orderlist);

        if (checkBox1.isChecked() ){

                textViewcheese.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                textViewcheese.setText(cheese + " Prata");
                linearLayout.addView(textViewcheese);

                k.push(cheese);

                checkBox1.setChecked(false);

                total_amount += 1.2;


        }



            if(checkBox2.isChecked()){

                textViewchocolate.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                textViewchocolate.setText(chocolate + " Prata");
                linearLayout.addView(textViewchocolate);


                checkBox2.setChecked(false);

                k.push(chocolate);

                total_amount += 1.2;


            }

            if(checkBox3.isChecked() ){

                textViewpepper.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                textViewpepper.setText(pepper + " Prata");
                linearLayout.addView(textViewpepper);


                checkBox3.setChecked(false);


                k.push(pepper);

                total_amount += 1.2;


            }




    }




}




