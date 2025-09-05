package com.Eleonor.ScreenMatch.models.serie;

import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "series")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    private Integer totalTemporadas;
    private double evaluacion;
    private String poster;
    @Enumerated(EnumType.STRING)
    private Categoria genero;
    private String actores;
    private String sinopsis;

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Episodio> episodios;





    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getTotalTemporadas() {
        return totalTemporadas;
    }
    public void setTotalTemporadas(Integer totalTemporadas) {
        this.totalTemporadas = totalTemporadas;
    }
    public double getEvaluacion() {
        return evaluacion;
    }
    public void setEvaluacion(double evaluacion) {
        this.evaluacion = evaluacion;
    }
    public String getPoster() {
        return poster;
    }
    public void setPoster(String poster) {
        this.poster = poster;
    }
    public Categoria getGenero() {
        return genero;
    }
    public void setGenero(Categoria genero) {
        this.genero = genero;
    }
    public String getActores() {
        return actores;
    }
    public void setActores(String actores) {
        this.actores = actores;
    }
    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }
    public List<Episodio> getEpisodios() {
        return episodios;
    }
    public void setEpisodios(List<Episodio> episodios) {
        this.episodios = episodios;
    }

    @Override
    public String toString() {
        return
                " Nombre de la Serie:" + titulo + "\n" +
                " Total de Temporadas:" + totalTemporadas + "\n" +
                " Evaluacion:" + evaluacion + "\n" +
                " Poster: " + poster + "\n" +
                " Genero: " + genero + "\n" +
                " Actores: " + actores + "\n" +
                " Sinopsis: " + sinopsis + "\n" +
                " Episodios: " + episodios;
    }
}
