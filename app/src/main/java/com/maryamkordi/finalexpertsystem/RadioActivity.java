package com.maryamkordi.finalexpertsystem;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RadioActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_radio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.radio), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        findViewById(R.id.button_submit).setOnClickListener(v -> {
            // بررسی اینکه آیا همه سوال‌ها پاسخ داده شده‌اند یا نه
            if (isAllQuestionsAnswered()) {
                try {
                    Intent intent = new Intent(RadioActivity.this, ResultActivity.class);

                    // دریافت مقادیر از RadioGroup ها
                    intent.putExtra("question_reading_time", getSelectedOption(R.id.radio_group_reading_time));
                    intent.putExtra("question_character", getSelectedOption(R.id.radio_group_character));
                    intent.putExtra("question_trait", getSelectedOption(R.id.radio_group_trait));
                    intent.putExtra("question_mind", getSelectedOption(R.id.radio_group_mind));
                    intent.putExtra("question_imaginaryWorld", getSelectedOption(R.id.radio_group_imaginaryWorld));
                    intent.putExtra("question_belief", getSelectedOption(R.id.radio_group_belief));
                    intent.putExtra("question_message", getSelectedOption(R.id.radio_group_message));
                    intent.putExtra("question_sense", getSelectedOption(R.id.radio_group_sense));
                    intent.putExtra("question_time", getSelectedOption(R.id.radio_group_time));
                    intent.putExtra("question_theEnd", getSelectedOption(R.id.radio_group_theEnd));
                    intent.putExtra("question_strange", getSelectedOption(R.id.radio_group_strange));

                    startActivity(intent);
                } catch (Exception e) {
                    // نمایش خطا در صورت بروز مشکل
                    e.printStackTrace();
                    Toast.makeText(RadioActivity.this, "خطا در ارسال داده‌ها", Toast.LENGTH_SHORT).show();
                }
            } else {
                // نمایش پیام توست در صورتی که همه سوال‌ها پاسخ داده نشده باشد
                Toast.makeText(RadioActivity.this, "لطفاً به همه سوالات پاسخ دهید", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // متد برای بررسی اینکه آیا همه سوالات پاسخ داده شده‌اند یا نه
    private boolean isAllQuestionsAnswered() {
        return !getSelectedOption(R.id.radio_group_reading_time).isEmpty() &&
                !getSelectedOption(R.id.radio_group_character).isEmpty() &&
                !getSelectedOption(R.id.radio_group_trait).isEmpty() &&
                !getSelectedOption(R.id.radio_group_mind).isEmpty() &&
                !getSelectedOption(R.id.radio_group_imaginaryWorld).isEmpty() &&
                !getSelectedOption(R.id.radio_group_belief).isEmpty() &&
                !getSelectedOption(R.id.radio_group_message).isEmpty() &&
                !getSelectedOption(R.id.radio_group_sense).isEmpty() &&
                !getSelectedOption(R.id.radio_group_time).isEmpty() &&
                !getSelectedOption(R.id.radio_group_theEnd).isEmpty() &&
                !getSelectedOption(R.id.radio_group_strange).isEmpty();
    }

    // متدی که مقدار انتخاب شده از هر RadioGroup را باز می‌گرداند
    private String getSelectedOption(int radioGroupId) {
        RadioGroup radioGroup = findViewById(radioGroupId);
        if (radioGroup == null) return ""; // اگر RadioGroup یافت نشد، مقدار خالی برمی‌گرداند
        int selectedId = radioGroup.getCheckedRadioButtonId();
        if (selectedId == -1) return ""; // اگر هیچ گزینه‌ای انتخاب نشده باشد، رشته خالی برمی‌گرداند
        View selectedButton = findViewById(selectedId);
        return getResources().getResourceEntryName(selectedButton.getId());
    }
}

