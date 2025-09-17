package com.cristophermartinez.ProyectoFinal.service;

import com.cristophermartinez.ProyectoFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsuarioValidacion {

    @Autowired
    private UsuarioServiceImplements usuarioService;

    public String validarUsuario(Usuario usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().trim().isEmpty()) {
            return "La contraseña es obligatoria.";
        }

        if (usuarioService.existsByUsername(usuario.getUsername())) {
            return "El nombre de usuario ya está en uso.";
        }

        if (usuario.getContraseña().length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        return null;
    }
}
