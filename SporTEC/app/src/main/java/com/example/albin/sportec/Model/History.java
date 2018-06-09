package com.example.albin.sportec.Model;

public class History {

    // Members variables
    private Long id;
    private Long idTeam;
    private String descripcion;
    private String date;

    public History(Long id, Long idTeam, String descripcion, String date) {
        this.id = id;
        this.idTeam = idTeam;
        this.descripcion = descripcion;
        this.date = date;
    }

    public History() {
        //Nothing
    }

    public Long getId() {
        return id;
    }

    public Long getIdTeam() {
        return idTeam;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDate() {
        return date;
    }

}
