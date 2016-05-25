package br.almadaapps.civilapp.dbCore;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by viniciusalmada on 20/04/2016.
 */
public class HorariosBD extends SQLiteOpenHelper{
    public static final String TABLE_NAME = "main";
    public static final String DISCIPLINA = "disc";
    public static final String INTERVALO = "hor";
    public static final String POSITION = "position";
    public static final String ORDEM = "ordem";
    public static final String DIA = "dia";
    public static final String PERIODO = "per";
    public static final String DB_PATH = "/data/data/br.almadaapps.civilapp/databases/";
    private static final String DB_NAME = "horarios_4.db";
    private Context context;

    public HorariosBD (Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    private boolean checkDatabase () {
        SQLiteDatabase db = null;

        try {
            String path = DB_PATH + DB_NAME;
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch ( SQLiteException e ) {
            //o banco não existe
        }
        return db != null;
    }

    private void createDatabase () throws Exception {
        boolean exists = checkDatabase();
        if (!exists) {
            this.getReadableDatabase();

            try {
                copyDatabase();
            } catch ( IOException e ) {
                throw new Error("Não foi possível copiar o arquivo");
            }
        }
    }

    private void copyDatabase () throws IOException {
        String path = DB_PATH + DB_NAME;
        OutputStream dbStream = new FileOutputStream(path);
        InputStream dbInputStream = context.getAssets().open(DB_NAME);
        byte[] buffer = new byte[1024];
        int lenght;
        while ((lenght = dbInputStream.read(buffer)) > 0)
            dbStream.write(buffer, 0, lenght);
        dbInputStream.close();
        dbStream.flush();
        dbStream.close();
    }

    public SQLiteDatabase getDatabase () {
        try {
            createDatabase();
            String path = DB_PATH + DB_NAME;
            return SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
        } catch ( Exception e ) {
            return getWritableDatabase();
        }
    }

    @Override
    public void onCreate (SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

