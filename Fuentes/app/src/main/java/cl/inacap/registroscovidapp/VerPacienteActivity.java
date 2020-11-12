package cl.inacap.registroscovidapp;

import android.os.Bundle;

import android.widget.TextView;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import java.util.List;


import cl.inacap.registroscovidapp.dao.PacientesDAO;
import cl.inacap.registroscovidapp.dao.PacientesDAOSQLite;
import cl.inacap.registroscovidapp.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private TextView rutTv;
    private TextView nombresTv;
    private TextView fechaTv;
    private TextView areaTrabajoTv;
    private TextView sintomasCovidTv;
    private TextView presentaTosTv;
    private TextView temperaturaTv;
    private TextView presionArterialTv;
    private Toolbar toolbar;
    private PacientesDAO pDAO = new PacientesDAOSQLite(this);
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(this.toolbar);
        //Permite ir hacia atras
        this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //MUESTRA EL ICONO
        this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        //INICIAR COMPONENTES
        this.rutTv = findViewById(R.id.rut_paci_lv);
        this.nombresTv = findViewById(R.id.nombres_paci_lv);
        this.fechaTv = findViewById(R.id.fecha_paci_lv);
        this.areaTrabajoTv = findViewById(R.id.area_trabajo_paci_lv);
        this.sintomasCovidTv = findViewById(R.id.sintomas_paci_lv);
        this.presentaTosTv = findViewById(R.id.presenta_tos_paci_lv);
        this.temperaturaTv = findViewById(R.id.temperatura__paci_lv);
        this.presionArterialTv = findViewById(R.id.presion_paci_lv);



        if(getIntent()!=null){
            String idPaciente = getIntent().getStringExtra("idPaciente");
            Paciente paciente = new Paciente();
            List<Paciente> pacientes = pDAO.getAll();
            for (Paciente p:pacientes){
                if (p.getRutPaciente().equals(idPaciente)){
                    paciente=p;
                    break;
                }
            }

        //RELLENAR COMPONENTES
            rutTv.setText(paciente.getRutPaciente());
            nombresTv.setText(paciente.getNombre() + " " + paciente.getApellido());
            fechaTv.setText(paciente.getFechaExamen());
            areaTrabajoTv.setText(paciente.getAreaTrabajo());
            sintomasCovidTv.setText(paciente.getPresentaSintomas());
            presentaTosTv.setText(paciente.getPresentaTos());
            temperaturaTv.setText(paciente.getTemperatura()+"°");
            presionArterialTv.setText(paciente.getPresionArterial()+"");
        }

    }



}
