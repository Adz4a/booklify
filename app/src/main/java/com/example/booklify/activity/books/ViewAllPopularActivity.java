package com.example.booklify.activity.books;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.adapter.ViewAllPopularAdapter;
import com.example.booklify.model.BookModel;
import com.example.booklify.response.BookResponse;
import com.example.booklify.viewmodels.BookListViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewAllPopularActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<BookModel> bookModelHolder;
    List<BookModel> bookModelList;
    BookListViewModel viewModel;
    ViewAllPopularAdapter viewAllPopularAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_popular);

        LinearLayout back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        EditText editText = findViewById(R.id.filter);


        recyclerView = findViewById(R.id.popularRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        viewAllPopularAdapter = new ViewAllPopularAdapter(getApplicationContext(), bookModelHolder);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    bookModelList = new ArrayList<>();
                    for (BookModel bookModel: bookModelHolder){
                        if (bookModel.isPopularity()){
                            bookModelList.add(bookModel);
                        }
                    }
                    viewAllPopularAdapter.setMovieList(bookModelList);
                    recyclerView.setAdapter(viewAllPopularAdapter);
                }
            }
        });


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

        viewModel.makeApiCall();

    }


    private void filter(String text) {
        ArrayList<BookModel> filteredList = new ArrayList<>();

        for (BookModel item : bookModelHolder) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
            }
        }
        viewAllPopularAdapter.filterList(filteredList);
    }




}
