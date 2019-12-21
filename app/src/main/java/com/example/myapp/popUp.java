package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class popUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        int height = displayMetrics.heightPixels;
        getWindow().setLayout((int) (width*0.8), (int) (height*0.8));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String title = getIntent().getStringExtra("title");
        String data = getIntent().getStringExtra("data");
        if(!(title == null)) {
            TextView screenTitle = (TextView) findViewById(R.id.title);
            screenTitle.setText(title);
        }

        Log.i("TEST", data);
        if(!(data == null)) {
            TextView screenData = (TextView) findViewById(R.id.data);
            screenData.setText(data);
        }
    }
}
