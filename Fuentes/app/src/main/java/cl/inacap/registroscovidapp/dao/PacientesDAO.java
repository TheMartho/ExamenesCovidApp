package cl.inacap.registroscovidapp.dao;

import java.util.List;

import cl.inacap.registroscovidapp.dto.Paciente;

public interface PacientesDAO {

    List<Paciente> getAll();
    Paciente save (Paciente p);

}
