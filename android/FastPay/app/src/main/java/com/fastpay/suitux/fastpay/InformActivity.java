package com.fastpay.suitux.fastpay;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class InformActivity extends AppCompatActivity {

    TextView title;
    TextView message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

         /* Request full window: */
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inform);


        this.title = (TextView) findViewById(R.id.title);
        this.message = (TextView) findViewById(R.id.message);


        this.title.setText("Toca el cajero con el movil porfavor.");
        this.message.setText("Esperando conexión");

       /* Thread inform = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                InformActivity.this.setTitle("Sincronizando, espere porfavor.");
                InformActivity.this.setMessage("Sincronizando...");
            }
        };

        inform.start();*/

        MyTask runner = new MyTask(2000, this, "Sincronizando, espere porfavor.", "Sincronizando...");
        runner.execute("test");




    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setMessage(String message) {
        this.message.setText(message);
    }

    private class MyTask extends AsyncTask<String, String, String> {

        int delay;
        InformActivity activity;
        String title;
        String message;
        String httpText = "0";
        boolean waitingNFC = false;
        boolean waitingPin = false;
        boolean waitingComplete = false;

        public MyTask(int delay, InformActivity activity, String title, String message) {
            super();
            this.delay = delay;
            this.activity = activity;
            this.title = title;
            this.message = message;



        }

        @Override
        protected String doInBackground(String... params) {

            while(httpText.equals("0")) {

                try {
                    URL oracle = new URL("http://192.168.10.42/finappsparty/getPhotoresistorStatus.php");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(oracle.openStream()));

                    String inputLine;
                    String total = "";
                    while ((inputLine = in.readLine()) != null)
                        total += inputLine;

                    httpText = total;
                    System.out.println("HTTP TEXT: " + httpText);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            waitingNFC = true;
            publishProgress(httpText);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            httpText = "0";

            while (httpText.equals("0")) {
                try {
                    URL oracle = new URL("http://192.168.10.42/finappsparty/getWaitPinCode.php");

                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(oracle.openStream()));

                    String inputLine;
                    String total = "";
                    while ((inputLine = in.readLine()) != null)
                        total += inputLine;

                    httpText = total;
                    System.out.println("HTTP TEXT: " + httpText);


                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    Thread.sleep(3500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            waitingPin = true;
            publishProgress(httpText);

            try {
                URL oracle = new URL("http://192.168.10.42/finappsparty/postWaitOperationFinished.php");
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String inputLine;
                String total = "";
                while ((inputLine = in.readLine()) != null)
                    total += inputLine;

                httpText = total;
                System.out.println("HTTP TEXT: " + httpText);

                Thread.sleep(3000);

            } catch (Exception e) {
                e.printStackTrace();
            }

            waitingComplete = true;
            publishProgress(httpText);




            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            if(waitingNFC) {
                InformActivity.this.setTitle("Introduzca el numero pin en el cajero.");
                InformActivity.this.setMessage("Esperando pin...");
                waitingNFC = false;
            }

            if(waitingPin) {
                InformActivity.this.setTitle("Realizando operacion, espere porfavor.");
                InformActivity.this.setMessage("Realizando operación...");
                waitingPin = false;
            }

            if(waitingComplete) {
                InformActivity.this.setTitle("Operación realizada, Gracias.");
                InformActivity.this.setMessage("Operacion finalizada.");


                AccountItem info = (AccountItem) getIntent().getSerializableExtra("info");

                TextView text = (TextView) findViewById(R.id.transferInfo);
                text.setText("Nombre:" + info.name + " \nCuenta: " + info.number + " \nExtracción: " + info.money + "€ \nSaldo restante: 2600€");

                Button exit = (Button) findViewById(R.id.exit);
                exit.setVisibility(View.VISIBLE);
                exit.setOnClickListener(new TextView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        InformActivity.this.finish();
                    }
                });
                waitingComplete = false;
            }
        }
    }

}

