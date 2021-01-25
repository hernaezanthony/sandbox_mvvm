package com.steer.sandbox_mvvm.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.model.Todo;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Todo> todoArrayList;

    public TodoListAdapter(Context context, ArrayList<Todo> todoArrayList) {
        this.context = context;
        this.todoArrayList = todoArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_todo_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.todoListTitle.setText(todoArrayList.get(position).getTitle());
        holder.isCompletedLabel.setText(getCompletionLabel(todoArrayList.get(position).isCompleted()));
    }

    @Override
    public int getItemCount() {
        return todoArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.todoListTitle)
        TextView todoListTitle;

        @BindView(R.id.isCompletedLabel)
        TextView isCompletedLabel;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public String getCompletionLabel(boolean isComplete){
        if(isComplete){return "Completed";}
        else{return "Incomplete";}
    }
}
