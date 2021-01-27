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
import com.steer.sandbox_mvvm.model.Todo;
import com.steer.sandbox_mvvm.model.Image;
import com.steer.sandbox_mvvm.view.adapter.ImageListAdapter;
import com.steer.sandbox_mvvm.view.adapter.TodoListAdapter;
import com.steer.sandbox_mvvm.viewmodel.ImageViewModel;
import com.steer.sandbox_mvvm.viewmodel.TodoViewModel;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class ImageListFragment extends BaseFragment {

    private ImageViewModel imageViewModel;

    private ImageListAdapter imageListAdapter;

    private ArrayList<Image> imageDataSet = new ArrayList<>();

    public ImageListFragment() {
    }

    @BindView(R.id.imageRecyclerView)
    RecyclerView imageRecyclerView;

    @OnClick({R.id.addImageItem})
    public void OnClick(View view){
        switch(view.getId()){
            case R.id.addImageItem:
                showAddItemDialog();
                break;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_image_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpViewModel();
        setUpRecyclerView();
    }

    private void setUpViewModel(){
        imageViewModel = ViewModelProviders.of(getActivity()).get(ImageViewModel.class);

        imageViewModel.mutableImageDataSet.observe(this, new Observer<ArrayList<Image>>() {
            @Override
            public void onChanged(ArrayList<Image> imageData) {
                if (imageData == null){
                    Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
                    return;
                }

                imageDataSet.clear();
                imageDataSet.addAll(imageData);
                imageListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void setUpRecyclerView(){
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        imageRecyclerView.setLayoutManager(mLayoutManager);
        imageListAdapter = new ImageListAdapter(getContext(), imageDataSet);
        imageRecyclerView.setAdapter(imageListAdapter);

        //get recycler view content
        imageViewModel.getListOfImage();
    }

    private void showAddItemDialog(){

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_add_image_item);

        Button addPostBtn = dialog.findViewById(R.id.saveBtn);
        TextInputEditText imageTitleTIE  = dialog.findViewById(R.id.imageTitleTIE);
        TextInputEditText imageUrlTIE  = dialog.findViewById(R.id.imageUrlTIE);
        TextInputEditText imageThumbUrlTIE  = dialog.findViewById(R.id.imageThumbUrlTIE);
        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Image image = new Image();
                image.setTitle(imageTitleTIE.getText().toString());
                image.setUrl(imageUrlTIE.getText().toString());
                image.setThumbnailUrl(imageThumbUrlTIE.getText().toString());
                image.setId(1);
                image.setAlbumId(1);

                imageViewModel.addItemToListofImageDataset(image);
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}