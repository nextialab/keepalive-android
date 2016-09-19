package com.nextialab.keepalive;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String PREFERENCES = "preferences";
    private static final String ACTIVE = "active";
    private static final String URL = "url";
    private static final String PERIOD = "period";

    private EditText mUrl;
    private EditText mPeriod;
    private Button mSave;
    private Switch mOn;

    private CompoundButton.OnCheckedChangeListener mOnCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
            int period = getSharedPreferences(PREFERENCES, MODE_PRIVATE).getInt(PERIOD, -1);
            SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES, MODE_PRIVATE).edit();
            if (isChecked) {
                MyAlarmManager.setAlarm(MainActivity.this, period);
                editor.putBoolean(ACTIVE, true);
            } else {
                MyAlarmManager.removeAlarm(MainActivity.this, period);
                editor.putBoolean(ACTIVE, false);
            }
            editor.apply();
            prepareView(isChecked);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = (EditText) findViewById(R.id.url_to_hit);
        mPeriod = (EditText) findViewById(R.id.period);
        mSave = (Button) findViewById(R.id.save);
        mOn = (Switch) findViewById(R.id.on);
        boolean isActive = getSharedPreferences(PREFERENCES, MODE_PRIVATE).getBoolean(ACTIVE, false);
        mOn.setChecked(isActive);
        mOn.setOnCheckedChangeListener(mOnCheckedChangeListener);
        prepareView(isActive);
    }

    private void prepareView(boolean isActive) {
        SharedPreferences prefs = getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        String url = prefs.getString(URL, "");
        int period = prefs.getInt(PERIOD, -1); // in seconds
        mUrl.setText(url);
        mPeriod.setText(String.format(Locale.getDefault(), "%d", period));
        if (isActive) {
            mUrl.setEnabled(false);
            mPeriod.setEnabled(false);
            mSave.setEnabled(false);
        } else {
            mUrl.setEnabled(true);
            mPeriod.setEnabled(true);
            mSave.setEnabled(true);
        }
    }

    public void onSave(View view) {
        SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES, MODE_PRIVATE).edit();
        editor.putString(URL, mUrl.getText().toString());
        editor.putInt(PERIOD, Integer.parseInt(mPeriod.getText().toString()));
        editor.apply();
    }

}
