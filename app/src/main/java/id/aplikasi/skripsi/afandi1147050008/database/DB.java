package id.aplikasi.skripsi.afandi1147050008.database;

import android.os.Environment;

import java.io.File;

public class DB {

    public static final String DATABASE_NAME = "db_bukhari";
    public static String BASE_PATH = Environment.getExternalStorageDirectory() + File.separator + "/HadistBukhori/";
    public static String DATABASE_PATH = BASE_PATH + DATABASE_NAME;
    public static final int DATABASE_VERSION = 1;

    static final String TABLE_NAME = "Bukhari";

    public static final String NO = "No";
    public static final String KITAB = "Kitab";
    public static final String ARAB = "Arab";
    public static final String TERJEMAH = "Terjemah";

    static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + NO + " INTEGER, " + KITAB + " TEXT, " +
                    ARAB + " TEXT, " + TERJEMAH + " TEXT)";
}
