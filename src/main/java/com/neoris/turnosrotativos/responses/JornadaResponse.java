package com.neoris.turnosrotativos.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.neoris.turnosrotativos.entities.Jornada;

import java.time.LocalDate;

public class JornadaResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer id;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer hsTrabajadas;


    public JornadaResponse(Integer nroDocumento, String nombreCompleto, LocalDate fecha, String concepto, Integer hsTrabajadas) {
        this.nroDocumento = nroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.concepto = concepto;
        this.hsTrabajadas = hsTrabajadas;
    }

    public JornadaResponse(Jornada jornada){
        this.id = jornada.getId();
        this.nroDocumento = jornada.getEmpleado().getNroDocumento();
        this.nombreCompleto = jornada.getEmpleado().getNombre() + " " + jornada.getEmpleado().getApellido();
        this.fecha = jornada.getFecha();
        this.concepto = jornada.getConcepto().getNombre();
        this.hsTrabajadas = jornada.getHsTrabajadas();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(Integer nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getHsTrabajadas() {
        return hsTrabajadas;
    }

    public void setHsTrabajadas(Integer hsTrabajadas) {
        this.hsTrabajadas = hsTrabajadas;
    }
}
