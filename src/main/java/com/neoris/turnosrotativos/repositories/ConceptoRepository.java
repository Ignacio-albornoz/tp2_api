package com.neoris.turnosrotativos.repositories;

import com.neoris.turnosrotativos.entities.Concepto;
import org.springframework.data.repository.CrudRepository;

public interface ConceptoRepository extends CrudRepository<Concepto, Long> {
}
