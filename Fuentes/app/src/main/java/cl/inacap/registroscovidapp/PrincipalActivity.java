package cl.inacap.registroscovidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroscovidapp.adapters.PacientesArrayAdapter;
import cl.inacap.registroscovidapp.dao.PacientesDAO;
import cl.inacap.registroscovidapp.dao.PacientesDAOSQLite;
import cl.inacap.registroscovidapp.dto.Paciente;

public class PrincipalActivity extends AppCompatActivity {
    private FloatingActionButton agregarBtn;
    private ListView pacientesLv;
    private List<Paciente> pacientes = new ArrayList<>();
    private PacientesArrayAdapter adaptador;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        this.setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        this.pacientesLv=findViewById(R.id.pacientes_lv);
        this.agregarBtn=findViewById(R.id.agregar_btn_fb);
        pacientes = pacientesDAO.getAll();
        this.pacientesLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(PrincipalActivity.this, VerPacienteActivity.class);
                Paciente paciActual = pacientes.get(i);
                intent.putExtra("idPaciente", paciActual.getRutPaciente());
                startActivity(intent);

            }
        });

        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PrincipalActivity.this, AgregarPacienteActivity.class));
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        pacientes = pacientesDAO.getAll();
        if(pacientes!=null) {
            adaptador = new PacientesArrayAdapter(this, R.layout.ver_pacientes_list, pacientes);
            pacientesLv = findViewById(R.id.pacientes_lv);
            pacientesLv.setAdapter(adaptador);
        }

    }
}