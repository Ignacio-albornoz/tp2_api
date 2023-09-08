package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConceptoServiceImplement implements ConceptoService {

    @Autowired
    ConceptoRepository conceptoRepository;

    @Override
    public Concepto getConceptoById(Integer id) {
        Optional<Concepto> concepto = conceptoRepository.findById(id);

        if (concepto.isPresent()){
            return concepto.get();
        } else {
            throw new BussinessException("El concepto con id: " + id + " no existe.", 404);

        }
    }

    @Override
    public List<Concepto> getConceptos() {
        return (List<Concepto>) conceptoRepository.findAll();
    }
}
