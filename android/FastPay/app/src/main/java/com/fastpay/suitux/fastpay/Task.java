package com.fastpay.suitux.fastpay;

import android.os.AsyncTask;
import android.widget.TextView;

/**
 * Created by suitux on 07/11/2015.
 */
public class Task extends AsyncTask<String, String, String> {

    int delay;
    InformActivity activity;
    String title;
    String message;

    public Task(int delay, InformActivity activity, String title, String message) {
        super();
        this.delay = delay;
        this.activity = activity;
        this.title = title;
        this.message = message;
    }

    @Override
    protected String doInBackground(String... params) {

        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.activity.setTitle(this.title);
        this.activity.setMessage(this.message);

        return null;
    }
}
