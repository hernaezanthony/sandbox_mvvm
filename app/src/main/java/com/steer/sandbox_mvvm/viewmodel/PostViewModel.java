package com.steer.sandbox_mvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.steer.sandbox_mvvm.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostViewModel extends AndroidViewModel {


    public MutableLiveData<ArrayList<Post>> postsMutableLiveData = new MutableLiveData<>();

    public PostViewModel(@NonNull Application application) {
        super(application);
    }

    public void addItemToListOfPost(Post post){

        ArrayList<Post> posts = new ArrayList<>();

        if(postsMutableLiveData.getValue() != null){
            posts = postsMutableLiveData.getValue();
        }

        posts.add(post);
        postsMutableLiveData.postValue(posts);

    }
}
