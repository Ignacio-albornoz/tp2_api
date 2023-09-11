package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import java.util.List;


public interface ConceptoService {

    public ConceptoDTO getConceptoById(Integer id);

    public List<ConceptoDTO> getConceptos();

}
