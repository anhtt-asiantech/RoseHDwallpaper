package com.anhttvn.roselivewallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.anhttvn.roselivewallpaper.ui.BaseActivity;
import com.anhttvn.roselivewallpaper.util.ChangeFont;

public class Main extends BaseActivity {

    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }
    private void init(){
        btnStart = findViewById(R.id.btnStart);
        btnStart.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
            }
        });
    }
}
