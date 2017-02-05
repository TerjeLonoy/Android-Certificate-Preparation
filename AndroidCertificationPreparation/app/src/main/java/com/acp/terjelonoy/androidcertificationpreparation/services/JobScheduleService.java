package com.acp.terjelonoy.androidcertificationpreparation.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by terjelonoy on 04/02/2017.
 */


public class JobScheduleService extends JobService {
    public static final String JOB_SCHEDULER_DONE = "JOB_SCHEDULER_DONE";

    private BackgroundTask backgroundTask = new BackgroundTask();

    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        // This is run on main thread!
        // Need to start a background thread here

        backgroundTask.execute(jobParameters);

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {

        // Send a message that were DONE!
        Intent intent =  new Intent(JOB_SCHEDULER_DONE);
        intent.putExtra("success", true);
        sendBroadcast(intent);

        // Return true if should be rescheduled
        return backgroundTask.stopJob(jobParameters);
    }

    private class BackgroundTask extends AsyncTask<JobParameters, Void, JobParameters[]> {
        @Override
        protected JobParameters[] doInBackground(JobParameters... params) {
            // Faking some background task here

            return params;
        }

        @Override
        protected void onPostExecute(JobParameters[] result) {
            for (JobParameters jobParameters : result) {
                if (!hasJobBeenStopped(jobParameters)) {
                    jobFinished(jobParameters, false);
                }
            }
        }

        private boolean hasJobBeenStopped(JobParameters jobParameters) {
            // Logic for checking stop.
            // Normally check if the job has done what it should
            // And check that it should not be rescheduled

            return true;
        }

        public boolean stopJob(JobParameters jobParameters) {
            // Return true if should be rescheduled

            return false;
        }

    }
}
