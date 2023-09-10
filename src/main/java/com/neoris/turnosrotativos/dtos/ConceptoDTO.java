package com.neoris.turnosrotativos.dtos;

import com.neoris.turnosrotativos.entities.Concepto;
import org.springframework.stereotype.Component;

import javax.persistence.Id;

@Component
public class ConceptoDTO {
    private Integer id;
    private String nombre;
    private Boolean laborable;
    private Integer hsMinimo;
    private Integer hsMaximo;


    public ConceptoDTO(){}

    public ConceptoDTO(Concepto concepto) {
        this.id = concepto.getId();
        this.nombre = concepto.getNombre();
        this.laborable = concepto.getLaborable();
        this.hsMinimo = concepto.getHsMinimo();
        this.hsMaximo = concepto.getHsMaximo();
    }

    /*public Concepto toEntity(){
        Concepto entity = new Concepto();


    }*/

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
