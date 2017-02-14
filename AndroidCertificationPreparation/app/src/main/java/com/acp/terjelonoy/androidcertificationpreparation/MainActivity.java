package com.acp.terjelonoy.androidcertificationpreparation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acp.terjelonoy.androidcertificationpreparation.activities.CustomViewActivity;
import com.acp.terjelonoy.androidcertificationpreparation.activities.JobSchedulerExampleActivity;
import com.acp.terjelonoy.androidcertificationpreparation.activities.LoaderExampleActivity;
import com.acp.terjelonoy.androidcertificationpreparation.managers.AlarmScheduleManager;
import com.acp.terjelonoy.androidcertificationpreparation.managers.DialogManager;
import com.acp.terjelonoy.androidcertificationpreparation.managers.NetworkManager;

public class MainActivity extends AppCompatActivity {
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindExampleButtons();
    }

    private void bindExampleButtons () {
        Button loaderExampleButton = (Button) findViewById(R.id.loaders_example_button);
        loaderExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, LoaderExampleActivity.class);
                startActivity(intent);
            }
        });

        Button alarmExampleButton = (Button) findViewById(R.id.alarm_example_button);
        alarmExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "Alarm will wake device in 15 seconds, put application in background mode!", Toast.LENGTH_LONG);
                toast.show();

                AlarmScheduleManager.setAlarm(context);
            }
        });

        Button jobScheduleExampleButton = (Button) findViewById(R.id.jobschedule_example_button);
        jobScheduleExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, JobSchedulerExampleActivity.class);
                startActivity(intent);
            }
        });

        Button customViewExampleButton = (Button) findViewById(R.id.customview_example_button);
        customViewExampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, CustomViewActivity.class);
                startActivity(intent);
            }
        });
    }
}