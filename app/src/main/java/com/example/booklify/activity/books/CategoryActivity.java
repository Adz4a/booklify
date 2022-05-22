package com.example.booklify.activity.books;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.booklify.R;
import com.example.booklify.adapter.CategoryAdapter;
import com.example.booklify.model.BookModel;
import com.example.booklify.response.BookResponse;
import com.example.booklify.viewmodels.BookListViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<BookModel> bookModelHolder;
    List<BookModel> categoryModel;
    BookListViewModel viewModel;

    CategoryAdapter categoryAdapter;
    boolean fantasy,history,classic, adventure,romance,health;
    TextView category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        LinearLayout back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        category = findViewById(R.id.category);

        fantasy = getIntent().getBooleanExtra("fantasy",false);
        history = getIntent().getBooleanExtra("history",false);
        classic = getIntent().getBooleanExtra("classic",false);
        adventure = getIntent().getBooleanExtra("adventure",false);
        romance = getIntent().getBooleanExtra("romance",false);
        health = getIntent().getBooleanExtra("health",false);

        if (fantasy){
            category.setText(getIntent().getStringExtra("category"));
            getFantasy();
        }
        if (history){
            category.setText(getIntent().getStringExtra("category"));
            getHistory();
        }
        if (classic){
            category.setText(getIntent().getStringExtra("category"));
            getClassic();
        }
        if(adventure){
            category.setText(getIntent().getStringExtra("category"));
            getAdventure();
        }
        if(romance){
            category.setText(getIntent().getStringExtra("category"));
            getRomance();
        }
        if(health){
            category.setText(getIntent().getStringExtra("category"));
            getHealth();
        }


        EditText editText = findViewById(R.id.filter);


        recyclerView = findViewById(R.id.categoryRecycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

        categoryAdapter = new CategoryAdapter(getApplicationContext(), bookModelHolder);
        recyclerView.setItemAnimator(new DefaultItemAnimator());




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



    private void getFantasy() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<>();
                    for (BookModel bookModel: bookModelHolder){
                        if (bookModel.getCategory() == 1){
                            categoryModel.add(bookModel);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void getRomance() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if (bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<>();
                    for (BookModel bookModel : bookModelHolder) {
                        if (bookModel.getCategory() == 2) {
                            categoryModel.add(bookModel);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void getAdventure() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<>();
                    for (BookModel bookModel: bookModelHolder){
                        if (bookModel.getCategory() == 3){
                            categoryModel.add(bookModel);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void getClassic() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<BookModel>();
                    for (BookModel categoryModels: bookModelHolder){
                        if (categoryModels.getCategory() == 4){
                            categoryModel.add(categoryModels);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void getHistory() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<BookModel>();
                    for (BookModel categoryModels: bookModelHolder){
                        if (categoryModels.getCategory() == 5){
                            categoryModel.add(categoryModels);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void getHealth() {
        viewModel = ViewModelProviders.of(this).get(BookListViewModel.class);
        viewModel.getBookListObserver().observe(this, new Observer<BookResponse>() {
            @Override
            public void onChanged(BookResponse bookResponse) {
                if(bookResponse != null) {
                    bookModelHolder = bookResponse.getBooks();
                    categoryModel = new ArrayList<BookModel>();
                    for (BookModel categoryModels: bookModelHolder){
                        if (categoryModels.getCategory() == 6){
                            categoryModel.add(categoryModels);
                        }
                    }

                    categoryAdapter.setMovieList(categoryModel);
                    recyclerView.setAdapter(categoryAdapter);

                }
            }
        });
    }

    private void filter(String text) {
        ArrayList<BookModel> filteredList = new ArrayList<>();

        for (BookModel item : categoryModel) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                filteredList.add( item);
            }
        }

        categoryAdapter.filterList(filteredList);
    }
}