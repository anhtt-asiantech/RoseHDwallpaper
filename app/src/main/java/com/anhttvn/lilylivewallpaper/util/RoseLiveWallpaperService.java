package com.anhttvn.lilylivewallpaper.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.os.Handler;
import android.service.wallpaper.WallpaperService;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class RoseLiveWallpaperService extends WallpaperService {
    @Override
    public Engine onCreateEngine() {
        return new MyWallpaperEngine();
    }
    private class MyWallpaperEngine extends Engine {
        private final int frameDuration = 20;

        private SurfaceHolder holder;
        private Movie movie;
        private boolean visible;
        private Handler handler;
        private Bitmap background;
        private String [] images;
        private ArrayList<String> listImages;
        private SharePrenFile mSharePrenFile;
        private int mPosition;

        public MyWallpaperEngine() {

            //this.movie = movie;
            handler = new Handler();
        }

        private void getData(){
            DisplayMetrics metrics = getApplication().getResources().getDisplayMetrics();
            int width = metrics.widthPixels;
            int height = metrics.heightPixels;
            mSharePrenFile = new SharePrenFile(getApplicationContext());
            mPosition = mSharePrenFile.getBg("bg");
            try {
                images =getAssets().list("image");
            } catch (IOException e) {
                e.printStackTrace();
            }
            listImages = new ArrayList<String>(Arrays.asList(images));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inPurgeable = true;
            BufferedInputStream bufferedInputStream;
            InputStream inputstream = null;
            try {
                inputstream = getApplicationContext().getAssets().open("image/"
                        + listImages.get(mPosition));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bufferedInputStream = new BufferedInputStream(inputstream);

            background = BitmapFactory.decodeStream(bufferedInputStream);
            background = Bitmap.createScaledBitmap(background, width, height, false);
        }
        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            this.holder = surfaceHolder;
        }

        private Runnable drawGIF = new Runnable() {
            public void run() {
                draw();
            }
        };

        private void draw() {
            getData();
            if (visible) {
                Canvas canvas = holder.lockCanvas();
                canvas.save();
                // Adjust size and position so that
                // the image looks good on your screen
                //movie.draw(canvas, -100, 0);
                canvas.drawBitmap(background, 0, 0, null);
                canvas.restore();
                holder.unlockCanvasAndPost(canvas);
                //movie.setTime((int) (System.currentTimeMillis() % movie.duration()));

                handler.removeCallbacks(drawGIF);
                handler.postDelayed(drawGIF, frameDuration);
            }
        }
        @Override
        public void onVisibilityChanged(boolean visible) {
            this.visible = visible;
            if (visible) {
                handler.post(drawGIF);
            } else {
                handler.removeCallbacks(drawGIF);
            }
        }
        @Override
        public void onDestroy() {
            super.onDestroy();
            handler.removeCallbacks(drawGIF);
        }

        @Override
        public void onSurfaceDestroyed(SurfaceHolder holder) {
            super.onSurfaceDestroyed(holder);
            this.visible = false;
            handler.removeCallbacks(drawGIF);
        }
    }
}
