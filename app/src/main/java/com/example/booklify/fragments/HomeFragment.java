package com.example.booklify.fragments;


import android.content.Intent;
import android.os.Bundle;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.activity.books.BookmarkActivity;
import com.example.booklify.activity.books.CategoryActivity;
import com.example.booklify.activity.books.DetailActivity;
import com.example.booklify.activity.books.ViewAllPopularActivity;
import com.example.booklify.adapter.HomeAdapter;
import com.example.booklify.adapter.RecommendationAdapter;
import com.example.booklify.model.BookModel;
import com.example.booklify.model.UserModel;
import com.example.booklify.response.BookResponse;
import com.example.booklify.viewmodels.BookListViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class HomeFragment extends Fragment {

    RecyclerView recyclerView, rcdRecyclerView;
    List<BookModel> bookModelHolder;
    List<BookModel> bookModelList;

    BookListViewModel viewModel;
    HomeAdapter homeAdapter;

    private Random randomGenerator = new Random();

    private TextView textUsername,viewAll;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userId;

    CategoryActivity categoryActivity;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.books);
        rcdRecyclerView = view.findViewById(R.id.rcd);

        EditText editText = view.findViewById(R.id.filter);

        textUsername = view.findViewById(R.id.username);
        getUsername();

        viewAll = view.findViewById(R.id.viewAll);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(),ViewAllPopularActivity.class));
            }
        });


        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);

        homeAdapter = new HomeAdapter(getContext(), bookModelHolder);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        rcdRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        randomGenerator = new Random();


        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    List<BookModel> bookModel1 = bookResponse.getBooks();
                    BookModel bookModels = bookModel1.get(randomGenerator.nextInt(bookModel1.size()));
                    List<BookModel> random = new ArrayList<>();
                    random.add(bookModels);
                    rcdRecyclerView.setAdapter(new RecommendationAdapter(getContext(), random));

                    bookModelList = new ArrayList<>();
                    for (BookModel bookModel: bookModelHolder){
                        if (bookModel.isPopularity()){
                            bookModelList.add(bookModel);
                        }
                    }

                    homeAdapter.setMovieList(bookModelList);
                    recyclerView.setAdapter(homeAdapter);

                } else {
                }
            }
        });

        viewModel.makeApiCall();


        Button fantasy = view.findViewById(R.id.fantasy);
        fantasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                intent.putExtra("romance",true);
                intent.putExtra("category","Fantasy");
                startActivity(intent);
            }
        });

        Button romance = view.findViewById(R.id.romance);
        romance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                intent.putExtra("romance",true);
                intent.putExtra("category","Romance");
                startActivity(intent);
            }
        });

        Button adventure = view.findViewById(R.id.adventure);
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                intent.putExtra("adventure",true);
                intent.putExtra("category","Adventure");
                startActivity(intent);
            }
        });


        Button classic = view.findViewById(R.id.classic);
        classic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                intent.putExtra("classic",true);
                intent.putExtra("category","Classic");
                startActivity(intent);
            }
        });

        Button history = view.findViewById(R.id.history);
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),CategoryActivity.class);
                intent.putExtra("history",true);
                intent.putExtra("category","History");
                startActivity(intent);
            }
        });






        return view;
    }

    private void getUsername() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("Users");
        userId = user.getUid();

        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserModel userProfile = snapshot.getValue(UserModel.class);

                if (userProfile != null){
                    String username = userProfile.username;
                    textUsername.setText(username);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(), "Something wrong happened!", Toast.LENGTH_LONG).show();
            }
        });

    }

}


