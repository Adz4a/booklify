package com.example.booklify.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.booklify.R;
import com.example.booklify.activity.books.DetailActivity;
import com.example.booklify.fragments.BasketFragment;
import com.example.booklify.model.BasketModel;
import com.example.booklify.model.BookModel;

import java.util.ArrayList;
import java.util.List;

public class BasketAdapter extends RecyclerView.Adapter<BasketAdapter.viewHolder> {

    List<BasketModel> bookModelHolder;
    Context context;

    public BasketAdapter(List<BasketModel> bookModelHolder, Context context) {
        this.bookModelHolder = bookModelHolder;
        this.context = context;

    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_basket,parent,false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        holder.title.setText(bookModelHolder.get(position).getTitle());
        holder.price.setText(String.valueOf(bookModelHolder.get(position).getTotalPrice()) + "$");
        Glide.with(context)
                .load(this.bookModelHolder.get(position).getImage())
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(context, DetailActivity.class);
                intent.putExtra("id", bookModelHolder.get(position).getId());
                intent.putExtra("title", bookModelHolder.get(position).getTitle());
                intent.putExtra("image", bookModelHolder.get(position).getImage());
                intent.putExtra("content", bookModelHolder.get(position).getContent());
                intent.putExtra("author", bookModelHolder.get(position).getAuthor());
                intent.putExtra("category", bookModelHolder.get(position).getCategory());
                intent.putExtra("popularity", bookModelHolder.get(position).isPopularity());
                intent.putExtra("library", bookModelHolder.get(position).getLibrary());
                intent.putExtra("price", String.valueOf(bookModelHolder.get(position).getPrice()) + "$");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bookModelHolder.size();
    }

    class viewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView title, price;
        public viewHolder(@NonNull View itemView)
        {
            super(itemView);
            img=itemView.findViewById(R.id.image);
            price=itemView.findViewById(R.id.price);
            title=itemView.findViewById(R.id.title);
        }
    }


}

