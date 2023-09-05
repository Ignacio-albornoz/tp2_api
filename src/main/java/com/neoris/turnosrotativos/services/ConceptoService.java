package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.entities.Concepto;

import java.util.List;
import java.util.Optional;

public interface ConceptoService {

    public Concepto getConceptoById(Integer id);

    public List<Concepto> getConceptos();

}
