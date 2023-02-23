package com.example.roomdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class PostAdpter extends RecyclerView.Adapter<PostAdpter.ViewHolder> {
      private List<Post>posts=new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
   holder.bodyTV.setText(posts.get(position).getBody());
   holder.titleTV.setText(posts.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void setPosts(List<Post> post) {
        this.posts = post;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
   private TextView titleTV,bodyTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTV=itemView.findViewById(R.id.item_title_textView);
            bodyTV=itemView.findViewById(R.id.item_body_textView2);
        }

    }
}
