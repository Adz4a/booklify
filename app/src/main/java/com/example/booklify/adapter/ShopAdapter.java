package com.example.booklify.adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.model.BookModel;

import java.util.ArrayList;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.viewHolder> {

    ArrayList<BookModel> bookModelHolder;

    public ShopAdapter(ArrayList<BookModel> bookModelHolder) {
        this.bookModelHolder = bookModelHolder;
    }



    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
//        holder.img.setImageResource(bookHolder.get(position).getImage());
//        holder.title.setText(bookHolder.get(position).getTitle());
          holder.library.setText(bookModelHolder.get(position).getLibrary());
    }

    @Override
    public int getItemCount() {
        return bookModelHolder.size();
    }

    class viewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title, library;
        public viewHolder(@NonNull View itemView)
        {
            super(itemView);
//            img=itemView.findViewById(R.id.image1);
//            title=itemView.findViewById(R.id.title1);
            library = itemView.findViewById(R.id.library);
        }
    }


}

