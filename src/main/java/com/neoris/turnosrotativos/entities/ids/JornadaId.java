package com.neoris.turnosrotativos.entities.ids;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class JornadaId implements Serializable {

    private Integer idEmpleado;
    private Integer idConcepto;

    public JornadaId(Integer idEmpleado, Integer idConcepto) {
        this.idEmpleado = idEmpleado;
        this.idConcepto = idConcepto;
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
}