package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
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
import java.util.stream.Collectors;

@Service
public class EmpleadoServiceImplement implements EmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;


    @Override
    public EmpleadoDTO getEmpleadoById(Integer id) {
        Optional<Empleado> empleado = empleadoRepository.findById(id);

        if (empleado.isPresent()){
            return new EmpleadoDTO(empleado.get());
        } else {
            throw new BussinessException("No se encontro el empleado con id: " + id, 404);
        }
    }


    @Override
    public List<EmpleadoDTO> getEmpleados(){
        List<Empleado> empleados = (List<Empleado>) empleadoRepository.findAll();
        return empleados
                .stream()
                .map(EmpleadoDTO::new)
                .collect(Collectors.toList());
    }


    @Override
    public EmpleadoDTO addEmpleado(EmpleadoDTO empleadoDTO) {

        if (existsEmpleadoWithEmail(empleadoDTO.getEmail())){
            throw new BussinessException("Ya existe un empleado con el email ingresado", 409);
        }

        if (existsEmpleadoWithNroDocumento(empleadoDTO.getNroDocumento())){
            throw new BussinessException("Ya existe un empleado con el documento ingresado", 409);
        }

        //esMayorDeEdad == false, se dispara un Bad request
        if (!esMayorDeEdad(empleadoDTO.getFechaNacimiento())){
            throw new BussinessException("La edad del empleado no puede ser menor a 18 años.", 400);
        }


        Empleado empleadoAdded = empleadoRepository.save(empleadoDTO.toEntity());

        EmpleadoDTO empleadoAddedDTO = new EmpleadoDTO(empleadoAdded);

        return empleadoAddedDTO;

    }

    @Override
    public EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDTO, Integer id) {


        if (existsEmpleadoWithEmail(empleadoDTO.getEmail())){
            throw new BussinessException("Ya existe un empleado con el email ingresado", 409);
        }

        if (existsEmpleadoWithNroDocumento(empleadoDTO.getNroDocumento())){
            throw new BussinessException("Ya existe un empleado con el documento ingresado", 409);
        }

        //esMayorDeEdad == false, se dispara un Bad request
        if (!esMayorDeEdad(empleadoDTO.getFechaNacimiento())){
            throw new BussinessException("La edad del empleado no puede ser menor a 18 años.", 400);
        }

        if (existsEmpleadoWithId(id)){
            empleadoDTO.setId(id);
            Empleado empleadoAdded = empleadoRepository.save(empleadoDTO.toEntity());
            EmpleadoDTO empleadoAddedDTO = new EmpleadoDTO(empleadoAdded);
            return empleadoAddedDTO;
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



    public boolean existsEmpleadoWithId(Integer id){

        if (empleadoRepository.existsById(id)){

            return true;
        }

        return false;
    }

    private boolean existsEmpleadoWithNroDocumento(Integer nroDocumento){

        if (empleadoRepository.existsByNroDocumento(nroDocumento)){
            return true;
        }

        return false;
    }

    private boolean existsEmpleadoWithEmail(String email){

        if (empleadoRepository.existsByEmail(email)){
            return true;
        }

        return false;
    }



}
