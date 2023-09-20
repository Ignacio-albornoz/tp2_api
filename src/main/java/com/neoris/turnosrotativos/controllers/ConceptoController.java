package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.ConceptoDTO;
import com.neoris.turnosrotativos.services.implement.ConceptoServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/concepto")
public class ConceptoController {

    @Autowired
    ConceptoServiceImplement conceptoServiceImplement;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getConceptos(){
        List<ConceptoDTO> conceptoList = conceptoServiceImplement.getConceptos();

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("response", conceptoList);
        responseBody.put("isSuccess", true);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getConceptosById(@PathVariable("id") Integer id){
        return ResponseEntity.ok(conceptoServiceImplement.getConceptoById(id));
    }
}
