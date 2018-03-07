package com.example.bertiwi.readiswhatilove.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private Context context;
    private final BookRecycler.OnItemClickListener listener;
    private ArrayList<Book> listBookOrigin;

    public interface OnItemClickListener {
        void onItemClick(Book book);
    }
    public BookRecycler(ArrayList<Book> datos, Context context, OnItemClickListener listener) {
        this.datos = datos;
        this.context = context;
        this.listener = listener;
        this.listBookOrigin = new ArrayList<>();
        listBookOrigin.addAll(datos);
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

        public void bindBook(final Book book, Context context, final BookRecycler.OnItemClickListener listener){
            Picasso.with(context)
                    .load(book.getImage())
                    .into(image);
            title.setText(book.getTitle());
            autor.setText(book.getAuthor());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book);
                }
            });
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

        holder.bindBook(item, context, listener);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    public void filter(String charText) {
        charText = charText.toLowerCase();
        datos.clear();
        if (charText.length() == 0) {
            datos.addAll(listBookOrigin);
        } else {
            for (Book book : listBookOrigin) {
                if (book.getTitle().toLowerCase().contains(charText)) {
                    datos.add(book);
                }
                if (book.getAuthor().toLowerCase().contains(charText)){
                    datos.add(book);
                }
            }
        }
        notifyDataSetChanged();
    }
}
