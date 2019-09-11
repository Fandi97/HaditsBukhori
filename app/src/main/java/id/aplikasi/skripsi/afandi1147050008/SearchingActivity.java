package id.aplikasi.skripsi.afandi1147050008;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.adapter.BoyerMooreAdapter;
import id.aplikasi.skripsi.afandi1147050008.algorithm.BoyerMoore;
import id.aplikasi.skripsi.afandi1147050008.database.DBHelper;
import id.aplikasi.skripsi.afandi1147050008.model.Hadist;
import id.aplikasi.skripsi.afandi1147050008.utils.Loading;

public class SearchingActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnFind;
    private RecyclerView recyclerView;
    private BoyerMooreAdapter boyerMooreAdapter;
    private List<Hadist> hadistList = new ArrayList<>();
    private List<Hadist> resultList = new ArrayList<>();
    private BoyerMoore boyerMoore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        etSearch = findViewById(R.id.etSearch);
        btnFind = findViewById(R.id.btnFind);
        recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchingActivity.this,
                LinearLayoutManager.VERTICAL, false);
        boyerMooreAdapter = new BoyerMooreAdapter(resultList, SearchingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(boyerMooreAdapter);

        hadistList.clear();
        resultList.clear();

        hadistList.addAll(new DBHelper(SearchingActivity.this).getData());

        getInit();

    }

    private void getInit() {
        boyerMoore = new BoyerMoore(hadistList);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String asd = "Telah menceritakan kepada kami Al Humaidi Abdullah bin Az Zubair dia berkata, Telah menceritakan kepada kami Sufyan yang berkata, bahwa Telah menceritakan kepada kami Yahya bin Sa'id Al Anshari berkata, telah mengabarkan kepada kami Muhammad bin Ibrahim At Taimi, bahwa dia pernah mendengar Alqamah bin Waqash Al Laitsi berkata; saya pernah mendengar Umar bin Al Khaththab diatas mimbar berkata; saya mendengar Rasulullah shallallahu 'alaihi wasallam bersabda: \"Semua perbuatan tergantung niatnya, dan (balasan) bagi tiap-tiap orang (tergantung) apa yang diniatkan; Barangsiapa niat hijrahnya karena dunia yang ingin digapainya atau karena seorang perempuan yang ingin dinikahinya, maka hijrahnya adalah kepada apa dia diniatkan\"";
                int result = boyerMoore.findPattern(asd, etSearch.getText().toString());
                Toast.makeText(SearchingActivity.this, String.valueOf(result), Toast.LENGTH_SHORT).show();
                /*Loading.show(SearchingActivity.this);
                String pattern = etSearch.getText().toString();
                List<Hadist> res = boyerMoore.doBoyerMoore(pattern);
                if (res.size() > 0) {
                    Loading.hide(SearchingActivity.this);
                    doResult(boyerMoore.doBoyerMoore(pattern));
                } else {
                    Toast.makeText(SearchingActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                    Loading.hide(SearchingActivity.this);
                }*/
            }
        });
    }

    private void doResult(List<Hadist> results) {
        resultList.addAll(results);
        boyerMooreAdapter.notifyDataSetChanged();
    }

}
