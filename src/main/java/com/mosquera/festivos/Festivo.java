package com.mosquera.festivos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Festivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int dia;
    private int mes;
    private String nombre;
    private int tipo;
    private Integer diasPascua;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(final int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(final int mes) {
        this.mes = mes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(final int tipo) {
        this.tipo = tipo;
    }

    public Integer getDiasPascua() {
        return diasPascua;
    }

    public void setDiasPascua(final Integer diasPascua) {
        this.diasPascua = diasPascua;
    }
}