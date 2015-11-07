package com.fastpay.suitux.fastpay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button habitualOperations;
    Button anotherOperations;
    Button accesories;
    Button info;
    Button options;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Request full window: */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.habitualOperations = (Button) findViewById(R.id.habitual_operations);
        this.anotherOperations = (Button) findViewById(R.id.another_operations);
        this.accesories = (Button) findViewById(R.id.accesories);
        this.info = (Button) findViewById(R.id.infobutton);
        this.options = (Button) findViewById(R.id.optionbutton);

        this.habitualOperations.setOnClickListener(this);
        this.anotherOperations.setOnClickListener(this);
        this.accesories.setOnClickListener(this);
        this.info.setOnClickListener(this);
        this.options.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.habitual_operations:
                break;
            case R.id.another_operations:
                Intent another = new Intent(MainActivity.this, AnotherOperationActivity.class);
                startActivity(another);
                break;
            case R.id.accesories:
                break;
            case R.id.infobutton:
                break;
            case R.id.optionbutton:
                break;
            default:
                break;
        }

    }
}
