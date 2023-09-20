package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    JornadaServiceImplement jornadaServiceImplement;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> obtenerJornadas(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha,
            @RequestParam(required = false) Integer nroDocumento
    ) {
        List<Jornada> jornadas = jornadaServiceImplement.getJornadasByFechaAndDocumento(fecha, nroDocumento);

        Map<String, Object> responseBody = new LinkedHashMap<>();
        List<JornadaResponse> jornadasResponse = jornadas.stream()
                .map(jornada -> new JornadaResponse(jornada)) // Aplica el constructor de JornadaResponse a cada jornada
                .collect(Collectors.toList());

        responseBody.put("response", jornadasResponse);
        responseBody.put("isSuccess", true);

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Map<String, Object>> addJornada(@Valid @RequestBody JornadaRequest jornadaRequest) {

        JornadaResponse jornadaResponse = jornadaServiceImplement.addJornada(jornadaRequest);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, String.format("/jornada/%d", jornadaResponse.getId()));

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("response", jornadaResponse);
        responseBody.put("isSuccess", true);

        return new ResponseEntity<Map<String, Object>>(responseBody, responseHeaders, HttpStatus.CREATED);

    }

    @GetMapping("/exists/{nroDocumento}")
    public ResponseEntity<Object> existsByEmpleadoNroDocumento(@PathVariable("nroDocumento") Integer nroDocumento){
        try {
            Boolean exists = jornadaServiceImplement.existsByEmpleadoNroDocumento(nroDocumento);

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("response", exists);
            responseBody.put("isSuccess", true);

            return ResponseEntity.ok(responseBody);
        } catch (NumberFormatException e) {
            // si el String no es un entero valido
            return ResponseEntity.badRequest().body("No se ingreso un DNI valido"); // devuelve una respuesta de error
        }
    }

}
