package com.maryamkordi.finalexpertsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // دکمه شروع
        Button buttonStart = findViewById(R.id.button_start);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // انتقال به اکتیویتی Radio
                Intent intent = new Intent(MainActivity.this, RadioActivity.class);
                startActivity(intent);
            }
        });

        // دکمه اطلاعات سازنده
        Button buttonBuilderInfo = findViewById(R.id.button_builder_info);
        buttonBuilderInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // انتقال به اکتیویتی Builder
                Intent intent = new Intent(MainActivity.this, BuilderActivity.class);
                startActivity(intent);
            }
        });
    }
}