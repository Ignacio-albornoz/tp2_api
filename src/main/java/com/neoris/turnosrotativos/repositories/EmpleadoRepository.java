package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    Optional<Empleado> findByEmail(String email);

    Optional<Empleado> findByNroDocumento(Integer nroDocumento);
}
