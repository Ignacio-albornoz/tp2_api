package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
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
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/jornada")
public class JornadaController {

    @Autowired
    JornadaServiceImplement jornadaServiceImplement;

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

    /*@GetMapping("/{empleadoId}")
    public ResponseEntity<Jornada> getJornadasById(@PathVariable("empleadoId") Integer empleadoId){
        Jornada empleado = jornadaServiceImplement.getJornadaById(empleadoId);
        return ResponseEntity.ok(empleado);
    }*/

    @PostMapping()
    public ResponseEntity<Jornada> addJornada(@RequestBody JornadaRequest jornadaRequest) {

        jornadaServiceImplement.addJornada(jornadaRequest);


        /*responseHeaders.set(HttpHeaders.LOCATION, String.format("/jornada/%d", jornadaAdded.getId()));*/

        return new ResponseEntity<Jornada>(HttpStatus.CREATED);
    }

    /*@PutMapping("/{empleadoId}")
    public ResponseEntity<Object> updateJornada(@PathVariable("empleadoId") Integer empleadoId,
                                                @Valid @RequestBody Jornada empleado) {
        Jornada studentMod = jornadaServiceImplement.updateJornada(empleado, empleadoId);
        return ResponseEntity.ok(studentMod);
    }*/


    /*@DeleteMapping("/{empleadoId}")
    public ResponseEntity<Object> deleteJornada(@PathVariable("empleadoId") Integer empleadoId) {
        this.jornadaServiceImplement.removeJornada(empleadoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El empleado fue eliminado con éxito.");
    }*/
}
