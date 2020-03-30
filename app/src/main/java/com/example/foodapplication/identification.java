package com.example.foodapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.PointerIcon;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class identification extends AppCompatActivity implements View.OnClickListener  {


    private FirebaseAuth mAuth;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        mAuth = FirebaseAuth.getInstance();

        progress = new ProgressDialog(this);

        Toast.makeText(getApplicationContext(), "Sign Up using a valid email address!", Toast.LENGTH_SHORT).show();


        Button signUp = findViewById(R.id.buttonnext);
        Button signIn = findViewById(R.id.buttonnext2);

        signUp.setOnClickListener(this);
        signIn.setOnClickListener(this);


    }

    private void usersignin(){

        final Intent intent2 = new Intent(this, MainActivity.class);


        EditText nameinput = findViewById(R.id.nameEditText);
        EditText numinput = findViewById(R.id.numEditText);

        String email = nameinput.getText().toString().trim();
        String password = numinput.getText().toString().trim();

       try {

           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {

                               startActivity(intent2);

                           } else {

                               Toast.makeText(identification.this, "Authentication failed.",
                                       Toast.LENGTH_SHORT).show();

                           }

                       }
                   });

       }

       catch(Exception e){

           Toast.makeText(identification.this, "Error",
                   Toast.LENGTH_SHORT).show();

       }


    }

    private void registeruser(){

        final Intent intent2 = new Intent(this, MainActivity.class);

        EditText nameinput = findViewById(R.id.nameEditText);
        EditText numinput = findViewById(R.id.numEditText);

        String email = nameinput.getText().toString().trim();
        String password = numinput.getText().toString().trim();

        if(email.length() == 0){

            Toast.makeText(getApplicationContext(), "Please provide email", Toast.LENGTH_LONG).show();
            return;

        }

        if(password.length() == 0){

            Toast.makeText(getApplicationContext(), "Please provide password", Toast.LENGTH_LONG).show();
            return;

        }


        progress.setMessage("Registering");
        progress.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    startActivity(intent2);

                }

                else{

                    progress.dismiss();
                    Toast.makeText(getApplicationContext(), "Could not register, try again?", Toast.LENGTH_LONG).show();
                    return;


                }

            }
        });
    }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.buttonnext:
                registeruser();
                break;


            case R.id.buttonnext2:
                usersignin();
                break;


        }

    }



}


