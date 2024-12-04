package com.example.konyvtar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import android.widget.BaseAdapter;
import java.util.ArrayList;

public class BookAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Book> books;

    public BookAdapter(Context context, ArrayList<Book> books) {
        this.context = context;
        this.books = books;
    }

    @Override
    public int getCount() {
        return books.size();
    }

    @Override
    public Object getItem(int position) {
        return books.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.book_item, parent, false);
        }

        TextView titleText = convertView.findViewById(R.id.titleText);
        TextView authorText = convertView.findViewById(R.id.authorText);
        TextView pagesText = convertView.findViewById(R.id.pagesText);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        Book book = books.get(position);

        titleText.setText(book.getTitle());
        authorText.setText(book.getAuthor());
        pagesText.setText(String.valueOf(book.getPages()));

        deleteButton.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Törlés")
                    .setMessage("Biztosan törlöd ezt a könyvet?")
                    .setPositiveButton("Igen", (dialog, which) -> {
                        books.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, "Könyv törölve!", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Nem", null)
                    .show();
        });

        convertView.setOnClickListener(view -> {
            Intent intent = new Intent(context, DetailsActivity.class);
            intent.putExtra("title", book.getTitle());
            intent.putExtra("author", book.getAuthor());
            intent.putExtra("pages", book.getPages());
            context.startActivity(intent);
        });

        return convertView;
    }
}