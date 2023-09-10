package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
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
    public ResponseEntity<List<EmpleadoDTO>> getEmpleados(){
        List<EmpleadoDTO> empleadoDTOListList = empleadoServiceImplement.getEmpleados();
        return ResponseEntity.ok(empleadoDTOListList);
    }

    @GetMapping("/{empleadoId}")
    public ResponseEntity<EmpleadoDTO> getEmpleadosById(@PathVariable("empleadoId") Integer empleadoId){
        EmpleadoDTO empleadoDTO = empleadoServiceImplement.getEmpleadoById(empleadoId);
        return ResponseEntity.ok(empleadoDTO);
    }

    @PostMapping()
    public ResponseEntity<EmpleadoDTO> addEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoAdded = empleadoServiceImplement.addEmpleado(empleadoDTO);
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set(HttpHeaders.LOCATION, String.format("/empleado/%d", empleadoAdded.getId()));

        return new ResponseEntity<EmpleadoDTO>(empleadoAdded, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{empleadoId}")
    public ResponseEntity<Object> updateEmpleado(@PathVariable("empleadoId") Integer empleadoId,
                                                @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoDTOUpdate = empleadoServiceImplement.updateEmpleado(empleadoDTO, empleadoId);
        return ResponseEntity.ok(empleadoDTOUpdate);
    }


    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable("empleadoId") Integer empleadoId) {
        this.empleadoServiceImplement.removeEmpleado(empleadoId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("El empleado fue eliminado con éxito.");
    }
}
