package com.example.booklify.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.adapter.BasketAdapter;
import com.example.booklify.adapter.BookmarkAdapter;
import com.example.booklify.model.BasketModel;
import com.example.booklify.model.BookModel;
import com.example.booklify.model.BookmarkModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<BookModel> bookModelHolder;
    List<BasketModel> basketModels;
    BasketAdapter basketAdapter;
    private String bookId;

    private FirebaseFirestore mDb;
    private FirebaseAuth mAuth;

    Button summary;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        mDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        summary = view.findViewById(R.id.summary);


        recyclerView = view.findViewById(R.id.basketRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        basketModels = new ArrayList<>();
        basketAdapter = new BasketAdapter(basketModels,getContext() );
        recyclerView.setAdapter(basketAdapter);


        if(mAuth.getCurrentUser() != null) {

            mDb.collection("CurrentUser").document(mAuth.getCurrentUser().getUid())
                    .collection("cartShop").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
//                                if (task.getResult().getDocuments().isEmpty()) {
//                                    isEmpty.setVisibility(View.VISIBLE);
//                                }
//                                else {
//                                    isEmpty.setVisibility(View.INVISIBLE);
                                    for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {

                                        bookId = documentSnapshot.getId();
                                        BasketModel basketModel = documentSnapshot.toObject(BasketModel.class);
                                        basketModel.setDocumentId(bookId);
                                        basketModels.add(basketModel);
                                        basketAdapter.notifyDataSetChanged();

                                        calculateSumList(basketModels);

//                                    }
                                }
                            }
                        }
                    });
        }



        return view;


    }


    public void calculateSumList(List<BasketModel> list) {
        double totalSum = 0.0;
        for(BasketModel model: list){
            totalSum += model.getTotalPrice();
        }
        summary.setText("Total Sum: " + totalSum);
    }


}


