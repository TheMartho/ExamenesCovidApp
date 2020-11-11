package cl.inacap.registroscovidapp.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroscovidapp.dto.Paciente;
import cl.inacap.registroscovidapp.helpers.PacientesSQLiteHelper;

public class PacientesDAOSQLite implements PacientesDAO{

    private PacientesSQLiteHelper paciHelper;

    public PacientesDAOSQLite(Context context){
        this.paciHelper = new PacientesSQLiteHelper(context, "DBPacientes"
        ,null,4);
    }

    @Override
    public List<Paciente> getAll() {
        SQLiteDatabase reader = this.paciHelper.getReadableDatabase();
        List<Paciente> pacientes = new ArrayList<>();
        try{
            if(reader != null){
                Cursor c = reader.rawQuery("SELECT rut,nombre,apellido,fechaExamen," +
                        "areaTrabajo,presentaSintomas,temperatura,presentaTos,presionArterial" +
                        " FROM productos",null);
                if(c.moveToFirst()){
                    do{
                        Paciente p = new Paciente();
                        p.setRutPaciente(c.getString(0));
                        p.setNombre(c.getString(1));
                        p.setApellido(c.getString(2));
                        p.setFechaExamen(c.getString(3));
                        p.setAreaTrabajo(c.getString(4));
                        p.setPresentaSintomas(c.getString(5));
                        p.setTemperatura(c.getDouble(6));
                        p.setPresentaTos(c.getString(7));
                        p.setPresionArterial(c.getInt(8));
                    }while(c.moveToNext());
                }
                reader.close();
            }
        }catch (Exception ex){
            pacientes=null;
        }
        return pacientes;
    }

    @Override
    public Paciente save(Paciente p) {
        SQLiteDatabase write = this.paciHelper.getWritableDatabase();
        String sql = String.format("INSERT INTO pacientes(rut,nombre,apellido,fechaExamen,areaTrabajo,presentaSintomas,temperatura,presentaTos,presionArterial)" +
                " VALUES ('%s','%s','%s','%s','%s','%s','%.2f','%s','%d')", p.getRutPaciente(), p.getNombre(), p.getApellido(), p.getFechaExamen(), p.getAreaTrabajo(), p.getPresentaSintomas(), p.getTemperatura(), p.getPresentaTos(), p.getPresionArterial());
        write.execSQL(sql);
        write.close();
        return p;
    }
}
