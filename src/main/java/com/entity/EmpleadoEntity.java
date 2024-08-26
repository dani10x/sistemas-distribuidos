package com.entity;

import java.io.Serializable;
import java.util.List;

public class EmpleadoEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private List<Integer> salarios;

    public EmpleadoEntity(List<Integer> salarios, String nombre) {
        this.salarios = salarios;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Integer> getSalarios() {
        return salarios;
    }

    public void setSalarios(List<Integer> salarios) {
        this.salarios = salarios;
    }
}
