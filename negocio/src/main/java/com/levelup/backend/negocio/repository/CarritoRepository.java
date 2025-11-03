package com.levelup.backend.negocio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.levelup.backend.negocio.model.CarritoItem;

@Repository
public interface CarritoRepository extends JpaRepository<CarritoItem, Long> {
    // Aquí van los metodso.
    // Necesiuto primero hacer una lista de los metodos que necesitamos.
}
