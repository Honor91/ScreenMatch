package com.Eleonor.ScreenMatch.models.episodios;

import com.Eleonor.ScreenMatch.models.serie.Serie;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name="episodios")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Episodio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer temporada;
    private String titulo;
    private LocalDate fechaDeLanzamiento;
    private Integer numeroEpisodio;
    private Double evaluacion;

    @ManyToOne()
    @JoinColumn(name="serie_id",nullable = false)
    private Serie serie;



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
        return  " Temporada: " +  temporada + "\n" +
                " Titulo: " + titulo + '\'' + "\n" +
                " Nro Episodio: " + numeroEpisodio + "\n" +
                " Evaluacion: " + evaluacion + "\n" +
                " Fecha de lanzamiento: " + fechaDeLanzamiento + "\n";
    }
}
