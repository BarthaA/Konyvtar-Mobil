package com.example.konyvtar;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class DetailsActivity extends AppCompatActivity {

    private TextView titleText, authorText, pagesText, yearText;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        titleText = findViewById(R.id.titleText);
        authorText = findViewById(R.id.authorText);
        pagesText = findViewById(R.id.pagesText);
        yearText = findViewById(R.id.yearText);
        backButton = findViewById(R.id.backButton);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String author = intent.getStringExtra("author");
        int pages = intent.getIntExtra("pages", 0);

        titleText.setText(title);
        authorText.setText(author);
        pagesText.setText(String.valueOf(pages));

        int randomYear = new Random().nextInt(2024 - 1900) + 1900;
        yearText.setText(String.valueOf(randomYear));

        backButton.setOnClickListener(view -> finish());
    }
}
