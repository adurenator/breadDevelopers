package com.fastpay.suitux.fastpay;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class SelectAccountActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView accounts;
    AccountItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        /* Request full window: */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_account);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        List<AccountItem> accountList = new ArrayList();
        accountList.add(new AccountItem("Xavi", "2100 0813 61 0123456789"));
        accountList.add(new AccountItem("Aleix", "2100 0813 61 0123456789"));
        accountList.add(new AccountItem("Nassim", "2100 0813 61 0123456789"));
        accountList.add(new AccountItem("Adur", "2100 0813 61 0123456789"));

        this.adapter = new AccountItemAdapter(this, accountList);
        this.accounts = (ListView) findViewById(R.id.accountsList);

        this.accounts.setAdapter(adapter);
        this.accounts.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        final AccountItem account = (AccountItem) this.adapter.getItem(position);
        final Dialog getMoneyDialog = new Dialog(SelectAccountActivity.this);
        getMoneyDialog.setContentView(R.layout.depositmoney_dialog);
        getMoneyDialog.show();

        Button ingressDialogButton = (Button)getMoneyDialog.findViewById(R.id.dialogButtonOK);
        final EditText moneyToIngress = (EditText) getMoneyDialog.findViewById(R.id.moneyToIngress);
        moneyToIngress.requestFocus();

        ingressDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moneyToIngress.getText().toString().length() > 0) {
                    final int moneyToGet = Integer.parseInt(moneyToIngress.getText().toString());

                    getMoneyDialog.dismiss();

                    final Dialog confirmdialog = new Dialog(SelectAccountActivity.this);
                    confirmdialog.setContentView(R.layout.confirm_dialog);
                    confirmdialog.show();

                    Button yes = (Button) confirmdialog.findViewById(R.id.dialogButtonOK);
                    Button no = (Button) confirmdialog.findViewById(R.id.dialogButtonDissmis);

                    final TextView accountName = (TextView) confirmdialog.findViewById(R.id.accountName);
                    final TextView accountNumber = (TextView) confirmdialog.findViewById(R.id.accountNumber);
                    final TextView money = (TextView) confirmdialog.findViewById(R.id.money);

                    accountName.setText("Nombre: " + account.name);
                    accountNumber.setText("Numero: " + account.number);

                    money.setText("Importe: " + moneyToGet);
                    account.money = moneyToGet;

                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            try {
                                confirmdialog.dismiss();
                                Intent inform = new Intent(SelectAccountActivity.this, InformActivity.class);

                                inform.putExtra("info", account);
                                startActivity(inform);
                            } catch (Exception e) {
                            }
                        }
                    });

                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            confirmdialog.dismiss();
                        }
                    });

                }
            }
        });
    }
}
