package com.anhttvn.lilylivewallpaper.ui;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.anhttvn.lilylivewallpaper.R;
import com.anhttvn.lilylivewallpaper.util.InternetConnection;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

public class BaseActivity extends Activity {

    private InterstitialAd mInterstitialAd;
    private AdRequest mAdRequest;
    protected void Toast(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    protected void runFullBanner(){
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-3840180634112397~9187759690");

        mAdRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("7A595B4AD228B73926357DCF1A633D4D").build();
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getString(R.string.banner_full_screen));
        if(InternetConnection.checkConnection(this)){
            mInterstitialAd.loadAd(mAdRequest);
        }

    }

    protected void showADS(){
        if(mInterstitialAd != null && mInterstitialAd.isLoaded()){
            mInterstitialAd.show();
        }
    }
    protected void runAdview(AdView adv){
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("7A595B4AD228B73926357DCF1A633D4D").build();
        if (InternetConnection.checkConnection(this)) {
            adv.setVisibility(View.VISIBLE);
            adv.loadAd(adRequest);
            Log.d("anhtt","connect");

        }else{
            adv.setVisibility(View.GONE);
        }
    }
}
