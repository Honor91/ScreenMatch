package com.Eleonor.ScreenMatch.models.episodios;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Released") String fechaLanzamiento,
        @JsonAlias("Episode") String numeroEpisodio,
        @JsonAlias("imdbRating") String evaluacion

) {
}
