package com.example.parcial2;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Request {
    private Handler handler;
    private String url;

    public Request(String url, Handler handler) {
        this.url = url;
        this.handler = handler;
    }

    public void run() {
        try {
            URL address = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) address.openConnection();
            int code = connection.getResponseCode();

            if(code == HttpURLConnection.HTTP_OK) {
                InputStream is = connection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder builder = new StringBuilder();
                String currentLine;
                while ((currentLine = br.readLine()) != null){
                    builder.append(currentLine);
                }
                String json = builder.toString();
                Log.wtf("REQUEST", json);
                JSONArray result = new JSONArray(json);
                Message message = new Message();
                message.obj = result;
                handler.sendMessage(message);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
