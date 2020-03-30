package com.example.foodapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;


public class noodleOrdering extends AppCompatActivity implements View.OnClickListener{

    Vector<String> v = new Vector();


    Date date = java.util.Calendar.getInstance().getTime();
    SimpleDateFormat ft = new SimpleDateFormat ("Hmm");
    String dateformatted = ft.format(date);

    Double total_amount = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noodle_ordering);

        Toast.makeText(getApplicationContext(), "Food ordering not available from 9-5pm", Toast.LENGTH_SHORT).show();


        EditText txt = findViewById(R.id.editText11);
        txt.setHint("Customize your order");

        Button sendbtn = findViewById(R.id.addbutton);
        sendbtn.setOnClickListener(this);

        sendsms();

    }

    @Override
    public void onClick(View view) {



        AlertDialog.Builder areusure = new AlertDialog.Builder(this);

        areusure.setMessage("Are you sure you want to order?")
                .setCancelable(true)

                .setNegativeButton("No", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();

                        }
                })

                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {

                         {

                                      sendit();
                                      finish();

                        }

                    }
                });



        AlertDialog alert = areusure.create();

        if (!v.isEmpty()){

            alert.show();

        }

        else {

            Toast.makeText(getApplicationContext(), "Cart cannot be empty", Toast.LENGTH_SHORT).show();

        }

    }

    protected void sendsms() {

        final int MY_PERMISSIONS_REQUEST_SEND_SMS =0 ;

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {

            if (!(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS))) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.SEND_SMS},
                        MY_PERMISSIONS_REQUEST_SEND_SMS);

            }

        }

    }


    public void sendit(){

        String number = "+6587119056";
        EditText txt;
        txt = findViewById(R.id.editText11);
        String text = txt.getText().toString();

        if(v.isEmpty()) {

            Toast.makeText(getApplicationContext(), "Cart cannot be empty", Toast.LENGTH_SHORT).show();

        }


        else{

            if(true) {

                try {

                    SmsManager smsManager = SmsManager.getDefault();

                    if (text.length() == 0){

                        smsManager.sendTextMessage(number, null, "Order items: " + v + "\n\n Total Price: " + total_amount , null, null);
                        Toast.makeText(getApplicationContext(), "Food Ordered", Toast.LENGTH_SHORT).show();

                    }

                    else {

                        smsManager.sendTextMessage(number, null, "Order items: " + v + "\n\n Customize: "  + text + "\n\n Total Price: " + total_amount, null, null);
                        Toast.makeText(getApplicationContext(), "Food Ordered", Toast.LENGTH_SHORT).show();

                    }

                }

                catch(Exception e){

                    Toast.makeText(getApplicationContext(), "Food did not order", Toast.LENGTH_SHORT).show();

                }
            }



        }

    }


    public void additem(View view) {

        final TextView totalprice = findViewById(R.id.totalprice);
        final LinearLayout linearLayout =  findViewById(R.id.cart);

        final View.OnClickListener deleteAll = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                linearLayout.removeAllViews();
                v.removeAllElements();
                total_amount = 0.0;
                totalprice.setText("Total: "  + String.valueOf(total_amount));
            }
        };


        ImageButton cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(deleteAll);

        switch(view.getId()){

            case R.id.foodselection1:

                TextView textViewlaksa = new TextView(this);

                textViewlaksa.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                textViewlaksa.setText("Laksa");
                linearLayout.addView(textViewlaksa);
                v.add("Laksa");

                total_amount += 1.2;
                totalprice.setText("Total: " + String.valueOf(total_amount));

                break;

            case R.id.foodselection2:
                int time2 = 0;

                for(int converter = 0; converter <= 2400; converter++){

                    if(String.valueOf(converter).equals(dateformatted)){

                        time2 = converter;
                        break;

                    }


                }

                if (!(time2 > 740 && time2 < 810))

                {

                    TextView textviewfishball = new TextView(this);

                    textviewfishball.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    textviewfishball.setText("Fishball Noodles");
                    linearLayout.addView(textviewfishball);
                    v.add("FishBall");

                    total_amount += 1.2;
                    totalprice.setText("Total: " + String.valueOf(total_amount));


                }

                else {

                        Toast.makeText(getApplicationContext(), "Food ordering not available at this time period", Toast.LENGTH_SHORT).show();

                    }

                break;

            case R.id.foodselection3:

                int time = 0;

                for(int converter = 0; converter <= 2400; converter++){

                    if(String.valueOf(converter).equals(dateformatted)){

                       time = converter;
                       break;

                    }


                }

                if (date.toString().contains("Thu"))

                {

                    TextView textviewMeePok = new TextView(this);

                    textviewMeePok.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                    textviewMeePok.setText("Mee Pok");

                    linearLayout.addView(textviewMeePok);
                    v.add("Mee Pok");
                    total_amount += 1.2;
                    totalprice.setText("Total: " + String.valueOf(total_amount));

                }


                else{

                        if(!date.toString().contains("Thu")){

                            Toast.makeText(getApplicationContext(), "Food does not sell today", Toast.LENGTH_SHORT).show();

                        }


                        if(!(time < 1100)){

                        Toast.makeText(getApplicationContext(), "Food ordering not available at this time period", Toast.LENGTH_SHORT).show();

                    }


                }

                break;

            default:

                Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();

        }
    }

}


