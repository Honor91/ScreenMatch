package com.Eleonor.ScreenMatch.models.serie;

import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.List;
import java.util.OptionalDouble;

@Entity
@Table(name = "series")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String titulo = "";
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer totalTemporadas= 0;
    @Column(nullable = false)
    private double evaluacion = 0.0;
    @Column(nullable = false, columnDefinition = "VARCHAR(500) DEFAULT ''")
    private String poster = "";
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "VARCHAR(50) DEFAULT 'DESCONOCIDO'")
    private Categoria genero;
    @Column(nullable = false, columnDefinition = "VARCHAR(255) DEFAULT ''")
    private String actores = "";
    @Column(nullable = false, columnDefinition = "TEXT DEFAULT ''")
    private String sinopsis = "";

    @OneToMany(mappedBy = "serie",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Episodio> episodios;

    public Serie(DatosSerie datosSerie){
        this.titulo = datosSerie.titulo();
        this.totalTemporadas = datosSerie.totalTemporadas();
        this.evaluacion = datosSerie.evaluacion() != null ? Double.valueOf(datosSerie.evaluacion()) : 0.0;
//        this.evaluacion = OptionalDouble.of(Double.valueOf(datosSerie.evaluacion())).orElse(0);
        this.poster = datosSerie.poster();
        this.genero = Categoria.fromString(datosSerie.genero().split(",")[0].trim());
        this.actores = datosSerie.actores();
        this.sinopsis = datosSerie.sinopsis();
    }
    public List<Episodio> getEpisodios() {
        return episodios;
    }

    public void setEpisodios(List<Episodio> episodios) {
        episodios.forEach(e -> e.setSerie(this));
        this.episodios = episodios;
    }


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


    @Override
    public String toString() {
        return
                "Nombre de la Serie: " + titulo + "\n" +
                "Total de Temporadas: " + totalTemporadas + "\n" +
                "Evaluacion: " + evaluacion + "\n" +
                "Poster: " + poster + "\n" +
                "Genero: " + genero + "\n" +
                "Actores: " + actores + "\n" +
                "Sinopsis: " + sinopsis + "\n" +
                "Episodios: " + episodios;
    }
}
