package com.maryamkordi.finalexpertsystem;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });



        String result = getMatchingBook();
        try {
            JSONObject book = new JSONObject(result);
            ((TextView) findViewById(R.id.text_book_name)).setText("نام کتاب: " + book.getString("book_name"));
            ((TextView) findViewById(R.id.text_author_name)).setText("نویسنده: " + book.getString("author_name"));
            ((TextView) findViewById(R.id.text_summary)).setText("خلاصه: " + book.getString("summary"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String getMatchingBook() {
        try {
            InputStream is = getAssets().open("books.json");
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();

            String json = new String(buffer, "UTF-8");
            JSONArray jsonArray = new JSONArray(json);

            // دریافت پارامترها از intent
            Bundle extras = getIntent().getExtras();
            if (extras == null) return "{}";

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject book = jsonArray.getJSONObject(i);
                boolean match = true;

                for (String key : extras.keySet()) {
                    if (!book.optString(key, "").equals(extras.getString(key))) {
                        match = false;
                        break;
                    }
                }

                if (match) return book.toString();
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return "{}";
    }
}
