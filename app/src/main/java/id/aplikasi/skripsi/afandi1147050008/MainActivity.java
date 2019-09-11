package id.aplikasi.skripsi.afandi1147050008;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

import id.aplikasi.skripsi.afandi1147050008.database.DB;
import id.aplikasi.skripsi.afandi1147050008.utils.Common;

public class MainActivity extends AppCompatActivity {

    private static final int FILE_SELECT_DB = 10001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        Button btninfo = findViewById(R.id.btninfo);
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, InfoActivity.class));
            }
        });
        Button btncari = findViewById(R.id.btncari);
        btncari.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SearchingActivity.class));
            }
        });
        Button btnImport = findViewById(R.id.btnImport);
        btnImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                importDb();
            }
        });
    }

    @SuppressLint("InlinedApi")
    private void importDb() {
        try {
            InputStream mInput = MainActivity.this.getResources().openRawResource(R.raw.db_bukhari);
            OutputStream mOutput = new FileOutputStream(DB.DATABASE_PATH);
            byte[] mBuffer = new byte[1024];
            int mLength;
            while ((mLength = mInput.read(mBuffer)) > 0)
                mOutput.write(mBuffer, 0, mLength);
            mOutput.flush();
            mOutput.close();
            mInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//        intent.setType("*/*");
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        try {
//            startActivityForResult(Intent.createChooser(intent, "Select a File to Upload"),
//                    FILE_SELECT_DB);
//        } catch (android.content.ActivityNotFoundException ex) {
//            Toast.makeText(this, "Please install a File Manager.", Toast.LENGTH_SHORT).show();
//        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FILE_SELECT_DB) {
            if (resultCode == RESULT_OK && data != null) {
                Uri returnUri = data.getData();
                try {
                    InputStream fileSource = this.getContentResolver().openInputStream(Objects.requireNonNull(returnUri));
                    File cacheFile = new File(this.getCacheDir(), "db_epkh");
                    OutputStream fileCacheWrite = new FileOutputStream(cacheFile);
                    Common.copyStream(fileSource, fileCacheWrite); // Copy from source to cache
                    InputStream fileCacheRead = new FileInputStream(cacheFile);
                    File dbFile = new File(DB.BASE_PATH);
                    OutputStream fileDestination = new FileOutputStream(dbFile);
                    Common.copyStream(fileCacheRead, fileDestination); // Copy from cache to destiation
                    Toast.makeText(this, "Data berhasil di input", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Gagal Import File : " + e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
