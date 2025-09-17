package com.cristophermartinez.ProyectoFinal.repository;

import com.cristophermartinez.ProyectoFinal.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

    @Query(value = "SELECT * FROM palabras ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Palabra obtenerPalabraAleatoria();
}
