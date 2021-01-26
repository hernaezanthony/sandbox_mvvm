package com.steer.sandbox_mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

//import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.model.Todo;
import com.steer.sandbox_mvvm.repository.TodoRepository;

import java.util.ArrayList;

public class TodoViewModel extends AndroidViewModel {
    public TodoRepository todoRepository =  new TodoRepository();

    public MutableLiveData<ArrayList<Todo>> todoMutableLiveData = new MutableLiveData<>();

    public TodoViewModel(@NonNull Application application) {
        super(application);
        todoMutableLiveData = todoRepository.todoMutableLiveData;
    }

    public void addItemToListOfTodo(Todo todo){

        ArrayList<Todo> todolist = new ArrayList<>();

        if(todoMutableLiveData.getValue() != null){
            todolist = todoMutableLiveData.getValue();
        }

        todolist.add(todo);
        todoMutableLiveData.postValue(todolist);

    }

    public void getListOfTodo(){ todoRepository.getListOfTodo();}
}
