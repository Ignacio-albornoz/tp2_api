package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.services.implement.EmpleadoServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoServiceImplement empleadoServiceImplement;

    @GetMapping()
    public ResponseEntity<List<Empleado>> getEmpleados(){
        List<Empleado> empleadoList = empleadoServiceImplement.getEmpleados();
        return ResponseEntity.ok(empleadoList);
    }

    @GetMapping("/{empleadoId}")
    public ResponseEntity<Empleado> getEmpleadosById(@PathVariable("empleadoId") Integer empleadoId){
        Empleado empleado = empleadoServiceImplement.getEmpleadoById(empleadoId);
        return ResponseEntity.ok(empleado);
    }

    @PostMapping()
    public ResponseEntity<Empleado> addEmpleado(@Valid @RequestBody Empleado empleado) {
        Empleado empleadoAdded = empleadoServiceImplement.addEmpleado(empleado);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, String.format("/empleado/%d", empleadoAdded.getId()));

        return new ResponseEntity<Empleado>(empleadoAdded, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{empleadoId}")
    public ResponseEntity<Object> updateEmpleado(@PathVariable("empleadoId") Integer empleadoId,
                                                @Valid @RequestBody Empleado empleado) {
        Empleado studentMod = empleadoServiceImplement.updateEmpleado(empleado, empleadoId);
        return ResponseEntity.ok(studentMod);
    }


    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable("empleadoId") Integer empleadoId) {
        this.empleadoServiceImplement.removeEmpleado(empleadoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
