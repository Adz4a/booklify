package com.example.booklify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.model.BookModel;

import java.util.ArrayList;

public class BasketFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<BookModel> bookModelHolder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);
        recyclerView = view.findViewById(R.id.basketRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        bookModelHolder = new ArrayList<>();

//        BookModel bookModel1 = new BookModel(R.drawable.harry_potter,  3,"Harry Potter and the Goblet of Fire", "Library Aitu");
//        bookModelHolder.add(bookModel1);
//
//        BookModel bookModel2 = new BookModel(R.drawable.sherlock,2, "Dilan 1990","Library Enu");
//        bookModelHolder.add(bookModel2);
//
//
//
//        recyclerView.setAdapter(new BasketAdapter(bookModelHolder,getContext()));

        return view;





    }

}


