package com.example.foodapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class yearTwocalculation extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_year_twocalculation);
        Button compute;

        compute = findViewById(R.id.button2);
        compute.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        calculate();
    }

    public void calculate(){
        TextView totalScore;
        EditText Assesment1;
        EditText Assesment2;
        EditText Assesment3;
        EditText Assesment4;

        Assesment1 = findViewById(R.id.testscoreOne);
        Assesment2 = findViewById(R.id.testscoreTwo);
        Assesment3 = findViewById(R.id.testscoreThree);
        Assesment4 = findViewById(R.id.testscoreFour);

        totalScore = findViewById(R.id.textView3);

        double testscore1 =  Integer.parseInt(Assesment1.getText().toString());
        double testscore2 =  Integer.parseInt(Assesment2.getText().toString());
        double testscore3 =  Integer.parseInt(Assesment3.getText().toString());
        double testscore4 =  Integer.parseInt(Assesment4.getText().toString());


        if((testscore1 > 0 && testscore2 > 0 && testscore3 > 0 && testscore4 > 0)){
          double a = (testscore1 * 0.15) + ( testscore2 * 0.15) + (testscore3 * 0.15) + (testscore4 * 0.55);

          String displayTotallScore = String.valueOf(a);

          totalScore.setText(displayTotallScore);
            if(a < 45.00){
                totalScore.setText("Subject is an E grade or less");
            }

        }
    }
}
