package com.example.uselessmachine;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonSelfDestruct;
    private Switch switchUseless;



    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wireWidgets();
        setListeners();
    }

    private void wireWidgets() {
        buttonSelfDestruct = findViewById(R.id.button_main_selfdestruct);
        switchUseless = findViewById(R.id.switch_main_useless);
    }

    private void setListeners() {
        //TODO self destruct button

        buttonSelfDestruct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSelfDestructSequence();
            }
        });


        switchUseless.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    startSwitchOffTimer();
                }
                //Toast.makeText(MainActivity.this, "on", Toast.LENGTH_SHORT).show();

                else {
                    //Toast.makeText(MainActivity.this, "Off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void startSelfDestructSequence() {

        buttonSelfDestruct.setEnabled(false);
        new CountDownTimer(5000, 1000) {
            int stuff = 5;
            @Override
            public void onTick(long millisUntilFinished) {
                buttonSelfDestruct.setText("Destruct in: " + stuff);
                stuff--;
            }

            @Override
            public void onFinish() {
                finish();
            }
        }.start();
    }

    private void startSwitchOffTimer() {

        new CountDownTimer(5000, 5000) {

            @Override
            public void onTick(long millisUntilFinished) {

                if (!switchUseless.isChecked()) {
                    Log.d(TAG, "onTick: cancelling");
                    //Toast.makeText(MainActivity.this, "cancelling", Toast.LENGTH_SHORT).show();
                    cancel();
                }

            }

            @Override
            public void onFinish() {

                switchUseless.setChecked(false);

            }
        }.start();

    }
}
