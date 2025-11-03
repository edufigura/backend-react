package com.levelup.backend.negocio.model;

import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CarritoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Por fa poner aquó los atributos del producto-
    // De momento solo están algunas que se me ocurren
    private String nombre;
    private Integer cantidad;
    private Double precio;
    private Double subtotal;

    //Poner Getters y Setters
}
