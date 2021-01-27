package com.steer.sandbox_mvvm.view.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.steer.sandbox_mvvm.R;
import com.steer.sandbox_mvvm.model.Image;
import com.steer.sandbox_mvvm.view.fragment.ImageListFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ImageListAdapter extends RecyclerView.Adapter<ImageListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Image> imageDataSet;

    public ImageListAdapter(Context context, ArrayList<Image> imageDataSet){
        this.context = context;
        this.imageDataSet = imageDataSet;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageTitle.setText(imageDataSet.get(position).getTitle());
        GlideUrl url = new GlideUrl(imageDataSet.get(position).getThumbnailUrl(), new LazyHeaders.Builder()
                .addHeader("User-Agent","PostmanRuntime/7.26.10").build());
        Glide.with(context).load(url).into(holder.realImage);


    }

    @Override
    public int getItemCount() {
        return imageDataSet.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.imageId)
        ImageView realImage;

        @BindView(R.id.imageTitle)
        TextView imageTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
