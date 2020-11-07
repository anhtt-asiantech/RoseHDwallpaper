package com.anhttvn.roselivewallpaper.ui;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.viewpager.widget.ViewPager;

import com.anhttvn.roselivewallpaper.R;
import com.anhttvn.roselivewallpaper.adapter.AdapterViewPager;
import com.anhttvn.roselivewallpaper.adapter.SlideAdapter;
import com.anhttvn.roselivewallpaper.util.RoseLiveWallpaperService;
import com.anhttvn.roselivewallpaper.util.SharePrenFile;
import com.google.android.gms.ads.AdView;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class ActivityPhoto extends BaseActivity implements SlideAdapter.Onclick {
    private AdapterViewPager adapterViewPager;
    private SlideAdapter mAdapterSlider;
    private SliderView mViewPager;
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
        mViewPager = findViewById(R.id.imageSlider);
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
            mAdapterSlider = new SlideAdapter(this,listImages,this);
            mViewPager.setSliderAdapter(mAdapterSlider);
            mViewPager.setIndicatorAnimation(IndicatorAnimationType.WORM);
            mViewPager.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
            mViewPager.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
            mViewPager.setIndicatorSelectedColor(Color.WHITE);
            mViewPager.setIndicatorUnselectedColor(Color.GRAY);
            mViewPager.setScrollTimeInSec(3);
            mViewPager.setAutoCycle(true);
            mViewPager.startAutoCycle();
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
//    @Override
//    public void clickSelect(int position) {
//        mSharePrenFile.saveBg(position,"bg");
//        finish();
//        Intent intent = new Intent(
//                WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//        intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
//                new ComponentName(getApplicationContext(), RoseLiveWallpaperService.class));
//        startActivity(intent);
//    }
}
