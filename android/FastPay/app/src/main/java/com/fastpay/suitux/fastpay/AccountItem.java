package com.fastpay.suitux.fastpay;

import android.widget.TextView;

import java.io.Serializable;

/**
 * Created by suitux on 06/11/2015.
 */
public class AccountItem implements Serializable {

    public String name;
    public String number;
    public int money;

    public AccountItem(String name, String number) {
        this.name = name;
        this.number = number;
    }

    public AccountItem(String name, String number, int money) {
        this.name = name;
        this.number = number;
        this.money = money;
    }



}
