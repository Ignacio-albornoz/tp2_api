package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;

import java.time.LocalDate;
import java.util.List;

public interface JornadaService {

    public List<JornadaResponse> getJornadas();

    public JornadaResponse addJornada(JornadaRequest jornadaRequest);

    public List<Jornada> getJornadasByFechaAndDocumento(LocalDate fecha, Integer nroDocumento);

    public Boolean existsByEmpleadoNroDocumento(Integer nroDocumento);

}
