package com.acp.terjelonoy.androidcertificationpreparation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.acp.terjelonoy.androidcertificationpreparation.activities.LoaderExampleActivity;
import com.acp.terjelonoy.androidcertificationpreparation.managers.AlarmScheduleManager;

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
        navigationClickHandler(loaderExampleButton, new LoaderExampleActivity());

        Button alarmExampleButton = (Button) findViewById(R.id.alarm_example_button);
        alarmClickHandler(alarmExampleButton);
    }

    private void navigationClickHandler(Button button, final Activity destination) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, destination.getClass());
                startActivity(intent);
            }
        });
    }

    private void alarmClickHandler(Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast = Toast.makeText(context, "Alarm will wake device in 15 seconds, put application in background mode!", Toast.LENGTH_LONG);
                toast.show();

                AlarmScheduleManager.setAlarm(context);
            }
        });
    }
}