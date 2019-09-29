package com.anhttvn.lilylivewallpaper.ui;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.anhttvn.lilylivewallpaper.R;
import com.anhttvn.lilylivewallpaper.adapter.AdapterViewPager;
import com.anhttvn.lilylivewallpaper.util.RoseLiveWallpaperService;
import com.anhttvn.lilylivewallpaper.util.SharePrenFile;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityPhoto extends BaseActivity implements AdapterViewPager.Onclick {
    private AdapterViewPager adapterViewPager;
    private ViewPager mViewPager;
    private AdView mAds;
    private String [] images;
    private ArrayList<String> listImages;
    private SharePrenFile mSharePrenFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_activity);
        init();
        getData();
        AdapterView();
    }
    private void init(){
        mViewPager = findViewById(R.id.viewPager);
        mAds = findViewById(R.id.ads_view_3);
        runAdview(mAds);
        mSharePrenFile = new SharePrenFile(this);
    }

    private void getData(){
        try {
            images =getAssets().list("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        listImages = new ArrayList<String>(Arrays.asList(images));
    }
    private void AdapterView(){
        if(listImages != null){
            adapterViewPager = new AdapterViewPager(this,listImages,this);
            mViewPager.setAdapter(adapterViewPager);
            mViewPager.setCurrentItem(1);
        }
    }
    @Override
    public void clickSelect(int position) {
        mSharePrenFile.saveBg(position,"bg");
        finish();
        Intent intent = new Intent(
                WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                new ComponentName(getApplicationContext(), RoseLiveWallpaperService.class));
        startActivity(intent);
    }
}
