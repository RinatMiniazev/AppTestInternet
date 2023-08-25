package com.minyazev.apptestinternet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final int SUCCESS = 1;
    public static final int ERROR = 2;

    private TextView tvData;

    private Handler mHandler = new Handler(Looper.getMainLooper()){

        @Override
        public void handleMessage( Message msg) {
            if(msg.what == SUCCESS){
                String responseData = (String)msg.obj;
                tvData.setText(responseData);
            } else{
                if(msg.what == ERROR){
                    tvData.setText("Ошибка при получении данных");
                }
            }

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvData = findViewById(R.id.tvData);

        Button btnFetchData = findViewById(R.id.btnFetchData);

        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataFetcherThread thread = new DataFetcherThread(mHandler);
                thread.start();
            }
        });

    }
}