package com.neoris.turnosrotativos.services.implement;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;
import com.neoris.turnosrotativos.entities.Concepto;
import com.neoris.turnosrotativos.entities.Empleado;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import com.neoris.turnosrotativos.repositories.JornadaRepository;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;
import com.neoris.turnosrotativos.services.JornadaService;
import com.neoris.turnosrotativos.validations.JornadaValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JornadaServiceImplement implements JornadaService {

    @Autowired
    JornadaRepository jornadaRepository;
    @Autowired
    EmpleadoServiceImplement empleadoServiceImplement;
    @Autowired
    ConceptoServiceImplement conceptoServiceImplement;

    /*Se declara clase que contiene todas las validaciones de Jornada*/
    JornadaValidation jornadaValidation = new JornadaValidation();


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
    public List<JornadaResponse> getJornadas(){

        List<Jornada> jornadas = (List<Jornada>) jornadaRepository.findAll();

        return jornadas.stream()
                .map(JornadaResponse::new)
                .collect(Collectors.toList());
    }


    @Override
    public JornadaResponse addJornada(JornadaRequest jornadaRequest) {

        List<Jornada> todasLasJornadas = (List<Jornada>) jornadaRepository.findAll();

        Empleado empleado = empleadoServiceImplement.getEmpleadoById(jornadaRequest.getIdEmpleado()).toEntity();

        Concepto concepto = conceptoServiceImplement.getConceptoById(jornadaRequest.getIdConcepto()).toEntity();


        Jornada jornada = new Jornada(empleado, concepto, jornadaRequest.getFecha(), jornadaRequest.getHsTrabajadas());

        /* VALIDACIONES */

        Integer idEmpleado = jornada.getEmpleado().getId();
        Integer idConcepto = jornada.getConcepto().getId();
        LocalDate fecha = jornada.getFecha();
        Integer hsTrabajadas = jornada.getHsTrabajadas();

        jornadaValidation.validarHorasDeTrabajoSegunConcepto(jornada.getConcepto().getLaborable(), hsTrabajadas);

        //Se evita lidiar con null en las siguientes validaciones
        if (hsTrabajadas == null){
            hsTrabajadas = 0;
        }

        jornadaValidation.validarRangoDeHoras(hsTrabajadas,
                jornada.getConcepto().getHsMinimo(),
                jornada.getConcepto().getHsMaximo(),
                jornada.getConcepto().getLaborable());

        jornadaValidation.validarDiaLibre(todasLasJornadas, jornada.getEmpleado().getId(), jornada.getFecha());

        jornadaValidation.validarMismoConcepto(todasLasJornadas, idEmpleado, idConcepto, fecha);

        jornadaValidation.validarHorasDia(todasLasJornadas, idEmpleado, fecha, hsTrabajadas);

        jornadaValidation.validarTurnosYDiaLibre(todasLasJornadas, idEmpleado, idConcepto, fecha);

        jornadaValidation.validarHorasSemana(todasLasJornadas, idEmpleado, fecha, hsTrabajadas);

        jornadaValidation.validarTurnosExtra(todasLasJornadas, idEmpleado, idConcepto, fecha);

        jornadaValidation.validarTurnosNormales(todasLasJornadas, idEmpleado, idConcepto, fecha);

        jornadaValidation.validarDiasLibres(todasLasJornadas, idEmpleado, idConcepto, fecha);

        Jornada jornadaAdded = jornadaRepository.save(jornada);
        JornadaResponse jornadaResponseAdded = new JornadaResponse(jornadaAdded);

        return jornadaResponseAdded;

    }

    @Override
    public List<Jornada> getJornadasByFechaAndDocumento(LocalDate fecha, Integer nroDocumento) {
        // Si ambos parámetros son nulos, retornar todas las jornadas
        if (fecha == null && nroDocumento == null) {
            return (List<Jornada>) jornadaRepository.findAll();
        }
        // Si solo el nroDocumento es nulo, retornar las jornadas por fecha
        if (nroDocumento == null) {
            return jornadaRepository.findByFecha(fecha);
        }
        // Si solo la fecha es nula, retornar las jornadas por nroDocumento
        if (fecha == null) {
            return jornadaRepository.findByEmpleadoNroDocumento(nroDocumento);
        }
        // Si ambos parámetros son no nulos, retornar las jornadas por fecha y nroDocumento
        return jornadaRepository.findByEmpleadoNroDocumentoAndFecha(nroDocumento, fecha);
    }

}
