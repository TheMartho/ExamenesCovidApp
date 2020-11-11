package cl.inacap.registroscovidapp.helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PacientesSQLiteHelper extends SQLiteOpenHelper {

    private final String sqlCreate="CREATE TABLE" +
            " pacientes(rut TEXT PRIMARY KEY NOT NULL" +
            ",nombre TEXT" +
            ",apellido TEXT" +
            ",fechaExamen TEXT" +
            ",areaTrabajo TEXT" +
            ",presentaSintomas TEXT" +
            ",temperatura REAL" +
            ",presentaTos TEXT" +
            ",presionArterial INTEGER)";

    public PacientesSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS pacientes");
        sqLiteDatabase.execSQL(sqlCreate);

    }
}
