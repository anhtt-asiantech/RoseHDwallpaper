package com.anhttvn.lilylivewallpaper.util;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Huu Duan on 9/7/2017.
 * @author anhtt
 */
public class ChangeFont {
    private Context mContex;
    public Typeface fontChange(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/cretino.ttf");
        return typeThuDay;
    }

    public Typeface fontHelo(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/cretino.ttf");
        return typeThuDay;
    }

    public Typeface fontbye(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/LimeGloryCaps.ttf");
        return typeThuDay;
    }

    public Typeface fontBeyond_Wonderland(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/Beyond Wonderland.ttf");
        return typeThuDay;
    }
    public Typeface fontIcon(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/GrafikText.ttf");
        return typeThuDay;
    }
    public Typeface fontDemo(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/UTM ThuPhap Thien An.ttf");
        return typeThuDay;
    }
    public Typeface fontTitle(Context context){
        mContex = context;
        Typeface typeThuDay = Typeface.createFromAsset(mContex.getResources().getAssets(), "font/TMC-Ong Do.TTF");
        return typeThuDay;
    }
}
