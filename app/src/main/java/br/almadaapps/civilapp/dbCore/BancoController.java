package br.almadaapps.civilapp.dbCore;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by viniciusalmada on 20/04/2016.
 */
public class BancoController {
    private SQLiteDatabase db;
    private HorariosBD horariosBD;
    private EmentasBD ementasBD;
    private ProjetoPedagogicoBD ppBD;

    public BancoController (Context context) {
        horariosBD = new HorariosBD(context);
        ementasBD = new EmentasBD(context);
        ppBD = new ProjetoPedagogicoBD(context);
    }

    public Cursor getHorarios (String periodo, String dia) {
        Cursor cursor;
        String tableName = horariosBD.TABLE_NAME;
        String[] campos = {horariosBD.INTERVALO, horariosBD.DISCIPLINA};
        String selection = horariosBD.PERIODO + " = ? AND " + horariosBD.DIA + " = ?";
        String[] selectionArgs = {periodo, dia};
        db = horariosBD.getDatabase();
        cursor = db.query(tableName, campos, selection, selectionArgs, null, null, horariosBD.INTERVALO + " ASC");
        if (cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getEmentas (String _id) {
        Cursor cursor;
        String tableName = ementasBD.TABLE_NAME;
        String[] campos = {ementasBD.CABECALHO, ementasBD.EMENTA, ementasBD.OBJETIVO, ementasBD.CONTEUDO, ementasBD.REFERENCIAS};
        String selection = ementasBD.ID + " = ?";
        String[] selectionArgs = {_id};
        db = ementasBD.getDatabase();
        cursor = db.query(tableName, campos, selection, selectionArgs, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor getHorariosToPersonal (String periodo, String dia) {
        Cursor cursor;
        String tableName = horariosBD.TABLE_NAME;
        String[] campos = {horariosBD.INTERVALO, horariosBD.ORDEM};
        String selection = horariosBD.PERIODO + " = ? AND " + horariosBD.DIA + " = ?";
        String[] selectionArgs = {periodo, dia};
        db = horariosBD.getDatabase();
        cursor = db.query(tableName, campos, selection, selectionArgs, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        db.close();

        return cursor;
    }

    public Cursor[] getProjetoPedagogico () {
        Cursor[] cursor = new Cursor[6];
        String[] table =  new String[6];
        table[0] = ppBD.TABLE_MAIN_NAME;
        table[1] = ppBD.TABLE_ANEXO_2;
        table[2] = ppBD.TABLE_ANEXO_3;
        table[3] = ppBD.TABLE_ANEXO_4;
        table[4] = ppBD.TABLE_ANEXO_5;
        table[5] = ppBD.TABLE_ANEXO_6;
        db = ppBD.getDatabase();
        for (int i = 0; i < cursor.length; i++) {
            cursor[i] = db.query(table[i],null,null,null,null,null,null);
            if (cursor[i] != null)
                cursor[i].moveToFirst();
        }
        db.close();

        return cursor;
    }

}
