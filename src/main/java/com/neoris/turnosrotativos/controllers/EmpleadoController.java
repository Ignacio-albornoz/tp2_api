package com.neoris.turnosrotativos.controllers;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.services.implement.EmpleadoServiceImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/empleado")

public class EmpleadoController {

    @Autowired
    EmpleadoServiceImplement empleadoServiceImplement;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getEmpleados(){
        List<EmpleadoDTO> empleadoDTOListList = empleadoServiceImplement.getEmpleados();
        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("response", empleadoDTOListList);
        responseBody.put("isSuccess", true);
        return ResponseEntity.ok(responseBody);
    }

    @GetMapping("/{empleadoId}")
    public ResponseEntity<Object> getEmpleadosById(@PathVariable("empleadoId") String empleadoId){
        try {
            Integer id = Integer.parseInt(empleadoId);
            EmpleadoDTO empleadoDTO = empleadoServiceImplement.getEmpleadoById(id);

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("response", empleadoDTO);
            responseBody.put("isSuccess", true);

            return ResponseEntity.ok(responseBody);
        } catch (NumberFormatException e) {
            // si el String no es un entero valido
            return ResponseEntity.badRequest().body("No se ingreso un id valido - Bad Request"); // devuelve una respuesta de error
        }
    }


    @PostMapping()
    public ResponseEntity<Map<String, Object>> addEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoAdded = empleadoServiceImplement.addEmpleado(empleadoDTO);
        HttpHeaders responseHeaders = new HttpHeaders();

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("response", empleadoAdded);
        responseBody.put("isSuccess", true);

        responseHeaders.set(HttpHeaders.LOCATION, String.format("/empleado/%d", empleadoAdded.getId()));

        return new ResponseEntity<Map<String, Object>>(responseBody, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{empleadoId}")
    public ResponseEntity<Object> updateEmpleado(@PathVariable("empleadoId") Integer empleadoId,
                                                @Valid @RequestBody EmpleadoDTO empleadoDTO) {
        EmpleadoDTO empleadoDTOUpdate = empleadoServiceImplement.updateEmpleado(empleadoDTO, empleadoId);

        Map<String, Object> responseBody = new LinkedHashMap<>();
        responseBody.put("response", empleadoDTOUpdate);
        responseBody.put("isSuccess", true);

        return ResponseEntity.ok(responseBody);
    }


    @DeleteMapping("/{empleadoId}")
    public ResponseEntity<Object> deleteEmpleado(@PathVariable("empleadoId") Integer empleadoId) {
        this.empleadoServiceImplement.removeEmpleado(empleadoId);

        Map<String, Object> responseBody = new LinkedHashMap<>();

        responseBody.put("isSuccess", true);
        responseBody.put("message", "El empleado fue eliminado con éxito.");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(responseBody);
    }

    /* Controller Exists*/
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/exists/nroDocumento/{nroDocumento}")
    public ResponseEntity<Object> existsByNroDocumento(@PathVariable("nroDocumento") Integer nroDocumento){
        try {
            Boolean exists = empleadoServiceImplement.existsByNroDocumento(nroDocumento);

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("response", exists);
            responseBody.put("isSuccess", true);

            return ResponseEntity.ok(responseBody);
        } catch (NumberFormatException e) {
            // si el String no es un entero valido
            return ResponseEntity.badRequest().body("No se ingreso un DNI valido"); // devuelve una respuesta de error
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/exists/{email}")
    public ResponseEntity<Object> existsByEmail(@PathVariable("email") String email){
        try {
            Boolean exists = empleadoServiceImplement.existsByEmail(email);

            Map<String, Object> responseBody = new LinkedHashMap<>();
            responseBody.put("response", exists);
            responseBody.put("isSuccess", true);

            return ResponseEntity.ok(responseBody);
        } catch (NumberFormatException e) {
            // si el String no es un entero valido
            return ResponseEntity.badRequest().body("No se ingreso un email valido"); // devuelve una respuesta de error
        }
    }
}
