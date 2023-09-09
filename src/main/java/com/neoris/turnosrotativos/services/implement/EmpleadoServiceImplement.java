package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.services.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
            throw new BussinessException("No se encontro el empleado con id: " + id, 404);
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
            throw new BussinessException("Ya existe un empleado con el email ingresado", 409);
        }

        if (empleadoOptionalfindByNroDocumento.isPresent()){
            throw new BussinessException("Ya existe un empleado con el documento ingresado", 409);
        }

        //esMayorDeEdad == false, se dispara un Bad request
        if (!esMayorDeEdad(empleado.getFechaNacimiento())){
            throw new BussinessException("La edad del empleado no puede ser menor a 18 años.", 400);
        }


        Empleado empleadoAdded = empleadoRepository.save(empleado);
        return empleadoAdded;

    }

    @Override
    public Empleado updateEmpleado(Empleado empleado, Integer id) {

        Optional<Empleado> empleadoOptionalFindById = empleadoRepository.findById(id);

        Optional<Empleado> empleadoOptionalfindByEmail = empleadoRepository.findByEmail(empleado.getEmail());

        Optional<Empleado> empleadoOptionalfindByNroDocumento = empleadoRepository.findByNroDocumento(empleado.getNroDocumento());

        if (empleadoOptionalfindByEmail.isPresent()){
            throw new BussinessException("Ya existe un empleado con el email ingresado", 409);
        }

        if (empleadoOptionalfindByNroDocumento.isPresent()){
            throw new BussinessException("Ya existe un empleado con el documento ingresado", 409);
        }

        //esMayorDeEdad == false, se dispara un Bad request
        if (!esMayorDeEdad(empleado.getFechaNacimiento())){
            throw new BussinessException("La edad del empleado no puede ser menor a 18 años.", 400);
        }

        if (empleadoOptionalFindById.isPresent()){

            Empleado empleadoAdded = empleadoRepository.save(empleado);
            return empleadoAdded;
        }

        throw new BussinessException("No se encontro el empleado con id: " + id, 404);


    }

    @Override
    public void removeEmpleado(Integer id) {
        Optional<Empleado> empleadoOptionalFindById = empleadoRepository.findById(id);

        if (empleadoOptionalFindById.isPresent()){
            empleadoRepository.delete(empleadoOptionalFindById.get());
            return;
        }

        throw new BussinessException("Empleado no encontrado", 404);
    }

    /* VALIDACIONES */

    private boolean esMayorDeEdad (LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        int edad = Period.between(fechaNacimiento, fechaActual).getYears();
        if (edad < 18) {
            return false;
        }
        return true;
    }

    public boolean checkEmpleadoById(Integer id){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findById(id);

        if (optionalEmpleado.isPresent()){
            return true;
        }

        return false;
    }

    private boolean checkEmpleadoByNroDocumento(Integer nroDocumento){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findByNroDocumento(nroDocumento);

        if (optionalEmpleado.isPresent()){
            return true;
        }

        return false;
    }

    private boolean checkEmpleadoByEmail(String email){
        Optional<Empleado> optionalEmpleado = empleadoRepository.findByEmail(email);

        if (optionalEmpleado.isPresent()){
            return true;
        }

        return false;
    }



}
