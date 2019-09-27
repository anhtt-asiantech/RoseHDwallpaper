package com.anhttvn.roselivewallpaper.ui;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import com.anhttvn.roselivewallpaper.R;

import androidx.annotation.Nullable;

public class Setting extends PreferenceActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.press);
    }
}

