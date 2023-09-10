package com.neoris.turnosrotativos.validations;

import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.exceptions.BussinessException;
import org.springframework.stereotype.Component;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JornadaValidation {

    final Integer ID_CONCEPTO_TURNO_NORMAL = 1;
    final Integer ID_CONCEPTO_TURNO_EXTRA = 2;
    final Integer ID_CONCEPTO_DIA_LIBRE = 3;

    final Integer NUMERO_MAXIMO_DE_TURNOS_EXTRAS_POR_SEMANA = 3;
    final Integer NUMERO_MAXIMO_DE_DIAS_LIBRES_POR_SEMANA = 2;


    public void validarHorasDeTrabajoSegunConcepto(Boolean laborable, Integer hrsTrabajadas){

        if (laborable && hrsTrabajadas <= 0 && hrsTrabajadas == null){
            throw new BussinessException("hsTrabajadas es obligatorio para el concepto ingresado", 400);
        } else if (!laborable && hrsTrabajadas > 0) {
            throw new BussinessException("El concepto ingresado no requiere el ingreso de hsTrabajadas", 400);
        }

    }


    //Validar que las horas trabajadas esten dentro del rango del concepto
    public void validarRangoDeHoras(Integer hsTrabajadas, Integer hsMinimo, Integer hsMaximo){
        if (hsTrabajadas == 0){
            return;
        }

        if (hsTrabajadas < hsMinimo || hsTrabajadas > hsMaximo) {
            throw new BussinessException("El rango de horas que se puede cargar para este concepto es de " + hsMinimo + " - " + hsMaximo, 400);
        }
    }

    //Validar que el empleado no tenga un dia libre en la fecha
    public void validarDiaLibre(List<Jornada> todasLasJornadas, Integer idEmpleado, LocalDate fecha) {

        //Se filtra lista de Jornadas, por idEmpleado, el idConcepto = 3 que corresponde a dia libre y fecha.
        List<Jornada> jornadasDiaLibre = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getConcepto().getId().equals(ID_CONCEPTO_DIA_LIBRE)
                        && j.getFecha().equals(fecha))
                .collect(Collectors.toList());

        if (!jornadasDiaLibre.isEmpty()) {
            throw new BussinessException("El empleado ingresado cuenta con un dia libre esa fecha.", 400);
        }
    }

    //Validar que el empleado no tenga otra jornada con el mismo concepto en la fecha
    public void validarMismoConcepto(List<Jornada> todasLasJornadas, Integer idEmpleado, Integer idConcepto, LocalDate fecha) {

        List<Jornada> jornadasMismoConcepto = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getConcepto().getId().equals(idConcepto)
                        && j.getFecha().equals(fecha))
                .collect(Collectors.toList());

        if (!jornadasMismoConcepto.isEmpty()) {
            throw new BussinessException("El empleado ya tiene registrado una jornada con este concepto en la fecha ingresada.", 400);
        }
    }

    //Validar que el empleado no supere las 12 horas trabajadas en un día
    public void validarHorasDia(List<Jornada> todasLasJornadas, Integer idEmpleado,LocalDate fecha, Integer hsTrabajadas){

        if (hsTrabajadas == null){
            return;
        }

        List<Jornada> jornadasMismoDia = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getFecha().equals(fecha))
                .collect(Collectors.toList());

        Integer totalHorasDia = hsTrabajadas;
        for (Jornada j : jornadasMismoDia) { totalHorasDia += j.getHsTrabajadas(); }
        if (totalHorasDia > 12) {
            throw new BussinessException(
                    "El empleado no puede cargar mas de 12 horas trabjadas en un dia.", 400);
        }
    }

    //Validar que el empleado no cargue un día libre si tiene otros turnos cargados
    public void validarTurnosYDiaLibre(List<Jornada> todasLasJornadas, Integer idEmpleado, Integer idConcepto, LocalDate fecha){

        List<Jornada> jornadasMismoDia = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getFecha().equals(fecha))
                .collect(Collectors.toList());
        if (idConcepto.equals(ID_CONCEPTO_DIA_LIBRE) && !jornadasMismoDia.isEmpty()) {
            throw new BussinessException("El empleado no puede cargar dia libre si cargo un turno previamente para la fecha ingresada", 400);
        }
    }

    //Validar que el empleado no supere las 48 horas semanales
    public void validarHorasSemana(List<Jornada> todasLasJornadas, Integer idEmpleado,LocalDate fecha, Integer hsTrabajadas) {

        if (hsTrabajadas == null){
            return;
        }

        List<Jornada> jornadasMismaSemana = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getFecha().isAfter(fecha.with(DayOfWeek.MONDAY))
                        && j.getFecha().isBefore(fecha.with(DayOfWeek.SUNDAY)))
                .collect(Collectors.toList());
        Integer totalHorasSemana = hsTrabajadas;

        for (Jornada j : jornadasMismaSemana) { totalHorasSemana += j.getHsTrabajadas(); }

        if (totalHorasSemana > 48) {
            throw new BussinessException("El empleado ingresado supera las 48 horas semanales.", 400);
        }
    }

    //Validar que el empleado no tenga más de 3 turnos extra en una semana
    public void validarTurnosExtra(List<Jornada> todasLasJornadas, Integer idEmpleado, Integer idConcepto, LocalDate fecha) {

        List<Jornada> jornadasTurnoExtra = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getConcepto().getId().equals(ID_CONCEPTO_TURNO_EXTRA)
                        && j.getFecha().isAfter(fecha.with(DayOfWeek.MONDAY))
                        && j.getFecha().isBefore(fecha.with(DayOfWeek.SUNDAY)))
                .collect(Collectors.toList());

        if (idConcepto.equals(ID_CONCEPTO_TURNO_EXTRA) && jornadasTurnoExtra.size() >= NUMERO_MAXIMO_DE_TURNOS_EXTRAS_POR_SEMANA) {
            throw new BussinessException("El empleado ingresado ya cuenta con 3 turnos extra esta semana.", 400);
        }
    }

    //Validar que el empleado no tenga más de 5 turnos normales en una semana
    public void validarTurnosNormales(List<Jornada> todasLasJornadas, Integer idEmpleado, Integer idConcepto, LocalDate fecha) {

        List<Jornada> jornadasTurnoNormal = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getConcepto().getId().equals(ID_CONCEPTO_TURNO_NORMAL)
                        && j.getFecha().isAfter(fecha.with(DayOfWeek.MONDAY))
                        && j.getFecha().isBefore(fecha.with(DayOfWeek.SUNDAY)))
                .collect(Collectors.toList());

        if (idConcepto.equals(ID_CONCEPTO_TURNO_NORMAL) && jornadasTurnoNormal.size() >= 5) {
            throw new BussinessException("El empleado ingresado ya cuenta con 5 turnos normales esta semana.", 400);
        }

    }

    // Validar que el empleado no tenga más de 2 días libres por semana
    public void validarDiasLibres(List<Jornada> todasLasJornadas, Integer idEmpleado, Integer idConcepto, LocalDate fecha){

        List<Jornada> jornadasDiaLibreSemana = todasLasJornadas
                .stream()
                .filter(j -> j.getEmpleado().getId().equals(idEmpleado)
                        && j.getConcepto().getId().equals(ID_CONCEPTO_DIA_LIBRE)
                        && j.getFecha().isAfter(fecha.with(DayOfWeek.MONDAY))
                        && j.getFecha().isBefore(fecha.with(DayOfWeek.SUNDAY)))
                .collect(Collectors.toList());
        if (idConcepto.equals(ID_CONCEPTO_DIA_LIBRE) && jornadasDiaLibreSemana.size() >= NUMERO_MAXIMO_DE_DIAS_LIBRES_POR_SEMANA) {
            throw new BussinessException("El empleado no cuenta con más días libres esta semana.", 400);
        }
    }



}

