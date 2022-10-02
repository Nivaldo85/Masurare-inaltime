package com.example.masurareinaltimev2;
import android.app.Application;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import android.widget.TextView;
public class MainActivity extends Activity {
    int miliseconds;
    long startTime=0l,timeToMiliseconds=0l,timeSwapBuff=0l,updateTime=0l;
    private int sec = 0;
    private boolean is_running=false;
    private boolean was_running;
    timer t=new timer();
    String timp="0";




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            sec = savedInstanceState.getInt("millis");
            is_running = savedInstanceState.getBoolean("running");
            was_running = savedInstanceState .getBoolean("wasRunning");
        }

        running_Timer();
    }
    @Override
    public void onSaveInstanceState(
            Bundle savedInstanceState)
    {
        savedInstanceState.putInt("seconds", sec);
        savedInstanceState.putBoolean("running", is_running);
        savedInstanceState.putBoolean("wasRunning", was_running);
    }
    @Override
    protected void onPause()
    {
        super.onPause();
        was_running = is_running;
        is_running = false;
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        if (was_running) {
            is_running = true;
        }
    }
    public void onClickStart(View view)
    {
        is_running = true;t.reset();
    }
    public void onClickStop(View view)
    {
        is_running = false;
    }
    public void onClickReset(View view)
    {
        System.exit(0);
    }
    private void running_Timer()
    {
        final TextView t_View = (TextView)findViewById(R.id.t_view);
        final TextView d_view=(TextView) findViewById(R.id.distance_view);
        final Handler handle = new Handler();
        handle.post(new Runnable() {
            @Override
            public void run()
            {
                int hrs = sec / 3600;
                int mins = (sec % 3600) / 60;
                int secs = sec % 60;

                if(is_running){
                 timp=t.getElapsedTimeString();}
                double dist=Double.parseDouble(timp);
                double dist2=4.905*dist*dist;
                String distanta=String.format("%.2f m",dist2);
                //String distanta3=String.format("%.2f m", distanta);

                String time_t = String.format("%.3f s",dist);
                //String distance=String.format(Locale.getDefault(),"%03d",distanta);
                t_View.setText(time_t);
                if(is_running){
                d_view.setText(distanta);}
                if (is_running) {
                    sec++;
                }
                handle.postDelayed(this, 1);
            }
        });
    }
}