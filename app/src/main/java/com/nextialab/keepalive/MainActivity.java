package com.nextialab.keepalive;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String PREFERENCES = "preferences";

    private EditText mUrl;
    private EditText mPeriod;
    private Button mSave;
    private Switch mOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrl = (EditText) findViewById(R.id.url_to_hit);
        mPeriod = (EditText) findViewById(R.id.period);
        mSave = (Button) findViewById(R.id.save);
        mOn = (Switch) findViewById(R.id.on);
        // load saved preferences
        SharedPreferences prefs = getSharedPreferences("kepalive", MODE_PRIVATE);
        boolean isActive = prefs.getBoolean("active", false);
    }

    public void onSave(View view) {

    }

}
