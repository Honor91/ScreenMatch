package com.Eleonor.ScreenMatch.dto;

import com.Eleonor.ScreenMatch.models.serie.Categoria;

public record SerieDTO(
        Long id,
        String titulo,
        Integer totalTemporadas,
        Double evaluacion,
        String poster,
        Categoria genero,
        String actores,
        String sinopsis
) {
}
