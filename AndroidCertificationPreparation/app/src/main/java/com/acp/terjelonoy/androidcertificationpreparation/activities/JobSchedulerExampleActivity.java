package com.acp.terjelonoy.androidcertificationpreparation.activities;

import android.app.Activity;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.acp.terjelonoy.androidcertificationpreparation.R;
import com.acp.terjelonoy.androidcertificationpreparation.services.JobScheduleService;

/**
 * Created by terjelonoy on 04/02/2017.
 */

public class JobSchedulerExampleActivity extends Activity {
    private static final int SERVICE_JOB_ID = 929;
    private BroadcastReceiver receiver;

    private Activity activity = this;
    private TextView jobDoneLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobschedule_example);

        jobDoneLabel = (TextView) activity.findViewById(R.id.job_done);

        registerJob();
        addReceiver();
    }

    private void registerJob() {
        ComponentName serviceComponent = new ComponentName(this, JobScheduleService.class);
        JobInfo.Builder builder = new JobInfo.Builder(SERVICE_JOB_ID, serviceComponent);

        builder.setMinimumLatency(0)
                .setOverrideDeadline(5 * 1000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresDeviceIdle(false)
                .setRequiresCharging(false);

        JobScheduler scheduler = (JobScheduler) getApplication().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        scheduler.schedule(builder.build());
    }

    private void addReceiver() {
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.hasExtra("success")) {
                    if (intent.getBooleanExtra("success", false)) {
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                jobDoneLabel.setVisibility(View.VISIBLE);
                            }
                        });
                    }
                }
            }
        };

        registerReceiver(receiver, new IntentFilter(JobScheduleService.JOB_SCHEDULER_DONE));
    }

    @Override
    protected void onResume() {
        super.onResume();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                jobDoneLabel.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
