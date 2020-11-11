package cl.inacap.registroscovidapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import cl.inacap.registroscovidapp.dao.PacientesDAO;
import cl.inacap.registroscovidapp.dao.PacientesDAOSQLite;
import cl.inacap.registroscovidapp.dto.Paciente;

public class AgregarPacienteActivity extends AppCompatActivity {

    private EditText rutPacienteTxt;
    private EditText nombreTxt;
    private EditText apellidoTxt;
    private EditText fechaExamenTxt;
    private Calendar calendario = Calendar.getInstance();
    private Spinner spinnerAreaTrabajo;
    private Switch switchSintomasCovid;
    private EditText temperaturaTxt;
    private Switch switchPresentaTos;
    private EditText presionArterialTxt;
    private Button agregarPacienteBtn;
    private PacientesDAO pDAO = new PacientesDAOSQLite(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_paciente);

        this.rutPacienteTxt = findViewById(R.id.rut_paci);
        this.nombreTxt=findViewById(R.id.nombre_paci);
        this.apellidoTxt=findViewById(R.id.apellido_paci);
        this.fechaExamenTxt=findViewById(R.id.fecha_paci);
        this.spinnerAreaTrabajo = findViewById(R.id.area_trabajo_paci);
        this.switchPresentaTos = findViewById(R.id.tos_paci);
        this.switchSintomasCovid=findViewById(R.id.sintomas_paci);
        this.temperaturaTxt=findViewById(R.id.temperatura_paci);
        this.presionArterialTxt = findViewById(R.id.presion_paci);
        this.agregarPacienteBtn = findViewById(R.id.agregar_btn);

        //SE CREA EL CALENDARIO
        this.fechaExamenTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AgregarPacienteActivity.this, date, calendario
                        .get(Calendar.YEAR), calendario.get(Calendar.MONTH),
                        calendario.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //SE LLENAN EL SPINNER
        String [] areas ={"Atención a publico","Otro"};
        this.spinnerAreaTrabajo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,areas));

        this.agregarPacienteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<String>errores = new ArrayList<>();
                String rutStr = rutPacienteTxt.getText().toString().trim();
                String nombreStr = nombreTxt.getText().toString().trim();
                String apellidoStr = apellidoTxt.getText().toString().trim();
                String fechaStr="";
                String areaTrabajoStr = spinnerAreaTrabajo.getSelectedItem().toString();
                String temperaturaStr= temperaturaTxt.getText().toString().trim();
                String sintomasCovidStr="";
                String presionArterialStr = presionArterialTxt.getText().toString().trim();
                if(switchSintomasCovid.isChecked()){
                    sintomasCovidStr="Sí";
                }else{
                    sintomasCovidStr="No";
                }
                if(temperaturaStr==null){
                    errores.add("Debe Ingresar Una Temperatura");
                }else{
                    double temperaturaDou = Double.parseDouble(temperaturaStr);
                }
                double temperaturaDou = Double.parseDouble(temperaturaTxt.getText().toString().trim());
                String presentaTosStr = "";
                if(switchPresentaTos.isChecked()){
                    presentaTosStr="Sí";
                }else {
                    presentaTosStr="No";
                }
                if (presionArterialStr==null){
                    errores.add("Debe Ingresar La Presion Arterial");
                }else {
                    int presionArterial=Integer.parseInt(presionArterialStr);
                }
                int presionArterial=Integer.parseInt(presionArterialTxt.getText().toString().trim());

                if(verificarRut(rutStr)==false){
                    errores.add("El Rut No Es Valido");
                }
                if (nombreStr.isEmpty()){
                    errores.add("Debe Ingresar Un Nombre");
                }
                if(apellidoStr.isEmpty()){
                    errores.add("Debe Ingresar Un Apellido");
                }
                Date fechaUsuario = calendario.getTime();
                Date fechaActual = new Date();
                if(fechaUsuario.before(fechaActual)){
                    errores.add("La Fecha Ingresada Es Anterior Al Dia Actual");
                }else{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy",Locale.US);
                    fechaStr = sdf.format(calendario.getTime());
                }

                if(errores.isEmpty()){
                    Paciente p = new Paciente();
                    p.setRutPaciente(rutStr);
                    p.setNombre(nombreStr);
                    p.setApellido(apellidoStr);
                    p.setFechaExamen(fechaStr);
                    p.setAreaTrabajo(areaTrabajoStr);
                    p.setPresentaSintomas(sintomasCovidStr);
                    p.setTemperatura(temperaturaDou);
                    p.setPresentaTos(presentaTosStr);
                    p.setPresionArterial(presionArterial);
                    pDAO.save(p);
                    startActivity(new Intent(AgregarPacienteActivity.this, VerPacienteActivity.class));
                }else{
                    mostrarErrores(errores);
                }

            }
        });
    }

    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            calendario.set(Calendar.YEAR, year);
            calendario.set(Calendar.MONTH, monthOfYear);
            calendario.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            actualizarInput();
        }

    };

    private void actualizarInput() {
        String formatoDeFecha = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(formatoDeFecha, Locale.US);

        fechaExamenTxt.setText(sdf.format(calendario.getTime()));
    }

    public boolean verificarRut(String rut){
        boolean esValido = true;
        if (rut.length() > 8 && rut.length() < 11){
            try {
                String [] rutSeparado = rut.split("-");
                if (rutSeparado.length == 2){
                    int dv=0;
                    try{
                        dv = Integer.parseInt(rutSeparado[1]);
                    }catch (Exception ex){
                        dv=20;
                    }


                    if ((dv > -1 && dv<10) || rutSeparado[1].equalsIgnoreCase("k")){
                        String hayPuntos [] = rutSeparado[0].split("\\.");
                        if (hayPuntos.length==1){
                            esValido=true;
                            Integer.parseInt(rutSeparado[0]);
                        }else{
                            esValido = false;
                        }
                    }else {
                        esValido = false;
                    }
                }else{
                    esValido = false;
                }
            }catch (Exception ex){
                esValido = false;
            }

        }else{
            esValido = false;
        }
        return esValido;
    }

    private void mostrarErrores(List<String> errores){
        //1. Generar una cadena de texto con los errores
        String mensaje="";
        for(String e:errores){
            mensaje+= "-" + e + "\n";
        }
        //2. Mostrar un mensaje de alerta
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(AgregarPacienteActivity.this);
        alertBuilder
                .setTitle("Error De Validacion")
                .setMessage(mensaje)
                .setPositiveButton("Aceptar",null)
                .create()
                .show();
    }

}