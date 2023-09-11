package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.dtos.EmpleadoDTO;

import java.util.List;

public interface EmpleadoService {

    public EmpleadoDTO getEmpleadoById(Integer id);

    public List<EmpleadoDTO> getEmpleados();

    public EmpleadoDTO addEmpleado(EmpleadoDTO empleadoDTO);

    public EmpleadoDTO updateEmpleado(EmpleadoDTO empleadoDTO, Integer id);

    public void removeEmpleado(Integer id);
}
