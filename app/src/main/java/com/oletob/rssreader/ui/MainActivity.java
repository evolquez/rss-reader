package com.oletob.rssreader.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.oletob.rssreader.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnDiarioLibre).setOnClickListener(this);
        findViewById(R.id.btnListinDiario).setOnClickListener(this);
        findViewById(R.id.btnElNacional).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        String key = "";
        switch(view.getId()){
            case R.id.btnDiarioLibre:
                key = "dl";
                break;
            case R.id.btnListinDiario:
                key = "ld";
                break;
            case R.id.btnElNacional:
                key = "en";
                break;
        }
        if(key != ""){
            Intent i = new Intent(MainActivity.this, FeedActivity.class);
            i.putExtra("key", key);
            startActivity(i);
        }
    }
}
