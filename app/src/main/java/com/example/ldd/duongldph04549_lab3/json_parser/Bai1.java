package com.example.ldd.duongldph04549_lab3.json_parser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.ldd.duongldph04549_lab3.R;

public class Bai1 extends AppCompatActivity {
    ListView listContact;
    GetContact getContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);
        listContact = (ListView) findViewById(R.id.listViewContact);

        getContact = new GetContact(this, listContact);
        getContact.execute();
    }
}
