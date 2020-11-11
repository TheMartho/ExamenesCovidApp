package cl.inacap.registroscovidapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.registroscovidapp.adapters.PacientesArrayAdapter;
import cl.inacap.registroscovidapp.dao.PacientesDAO;
import cl.inacap.registroscovidapp.dao.PacientesDAOSQLite;
import cl.inacap.registroscovidapp.dto.Paciente;

public class VerPacienteActivity extends AppCompatActivity {

    private FloatingActionButton agregarBtn;
    private ListView pacientesLv;
    private List<Paciente> pacientes = new ArrayList<>();
    private PacientesArrayAdapter adaptador;
    private PacientesDAO pacientesDAO = new PacientesDAOSQLite(this);
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_paciente);
        this.setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        this.agregarBtn=findViewById(R.id.agregar_btn_fb);
        this.agregarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VerPacienteActivity.this, AgregarPacienteActivity.class));
            }
        });
        pacientes = pacientesDAO.getAll();
        if(pacientes!=null){
            adaptador = new PacientesArrayAdapter(this,R.layout.ver_pacientes_list,pacientes);
            pacientesLv=findViewById(R.id.pacientes_lv);
            pacientesLv.setAdapter(adaptador);
        }else{
            Toast.makeText(VerPacienteActivity.this, "Ta Mal",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

}
