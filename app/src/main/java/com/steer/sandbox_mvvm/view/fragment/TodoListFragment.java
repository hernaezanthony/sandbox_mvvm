package com.steer.sandbox_mvvm.view.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.base.BaseFragment;
import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Todo;
import com.steer.sandbox_mvvm.view.adapter.PostListAdapter;
import com.steer.sandbox_mvvm.view.adapter.TodoListAdapter;
import com.steer.sandbox_mvvm.viewmodel.PostViewModel;
import com.steer.sandbox_mvvm.viewmodel.TodoViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class TodoListFragment extends BaseFragment {

    private TodoViewModel todoViewModel;

    private TodoListAdapter todoListAdapter;

    private ArrayList<Todo> todoArrayList = new ArrayList<>();

    public TodoListFragment() {
    }

    @BindView(R.id.todoRecyclerView)
    RecyclerView todoRecyclerView;

    @OnClick({R.id.addTodoFab})
    public void OnClick(View view){
        switch (view.getId()){
            case R.id.addTodoFab:
                showAddItemDialog();
                break;
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();
        setUpRecyclerView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_todo_list, container, false);
    }

    private void setUpViewModel(){
        todoViewModel = ViewModelProviders.of(getActivity()).get(TodoViewModel.class);

        todoViewModel.todoMutableLiveData.observe(this, new Observer<ArrayList<Todo>>() {
            @Override
            public void onChanged(ArrayList<Todo> todo) {
                if (todo == null){
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                todoArrayList.clear();
                todoArrayList.addAll(todo);
                todoListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        todoRecyclerView.setLayoutManager(mLayoutManager);
        todoListAdapter = new TodoListAdapter(getContext(), todoArrayList);
        todoRecyclerView.setAdapter(todoListAdapter);

        //get recycler view content
        todoViewModel.getListOfTodo();
    }

    private void showAddItemDialog(){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_todo_item);

        Button addPostBtn = dialog.findViewById(R.id.saveBtn);
        TextInputEditText todoTitleTIE  = dialog.findViewById(R.id.todoTitleTIE);
        CheckBox iscompletedcheckbox  = dialog.findViewById(R.id.isCompleteCheckBox);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Todo todo = new Todo();
                todo.setTitle(todoTitleTIE.getText().toString());
                todo.setCompleted(iscompletedcheckbox.isChecked());
                todo.setId(1);
                todo.setUserId(1);

                todoViewModel.addItemToListOfTodo(todo);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}