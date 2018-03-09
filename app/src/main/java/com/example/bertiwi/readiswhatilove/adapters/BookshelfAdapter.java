package com.example.bertiwi.readiswhatilove.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.model.Bookshelf;

import java.util.ArrayList;

/**
 * Created by Nil Ordoñez on 7/3/18.
 */

public class BookshelfAdapter extends RecyclerView.Adapter<BookshelfAdapter.BookshelfViewholder> {

    private ArrayList<Bookshelf> mBookshelfArrayList;


    static class BookshelfViewholder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private RecyclerView mRecyclerView;

        public BookshelfViewholder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.home_bookshelf_cardview);
            mRecyclerView = itemView.findViewById(R.id.home_bookshelf_book_recyclerview);
        }

        public void bindData(Bookshelf bookshelf){
            //TODO: aqui he de bindear el adapter dels books a dins. Els llisbres els trec del adapter
            //bookshelf.getBookArrayList();
            //mRecyclerView.
        }
    }


    @NonNull
    @Override
    public BookshelfViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_bookshelf_item,parent,false);
        return new BookshelfViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookshelfViewholder holder, int position) {
        Bookshelf item = mBookshelfArrayList.get(position);
        holder.bindData(item);
    }


    @Override
    public int getItemCount() {
        return mBookshelfArrayList.size();
    }
}
