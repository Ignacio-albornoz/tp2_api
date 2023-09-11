package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.data.repository.CrudRepository;

public interface EmpleadoRepository extends CrudRepository<Empleado, Integer> {

    Boolean existsByEmail(String email);

    Boolean existsByNroDocumento(Integer nroDocumento);
}
