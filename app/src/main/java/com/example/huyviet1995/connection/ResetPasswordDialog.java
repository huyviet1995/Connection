package com.example.huyviet1995.connection;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.huyviet1995.connection.asynctask.AsyncResetPassword;

/**
 * Created by huyviet1995 on 8/23/16.
 */
public class ResetPasswordDialog extends DialogFragment {
    private Button mResetPassword;
    private EditText mPasswordField;
    private String mPassword,mEmail;

    private static final String argument = "EMAIL";

    static ResetPasswordDialog newInstance() {
        return new ResetPasswordDialog();
    }

    @Override
    public View onCreateView (LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        View v = layoutInflater.inflate(R.layout.password_dialog,container, false);
        mResetPassword = (Button) v.findViewById(R.id.password_reset);
        mPasswordField = (EditText) v.findViewById(R.id.reset_field);
        mEmail = mPasswordField.getText().toString();
        mResetPassword.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                new AsyncResetPassword(getActivity()).execute(mEmail);
            }
        });
        return v;
    }
}
