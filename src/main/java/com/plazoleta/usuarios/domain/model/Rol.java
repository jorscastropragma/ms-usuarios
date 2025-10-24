package com.plazoleta.usuarios.domain.model;

public class Rol {
    private Long id;
    private String nombreRol;

    public Rol(Long id, String nombreRol) {
        this.id = id;
        this.nombreRol = nombreRol;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreRol() {return nombreRol;}

    public void setNombreRol(String nombreRol) {this.nombreRol = nombreRol;}
}
