package cl.inacap.registroscovidapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import cl.inacap.registroscovidapp.R;
import cl.inacap.registroscovidapp.dto.Paciente;

public class PacientesArrayAdapter extends ArrayAdapter<Paciente> {
    private Activity activity;
    private List<Paciente> pacientes;
    public PacientesArrayAdapter(@NonNull Activity context, int resource, @NonNull List<Paciente> objects) {
        super(context, resource, objects);
        this.activity=context;
        this.pacientes=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater = this.activity.getLayoutInflater();
        View fila = inflater.inflate(R.layout.ver_pacientes_list,null,true);
        TextView rutTv = fila.findViewById(R.id.rut_paci_lv);
        TextView nombresTv = fila.findViewById(R.id.nombres_paci_lv);
        TextView fechaTv = fila.findViewById(R.id.fecha_paci_lv);
        TextView areaTrabajoTv = fila.findViewById(R.id.area_trabajo_paci_lv);
        TextView sintomasCovidTv = fila.findViewById(R.id.sintomas_paci_lv);
        TextView presentaTosTv = fila.findViewById(R.id.presenta_tos_paci_lv);
        TextView temperaturaTv = fila.findViewById(R.id.temperatura__paci_lv);
        TextView presionArterialTv = fila.findViewById(R.id.presion_paci_lv);

        Paciente actual = pacientes.get(position);
        rutTv.setText(actual.getRutPaciente());
        nombresTv.setText(actual.getNombre() + " " + actual.getApellido());
        fechaTv.setText(actual.getFechaExamen());
        areaTrabajoTv.setText(actual.getAreaTrabajo());
        sintomasCovidTv.setText(actual.getPresentaSintomas());
        presentaTosTv.setText(actual.getPresentaTos());
        temperaturaTv.setText(actual.getTemperatura()+"°");
        presionArterialTv.setText(actual.getPresionArterial()+"");

        return fila;
    }
}
