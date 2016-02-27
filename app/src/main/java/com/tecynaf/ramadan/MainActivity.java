package com.tecynaf.ramadan;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView month, day, hour, minute, second, sample, quotes, pagination;
    CountDownTimer the_timer;
    public static final String MyPREFERENCES = "MyPrefs" ;
    public static final String Counter = "counterKey";
    int counter = 0;
    SharedPreferences.Editor editor;
    String[] quote_array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();

        counter = sharedpreferences.getInt(Counter, 0);

        pagination = (TextView) findViewById(R.id.pagination);

        month = (TextView) findViewById(R.id.month);
        day = (TextView) findViewById(R.id.day);
        hour = (TextView) findViewById(R.id.hour);
        minute = (TextView) findViewById(R.id.minute);
        second = (TextView) findViewById(R.id.second);
        sample = (TextView) findViewById(R.id.sample);
        quotes = (TextView) findViewById(R.id.quotes);
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
//        AdRequest adRequest = new AdRequest.Builder()
//                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)        // All emulators
//                .addTestDevice("1C5E6735FD417BE68CB1CF838B08D33A") // An example device ID
//                .addTestDevice("E736E2EFB7351285C9D08B3C0F84A8D2")
//                .build();
        mAdView.loadAd(adRequest);



        Resources res = getResources();
        quote_array = res.getStringArray(R.array.quote_array);
        quotes.setText(quote_array[counter]);
        pagination.setText((counter+1) + " of " + quote_array.length);

        the_timer = new CountDownTimer(999999L, 1000) {

           public void onTick(long millisUntilFinished) {
               SimpleDateFormat s = new SimpleDateFormat("dd:MM::hh:mm:ss");
               long date1 = new Date().getTime();
               long unixTime = System.currentTimeMillis();
               Log.d("time", unixTime + "");
               Date date2 = new Date(1465214400L - unixTime);
               Date startDate = new Date();
               startDate.setTime(unixTime);

               Date endDate =  new Date();
               endDate.setTime(1465214400000L);

               //milliseconds
               long different = endDate.getTime() - startDate.getTime();

               System.out.println("startDate : " + startDate);
               System.out.println("endDate : "+ endDate);
               System.out.println("different : " + different);

               long secondsInMilli = 1000;
               long minutesInMilli = secondsInMilli * 60;
               long hoursInMilli = minutesInMilli * 60;
               long daysInMilli = hoursInMilli * 24;
               long monthsInMilli = daysInMilli * 30;

               long elapsedMonths = different / monthsInMilli;
               different = different % monthsInMilli;


               long elapsedDays = different / daysInMilli;
               different = different % daysInMilli;

               long elapsedHours = different / hoursInMilli;
               different = different % hoursInMilli;

               long elapsedMinutes = different / minutesInMilli;
               different = different % minutesInMilli;

               long elapsedSeconds = different / secondsInMilli;

               Log.d("time","%d days %d hours, %d minutes, %d seconds%n"+
                       elapsedDays +
                       elapsedHours + elapsedMinutes + elapsedSeconds  );



               month.setText(elapsedMonths +"" );
               day.setText(elapsedDays +"");
               hour.setText(elapsedHours+"");
               minute.setText(elapsedMinutes +"");
               second.setText(elapsedSeconds +"");

           }

           public void onFinish() {
               the_timer.start();
           }
       }.start();



    }

    public void nextButtonPressed(View v){

        if(counter == quote_array.length-1){
//            quotes.setText("No more quote to display");
        }else{
            counter ++;
            editor.putInt(Counter, counter);
            editor.commit();
            quotes.setText(quote_array[counter]);
            pagination.setText((counter+1) + " of " + quote_array.length);
        }


    }

    public void previousButtonPressed(View v){
        if(counter == 0){
//            quotes.setText("No more quote to display");
        }else{
            counter --;
            editor.putInt(Counter, counter);
            editor.commit();
            quotes.setText(quote_array[counter]);
            pagination.setText((counter+1) + " of " + quote_array.length);
        }


    }

    public void printDifference(Date startDate, Date endDate){

        //milliseconds
        long different = endDate.getTime() - startDate.getTime();

        System.out.println("startDate : " + startDate);
        System.out.println("endDate : "+ endDate);
        System.out.println("different : " + different);

        long secondsInMilli = 1000;
        long minutesInMilli = secondsInMilli * 60;
        long hoursInMilli = minutesInMilli * 60;
        long daysInMilli = hoursInMilli * 24;

        long elapsedDays = different / daysInMilli;
        different = different % daysInMilli;

        long elapsedHours = different / hoursInMilli;
        different = different % hoursInMilli;

        long elapsedMinutes = different / minutesInMilli;
        different = different % minutesInMilli;

        long elapsedSeconds = different / secondsInMilli;

        Log.d("time", "%d days %d hours, %d minutes, %d seconds%n" +
                elapsedDays +
                elapsedHours + elapsedMinutes + elapsedSeconds);

    }


}
