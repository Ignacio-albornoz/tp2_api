package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.requests.JornadaRequest;

import java.time.LocalDate;
import java.util.List;

public interface JornadaService {

    public List<Jornada> getJornadas();

    public Jornada addJornada(JornadaRequest jornadaRequest);

    public List<Jornada> getJornadasByNroDocumento(Integer nroDocumento);

    public List<Jornada> getJornadasByFecha(LocalDate fecha);

    public List<Jornada> getJornadasByNroDocumentoAndFecha(Integer nroDocumento, LocalDate fecha);

}
