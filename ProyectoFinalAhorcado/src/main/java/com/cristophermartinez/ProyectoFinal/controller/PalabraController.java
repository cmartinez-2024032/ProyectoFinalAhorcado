package com.cristophermartinez.ProyectoFinal.controller;

import com.cristophermartinez.ProyectoFinal.model.Palabra;
import com.cristophermartinez.ProyectoFinal.service.PalabraService;
import com.cristophermartinez.ProyectoFinal.service.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palabras")
@CrossOrigin(origins = "*")
public class PalabraController {

    @Autowired
    private PalabraService palabraService;

    @Autowired
    private Validacion validacion;

    @GetMapping
    public List<Palabra> obtenerTodas() {
        return palabraService.obtenerTodas();
    }

    @GetMapping("/aleatoria")
    public Palabra obtenerAleatoria() {
        return palabraService.obtenerAleatoria();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerPorId(@PathVariable Integer id) {
        return palabraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearPalabra(@RequestBody Palabra palabra) {
        String error = validacion.validarPalabra(palabra);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        Palabra creada = palabraService.guardarPalabra(palabra);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarPalabra(@PathVariable Integer id, @RequestBody Palabra palabra) {
        return palabraService.obtenerPorId(id)
                .map(actual -> {
                    String error = validacion.validarPalabra(palabra);
                    if (error != null) {
                        return ResponseEntity.badRequest().body(error);
                    }

                    actual.setPalabra(palabra.getPalabra());
                    actual.setPista1(palabra.getPista1());
                    actual.setPista2(palabra.getPista2());
                    actual.setPista3(palabra.getPista3());

                    Palabra actualizada = palabraService.guardarPalabra(actual);
                    return ResponseEntity.ok(actualizada);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPalabra(@PathVariable Integer id) {
        if (!palabraService.obtenerPorId(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        palabraService.eliminarPorId(id);
        return ResponseEntity.ok().build();
    }
}
