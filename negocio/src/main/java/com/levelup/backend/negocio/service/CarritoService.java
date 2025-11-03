package com.levelup.backend.negocio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.levelup.backend.negocio.model.CarritoItem;
import com.levelup.backend.negocio.repository.CarritoRepository;

@Service
public class CarritoService {
    @Autowired
    private CarritoRepository carritoRepository;

    // ----------------------------------------------------------
    // CREATE

    public CarritoItem guardarItem(CarritoItem item) {
        return carritoRepository.save(item);
    }

    // ----------------------------------------------------------
    // READ
    public List<CarritoItem> obtenerTodos() {
        return carritoRepository.findAll();
    }

    public CarritoItem obtenerCarritoItem(Long id) {
        return carritoRepository.findById(id).orElse(null);
    }

    // ----------------------------------------------------------
    // UPDATE

    // ----------------------------------------------------------
    // DELETE

    public void eliminarCarritoItem(Long id) {
        carritoRepository.deleteById(id);
    }
    
}
