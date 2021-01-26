package com.steer.sandbox_mvvm.view.fragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.base.BaseFragment;
import com.steer.sandbox_mvvm.model.Post;
import com.steer.sandbox_mvvm.view.adapter.PostListAdapter;
import com.steer.sandbox_mvvm.viewmodel.PostViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment {

    private PostViewModel postViewModel;

    private PostListAdapter postListAdapter;

    private ArrayList<Post> postArrayList = new ArrayList<>();

    public HomeFragment() {
    }

    @BindView(R.id.postsRecyclerView)
    RecyclerView postsRecyclerView;

    @OnClick({R.id.addPostFab})
    public void OnClick(View view){

        switch (view.getId()){

            case R.id.addPostFab:

                showAddItemDialog();

                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        setUpVieModelAndObservers();
        setUpRecyclerView();

    }

    private void setUpVieModelAndObservers(){

        postViewModel = ViewModelProviders.of(getActivity()).get(PostViewModel.class);

        postViewModel.postsMutableLiveData.observe(this, new Observer<ArrayList<Post>>() {
            @Override
            public void onChanged(ArrayList<Post> posts) {

                if (posts == null){
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                postArrayList.clear();
                postArrayList.addAll(posts);
                postListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpRecyclerView(){

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        postsRecyclerView.setLayoutManager(mLayoutManager);
        postListAdapter = new PostListAdapter(getContext(), postArrayList);
        postsRecyclerView.setAdapter(postListAdapter);

        //get recyclerView content
        postViewModel.getListOfPost();
    }

    private void showAddItemDialog(){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_item);

        Button addPostBtn = dialog.findViewById(R.id.addPostBtn);
        TextInputEditText postTitleTIE  = dialog.findViewById(R.id.postTitleTIE);
        TextInputEditText postBodyTIE  = dialog.findViewById(R.id.postBodyTIE);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Post post = new Post();
                post.setTitle(postTitleTIE.getText().toString());
                post.setBody(postBodyTIE.getText().toString());
                post.setId(1);
                post.setUserId(1);

                postViewModel.addItemToListOfPost(post);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}