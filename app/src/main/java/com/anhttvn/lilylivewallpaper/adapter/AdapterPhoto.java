package com.anhttvn.lilylivewallpaper.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.anhttvn.lilylivewallpaper.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class AdapterPhoto extends BaseAdapter implements View.OnClickListener {

    private Context mContext;
    private ArrayList<String> mListImages;
    private LayoutInflater mLayout;
    private int mPosition;
    private Onclick mOnclick;
    public AdapterPhoto(Context context, ArrayList<String> listImage, int position,Onclick click){
        mContext = context;
        mListImages = listImage;
        mPosition = position;
        mOnclick = click;
        mLayout = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mListImages.size();
    }

    @Override
    public Object getItem(int position) {
        return mListImages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag()+"");
        switch (v.getId()){
            case R.id.check_select:
                mPosition = position;
                notifyDataSetChanged();
                mOnclick.onClick(mPosition);
                break;
        }
    }

    private class ViewHolder{
        private ImageView img_icon;
        private RadioButton rb;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if(convertView ==null){
            convertView = mLayout.inflate(R.layout.item_photo_select,null);
            holder = new ViewHolder();
            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_item_select);
            holder.rb = (RadioButton) convertView.findViewById(R.id.check_select);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        InputStream inputstream= null;
        try {
            inputstream = mContext.getAssets().open("image/"
                    +mListImages.get(position));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Drawable drawable = Drawable.createFromStream(inputstream, null);
        holder.img_icon.setImageDrawable(drawable);
        holder.rb.setOnClickListener(this);

        if(mPosition == position){
            holder.rb.setChecked(true);
        }else{
            holder.rb.setChecked(false);
        }
        holder.rb.setTag(position);
        return convertView;
    }

    public interface Onclick{
        public void onClick(int position);
    }
}
