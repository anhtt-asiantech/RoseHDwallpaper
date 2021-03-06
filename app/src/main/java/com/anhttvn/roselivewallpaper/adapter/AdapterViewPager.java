package com.anhttvn.roselivewallpaper.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.anhttvn.roselivewallpaper.R;
import com.anhttvn.roselivewallpaper.util.ChangeFont;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * adapter view pager
 */
public class AdapterViewPager extends PagerAdapter implements View.OnClickListener {
    private Context mContext;
    private ArrayList<String> listPhoto;
    private Onclick mOnclick;
    public AdapterViewPager(Context context, ArrayList<String> list,Onclick click){
        mContext =context;
        listPhoto = list;
        mOnclick = click;
    }
    @Override
    public int getCount() {
        return listPhoto.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((RelativeLayout) object);
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_photo_adapter, container, false);

        ImageView imageView = itemView.findViewById(R.id.img_item);

        InputStream inputstream= null;
        try {
            inputstream = mContext.getAssets().open("image/"
                    +listPhoto.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        imageView.setImageDrawable(drawable);
        container.addView(itemView);
        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

    @Override
    public void onClick(View view) {
        int position = Integer.parseInt(view.getTag() + "");
//        switch (view.getId()){
//            case R.id.btn_select:
//                mOnclick.clickSelect(position);
//                break;
//        }
    }
    public interface Onclick{
        void clickSelect(int position);
    }
}
