package com.example.huyviet1995.connection.asynctask;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.Toast;
import com.example.huyviet1995.connection.MainActivity;
import com.example.huyviet1995.connection.SuccessActivity;
import org.json.JSONArray;
import org.json.JSONException;
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
import java.util.ArrayList;
/**
 * Created by huyviet1995 on 8/24/16.
 */
public class AsyncLogin extends AsyncTask<String, String, ArrayList<String>> {
    public static final String EXTRA_MESSAGE = "MESSAGE";
    HttpURLConnection conn;
    URL url = null;
    String message;
    Activity mActivity;
    private static final int READ_TIMEOUT = 10000;
    private static final int CONNECTION_TIMEOUT = 15000;
    public AsyncLogin(Activity activity) {
        mActivity = activity;
    }
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        ArrayList<String> result = new ArrayList<String>();
        try {
            //Try to enter the URL message
            url = new URL("http://vietdanghuy.net23.net/android_connect/connectLogin.php");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            //set up HTTPurlConnection to send and receive data from the internet
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECTION_TIMEOUT);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("username", params[0])
                    .appendQueryParameter("password", params[1]);
            String query = builder.build().getEncodedQuery();

            //open connection for sending data
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
        } catch (IOException e1) {
            e1.printStackTrace();
            message = "exception";
        }

        try {
            int response_code = conn.getResponseCode();
            if (response_code == HttpURLConnection.HTTP_OK) {
                //Read the data sent from server
                InputStream inputStream = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                StringBuilder response= new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                JSONArray jsonArray = new JSONArray(response.toString());
                for (int i = 0; i <jsonArray.length();i++) {
                    result.add(jsonArray.getString(i));
                }
                return result;
            } else {
                message = "exception";
                result.add(message);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
            message = "exception";
            result.add(message);
            return result;
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            conn.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<String> result) {

        if (result.get(0).equals("true")) {
            Intent intent = new Intent(mActivity,SuccessActivity.class);
            Toast.makeText(mActivity,"Login successfully",Toast.LENGTH_SHORT).show();
            String[] messages = new String[]{result.get(1),result.get(2),result.get(3)};
            intent.putExtra(MainActivity.EXTRA_MESSAGE,messages);
            mActivity.startActivity(intent);
        }
        else {
            Toast.makeText(mActivity, "Sign up failed",Toast.LENGTH_SHORT).show();
        }
    }
}
