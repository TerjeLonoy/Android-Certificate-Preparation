package com.acp.terjelonoy.androidcertificationpreparation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.acp.terjelonoy.androidcertificationpreparation.activities.LoaderExampleActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindExampleButtons();
    }

    private void bindExampleButtons () {
        Button loaderExampleButton = (Button) findViewById(R.id.loaders_example_button);
        navigationClickHandler(loaderExampleButton, new LoaderExampleActivity());
    }

    private void navigationClickHandler(Button button, final Activity destination) {
        final Context context = this;

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, destination.getClass());
                startActivity(intent);
            }
        });
    }
}