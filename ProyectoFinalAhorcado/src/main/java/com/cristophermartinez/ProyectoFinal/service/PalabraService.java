package com.cristophermartinez.ProyectoFinal.service;

import com.cristophermartinez.ProyectoFinal.model.Palabra;

import java.util.List;
import java.util.Optional;

public interface PalabraService {
    List<Palabra> obtenerTodas();
    Palabra obtenerAleatoria();
    Palabra guardarPalabra(Palabra palabra);
    Optional<Palabra> obtenerPorId(Integer id);
    void eliminarPorId(Integer id);
}
