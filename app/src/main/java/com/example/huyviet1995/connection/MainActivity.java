package com.example.huyviet1995.connection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.huyviet1995.connection.asynctask.AsyncLogin;
import com.example.huyviet1995.connection.asynctask.AsyncSignUp;

public class MainActivity extends AppCompatActivity {
    private Button mSignUpButton;
    private Button mLoginButton;
    private Button mResetPassword;
    private EditText mSignUpUsername;
    private EditText mSignUpPassword;
    private EditText mLoginUsername;
    private EditText mLoginPassword;
    private EditText mSignUpEmail;
    private EditText mSignUpAddress;

    private String loginUsername, loginPassword, signupUsername, signupPassword,signupEmail,signupAdress;
    private final int READ_TIMEOUT = 15000;
    private final int CONNECTION_TIMEOUT = 10000;
    public final static String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSignUpButton = (Button) findViewById(R.id.signUpButton);
        mLoginButton = (Button) findViewById(R.id.loginButton);
        mSignUpUsername = (EditText) findViewById(R.id.usernameSignUp);
        mSignUpPassword = (EditText) findViewById(R.id.passwordSignUp);
        mSignUpEmail = (EditText) findViewById(R.id.emailSignUp);
        mSignUpAddress = (EditText) findViewById(R.id.addressSignUp);
        mLoginUsername = (EditText) findViewById(R.id.usernameLogin);
        mLoginPassword = (EditText) findViewById(R.id.passwordLogin);
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLogin();
            }
        });
        mResetPassword = (Button) findViewById(R.id.reset_password);
        mResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }
    public void checkLogin() {
        loginUsername = mLoginUsername.getText().toString();
        loginPassword = mLoginPassword.getText().toString();
        new AsyncLogin(MainActivity.this).execute(loginUsername, loginPassword);
    }
    public void signUp() {
        signupUsername = mSignUpUsername.getText().toString();
        signupPassword = mSignUpPassword.getText().toString();
        signupEmail = mSignUpEmail.getText().toString();
        signupAdress = mSignUpAddress.getText().toString();
        new AsyncSignUp(MainActivity.this).execute(signupUsername, signupPassword,signupEmail,signupAdress);
    }
    public void resetPassword() {
        ResetPasswordDialog dialog = ResetPasswordDialog.newInstance();
        dialog.show(getSupportFragmentManager(),"dialog");
    }
}

