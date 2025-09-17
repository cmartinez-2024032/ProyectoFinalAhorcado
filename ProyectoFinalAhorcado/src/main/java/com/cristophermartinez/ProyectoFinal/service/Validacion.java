package com.cristophermartinez.ProyectoFinal.service;

import com.cristophermartinez.ProyectoFinal.model.Palabra;
import com.cristophermartinez.ProyectoFinal.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class Validacion {

    @Autowired
    private UsuarioServiceImplements usuarioService;

    public String validarPalabra(Palabra palabra) {
        if (palabra.getPalabra() == null || palabra.getPalabra().trim().isEmpty()) {
            return "El campo 'palabra' no puede estar vacío.";
        }

        String texto = palabra.getPalabra().trim();

        if (texto.length() < 3 || texto.length() > 20) {
            return "La palabra debe tener entre 3 y 20 caracteres.";
        }

        for (char c : texto.toCharArray()) {
            if (!Character.isLetter(c)) {
                return "La palabra solo debe contener letras.";
            }
        }

        return null;
    }

    public String validarUsuario(Usuario usuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }

        String username = usuario.getUsername().trim();
        if (username.length() < 3 || username.length() > 20) {
            return "El nombre de usuario debe tener entre 3 y 20 caracteres.";
        }

        Optional<Usuario> existente = usuarioService.getUsuarioByUsername(username);
        if (existente.isPresent()) {
            return "El nombre de usuario ya está en uso.";
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        return null;
    }

    public String validarUsuarioActualizado(Usuario usuario, Integer idUsuario) {
        if (usuario.getUsername() == null || usuario.getUsername().trim().isEmpty()) {
            return "El nombre de usuario es obligatorio.";
        }

        String username = usuario.getUsername().trim();
        if (username.length() < 3 || username.length() > 20) {
            return "El nombre de usuario debe tener entre 3 y 20 caracteres.";
        }

        Optional<Usuario> existente = usuarioService.getUsuarioByUsername(username);
        if (existente.isPresent() && !existente.get().getId().equals(idUsuario)) {
            return "El nombre de usuario ya está en uso por otro usuario.";
        }

        if (usuario.getContraseña() == null || usuario.getContraseña().length() < 6) {
            return "La contraseña debe tener al menos 6 caracteres.";
        }

        return null;
    }
}
