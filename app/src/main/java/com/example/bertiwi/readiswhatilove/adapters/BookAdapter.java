package com.example.bertiwi.readiswhatilove.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bertiwi.readiswhatilove.R;
import com.example.bertiwi.readiswhatilove.model.Book;

import java.util.ArrayList;

/**
 * BookAdapter para el HomeFragment
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewholder> {

    private ArrayList<Book> mBookArrayList;

    @NonNull
    @Override
    public BookViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.home_book_item,parent,false);
        return new BookViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewholder holder, int position) {
        Book item = mBookArrayList.get(position);
        holder.bindData(item);
    }

    @Override
    public int getItemCount() {
        return mBookArrayList.size();
    }

    static class BookViewholder extends RecyclerView.ViewHolder {

        private CardView mCardView;
        private ImageView mCoverImageView;
        private TextView mTitleTextView;
        private TextView mAuthorTextView;


        public BookViewholder(View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.home_book_cardview);
            mCoverImageView = itemView.findViewById(R.id.book_cover_imageview);
            mTitleTextView = itemView.findViewById(R.id.book_title_textview);
            mAuthorTextView = itemView.findViewById(R.id.book_author_textview);
        }

        public void bindData(Book book){
            //TODO: bindear datos a la view
            //TODO: load image
            mTitleTextView.setText(book.getTitle());
            //TODO: falta el autor, fer quan Berta actualitzi model
            //mAuthorTextView.setText(book.getAuthor());
            /*

        <ImageView
            android:id="@+id/book_cover_imageview"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            card_view:layout_constraintTop_toTopOf="parent" />

        <TextView
            android:id="@+id/book_title_textview"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginLeft="8dp"
            android:layout_marginStart="8dp"
            android:textSize="18sp"
            android:textStyle="bold"
            card_view:layout_constraintStart_toStartOf="parent"
            card_view:layout_constraintTop_toBottomOf="@id/book_cover_imageview" />

        <TextView
            android:id="@+id/book_author_textview"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_marginTop="2dp"
            android:textColor="@android:color/darker_gray"
            card_view:layout_constraintStart_toStartOf="@id/book_title_textview"
            card_view:layout_constraintTop_toBottomOf="@id/book_title_textview" />
             */

        }
    }
}