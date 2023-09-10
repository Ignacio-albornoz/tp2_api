package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.services.ConceptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConceptoServiceImplement implements ConceptoService {

    @Autowired
    ConceptoRepository conceptoRepository;

    @Override
    public ConceptoDTO getConceptoById(Integer id) {
        Optional<Concepto> concepto = conceptoRepository.findById(id);

        if (concepto.isPresent()){
            return new ConceptoDTO(concepto.get());
        } else {
            throw new BussinessException("No se encontro el concepto con id: " + id, 404);

        }
    }

    @Override
    public List<ConceptoDTO> getConceptos() {

        List<Concepto> conceptos = (List<Concepto>) conceptoRepository.findAll();
        return conceptos.stream()
                .map(ConceptoDTO::new)
                .collect(Collectors.toList());
    }


    /* VALIDACIONES */

    public boolean checkConceptoById(Integer id){
        Optional<Concepto> optionalConcepto = conceptoRepository.findById(id);

        if (optionalConcepto.isPresent()){
            return true;
        }

        return false;
    }
}
