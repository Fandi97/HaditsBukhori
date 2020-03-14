package id.aplikasi.skripsi.afandi1147050008;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        int waktu_loading = 2000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //Tambahkan permintaan permission read/write external

                Intent home = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(home);
                finish();
            }
        }, waktu_loading);
    }
}
