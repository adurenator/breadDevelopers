package com.fastpay.suitux.fastpay;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class AnotherOperationActivity extends AppCompatActivity implements View.OnClickListener {
    //Activity:
    Button getmoney;
    Button depositMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Request full window: */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_operation);

        this.getmoney = (Button) findViewById(R.id.get_money);
        this.depositMoney = (Button) findViewById(R.id.deposity_money);

        this.getmoney.setOnClickListener(this);
        this.depositMoney.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.get_money:

            /*    final Dialog getMoneyDialog = new Dialog(AnotherOperationActivity.this);
                getMoneyDialog.setContentView(R.layout.depositmoney_dialog);
                getMoneyDialog.show();

                Button ingressDialogButton = (Button)getMoneyDialog.findViewById(R.id.dialogButtonOK);
                EditText moneyToIngress = (EditText) getMoneyDialog.findViewById(R.id.moneyToIngress);

                ingressDialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getMoneyDialog.dismiss();
                    }
                });
            */
                Intent getMoneyIntent = new Intent(AnotherOperationActivity.this, SelectAccountActivity.class);
                startActivity(getMoneyIntent);

                break;
            case R.id.deposity_money:
                break;
            default:
                break;
        }
    }
}
