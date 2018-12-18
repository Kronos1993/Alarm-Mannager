package com.quimera.kronos.alarmmannager.Activities;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.quimera.kronos.alarmmannager.R;
import com.quimera.kronos.alarmmannager.Utility;
import com.quimera.kronos.alarmmannager.Activities.broadcasterreciever.NotificatioReciever;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Context context=this;
    Calendar calendar=Calendar.getInstance();
    int my_year,my_month,my_day,my_hour,my_min;
    PendingIntent pendingIntent;
    Intent intent;
    AlarmManager alarmManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(R.string.main_name);

        PreferenceManager.setDefaultValues(context, R.xml.preferences, false);



        my_year=calendar.get(calendar.YEAR);
        my_month=calendar.get(calendar.MONTH);
        my_day=calendar.get(calendar.DAY_OF_MONTH);
        my_hour=calendar.get(calendar.HOUR_OF_DAY);
        my_min=calendar.get(calendar.MINUTE);

        final TextView textView= (TextView) findViewById(R.id.txtDate);

       final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() {
                     @Override
                     public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                         my_year=year;
                         my_month=monthOfYear;
                         my_day=dayOfMonth;

                         calendar.set(calendar.YEAR,my_year);
                         calendar.set(calendar.MONTH,my_month);
                         calendar.set(calendar.DAY_OF_MONTH,my_day);

                         new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() {
                             @Override
                             public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                 my_hour=hourOfDay;
                                 my_min=minute;

                                 calendar.set(calendar.HOUR_OF_DAY,my_hour);
                                 calendar.set(calendar.MINUTE,my_min);
                                 calendar.set(calendar.SECOND,0);

                                 textView.setText(calendar.get(Calendar.DAY_OF_MONTH)+"/"+(calendar.get(Calendar.MONTH)+1)+"/"
                                         +calendar.get(Calendar.YEAR)+" a las "+calendar.get(Calendar.HOUR)+":"+calendar.get(Calendar.MINUTE));

                                 intent= new Intent(context,NotificatioReciever.class);
                                 pendingIntent= PendingIntent.getBroadcast(context,100,intent,pendingIntent.FLAG_UPDATE_CURRENT);
                                 alarmManager= (AlarmManager) getSystemService(ALARM_SERVICE);
                                 alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),alarmManager.INTERVAL_DAY,pendingIntent);
                             }
                         }, my_hour, my_min,true).show();

                     }
                 },my_year,my_month,my_day).show();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Utility.navigate(context, PreferencesActivity.class);
        }

        return super.onOptionsItemSelected(item);
    }
}
