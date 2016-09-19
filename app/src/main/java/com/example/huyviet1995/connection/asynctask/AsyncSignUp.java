package com.example.huyviet1995.connection.asynctask;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by huyviet1995 on 8/24/16.
 */
public class AsyncSignUp extends AsyncTask<String, String, String> {
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECTION_TIMEOUT = 15000 ;
    private Context mContext;
    HttpURLConnection conn;
    private Activity mActivity;
    URL url = null;
    public AsyncSignUp(Activity activity) {
        mActivity = activity;
    }
    @Override
    protected String doInBackground(String... params) {
        //Connect the android to the specified URL
        try {
            url = new URL("http://vietdanghuy.net23.net/android_connect/connectSignUp.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", params[0])
                    .appendQueryParameter("password", params[1])
                    .appendQueryParameter("email",params[2])
                    .appendQueryParameter("address",params[3]);
            String query = builder.build().getEncodedQuery();
            //start writing out stream
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();

        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        }
        //Try to get the result from the server

        try {
            int response_code = conn.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                return result.toString();
            } else {
                return "Connection failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "exception";
        } finally {
            conn.disconnect();
        }
    }
    @Override
    protected void onPostExecute(String result) {
        Toast.makeText(mActivity,result,Toast.LENGTH_SHORT).show();
    }
}
