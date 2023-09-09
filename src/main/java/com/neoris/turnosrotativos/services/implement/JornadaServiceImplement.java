package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.ConceptoRepository;
import com.neoris.turnosrotativos.repositories.EmpleadoRepository;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;
import com.neoris.turnosrotativos.services.ConceptoService;
import com.neoris.turnosrotativos.services.JornadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JornadaServiceImplement implements JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;
    EmpleadoRepository empleadoRepository;
    ConceptoRepository conceptoRepository;


    /*@Override
    public Jornada getJornadatoById(Integer id) {
        Optional<Jornada> jornada = jornadaRepository.findById(id);

        if (jornada.isPresent()){
            return jornada.get();
        } else {
            throw new BussinessException("El concepto con id: " + id + " no existe.", 404);

        }
    }*/

    @Override
    public List<Jornada> getJornadas(){

        return (List<Jornada>) jornadaRepository.findAll();
    }


    @Override
    public Jornada addJornada(JornadaRequest jornadaRequest) {
        Optional<Empleado> empleado = empleadoRepository.findById(jornadaRequest.getIdEmpleado());

        Optional<Concepto> concepto = conceptoRepository.findById(jornadaRequest.getIdConcepto());


        if (empleado.isEmpty()){
            throw new BussinessException("El concepto con id: " + " no existe.", 404);

        }

        if (concepto.isEmpty()){
            throw new BussinessException("El concepto con id: " + " no existe.", 404);

        }

        validarHorasTrabajasSegunConcepto(jornadaRequest.isLaborable(), jornadaRequest.getHorasTrabajadas());

        Jornada jornada = new Jornada();
        jornada.getEmpleado().setId(jornadaRequest.getIdEmpleado());
        jornada.getConcepto().setId(jornadaRequest.getIdConcepto());
        jornada.setFecha(jornadaRequest.getFecha());
        jornada.setHsTrabajadas(jornadaRequest.getHorasTrabajadas());

        Jornada savedJornada = jornadaRepository.save(jornada);

        return savedJornada;
    }

    /*Validaciones*/

    private void validarHorasTrabajasSegunConcepto(Boolean laborable, Integer hrsTrabajadas){
        if (laborable && hrsTrabajadas == null){
            throw new BussinessException("hsTrabajadas es obligatorio para el concepto ingresado", 400);
        } else if (!laborable && hrsTrabajadas != null) {
            throw new BussinessException("El concepto ingresado no requiere el ingreso de hsTrabajadas", 400);
        }

    }
}
