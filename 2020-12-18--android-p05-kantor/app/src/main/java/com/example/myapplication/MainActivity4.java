package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {
    DatabaseHelper db;
    EditText e1,e2,e3;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main4);
       db = new DatabaseHelper(this,"Student.db");
       e1=(EditText)findViewById(R.id.email);
        e2=(EditText)findViewById(R.id.pass);
        e3=(EditText)findViewById(R.id.cpass);
        b1=(Button)findViewById(R.id.register);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1= e1.getText().toString();
                String s2= e2.getText().toString();
                String s3= e3.getText().toString();
                if(s1.equals("")||s2.equals("")||s3.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Pola są puste", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(s2.equals(s3))
                    {
                        Boolean chkemail = db.chkemail(s1);
                        if (chkemail==true)
                        {
                            Boolean insert = db.insert(s1,s2);
                            if(insert==true){
                                Toast.makeText(getApplicationContext(),"Rejstracja zakończona",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Email w użyciu", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }


        }