package com.neoris.turnosrotativos.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity(name = "jornada")
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


    private Integer hsTrabajadas;
}
