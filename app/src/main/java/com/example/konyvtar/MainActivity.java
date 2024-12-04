package com.example.konyvtar;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText titleInput, authorInput, pagesInput;
    private Button addButton;
    private ListView booksListView;
    private BookAdapter bookAdapter;
    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleInput = findViewById(R.id.titleInput);
        authorInput = findViewById(R.id.authorInput);
        pagesInput = findViewById(R.id.pagesInput);
        addButton = findViewById(R.id.addButton);
        booksListView = findViewById(R.id.booksListView);

        books = new ArrayList<>();
        bookAdapter = new BookAdapter(this, books);
        booksListView.setAdapter(bookAdapter);

        addButton.setOnClickListener(view -> addBook());
    }

    private void addBook() {
        String title = titleInput.getText().toString().trim();
        String author = authorInput.getText().toString().trim();
        String pagesStr = pagesInput.getText().toString().trim();

        if (title.isEmpty() || author.isEmpty() || pagesStr.isEmpty()) {
            Toast.makeText(this, "Minden mezőt ki kell tölteni!", Toast.LENGTH_SHORT).show();
            return;
        }

        int pages;
        try {
            pages = Integer.parseInt(pagesStr);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Az oldalszámnak számnak kell lennie!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (pages < 50) {
            Toast.makeText(this, "Az oldalszám nem lehet kevesebb, mint 50!", Toast.LENGTH_SHORT).show();
            return;
        }

        books.add(new Book(title, author, pages));
        bookAdapter.notifyDataSetChanged();

        titleInput.setText("");
        authorInput.setText("");
        pagesInput.setText("");
    }
}