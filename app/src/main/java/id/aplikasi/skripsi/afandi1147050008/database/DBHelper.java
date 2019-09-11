package id.aplikasi.skripsi.afandi1147050008.database;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import id.aplikasi.skripsi.afandi1147050008.model.Hadist;

@SuppressLint("Recycle")
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DB.DATABASE_PATH, null, DB.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DB.TABLE_NAME);
    }

    public List<Hadist> getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<Hadist> result = new ArrayList<>();

        String qr = "SELECT * FROM " + DB.TABLE_NAME;
        Cursor cursor = db.rawQuery(qr, null);
        if (cursor.moveToFirst()) {
            do {
                Hadist hadist = new Hadist();
                hadist.setNo((cursor).getString((cursor).getColumnIndex(DB.NO)));
                hadist.setKitab((cursor).getString((cursor).getColumnIndex(DB.KITAB)));
                hadist.setArab((cursor).getString((cursor).getColumnIndex(DB.ARAB)));
                hadist.setTerjemahan((cursor).getString((cursor).getColumnIndex(DB.TERJEMAH)));

                result.add(hadist);
            } while (cursor.moveToNext());
        }

        return result;
    }
}
