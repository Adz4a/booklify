package com.example.booklify.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.adapter.BookAdapter;
import com.example.booklify.model.BookModel;
import com.example.booklify.response.BookResponse;
import com.example.booklify.viewmodels.BookListViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookFragment extends Fragment{

    RecyclerView recyclerView;
    List<BookModel> bookModelHolder;
    BookListViewModel viewModel;
    BookAdapter bookAdapter;

    Map<String, Object> user;
    FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_book, container, false);

        recyclerView = view.findViewById(R.id.bookRecycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        bookAdapter = new BookAdapter(getContext(), bookModelHolder);

        EditText editText = view.findViewById(R.id.filter);

        db = FirebaseFirestore.getInstance();


        String type = getActivity().getIntent().getStringExtra("type");

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });


        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
                    @Override
                    public void onChanged(BookResponse bookResponse) {
                        if (bookResponse != null) {
                            bookModelHolder = bookResponse.getBooks();
                            bookAdapter.setMovieList(bookResponse.getBooks());
                            recyclerView.setAdapter(bookAdapter);

                            user = new HashMap<>();

                            for (BookModel bookModel : bookModelHolder) {

                                user.put("id", bookModel.getId());
                                user.put("title", bookModel.getTitle());
                                user.put("image", bookModel.getImage());
                                user.put("content", bookModel.getContent());
                                user.put("author", bookModel.getAuthor());
                                user.put("category", bookModel.getCategory());
                                user.put("popularity" , bookModel.isPopularity());
                                user.put("price",bookModel.getPrice());
                            }

                        }
                    }
                });


        //Fantasy
        if(type != null && type.equalsIgnoreCase("1")){
            db.collection("books").whereEqualTo("category","1")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }

        //Romance
        if(type != null && type.equalsIgnoreCase("2")){
            db.collection("books").whereEqualTo("category","2")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }

        //Adventure
        if(type != null && type.equalsIgnoreCase("3")){
            db.collection("books").whereEqualTo("category","3")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }


        //Classic
        if(type != null && type.equalsIgnoreCase("4")){
            db.collection("books").whereEqualTo("category","4")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }

        //Historical Novel
        if(type != null && type.equalsIgnoreCase("5")){
            db.collection("books").whereEqualTo("category","5")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }

//      Health
        if(type != null && type.equalsIgnoreCase("6")){
            db.collection("books").whereEqualTo("category","6")
                    .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            for (DocumentSnapshot documentSnapshot:task.getResult().getDocuments()){
                                BookModel viewAllModel = documentSnapshot.toObject(BookModel.class);
                                bookModelHolder.add(viewAllModel);
                                bookAdapter.notifyDataSetChanged();
                                recyclerView.setVisibility(View.VISIBLE);
                            }
                        }
                    });
        }

        viewModel.makeApiCall();

        return view;
    }


    private void filter(String text) {
        ArrayList<BookModel> filteredList = new ArrayList<>();

        for (BookModel item : bookModelHolder) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        bookAdapter.filterList(filteredList);
    }


}

