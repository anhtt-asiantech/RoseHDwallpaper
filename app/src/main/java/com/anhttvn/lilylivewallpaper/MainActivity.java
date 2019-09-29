package com.anhttvn.lilylivewallpaper;

import android.app.Dialog;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.anhttvn.lilylivewallpaper.ui.ActivityPhoto;
import com.anhttvn.lilylivewallpaper.ui.BaseActivity;
import com.anhttvn.lilylivewallpaper.ui.SettingLiveWallpaper;
import com.anhttvn.lilylivewallpaper.util.ChangeFont;
import com.anhttvn.lilylivewallpaper.util.RoseLiveWallpaperService;
import com.anhttvn.lilylivewallpaper.util.SharePrenFile;
import com.google.android.gms.ads.AdView;


public class MainActivity extends BaseActivity {

    private TextView tv_logo_app;
    private TextView tv_setWallpaper;
    private TextView tv_setting,tv_About;
    private SharePrenFile mSharePrenFile;
    private AdView money_home;
    private Button btn_start, btn_photo,btn_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        runAdview(money_home);
        functionClick();
        checkFile();
        runFullBanner();
    }

    private void checkFile(){
        mSharePrenFile = new SharePrenFile(this);
        if(mSharePrenFile == null){
            mSharePrenFile.saveBg(0,"bg");
            mSharePrenFile.saveTouch(true,"touch");
        }
    }
    private void initView(){
        tv_logo_app = findViewById(R.id.tv_logo_app);
        tv_logo_app.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        tv_setWallpaper = findViewById(R.id.tv_setwallpaper);
        tv_setWallpaper.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        tv_setting = findViewById(R.id.tv_setting);
        tv_About = findViewById(R.id.tv_about);
        tv_setting.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        tv_About.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        money_home = findViewById(R.id.monney_home);
        btn_start = findViewById(R.id.btn_start);
        btn_start.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        btn_photo = findViewById(R.id.btn_photo);
        btn_photo.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        btn_setting = findViewById(R.id.btn_setting);
        btn_setting.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
    }
    private void functionClick(){
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT,
                        new ComponentName(getApplicationContext(), RoseLiveWallpaperService.class));
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ActivityPhoto.class));
            }
        });
        findViewById(R.id.btn_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),SettingLiveWallpaper.class));
            }
        });
    }

    private void showDialogAbout(){
        final Dialog dialog = new Dialog(this,R.style.full_screen_dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setContentView(R.layout.dialog_about);
        dialog.setCancelable(true);
        dialog.findViewById(R.id.btn_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getApplication().getResources().getString(R.string.link_app)));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        showADS();
    }
}
