package id.aplikasi.skripsi.afandi1147050008.algorithm;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

public class BoyerMoore {
    private List<Hadist> hadistList;

    public BoyerMoore(List<Hadist> hadistList) {
        this.hadistList = hadistList;
    }

    public List<Hadist> doBoyerMoore(String pattern) {
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

    public int findPattern(String text, String word) {
        char[] textC = text.toCharArray();
        char[] wordC = word.toCharArray();
        return bm(textC, wordC);

    }

    public static int[] makeD1(char[] pat) {
        int[] table = new int[255];
        for(int i=0; i<255; i++)
            table[i] = pat.length;
        for(int i=0; i<pat.length-1; i++)
            table[pat[i]] = pat.length-1-i;
        return table;
    }

    public static boolean isPrefix(char[] word, int pos) {
        int suffixlen = word.length - pos;
        for(int i=0; i<suffixlen; i++)
            if(word[i] != word[pos+i])
                return false;
        return true;
    }

    public static int suffix_length(char[] word, int pos) {
        int i;
        for(i=0; ((word[pos-i] == word[word.length-1-i]) &&
                (i < pos)); i++)
        {}
        return i;
    }

    public static int[] makeD2(char[] pat) {
        int[] delta2 = new int[pat.length];
        int p;
        int last_prefix_index = pat.length - 1;
        for(p = pat.length-1; p>=0; p--) {
            if(isPrefix(pat, p+1))
                last_prefix_index = p+1;
            delta2[p] = last_prefix_index + (pat.length-1-p);
        }
        for(p=0; p<pat.length-1; p++) {
            int slen = suffix_length(pat, p);
            if(pat[p-slen] != pat[pat.length-1-slen])
                delta2[pat.length-1-slen] = pat.length-1-p+slen;
        }
        return delta2;
    }

    public static int bm(char[] string, char[] pat) {
        int[] d1 = makeD1(pat);
        int[] d2 = makeD2(pat);
        int i = pat.length-1;
        while(i < string.length) {
            int j = pat.length-1;
            while(j>=0 && (string[i] == pat[j])) {
                i--;
                j--;
            }
            if(j < 0)
                return (i+1);
            i += Math.max(d1[string[i]], d2[j]);
        }
        return -1;
    }

    /*private int findPattern(String t, String p) {
        char[] text = t.toCharArray();
        char[] pattern = p.toCharArray();
        int pos = indexOf(text, pattern);
        if (pos == -1)
            System.out.println("\nNo Match\n");
        else
            System.out.println("Pattern found at position : " + pos);
        return pos;
    }

    *//* Function to calculate index of pattern substring *//*
    private int indexOf(char[] text, char[] pattern) {
        if (pattern.length == 0)
            return 0;
        int[] charTable = makeCharTable(pattern);
        int[] offsetTable = makeOffsetTable(pattern);
        for (int i = pattern.length - 1, j; i < text.length; ) {
            for (j = pattern.length - 1; pattern[j] == text[i]; --i, --j)
                if (j == 0)
                    return i;
            i += Math.max(offsetTable[pattern.length - 1 - j], charTable[text[i]]);
        }
        return -1;
    }

    *//* Makes the jump table based on the mismatched character information *//*
    private int[] makeCharTable(char[] pattern) {
        final int ALPHABET_SIZE = 256;
        int[] table = new int[ALPHABET_SIZE];
        for (int i = 0; i < table.length; ++i)
            table[i] = pattern.length;
        for (int i = 0; i < pattern.length - 1; ++i)
            table[pattern[i]] = pattern.length - 1 - i;
        return table;
    }

    *//* Makes the jump table based on the scan offset which mismatch occurs. *//*
    private static int[] makeOffsetTable(char[] pattern) {
        int[] table = new int[pattern.length];
        int lastPrefixPosition = pattern.length;
        for (int i = pattern.length - 1; i >= 0; --i) {
            if (isPrefix(pattern, i + 1))
                lastPrefixPosition = i + 1;
            table[pattern.length - 1 - i] = lastPrefixPosition - i + pattern.length - 1;
        }
        for (int i = 0; i < pattern.length - 1; ++i) {
            int slen = suffixLength(pattern, i);
            table[slen] = pattern.length - 1 - i + slen;
        }
        return table;
    }

    *//* function to check if needle[p:end] a prefix of pattern *//*
    private static boolean isPrefix(char[] pattern, int p) {
        for (int i = p, j = 0; i < pattern.length; ++i, ++j)
            if (pattern[i] != pattern[j])
                return false;
        return true;
    }

    *//* function to returns the maximum length of the substring ends at p and is a suffix *//*
    private static int suffixLength(char[] pattern, int p) {
        int len = 0;
        for (int i = p, j = pattern.length - 1; i >= 0 && pattern[i] == pattern[j]; --i, --j)
            len += 1;
        return len;
    }*/

}
