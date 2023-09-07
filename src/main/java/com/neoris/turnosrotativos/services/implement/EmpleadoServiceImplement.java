package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public Empleado getEmpleadoById(Integer id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if (empleado.isPresent()){
            return empleado.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el empleado con id: " + empleado.get().getId());
        }
    }

    @Override
    public Empleado getEmpleadoByEmail(String email) {
        Optional<Empleado> empleado = empleadoRepository.findByEmail(email);

        if (empleado.isPresent()){
            return empleado.get();
        } else {
            return null;
        }
    }

    @Override
    public Empleado getEmpleadoByNroDocumento(Integer nroDocumento) {

        Optional<Empleado> empleado = empleadoRepository.findByNroDocumento(nroDocumento);

        if (empleado.isPresent()){
            return empleado.get();
        } else {
            return null;
        }
    }

    @Override
    public List<Empleado> getEmpleados() {
        return (List<Empleado>) empleadoRepository.findAll();
    }

    @Override
    public Empleado addEmpleado(Empleado empleado) {
        Optional<Empleado> empleadoOptionalfindByEmail = empleadoRepository.findByEmail(empleado.getEmail());

        Optional<Empleado> empleadoOptionalfindByNroDocumento = empleadoRepository.findByNroDocumento(empleado.getNroDocumento());

        if (empleadoOptionalfindByEmail.isPresent()){
            throw new BussinessException("Ya existe un empleado con el email ingresado");
        }

        if (empleadoOptionalfindByNroDocumento.isPresent()){
            throw new BussinessException("Ya existe un empleado con el documento ingresado");
        }

        //Valida si el empleado es mayor a 18 años, a travez de un metodo void, que devolvera una excepcion en el caso de no serlo.
        validarEdad(empleado.getFechaNacimiento());



        Empleado empleadoAdded = empleadoRepository.save(empleado);
        return empleadoAdded;

    }

    @Override
    public Empleado updateEmpleado(Empleado empleado, Integer id) {

        Optional<Empleado> empleadoOptionalFindById = empleadoRepository.findById(id);

        Optional<Empleado> empleadoOptionalfindByEmail = empleadoRepository.findByEmail(empleado.getEmail());

        Optional<Empleado> empleadoOptionalfindByNroDocumento = empleadoRepository.findByNroDocumento(empleado.getNroDocumento());

        if (empleadoOptionalfindByEmail.isPresent()){
            throw new BussinessException("Ya existe un empleado con el email ingresado");
        }

        if (empleadoOptionalfindByNroDocumento.isPresent()){
            throw new BussinessException("Ya existe un empleado con el documento ingresado");
        }

        //Valida si el empleado es mayor a 18 años, a travez de un metodo void, que devolvera una excepcion en el caso de no serlo.
        validarEdad(empleado.getFechaNacimiento());

        if (empleadoOptionalFindById.isPresent()){

            Empleado empleadoAdded = empleadoRepository.save(empleado);
            return empleadoAdded;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el empleado con id: " + id);


    }

    @Override
    public void removeEmpleado(Integer id) {
        Optional<Empleado> empleadoOptionalFindById = empleadoRepository.findById(id);

        if (empleadoOptionalFindById.isPresent()){
            empleadoRepository.delete(empleadoOptionalFindById.get());
            return;
        }

        throw new BussinessException("Empleado no encontrado");
    }

    /* VALIDACIONES */

    private void validarEdad (LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();
        if (edad < 18) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "La edad del empleado no puede ser menor a 18 años.");
        }
    }

}
