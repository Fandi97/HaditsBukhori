package id.aplikasi.skripsi.afandi1147050008.algorithm;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

public class BruteForce {
    private List<Hadist> hadistList;
    private int starttime;
    private int endtime;

    public BruteForce(List<Hadist> hadistList) {
        this.hadistList = hadistList;
    }

    public List<Hadist> doBruteForce(String pattern) {
        List<Hadist> result = new ArrayList<>();
        for (int i = 0; i < hadistList.size(); i++) {

            starttime = (int) System.nanoTime();
            int searchIndex = findPattern(hadistList.get(i).getTerjemahan(), pattern);
            if (searchIndex > -1) {
                float second = endtime;// / 1000F;
//                hadistList.get(i).setTime(String.valueOf(second));
                hadistList.get(i).setBruteTime(String.valueOf(second));

                result.add(hadistList.get(i));
            }
        }

        return result;
    }

    private int findPattern(String text, String word) {
        int length = text.length();//length of the text
        int plength = word.length();//length of the pattern;

        //loop condition
        for (int i = 0; i < length - plength; i++) {
            //initialization of j
            int j = 0;
            while ((j < plength) && (text.charAt(i + j) == word.charAt(j))) {
                j++;
            }
            if (j == plength) {
                endtime = (int) System.nanoTime() - starttime;
                return i;
            }
        }

        return -1;
    }
}
