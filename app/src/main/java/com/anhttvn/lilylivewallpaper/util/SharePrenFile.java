package com.anhttvn.lilylivewallpaper.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePrenFile {
    private SharedPreferences sharedPreferences;
    private Context mContext;
    public SharePrenFile(Context context){
        super();
        this.mContext = context;
        this.sharedPreferences = mContext.getSharedPreferences("LiveWallpaperRose",Context.MODE_PRIVATE);
    }
    public void saveBg(int position,String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("bg",position);
        editor.commit();
    }
    public void saveTouch(boolean check,String key){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("touch",check);
        editor.commit();
    }
    public int getBg(String key){
        return sharedPreferences.getInt(key,0);
    }
    public boolean getTouch(String key){
        return sharedPreferences.getBoolean(key,false);
    }
}
