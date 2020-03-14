package id.aplikasi.skripsi.afandi1147050008.algorithm;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

public class BoyerMoore {
    private List<Hadist> hadistList;
    private int starttime;
    private int endtime;

    public BoyerMoore(List<Hadist> hadistList) {
        this.hadistList = hadistList;
    }

    public List<Hadist> doBoyerMoore(String pattern) {
        List<Hadist> result = new ArrayList<>();
        for (int i = 0; i < hadistList.size(); i++) {
            starttime = (int) System.nanoTime();
            int searchIndex = findPattern(hadistList.get(i).getTerjemahan(), pattern);
            if (searchIndex > -1) {
                float second = endtime;// / 1000F;
                hadistList.get(i).setBoyerTime(String.valueOf(second));
                result.add(hadistList.get(i));
            }
        }
        return result;
    }

    private int findPattern(String text, String word) {
        try {
            char[] textC = text.toCharArray();
            char[] wordC = word.toCharArray();
            int[] d1 = makeD1(wordC);
            int[] d2 = makeD2(wordC);
            int i = wordC.length - 1;
            while (i < textC.length) {
                int j = wordC.length - 1;
                while (j >= 0 && (textC[i] == wordC[j])) {
                    i--;
                    j--;
                }
                if (j < 0) {
                    endtime = (int) System.nanoTime() - starttime;
                    return (i + 1);
                }
                i += Math.max(d1[textC[i]], d2[j]);
            }
        } catch (Exception ignore) {
        }
        return -1;
    }

    private static int[] makeD1(char[] pat) {
        int[] table = new int[255];
        for (int i = 0; i < 255; i++)
            table[i] = pat.length;
        for (int i = 0; i < pat.length - 1; i++)
            table[pat[i]] = pat.length - 1 - i;
        return table;
    }

    private static boolean isPrefix(char[] word, int pos) {
        int suffixlen = word.length - pos;
        for (int i = 0; i < suffixlen; i++)
            if (word[i] != word[pos + i])
                return false;
        return true;
    }

    private static int suffix_length(char[] word, int pos) {
        int i;
        for (i = 0; ((word[pos - i] == word[word.length - 1 - i]) &&
                (i < pos)); i++) {
        }
        return i;
    }

    private static int[] makeD2(char[] pat) {
        int[] delta2 = new int[pat.length];
        int p;
        int last_prefix_index = pat.length - 1;
        for (p = pat.length - 1; p >= 0; p--) {
            if (isPrefix(pat, p + 1))
                last_prefix_index = p + 1;
            delta2[p] = last_prefix_index + (pat.length - 1 - p);
        }
        for (p = 0; p < pat.length - 1; p++) {
            int slen = suffix_length(pat, p);
            if (pat[p - slen] != pat[pat.length - 1 - slen])
                delta2[pat.length - 1 - slen] = pat.length - 1 - p + slen;
        }
        return delta2;
    }

}
