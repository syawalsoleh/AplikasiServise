package com.example.aplikasiservise;

import android.app.Service;
import android.app.WallpaperManager;
import android.os.Bundle;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.content.Intent;
import android.os.IBinder;
import java.io.IOException;


public class WallpaperChangeServise extends Service implements Runnable {
    private int wallpaperId[] = {R.drawable.wallpaper1, R.drawable.wallpaper2};
    private int waktu;
    private int FLAG=0;
    private Thread t;
    private Bitmap gambar;
    private Bitmap gambar1;

    @Override
    public IBinder onBind(Intent argO){
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public int onStartCommand(Intent intent, int flag, int startId)
    {
        super.onStartCommand(intent,flag,startId);
        Bundle bundle=intent.getExtras();
        waktu = bundle.getInt("durasi");
        gambar=BitmapFactory.decodeResource(getResources(),wallpaperId[0]);
        gambar1=BitmapFactory.decodeResource(getResources(),wallpaperId[1]);
        t = new Thread(WallpaperChangeServise.this);
        t.start();
        return START_STICKY_COMPATIBILITY;
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        WallpaperManager myWallpaper;
        myWallpaper = WallpaperManager.getInstance(this);
        try{
            while (true){
                if (FLAG==0){
                    myWallpaper.setBitmap(gambar);
                    FLAG++;
                }
                else {
                    myWallpaper.setBitmap(gambar1);
                    FLAG--;
                }
                Thread.sleep(100*waktu);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
