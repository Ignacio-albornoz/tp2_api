package com.neoris.turnosrotativos.entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;


@Entity(name = "empleados")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotNull(message = "nroDocumento es obligatorio.")
    @Column(unique = true, name = "nro_documento")
    Integer nroDocumento;

    @NotNull(message = "nombre es obligatorio.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Solo se permiten letras en el campo nombre")
    String nombre;

    @NotNull(message = "apellido es obligatorio.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Solo se permiten letras en el campo apellido")
    String apellido;

    @NotNull(message = "email es obligatorio.")
    @Email(message = "El email ingresado no es correcto.")
    @Column(unique = true)
    String email;

    @NotNull(message = "fechaNacimiento es obligatorio.")
    @PastOrPresent(message = "La fecha de nacimiento no puede ser posterior al día de la fecha.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_nacimiento")
    LocalDate fechaNacimiento;

    @NotNull(message = "fechaIngreso es obligatorio.")
    @PastOrPresent(message = "La fecha de ingreso no puede ser posterior al día de la fecha.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_ingreso")
    LocalDate fechaIngreso;

    @NotNull
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_creacion")
    LocalDate fechaCreacion;



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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
