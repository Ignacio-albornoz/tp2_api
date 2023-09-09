package com.neoris.turnosrotativos.responses;

import org.springframework.stereotype.Component;


import javax.persistence.Id;
import java.time.LocalDate;

public class JornadaResponse {
    @Id
    private Integer id;
    private Integer nroDocumento;
    private String nombreCompleto;
    private LocalDate fecha;
    private String concepto;
    private Integer hsTrabajadas;


    public JornadaResponse(Integer nroDocumento, String nombreCompleto, LocalDate fecha, String concepto, Integer hsTrabajadas) {
        this.nroDocumento = nroDocumento;
        this.nombreCompleto = nombreCompleto;
        this.fecha = fecha;
        this.concepto = concepto;
        this.hsTrabajadas = hsTrabajadas;
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
