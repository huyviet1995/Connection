package com.example.huyviet1995.connection;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by huyviet1995 on 8/22/16.
 */
public class SuccessActivity extends AppCompatActivity{
    private EditText mUsername,mEmail,mAddress;
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.success_activity);
        mUsername = (EditText)findViewById(R.id.username);
        mEmail = (EditText)findViewById(R.id.email);
        mAddress = (EditText)findViewById(R.id.address);
        Intent intent = getIntent();
        String[] messages = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);
        mUsername.setText(messages[0]);
        mEmail.setText(messages[1]);
        mAddress.setText(messages[2]);
    }
}
