package com.cristophermartinez.ProyectoFinal.service;

import com.cristophermartinez.ProyectoFinal.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Integer id);
    Usuario createUsuario(Usuario usuario);
    boolean deleteUsuario(Integer id);
    Optional<Usuario> getUsuarioByUsername(String username);
    boolean existsByUsername(String username);
}
