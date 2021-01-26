package com.steer.sandbox_mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

//import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Todo;

import java.util.ArrayList;

public class TodoViewModel extends AndroidViewModel {

    public MutableLiveData<ArrayList<Todo>> todoMutableLiveData = new MutableLiveData<>();

    public TodoViewModel(@NonNull Application application) {
        super(application);
    }

    public void addItemToListOfTodo(Todo todo){

        ArrayList<Todo> todolist = new ArrayList<>();

        if(todoMutableLiveData.getValue() != null){
            todolist = todoMutableLiveData.getValue();
        }

        todolist.add(todo);
        todoMutableLiveData.postValue(todolist);

    }
}
