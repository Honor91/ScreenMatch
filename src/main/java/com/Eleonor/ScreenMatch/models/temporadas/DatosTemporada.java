package com.Eleonor.ScreenMatch.models.temporadas;

import com.Eleonor.ScreenMatch.models.episodios.DatosEpisodio;
import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTemporada(
        @JsonAlias("Season") Integer numero,
        @JsonAlias("Episodes") List<DatosEpisodio> episodios
) {
}
