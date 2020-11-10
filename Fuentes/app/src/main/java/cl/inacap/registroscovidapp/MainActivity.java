package cl.inacap.registroscovidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText passwordTxt;
    private EditText nombreTxt;
    private Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.passwordTxt = findViewById(R.id.password);
        this.nombreTxt = findViewById(R.id.nombre);

        this.loginBtn = findViewById(R.id.login);
        this.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = passwordTxt.getText().toString().trim();
                String nombre = nombreTxt.getText().toString().trim();
                String passwordEsperada="";
                int proseguir =0;
                if(nombre.equals("")){
                    nombreTxt.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.edittext_borde_rojo_background));
                    Toast.makeText(MainActivity.this,
                            "Debe Ingresar nombre de usuario",
                            Toast.LENGTH_SHORT).show();
                }else{
                    if(verificarRut(nombre)==false){
                        Toast.makeText(MainActivity.this,
                                "Nombre de Usuario Invalido",
                                Toast.LENGTH_SHORT).show();
                    }else{
                     proseguir = 1;
                    }
                }

                if(proseguir==1){
                    char [] caracteres = nombre.toCharArray();
                    if (nombre.length()==10){
                        passwordEsperada="" + caracteres[4] + caracteres[5] + caracteres[6] + caracteres[7];
                    }else {
                        passwordEsperada="" + caracteres[3] + caracteres[4] + caracteres[5] + caracteres[6];
                    }
                    if(passwordEsperada.equals(password)){
                        proseguir = 2;
                    }
                }

                if (proseguir==2){
                    //A LA SIGUIENTE ACTIVITY
                    Intent intent = new Intent(MainActivity.this, VerPacienteActivity.class);
                    startActivity(intent);

                }

            }
        });





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


}