package com.neoris.turnosrotativos.dtos;

import com.neoris.turnosrotativos.entities.Concepto;
import org.springframework.stereotype.Component;


@Component
public class ConceptoDTO {
    private Integer id;
    private String nombre;
    private Integer hsMinimo;
    private Integer hsMaximo;
    private Boolean laborable;

    /* Constructors */

    public ConceptoDTO(){}

    public ConceptoDTO(Concepto concepto) {
        this.id = concepto.getId();
        this.nombre = concepto.getNombre();
        this.laborable = concepto.getLaborable();
        this.hsMinimo = concepto.getHsMinimo();
        this.hsMaximo = concepto.getHsMaximo();
    }

    /* To Entity */

    public Concepto toEntity(){
        Concepto entity = new Concepto();

        entity.setId(this.id);
        entity.setNombre(this.nombre);
        entity.setLaborable(this.laborable);
        entity.setHsMinimo(this.hsMinimo);
        entity.setHsMaximo(this.hsMaximo);

        return entity;
    }

    /* Getters & Setters */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getLaborable() {
        return laborable;
    }

    public void setLaborable(Boolean laborable) {
        this.laborable = laborable;
    }

    public Integer getHsMinimo() {
        return hsMinimo;
    }

    public void setHsMinimo(Integer hsMinimo) {
        this.hsMinimo = hsMinimo;
    }

    public Integer getHsMaximo() {
        return hsMaximo;
    }

    public void setHsMaximo(Integer hsMaximo) {
        this.hsMaximo = hsMaximo;
    }
}
