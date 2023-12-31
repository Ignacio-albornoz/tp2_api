package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import com.neoris.turnosrotativos.services.implement.ConceptoServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/concepto")
public class ConceptoController {

    @Autowired
    ConceptoServiceImplement conceptoServiceImplement;

    @GetMapping()
    public ResponseEntity<List<ConceptoDTO>> getConceptos(){
        List<ConceptoDTO> conceptoList = conceptoServiceImplement.getConceptos();
        return ResponseEntity.ok(conceptoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConceptoDTO> getConceptosById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(conceptoServiceImplement.getConceptoById(id));
    }
}
