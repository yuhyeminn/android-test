package com.example.test_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NotifyList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notify_list);

        Intent intent = getIntent();
        int hour = intent.getIntExtra("hour",0);
        int min = intent.getIntExtra("min",0);

        TextView textView = findViewById(R.id.clock_data);
        textView.setText(hour+"시 "+min+"분..");
    }
}