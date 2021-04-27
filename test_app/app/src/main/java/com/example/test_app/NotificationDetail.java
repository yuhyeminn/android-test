package com.example.test_app;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

public class NotificationDetail extends AppCompatActivity {
    private Button create_notify;
    private TimePicker timePicker;
    private int hour, min;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        
        // timepicker 현재시간으로 설정
        timePicker = findViewById(R.id.timePicker);
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.toString());
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            timePicker.setIs24HourView(false);
            timePicker.setHour(calendar.get(Calendar.HOUR_OF_DAY));
            timePicker.setMinute(calendar.get(Calendar.MINUTE));
        }
    }

    /** create 버튼 클릭시 호출*/
    public void createNotify(View view){

        Intent intent = new Intent(this,  NotifyList.class);
        timePicker = (TimePicker) findViewById(R.id.timePicker);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            hour = timePicker.getHour();
            min = timePicker.getMinute();
        }else{
            hour = timePicker.getCurrentHour();
            min = timePicker.getCurrentMinute();
        }

        intent.putExtra("hour", hour);
        intent.putExtra("min", min);

        startActivity(intent);
    }
}