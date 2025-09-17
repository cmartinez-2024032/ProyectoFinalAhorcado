package com.cristophermartinez.ProyectoFinal.controller;

import com.cristophermartinez.ProyectoFinal.model.Usuario;
import com.cristophermartinez.ProyectoFinal.service.UsuarioServiceImplements;
import com.cristophermartinez.ProyectoFinal.service.Validacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {

    @Autowired
    private UsuarioServiceImplements usuarioService;

    @Autowired
    private Validacion validacion;

    @GetMapping
    public List<Usuario> getAllUsuarios() {
        return usuarioService.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getUsuarioById(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.getUsuarioById(id);
        return usuario.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuario) {
        String error = validacion.validarUsuario(usuario);
        if (error != null) {
            return ResponseEntity.badRequest().body(error);
        }

        Usuario nuevoUsuario = usuarioService.createUsuario(usuario);
        return ResponseEntity.ok(nuevoUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUsuario(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioExistente = usuarioService.getUsuarioById(id);
        if (usuarioExistente.isPresent()) {
            String error = validacion.validarUsuarioActualizado(usuario, id);
            if (error != null) {
                return ResponseEntity.badRequest().body(error);
            }

            Usuario actualizado = usuarioExistente.get();
            actualizado.setUsername(usuario.getUsername());
            actualizado.setContraseña(usuario.getContraseña());
            Usuario usuarioGuardado = usuarioService.createUsuario(actualizado);
            return ResponseEntity.ok(usuarioGuardado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) {
        boolean eliminado = usuarioService.deleteUsuario(id);
        if (eliminado) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}