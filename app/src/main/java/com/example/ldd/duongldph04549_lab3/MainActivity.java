package com.example.ldd.duongldph04549_lab3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

import com.example.ldd.duongldph04549_lab3.json_parser.Bai1;
import com.example.ldd.duongldph04549_lab3.retrofit.Bai3;
import com.example.ldd.duongldph04549_lab3.volley.VolleyJSON;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                startActivity(new Intent(this,Bai1.class));
                break;
            case R.id.button2:
                startActivity(new Intent(this,VolleyJSON.class));
                break;
            case R.id.button3:
                startActivity(new Intent(this,Bai3.class));
                break;
            case R.id.button4:
                startActivity(new Intent(this,Bai4.class));
                break;
        }
    }
}
