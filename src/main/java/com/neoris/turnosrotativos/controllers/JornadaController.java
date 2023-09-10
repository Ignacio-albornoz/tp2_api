package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;
import com.neoris.turnosrotativos.services.implement.JornadaServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Jornada>> obtenerJornadas(
            @RequestParam(required = false) LocalDate fecha,
            @RequestParam(required = false) Integer nroDocumento
    ) {
        // Llamar al método del servicio que se encarga de filtrar las jornadas según los parámetros
        List<Jornada> jornadas = jornadaServiceImplement.getJornadasByNroDocumentoAndFecha(nroDocumento, fecha);
        // Retornar la lista de jornadas con el código 200
        return new ResponseEntity<>(jornadas, HttpStatus.OK);
    }

    @GetMapping("/lista")
    public List<Jornada> getJornadasCompletas(){
        List<Jornada> jornadas = jornadaServiceImplement.getJornadas();

        return jornadas;
    }

    @GetMapping()
    public ResponseEntity<List<JornadaResponse>> getJornadas() {
        List<JornadaResponse> jornadas = jornadaServiceImplement.getJornadas().stream()
                .map(jornada -> new JornadaResponse(
                        jornada.getEmpleado().getNroDocumento(),
                        jornada.getEmpleado().getNombre() + " " + jornada.getEmpleado().getApellido(),
                        jornada.getFecha(),
                        jornada.getConcepto().getNombre(),
                        jornada.getHsTrabajadas()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(jornadas);
    }

    @PostMapping()
    public ResponseEntity<JornadaResponse> addJornada(@Valid @RequestBody JornadaRequest jornadaRequest) {

        Jornada jornadaAdded = jornadaServiceImplement.addJornada(jornadaRequest);
        JornadaResponse jornadaResponse = new JornadaResponse(jornadaAdded);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, String.format("/jornada/%d", jornadaAdded.getId()));

        return new ResponseEntity<JornadaResponse>(jornadaResponse, responseHeaders, HttpStatus.CREATED);

    }

}
