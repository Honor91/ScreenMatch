package com.Eleonor.ScreenMatch.models.serie;

import com.Eleonor.ScreenMatch.models.episodios.Episodio;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.List;
import java.util.Optional;

public interface ISerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String movieTitle);
    List<Serie> findTop5ByOrderByEvaluacionDesc();
    List<Serie> findByGenero(Categoria myCategory);
    // Tener en cuenta que Serie, totalTemporadas, evaluacion tiene que venir de la Sere.java no de la entidad (base de datos)
    @Query(
            value = "SELECT s FROM Serie s WHERE s.totalTemporadas <= :totalTemporadas AND s.evaluacion >= :minimaEvaluacion"
    )
    List<Serie> seriesPorTemporadaYEvaluacion(@Param("totalTemporadas") int temporadas,
                                              @Param("minimaEvaluacion") Double minimaEvaluacion);

    @Query(
            value = "SELECT e FROM Serie s Join s.episodios e WHERE s.titulo = :titulo Order By e.evaluacion DESC "
    )
    List<Episodio> top5Episodios(@Param("titulo") String titulo, Pageable pageable);

    @Query(
            value = "SELECT e FROM Serie s Join s.episodios e WHERE e.titulo ILIKE %:episodeTitle%"
    )
    List<Episodio> episodiosPorNombre(@Param("episodeTitle") String episodeTitle);



}

