package com.neoris.turnosrotativos.dtos;

import com.neoris.turnosrotativos.entities.Empleado;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Component
public class EmpleadoDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @Max(value = 99999999, message = "El nroDocumento ingresado es incorrecto.")
    @NotNull(message = "NroDocumento es obligatorio.")
    @Column(unique = true, name = "nro_documento")
    Integer nroDocumento;

    @NotNull(message = "Nombre es obligatorio.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Solo se permiten letras en el campo nombre")
    String nombre;

    @NotNull(message = "Apellido es obligatorio.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Solo se permiten letras en el campo apellido")
    String apellido;

    @NotNull(message = "Email es obligatorio.")
    @Email(message = "El email ingresado no es correcto.")
    String email;

    @NotNull(message = "FechaNacimiento es obligatorio.")
    @PastOrPresent(message = "Fecha de nacimiento no puede ser posterior al día de la fecha.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate fechaNacimiento;

    @NotNull(message = "FechaIngreso es obligatorio.")
    @PastOrPresent(message = "Fecha de ingreso no puede ser posterior al día de la fecha.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_ingreso")
    LocalDate fechaIngreso;

    @NotNull
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    @Column(name = "fecha_creacion")
    LocalDate fechaCreacion = LocalDate.now();

    /* Constructors */

    public EmpleadoDTO(){}

    public EmpleadoDTO(Empleado empleado) {
        this.id = empleado.getId();
        this.nroDocumento = empleado.getNroDocumento();
        this.nombre = empleado.getNombre();
        this.apellido = empleado.getApellido();
        this.email = empleado.getEmail();
        this.fechaNacimiento = empleado.getFechaNacimiento();
        this.fechaIngreso = empleado.getFechaIngreso();
    }

    /* To Entity */

    public Empleado toEntity(){
        Empleado entity = new Empleado();

        entity.setId(this.id);
        entity.setNroDocumento(this.nroDocumento);
        entity.setNombre(this.nombre);
        entity.setApellido(this.apellido);
        entity.setEmail(this.email);
        entity.setFechaNacimiento(this.fechaNacimiento);
        entity.setFechaIngreso(this.fechaIngreso);
        entity.setFechaCreacion(this.fechaCreacion);

        return entity;

    }

    /* Getters & Setters */

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
