package com.Eleonor.ScreenMatch.models.serie;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ISerieRepository extends JpaRepository<Serie,Long> {
    Optional<Serie> findByTituloContainsIgnoreCase(String movieTitle);

    List<Serie> findTop5ByOrderByEvaluacionesDesc();
}
