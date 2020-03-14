package id.aplikasi.skripsi.afandi1147050008;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Objects;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

public class InfoActivity extends AppCompatActivity {
 private TextView tv_info;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        Objects.requireNonNull(getSupportActionBar()).hide();
        tv_info = findViewById(R.id.tvInfo);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tv_info.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }
    }
}
