package com.example.counter;

import androidx.appcompat.app.AppCompatActivity;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    boolean running=false;
    Button startBtn,stopBtn;
    TextView counterVal;
    private final String TAG="thread";
    Handler mainhandler = new Handler();
    int count=0;

    void startThread(){
        NewThread nobj =new NewThread();
        nobj.start();



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
          counterVal = findViewById(R.id.counterValue);
        startBtn=findViewById(R.id.startBtn);
        stopBtn=findViewById(R.id.stopBtn);
        startBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                count=0;
                running=true;
                startThread();
            }
        });
        stopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                running=false;

            }
        });
    }

class NewThread extends Thread{
    @Override
    public void run(){
        while (running)
        {
            count++;
                mainhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        counterVal.setText(String.valueOf(count));
                    }
                });

            Log.d(TAG,"startThread :");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}}