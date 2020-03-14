 package id.aplikasi.skripsi.afandi1147050008;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.adapter.HadistAdapter;
import id.aplikasi.skripsi.afandi1147050008.algorithm.BoyerMoore;
import id.aplikasi.skripsi.afandi1147050008.algorithm.BruteForce;
import id.aplikasi.skripsi.afandi1147050008.database.DBHelper;
import id.aplikasi.skripsi.afandi1147050008.model.Hadist;
import id.aplikasi.skripsi.afandi1147050008.utils.Loading;

public class SearchingActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnFind;
    private HadistAdapter hadistAdapter;
    private List<Hadist> hadistList = new ArrayList<>();
    private List<Hadist> resultList = new ArrayList<>();
    private List<Hadist> resBoyer = new ArrayList<>();
    private List<Hadist> resBrute = new ArrayList<>();
    private BoyerMoore boyerMoore;
    private BruteForce bruteForce;

//    private TabLayout tabLayout;
//    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching);

        etSearch = findViewById(R.id.etSearch);
        btnFind = findViewById(R.id.btnFind);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(SearchingActivity.this,
                LinearLayoutManager.VERTICAL, false);
        hadistAdapter = new HadistAdapter(resultList, SearchingActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(hadistAdapter);

        hadistList.clear();
        resultList.clear();

        hadistList.addAll(new DBHelper(SearchingActivity.this).getData());

        getInit();

    }

    private void getInit() {
        Toast.makeText(this, String.valueOf(hadistList.size()), Toast.LENGTH_SHORT).show();
        boyerMoore = new BoyerMoore(hadistList);
        bruteForce = new BruteForce(hadistList);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Loading.show(SearchingActivity.this);
                doProgress(etSearch.getText().toString());
            }
        });
    }

    private void doProgress(String pattern) {
        if (doBrute(pattern)) {

            if (doBoyer(pattern)) {
                List<Hadist> res = new ArrayList<>();
                res.clear();


                for (int x = 0; x < resBrute.size(); x++) {
                    Hadist hadist = new Hadist();
                    hadist.setNo(resBrute.get(x).getNo());
                    hadist.setKitab(resBrute.get(x).getKitab());
                    hadist.setArab(resBrute.get(x).getArab());
                    hadist.setTerjemahan(resBrute.get(x).getTerjemahan());

                    hadist.setBruteTime(resBrute.get(x).getBruteTime());
                    hadist.setBoyerTime("-");
                    for (int y = 0; y < resBoyer.size(); y++) {
                        if (resBrute.get(x).getNo().equals(resBoyer.get(y).getNo())) {
                            hadist.setBoyerTime(resBrute.get(x).getBoyerTime());
                            break;
                        }
                    }
                    res.add(hadist);
                }
                Toast.makeText(SearchingActivity.this, ("BruteForce : "+resBrute.size()+" - BayerMore : "+resBoyer.size()), Toast.LENGTH_SHORT).show();
//                doResult(res);

                if (res.size() > 0) {
                    Loading.hide(SearchingActivity.this);
//                    Toast.makeText(SearchingActivity.this, String.valueOf(resBrute.size()+" - "+resBoyer.size()), Toast.LENGTH_SHORT).show();
                    doResult(res);
                } else {
//                    Toast.makeText(SearchingActivity.this, "Tidak ada data", Toast.LENGTH_SHORT).show();
                    Loading.hide(SearchingActivity.this);
                }
            }
        }
    }

    private boolean doBrute(String pattern) {
        resBrute.clear();
        resBrute = bruteForce.doBruteForce(pattern);
        return true;
    }

    private boolean doBoyer(String pattern) {
        resBoyer.clear();
        resBoyer = boyerMoore.doBoyerMoore(pattern);
        return true;
    }

    private void doResult(List<Hadist> results) {
        resultList.clear();
        resultList.addAll(results);
        hadistAdapter.notifyDataSetChanged();
    }

}
