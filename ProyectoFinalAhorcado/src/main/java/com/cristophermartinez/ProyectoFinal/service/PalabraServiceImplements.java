package com.cristophermartinez.ProyectoFinal.service;

import com.cristophermartinez.ProyectoFinal.model.Palabra;
import com.cristophermartinez.ProyectoFinal.repository.PalabraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PalabraServiceImplements implements PalabraService {

    @Autowired
    private PalabraRepository palabraRepository;

    @Override
    public List<Palabra> obtenerTodas() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra obtenerAleatoria() {
        return palabraRepository.obtenerPalabraAleatoria();
    }

    @Override
    public Palabra guardarPalabra(Palabra palabra) {
        return palabraRepository.save(palabra);
    }

    @Override
    public void eliminarPorId(Integer id) {
        palabraRepository.deleteById(id);
    }

    @Override
    public Optional<Palabra> obtenerPorId(Integer id) {
        return palabraRepository.findById(id);
    }

}
