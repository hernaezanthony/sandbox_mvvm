package com.steer.sandbox_mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.model.Post;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Post> postArrayList;

    public PostListAdapter(Context context, ArrayList<Post> postArrayList) {
        this.context = context;
        this.postArrayList = postArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_post_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.postTitleLabel.setText(postArrayList.get(position).getTitle());
        holder.postBodyLabel.setText(postArrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return postArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.postTitleLabel)
        TextView postTitleLabel;

        @BindView(R.id.postBodyLabel)
        TextView postBodyLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
