package com.cristophermartinez.ProyectoFinal.controller;

import com.cristophermartinez.ProyectoFinal.model.Palabra;
import com.cristophermartinez.ProyectoFinal.service.PalabraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palabras")
@CrossOrigin(origins = "*")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @GetMapping
    public List<Palabra> obtenerTodas() {
        return palabraService.obtenerTodas();
    }

    @GetMapping("/aleatoria")
    public Palabra obtenerAleatoria() {
        return palabraService.obtenerAleatoria();
    }

    @GetMapping("/{id}")
    public Palabra obtenerPorId(@PathVariable Integer id) {
        return palabraService.obtenerPorId(id).orElse(null);
    }

    @PostMapping
    public Palabra crearPalabra(@RequestBody Palabra palabra) {
        return palabraService.guardarPalabra(palabra);
    }

    @PutMapping("/{id}")
    public Palabra actualizarPalabra(@PathVariable Integer id, @RequestBody Palabra palabra) {
        Palabra actualizada = palabraService.obtenerPorId(id).orElse(null);
        actualizada.setPalabra(palabra.getPalabra());
        actualizada.setPista1(palabra.getPista1());
        actualizada.setPista2(palabra.getPista2());
        actualizada.setPista3(palabra.getPista3());
        return palabraService.guardarPalabra(actualizada);
    }

    @DeleteMapping("/{id}")
    public void eliminarPalabra(@PathVariable Integer id) {
        palabraService.eliminarPorId(id);
    }
}
