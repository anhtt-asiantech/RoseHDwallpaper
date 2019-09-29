package com.anhttvn.lilylivewallpaper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.anhttvn.lilylivewallpaper.ui.BaseActivity;
import com.anhttvn.lilylivewallpaper.util.ChangeFont;

public class Main extends BaseActivity {

    private Button btnStart;
    private TextView txtVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        init();
    }
    private void init(){
        btnStart = findViewById(R.id.btnStart);
        txtVersion = findViewById(R.id.txtVersion);
        txtVersion.setTypeface(new ChangeFont().fontBeyond_Wonderland(this));
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
