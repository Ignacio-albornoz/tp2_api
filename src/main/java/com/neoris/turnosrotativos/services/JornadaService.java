package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.JornadaDTO;
import com.neoris.turnosrotativos.entities.Jornada;
import com.neoris.turnosrotativos.requests.JornadaRequest;
import com.neoris.turnosrotativos.responses.JornadaResponse;

import java.util.List;

public interface JornadaService {

    public List<Jornada> getJornadas();

    public Jornada addJornada(JornadaRequest jornadaRequest);

}
