package com.neoris.turnosrotativos.requests;


import org.springframework.stereotype.Component;


import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


public class JornadaRequest {
    @Id
    private Integer id;
    @NotNull(message = "idEmpleado es obligatorio")
    private Integer idEmpleado;
    @NotNull(message = "idConcepto es obligatorio")
    private Integer idConcepto;
    @NotNull(message = "fecha es obligatoria")
    private LocalDate fecha;

    private Integer horasTrabajadas;

    private boolean laborable;


    public JornadaRequest(Integer idEmpleado, Integer idConcepto, LocalDate fecha, Integer horasTrabajadas, boolean laborable) {
        this.idEmpleado = idEmpleado;
        this.idConcepto = idConcepto;
        this.fecha = fecha;
        this.horasTrabajadas = horasTrabajadas;
        this.laborable = laborable;
    }

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

    public Integer getHorasTrabajadas() {
        return horasTrabajadas;
    }

    public void setHorasTrabajadas(Integer horasTrabajadas) {
        this.horasTrabajadas = horasTrabajadas;
    }

    public boolean isLaborable() {
        return laborable;
    }

    public void setLaborable(boolean laborable) {
        this.laborable = laborable;
    }
}
