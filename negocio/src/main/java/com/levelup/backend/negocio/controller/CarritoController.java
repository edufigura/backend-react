package com.levelup.backend.negocio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.levelup.backend.negocio.model.CarritoItem;
import com.levelup.backend.negocio.service.CarritoService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {
    @Autowired
    private CarritoService carritoService;
    
    // ----------------------------------------------------------
    // CREATE
    @PostMapping
    public CarritoItem crearItem(@RequestBody CarritoItem item) {
        return carritoService.guardarItem(item);
    }
    
    // ----------------------------------------------------------
    // READ
    @GetMapping
    public List<CarritoItem> obtenerTodos() {
        return carritoService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public CarritoItem obtenerCarritoItem(@PathVariable Long id) {
        return carritoService.obtenerCarritoItem(id);
    }
    // ----------------------------------------------------------
    // UPDATE

    
    // ----------------------------------------------------------
    // DELETE
    @DeleteMapping("/{id}")
    public void eliminarCarritoItem(@PathVariable Long id) {
        carritoService.eliminarCarritoItem(id);
    }
}
