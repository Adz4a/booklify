package com.example.booklify.fragments;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class BasketFragment extends Fragment {

    RecyclerView recyclerView;
    List<BasketModel> basketModels;
    BasketAdapter basketAdapter;
    private String bookId;

    private FirebaseFirestore mDb;
    private FirebaseAuth mAuth;

    Button summary;
    TextView isEmpty;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basket, container, false);

        mDb = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        summary = view.findViewById(R.id.summary);
        isEmpty = view.findViewById(R.id.isEmpty);

        EditText editText = view.findViewById(R.id.filter);


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
                                        summary.setVisibility(View.VISIBLE);
                                        calculateSumList(basketModels);
                                        isEmpty.setVisibility(View.GONE);


                                        summary.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                mDb.collection("CurrentUser").document(mAuth.getCurrentUser().getUid())
                                                        .collection("cartShop")
                                                        .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                            @Override
                                                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                                if (task.isSuccessful()) {
                                                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                                                        mDb.collection("CurrentUser").document(mAuth.getCurrentUser().getUid())
                                                                                .collection("cartShop").document(document.getId()).delete();
                                                                        Toast.makeText(getContext(), "The buy was successfully!", Toast.LENGTH_LONG).show();

                                                                    }
                                                                }
                                                            }
                                                        });
                                            }
                                        });
                                    }
                            }
                        }
                    });
        }

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



        return view;


    }


    public void calculateSumList(List<BasketModel> list) {
        int totalSum = 0;
        for(BasketModel model: list){
            totalSum += model.getPrice();
        }
        summary.setText("Total Sum: " + totalSum + "$");
    }

    private void filter(String text) {
        ArrayList<BasketModel> filteredList = new ArrayList<>();

        for (BasketModel item : basketModels) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }

        basketAdapter.filterList(filteredList);
    }


}


