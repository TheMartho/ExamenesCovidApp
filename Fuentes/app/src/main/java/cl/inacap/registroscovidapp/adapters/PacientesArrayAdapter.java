package cl.inacap.registroscovidapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
        TextView toStringPaciente = fila.findViewById(R.id.paciente_to_string);
        ImageView advertencia = fila.findViewById(R.id.advertencia);
        Paciente actual = pacientes.get(position);
        toStringPaciente.setText(actual.toString());
        if (actual.getPresentaSintomas().equalsIgnoreCase("sí")){
            advertencia.setImageResource(R.drawable.ic_baseline_warning_24);
        }
        return fila;
    }
}
