package com.anhttvn.roselivewallpaper.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.anhttvn.roselivewallpaper.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SlideAdapter extends SliderViewAdapter<SlideAdapter.SliderAdapterVH> {

    private Context context;
    private List<String> mSliderItems = new ArrayList<>();
    private Onclick mOnclick;

    public SlideAdapter(Context context,List<String> list,Onclick click) {
        this.context = context;
        this.mSliderItems = list;
        this.mOnclick = click;
    }

    public void renewItems(List<String> sliderItems) {
        this.mSliderItems = sliderItems;
        notifyDataSetChanged();
    }

    public void deleteItem(int position) {
        this.mSliderItems.remove(position);
        notifyDataSetChanged();
    }

    public void addItem(String sliderItem) {
        this.mSliderItems.add(sliderItem);
        notifyDataSetChanged();
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo_adapter, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {


        InputStream inputstream= null;
        try {
            inputstream = context.getAssets().open("image/"
                    +this.mSliderItems.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        viewHolder.imgPhoto.setImageDrawable(drawable);
        viewHolder.imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnclick.clickSelect(position);
            }
        });
    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imgPhoto;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item);
            this.itemView = itemView;
        }
    }

    public interface Onclick{
        void clickSelect(int position);
    }
}
