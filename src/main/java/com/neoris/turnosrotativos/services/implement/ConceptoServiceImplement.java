package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConceptoServiceImplement implements ConceptoService {

    @Autowired
    ConceptoRepository conceptoRepository;

    @Override
    public Concepto getConcepto(Long id) {
        return null;
    }

    @Override
    public List<Concepto> getConceptos() {
        return (List<Concepto>) conceptoRepository.findAll();
    }
}
