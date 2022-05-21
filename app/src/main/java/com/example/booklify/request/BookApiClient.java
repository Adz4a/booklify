package com.example.booklify.request;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.booklify.model.BookModel;

import java.util.List;

public class BookApiClient {

    private static BookApiClient instance;

//    private RetrieveBooksRunnable retrieveBooksRunnable;

    private MutableLiveData<List<BookModel>> mBooks;

    public static BookApiClient getInstance(){
        if (instance == null){
            new BookApiClient();
        }
        return instance;
    }

    private BookApiClient(){
        mBooks = new MutableLiveData<>();
    }

    public LiveData<List<BookModel>> getBooks(){
        return mBooks;
    }

//    public void searchBookApi(int id){

//        if (retrieveBooksRunnable != null){
//            retrieveBooksRunnable = null;
//        }

//        retrieveBooksRunnable = new RetrieveBooksRunnable(id);

//        final Future myHandler = AppExecutor.getInstance().networkIo().submit(retrieveBooksRunnable);

//        AppExecutor.getInstance().networkIo().schedule(new Runnable() {
//            @Override
//            public void run() {
//                myHandler.cancel(true);
//            }
//        }, 3000, TimeUnit.MILLISECONDS);
//    }

//    private class RetrieveBooksRunnable implements Runnable {
//
//        private int id;
//        boolean cancelRequest;
//
//        public RetrieveBooksRunnable(int id) {
//            this.id = id;
//            this.cancelRequest = false;
//        }
//
////        @Override
////        public void run() {
////
////            try {
////                Response response = getBooks().execute();
////
////                if (cancelRequest){
////                    return;
////                }
////
//////                if (response.code() == 200){
//////                    List<BookModel> list = new ArrayList<>(((BookResponse)response.body()).getId());
//////
//////                }
////
////                else{
////                    String error = response.errorBody().string();
////                    Log.v("Tag","SEARCH ");
////                    mBooks.postValue(null);
////                }
////
////            }
////
////            catch (IOException e) {
////                e.printStackTrace();
////                mBooks.postValue(null);
////            }
////
////
////
////        }
//
////        private Call<List<BookModel>> getBooks(){
////            return Services.getBookApi().getBooks();
////        }
//
//        private void CancelRequest(){
//            Log.v("Tag", "Cancelling Search Book ");
//            cancelRequest = true;
//        }
//
//    }


}

