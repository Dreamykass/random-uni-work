package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity5 extends AppCompatActivity {
    EditText e1,e2;
    Button b1;
    DatabaseHelper db;

    public void openactivity(){
        Intent intent = new Intent(this,MainActivity6.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        db = new DatabaseHelper(this,"Login.db");
        e1 = (EditText)findViewById(R.id.login);
        e2 = (EditText)findViewById(R.id.password);
        b1 = (Button)findViewById(R.id.zaloguj);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean chkemailpass = db.emailpassword(email,password);
                if(chkemailpass==true) {
                    Toast.makeText(getApplicationContext(), "Zalogowano", Toast.LENGTH_SHORT).show();
                    openactivity();
                }
                else
                    Toast.makeText(getApplicationContext(),"Błąd podczas logowania",Toast.LENGTH_SHORT).show();


            }
        });
    }
}