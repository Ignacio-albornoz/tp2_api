package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;


public interface JornadaRepository extends CrudRepository<Jornada, Integer> {

    public List<Jornada> findByEmpleadoNroDocumento(Integer nroDocumento);

    public List<Jornada> findByFecha(LocalDate fecha);

    public List<Jornada> findByEmpleadoNroDocumentoAndFecha(Integer nroDocumento, LocalDate fecha);

    public Boolean existsByEmpleado(Empleado empleado);

    public Boolean existsByEmpleadoNroDocumento(Integer nroDocumento);

}


