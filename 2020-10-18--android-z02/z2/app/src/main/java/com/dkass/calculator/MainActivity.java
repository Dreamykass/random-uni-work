package com.dkass.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = (TextView) findViewById(R.id.textView);
        Button button;

        {
            button = (Button) findViewById(R.id.button0);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("0");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button1);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("1");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button2);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("2");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button3);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("3");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button4);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("4");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button5);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("5");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button6);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("6");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button7);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("7");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button8);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("8");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.button9);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_number("9");
                    textView.setText(Logic.output_text());
                }
            });
        }


        button = (Button) findViewById(R.id.buttonSolve);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_solve();
                textView.setText(Logic.output_text());
            }
        });

        button = (Button) findViewById(R.id.buttonClea5r);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_clear();
                textView.setText(Logic.output_text());
            }
        });


        {
            button = (Button) findViewById(R.id.buttonAdd);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_operation("+");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.buttonSubstr);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_operation("-");
                    textView.setText(Logic.output_text());
                }
            });


            button = (Button) findViewById(R.id.buttonMultip);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_operation("*");
                    textView.setText(Logic.output_text());
                }
            });
            button = (Button) findViewById(R.id.buttonDivi);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Logic.input_operation("/");
                    textView.setText(Logic.output_text());
                }
            });
        }


        button = (Button) findViewById(R.id.buttonSIgn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_sign();
                textView.setText(Logic.output_text());
            }
        });
        button = (Button) findViewById(R.id.buttonComma);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_comma();
                textView.setText(Logic.output_text());
            }
        });



        button = (Button) findViewById(R.id.buttonFloor);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_floor();
                textView.setText(Logic.output_text());
            }
        });
        button = (Button) findViewById(R.id.buttonCeil);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Logic.input_ceiling();
                textView.setText(Logic.output_text());
            }
        });

    }

}