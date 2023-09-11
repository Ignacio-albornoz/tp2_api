package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;
import com.neoris.turnosrotativos.services.implement.JornadaServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    JornadaServiceImplement jornadaServiceImplement;

    @GetMapping()
    public ResponseEntity<List<JornadaResponse>> obtenerJornadas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Integer nroDocumento
    ) {
        List<Jornada> jornadas = jornadaServiceImplement.getJornadasByFechaAndDocumento(fecha, nroDocumento);

        List<JornadaResponse> jornadasResponse = jornadas.stream()
                .map(jornada -> new JornadaResponse(jornada)) // Aplica el constructor de JornadaResponse a cada jornada
                .collect(Collectors.toList());

        return new ResponseEntity<>(jornadasResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<JornadaResponse> addJornada(@Valid @RequestBody JornadaRequest jornadaRequest) {

        JornadaResponse jornadaResponse = jornadaServiceImplement.addJornada(jornadaRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, String.format("/jornada/%d", jornadaResponse.getId()));

        return new ResponseEntity<JornadaResponse>(jornadaResponse, responseHeaders, HttpStatus.CREATED);

    }

}
