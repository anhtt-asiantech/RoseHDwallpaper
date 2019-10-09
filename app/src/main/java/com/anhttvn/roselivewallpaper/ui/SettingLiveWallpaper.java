package com.anhttvn.roselivewallpaper.ui;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.anhttvn.roselivewallpaper.R;
import com.anhttvn.roselivewallpaper.adapter.AdapterPhoto;
import com.anhttvn.roselivewallpaper.util.ChangeFont;
import com.anhttvn.roselivewallpaper.util.SharePrenFile;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;


public class SettingLiveWallpaper extends BaseActivity implements AdapterPhoto.Onclick {

    private CheckBox mCheckBok;
    private TextView tv_logo_setting,tv_changeBackground;
    private AdView mAdview;
    private AdapterPhoto mAdapter;
    private String [] images;
    private ArrayList<String> listImages;
    private SharePrenFile mSharePrenFile;
    private int mPosition;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        runAdview(mAdview);
        funClick();
        mSharePrenFile = new SharePrenFile(this);
        mPosition = mSharePrenFile.getBg("bg");
        mCheckBok.setChecked(mSharePrenFile.getTouch("touch"));
        runFullBanner();

    }
    private void init(){
        mCheckBok = findViewById(R.id.checkTouch);
        tv_logo_setting = findViewById(R.id.tv_setting_wallpaper);
        mAdview = findViewById(R.id.adViewSetiing);
        tv_changeBackground = findViewById(R.id.tv_changeBackground);


        tv_logo_setting.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));

    }
    private void funClick(){
        tv_changeBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogBackground();
            }
        });

        mCheckBok.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mSharePrenFile.saveTouch(isChecked,"touch");
            }
        });
        findViewById(R.id.tv_share_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareTextUrl();

            }
        });
        findViewById(R.id.tv_rate_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getApplication().getResources().getString(R.string.link_app)));
                startActivity(intent);

            }
        });

    }

    private Dialog dialog;
    private void showDialogBackground(){
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_bground);
        dialog.setCancelable(true);
        ListView listView = (ListView)dialog.findViewById(R.id.listView);
        initData(listView);
        dialog.show();
    }

    private void initData(ListView listView){

        try {
            images =getAssets().list("image");
        } catch (IOException e) {
            e.printStackTrace();
        }
        listImages = new ArrayList<String>(Arrays.asList(images));
        mAdapter = new AdapterPhoto(this,listImages,mPosition,this);
        listView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    private void shareTextUrl() {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

        // Add data to the intent, the receiving app will decide
        // what to do with it.
        share.putExtra(Intent.EXTRA_SUBJECT, "Rose Live Wallpaper");
        share.putExtra(Intent.EXTRA_TEXT, this.getResources().getString(R.string.link_app));

        startActivity(Intent.createChooser(share, "Share App!"));
    }

    @Override
    public void onClick(int position) {
        mAdapter.notifyDataSetChanged();
        dialog.dismiss();
        mSharePrenFile.saveBg(position,"bg");
        showADS();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showADS();
    }
}

