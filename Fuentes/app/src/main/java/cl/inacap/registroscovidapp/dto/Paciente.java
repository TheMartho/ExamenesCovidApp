package cl.inacap.registroscovidapp.dto;

import android.app.DatePickerDialog;

public class Paciente {

    private String rutPaciente;
    private String nombre;
    private String apellido;
    private String fechaExamen;
    private String areaTrabajo;
    private String presentaSintomas;
    private Double temperatura;
    private String presentaTos;
    private int presionArterial;

    public Double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public String getPresentaSintomas() {
        return presentaSintomas;
    }

    public void setPresentaSintomas(String presentaSintomas) {
        this.presentaSintomas = presentaSintomas;
    }

    public String getPresentaTos() {
        return presentaTos;
    }

    public void setPresentaTos(String presentaTos) {
        this.presentaTos = presentaTos;
    }

    public String getFechaExamen() {
        return fechaExamen;
    }

    public void setFechaExamen(String fechaExamen) {
        this.fechaExamen = fechaExamen;
    }

    public String getRutPaciente() {
        return rutPaciente;
    }

    public void setRutPaciente(String rutPaciente) {
        this.rutPaciente = rutPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public int getPresionArterial() {
        return presionArterial;
    }

    public void setPresionArterial(int presionArterial) {
        this.presionArterial = presionArterial;
    }


    @Override
    public String toString() {
        return "Rut: "+rutPaciente+"\n"
                +"Nombre: "+nombre+"\n"
                +"Apellido: "+apellido+"\n"
                +"Fecha de Examen: "+fechaExamen+"\n";
    }
}
