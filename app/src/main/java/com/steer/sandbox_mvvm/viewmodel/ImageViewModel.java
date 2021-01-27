package com.steer.sandbox_mvvm.viewmodel;

import android.app.Application;
import android.provider.ContactsContract;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.steer.sandbox_mvvm.model.Image;
import com.steer.sandbox_mvvm.repository.ImageRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ImageViewModel extends AndroidViewModel {
    public ImageRepository imageRepository = new ImageRepository();

    public MutableLiveData<ArrayList<Image>> mutableImageDataSet = new MutableLiveData<>();

    public ImageViewModel(@NonNull Application application) {
        super(application);
        mutableImageDataSet = imageRepository.mutableImageDataSet;
    }

    public void addItemToListofImageDataset(Image data){
        ArrayList<Image> imageDataset = new ArrayList<>();

        if(mutableImageDataSet.getValue() != null){
            imageDataset = mutableImageDataSet.getValue();
        }

        imageDataset.add(data);
        mutableImageDataSet.postValue(imageDataset);
    }

    public void getListOfImage(){imageRepository.getListOfImage();}
}
