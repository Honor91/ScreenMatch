package com.Eleonor.ScreenMatch.models.episodios;

import com.Eleonor.ScreenMatch.models.serie.Serie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


@Entity
@Table(name="episodios")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,columnDefinition = "INT DEFAULT 1")
    private Integer temporada = 1;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String titulo = "";
    @Column(nullable = false,columnDefinition = "DATE DEFAULT NULL")
    private LocalDate fechaDeLanzamiento;
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer numeroEpisodio;
    @Column(nullable = false)
    private Double evaluacion = 0.0;

    @ManyToOne()
    @JoinColumn(name="serie_id",nullable = false)
    private Serie serie;

    public Episodio(){}

    public Episodio(Integer numero, DatosEpisodio d) {
        this.temporada = numero;
        this.titulo = d.titulo();
        this.numeroEpisodio = d.numeroEpisodio();
        try {
            this.evaluacion = Double.valueOf(d.evaluacion());
        } catch (NumberFormatException e){
            this.evaluacion = 0.0;
        }

        try {
            this.fechaDeLanzamiento = LocalDate.parse(d.fechaLanzamiento());
        } catch (DateTimeParseException e){
            this.fechaDeLanzamiento = null;
        }
    }

    public Serie getSerie() {
        return serie;
    }
    public void setSerie(Serie serie) {
        this.serie = serie;
    }
    public Integer getTemporada() {
        return temporada;
    }
    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public LocalDate getFechaDeLanzamiento() {
        return fechaDeLanzamiento;
    }
    public void setFechaDeLanzamiento(LocalDate fechaDeLanzamiento) {
        this.fechaDeLanzamiento = fechaDeLanzamiento;
    }
    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }
    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }
    public Double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return  "Temporada: " +  temporada + ", " +
                "Titulo: " + titulo + ", " +
                "Nro Episodio: " + numeroEpisodio + ", " +
                "Evaluacion: " + evaluacion + ", " +
                "Fecha de lanzamiento: " + fechaDeLanzamiento ;
    }
}
