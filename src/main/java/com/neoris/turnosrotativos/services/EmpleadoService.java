package com.neoris.turnosrotativos.services;

import com.neoris.turnosrotativos.entities.Empleado;

import java.util.List;

public interface EmpleadoService {

    public Empleado getEmpleadoById(Integer id);

    public Empleado getEmpleadoByEmail(String email);

    public Empleado getEmpleadoByNroDocumento(Integer nroDocumento);

    public List<Empleado> getEmpleados();

    public Empleado addEmpleado(Empleado empleado);

    public Empleado updateEmpleado( Empleado empleado, Integer id);

    public void removeEmpleado(Integer id);
}
