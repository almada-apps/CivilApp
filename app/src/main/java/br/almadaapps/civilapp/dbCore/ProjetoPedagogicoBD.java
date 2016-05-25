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
 * Created by viniciusalmada on 23/05/2016.
 */

public class ProjetoPedagogicoBD extends SQLiteOpenHelper {
    /*public static final String INTRO_1 = "introduction";
    public static final String INTRO_2 = "responsaveis";
    public static final String INTRO_3 = "com_implatacao";
    public static final String INTRO_4 = "com_atualizacao";
    public static final String CORPO_1 = "apresentacao";
    public static final String CORPO_2 = "contexto";
    public static final String CORPO_3 = "justificativa";
    public static final String CORPO_4 = "objetivos";
    public static final String CORPO_5 = "perfil";
    public static final String CORPO_6 = "competencias";
    public static final String CORPO_7 = "funcionamento";
    public static final String CORPO_8 = "organizacao";
    public static final String CORPO_9 = "carga_horaria";
    public static final String CORPO_10 = "metodologia";
    public static final String CORPO_11 = "avaliacao";
    public static final String CORPO_12 = "estagio";
    public static final String CORPO_13 = "pesquisa";
    public static final String CORPO_14 = "monitoria";
    public static final String CORPO_15 = "complementares";
    public static final String CORPO_16 = "apoio";
    public static final String CORPO_17 = "eletivas";
    public static final String CORPO_18 = "inst_fisicas";
    public static final String CORPO_19 = "corpo_docente";
    public static final String CORPO_20 = "referencias";*/
    public static final String TABLE_MAIN_NAME = "main";
    public static final String TABLE_ANEXO_2 = "anexo_2";
    public static final String TABLE_ANEXO_3 = "anexo_3";
    public static final String TABLE_ANEXO_4 = "anexo_4";
    public static final String TABLE_ANEXO_5 = "anexo_5";
    public static final String TABLE_ANEXO_6 = "anexo_6";
    public static final String DB_PATH = "/data/data/br.almadaapps.civilapp/databases/";
    private static final String DB_NAME = "ppec_5.db";

    private Context context;

    public ProjetoPedagogicoBD(Context context){
        super(context, DB_NAME,null, 2);
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
