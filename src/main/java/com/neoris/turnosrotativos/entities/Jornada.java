package com.neoris.turnosrotativos.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "jornadas")
public class Jornada {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id")
    private Empleado empleado;

    @OneToOne
    @JoinColumn(name = "id_concepto", referencedColumnName = "id")
    private Concepto concepto;

    @NotNull(message = "fecha es obligatoria.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;

    @Column(name = "hs_trabajadas")
    private Integer hsTrabajadas;

    public Jornada(Empleado empleado, Concepto concepto, LocalDate fecha, Integer hsTrabajadas) {
        this.empleado = empleado;
        this.concepto = concepto;
        this.fecha = fecha;
        if (hsTrabajadas == null){
            this.hsTrabajadas = 0;
        }
        this.hsTrabajadas = hsTrabajadas;

    }

    public Jornada() {

    }

    public Integer getId() {
        return id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }


    public void setEmpleadoId(Integer id){

    }


    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
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
}
