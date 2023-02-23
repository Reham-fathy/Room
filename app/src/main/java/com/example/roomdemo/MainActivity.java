package com.example.roomdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {
    private RecyclerView postsRecyclerView;
    private Button insertBtn, getBtn;
    private EditText titleEt, bodyEt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        insertBtn = findViewById(R.id.insert_button);
        getBtn = findViewById(R.id.get_button);
        titleEt = findViewById(R.id.editTextTextTitle);
        bodyEt = findViewById(R.id.editTextTextBody);
        postsRecyclerView = findViewById(R.id.rvPosts);
        final PostAdpter adapter=new PostAdpter();
        postsRecyclerView.setAdapter(adapter);
        final PostDatabase postDatabase=PostDatabase.getInstance(this);
        insertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postDatabase.postDao().insertPost(new Post(2,titleEt.getText().toString(),bodyEt.getText().toString()))
                        .subscribeOn(Schedulers.computation())
                        .subscribe(new CompletableObserver() {
                            @Override
                            public void onSubscribe(Disposable d) {

                            }

                            @Override
                            public void onComplete() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }
                        });

            }
        });

getBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        postDatabase.postDao().getPosts()
                .subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Post>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<Post> posts) {
                        adapter.setPosts(posts);
                        adapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }
});


    }
}