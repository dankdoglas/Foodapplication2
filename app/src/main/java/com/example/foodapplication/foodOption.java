package com.example.foodapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;


public class foodOption extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_option);

        Button btnpu1 = findViewById(R.id.imageButton4);
        Button btnpu2 = findViewById(R.id.imageButton7);

        btnpu1.setOnClickListener(this);
        btnpu2.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageButton4:
                noodles(view);
                break;

            case R.id.imageButton7:
                prata(view);
                break;
        };
    }

    public void noodles(View view){
        Intent i = new Intent(this, noodleOrdering.class);
        startActivity(i);
    }

    public void prata(View view){
        Intent i = new Intent(this, prataOrdering.class);
        startActivity(i);
    }
}
