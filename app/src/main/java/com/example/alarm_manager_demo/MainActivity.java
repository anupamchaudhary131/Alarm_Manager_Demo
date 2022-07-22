package com.example.alarm_manager_demo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int ALARM_REQ_CODE = 100;
    EditText edtText;
    Button btnSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtText = findViewById(R.id.edtText);
        btnSet = findViewById(R.id.btnSet);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        btnSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int time = Integer.parseInt(edtText.getText().toString());
                long triggerTime = System.currentTimeMillis()+(time*1000);

                Intent iBroadcast = new Intent(MainActivity.this, MyReciever.class);
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, ALARM_REQ_CODE, iBroadcast, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pi);

            }
        });

    }
}