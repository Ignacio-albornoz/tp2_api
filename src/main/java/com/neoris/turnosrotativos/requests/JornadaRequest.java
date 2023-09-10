package com.neoris.turnosrotativos.requests;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

public class JornadaRequest {

    @NotNull(message = "idEmpleado es obligatorio")
    private Integer idEmpleado;
    @NotNull(message = "idConcepto es obligatorio")
    private Integer idConcepto;
    @NotNull(message = "fecha es obligatoria")
    private LocalDate fecha;
    private Integer hsTrabajadas = 0;
    private boolean laborable;

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Integer getIdConcepto() {
        return idConcepto;
    }

    public void setIdConcepto(Integer idConcepto) {
        this.idConcepto = idConcepto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }

    public boolean isLaborable() {
        return laborable;
    }

    public void setLaborable(boolean laborable) {
        this.laborable = laborable;
    }
}
