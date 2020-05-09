package com.erturk.bilbakalim;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

public class SplashEkrani extends Activity {

    private static String TAG = SplashEkrani.class.getName();
    private static long SLEEP_TIME = 3; // Bekletilecek saniye

    // ÖMER FARUK ERTÜRK

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE); // Splash ekrandan basligi kaldirir
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); // Bilgi cubugunu kaldirir

        setContentView(R.layout.activity_splash_ekrani);

        IntentLauncher launcher = new IntentLauncher();
        launcher.start();
    }

    private class IntentLauncher extends Thread {
        @Override

        public void run() {
            try {

                Thread.sleep(SLEEP_TIME*1000);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }

            Intent intent = new Intent(SplashEkrani.this, Giris.class);
            startActivity(intent);
            finish(); //Bu activity kapanır
        }
    }
}