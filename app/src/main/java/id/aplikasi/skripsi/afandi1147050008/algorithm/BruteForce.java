package id.aplikasi.skripsi.afandi1147050008.algorithm;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

public class BruteForce {
    private List<Hadist> hadistList;

    public BruteForce(List<Hadist> hadistList) {
        this.hadistList = hadistList;
    }

    public List<Hadist> doBruteForce(String pattern) {
        List<Hadist> result = new ArrayList<>();
        for (int i = 0; i < hadistList.size(); i++) {
            int searchIndex = findPattern(hadistList.get(i).getTerjemahan(), pattern);

            //int starttime = (int) System.nanoTime();
            //int endtime = (int) System.nanoTime() - starttime;
            //float second = endtime / 1000F;
            //etWaktu.setText(second + " Milliseconds");
            //Log.d("BOOYER MOORE", ": Ada" + searchIndex);

            if (searchIndex > -1) {
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
                return i;
            }
        }

        return -1;
    }
}
