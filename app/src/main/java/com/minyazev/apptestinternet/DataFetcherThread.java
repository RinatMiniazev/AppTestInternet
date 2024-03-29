package com.minyazev.apptestinternet;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class DataFetcherThread extends Thread{

    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    private Handler mHandler;
    public DataFetcherThread (Handler handler){
        this.mHandler = handler;
    }

    @Override
    public void run() {

        try {
            URL url = new URL("https://example.com");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null){

                builder.append(line).append("\n");
            }
            reader.close();
            Message message = mHandler.obtainMessage(SUCCESS, builder.toString());
            mHandler.sendMessage(message);


        } catch (Exception e){

            e.printStackTrace();
            mHandler.sendEmptyMessage(ERROR);

        }
    }
}
