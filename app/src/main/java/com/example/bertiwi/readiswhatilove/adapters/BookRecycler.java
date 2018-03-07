package com.example.bertiwi.readiswhatilove.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.model.Book;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Bertiwi on 07/03/2018.
 */

public class BookRecycler extends RecyclerView.Adapter<BookRecycler.BookViewHolder> {

    private ArrayList<Book> datos;

    public BookRecycler(ArrayList<Book> datos) {
        this.datos = datos;
    }

    public class BookViewHolder extends RecyclerView.ViewHolder{

        private ImageView image;
        private TextView title;
        private TextView autor;

        public BookViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_book_profile);
            autor = itemView.findViewById(R.id.autor_book_profile);
        }

        public void bindBook(Book book){
            Picasso.with(image.getContext())
                    .load(book.getImage())
                    .into(image);
            title.setText(book.getTitle());
            autor.setText(book.getAuthor());
        }
    }

    @Override
    public BookRecycler.BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_books_layout, parent, false);

        BookRecycler.BookViewHolder bookViewHolder = new BookRecycler.BookViewHolder(v);
        return bookViewHolder;
    }

    @Override
    public void onBindViewHolder(BookRecycler.BookViewHolder holder, int position) {
        Book item = datos.get(position);

        holder.bindBook(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }
}
