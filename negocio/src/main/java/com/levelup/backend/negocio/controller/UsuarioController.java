package com.levelup.backend.negocio.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.backend.negocio.model.Usuario;
import com.levelup.backend.negocio.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuarios")
@CrossOrigin(origins = "*")
public class UsuarioController {
    
    private final UsuarioRepository usuarioRepository;
    
    // CREATE usuario
    @PostMapping
    @Operation(summary = "Crear usuario")
    public ResponseEntity<?> crearUsuario(@RequestBody Usuario usuario) {
        try {
            if (usuarioRepository.existsByEmail(usuario.getEmail())) {
                Map<String, String> error = new HashMap<>();
                error.put("error", "El email ya está registrado");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
            
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioGuardado);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Error al crear usuario: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
    
    // READ usuario 
    @GetMapping
    @Operation(summary = "Listar usuarios")
    public ResponseEntity<List<Usuario>> obtenerTodos() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return ResponseEntity.ok(usuarios);
    }
    
    // READ - Obtener usuario por ID
    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario")
    public ResponseEntity<?> obtenerPorId(@PathVariable Long id) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    // UPDATE usuario
    @PutMapping("/{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Usuario usuarioActualizado) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(id);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            usuario.setNombre(usuarioActualizado.getNombre());
            usuario.setEmail(usuarioActualizado.getEmail());
            usuario.setFechaNacimiento(usuarioActualizado.getFechaNacimiento());
            usuario.setContrasena(usuarioActualizado.getContrasena());
            
            Usuario guardado = usuarioRepository.save(usuario);
            return ResponseEntity.ok(guardado);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    // DELETE usuario
    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar usuario")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
            Map<String, String> response = new HashMap<>();
            response.put("mensaje", "Usuario eliminado exitosamente");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Usuario no encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
    
    // LOGIN usuario
    @PostMapping("/login")
    @Operation(summary = "Login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String contrasena = credentials.get("contrasena");
        
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmailAndContrasena(email, contrasena);
        
        if (usuarioOpt.isPresent()) {
            return ResponseEntity.ok(usuarioOpt.get());
        } else {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Email o contraseña incorrectos");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
        }
    }
}