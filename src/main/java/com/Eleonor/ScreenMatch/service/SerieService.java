package com.Eleonor.ScreenMatch.service;

import com.Eleonor.ScreenMatch.dto.EpisodioDTO;
import com.Eleonor.ScreenMatch.dto.SerieDTO;
import com.Eleonor.ScreenMatch.models.serie.Categoria;
import com.Eleonor.ScreenMatch.models.serie.ISerieRepository;
import com.Eleonor.ScreenMatch.models.serie.Serie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SerieService {

    @Autowired
    private ISerieRepository repository;
    private Optional<Serie> serie;

    public List<SerieDTO> obtenerTodasLasSeries() {
        return convierteDatos(repository.findAll());
    }
    public List<SerieDTO> convierteDatos(List<Serie> serie){
        return serie.stream()
                .map( s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getGenero(),
                        s.getActores(),
                        s.getSinopsis()))
                .collect(Collectors.toList());

    }

    public List<SerieDTO> obtenertop5() {
        return convierteDatos(repository.findTop5ByOrderByEvaluacionDesc());
    }

    public List<SerieDTO> obtenerNuevosLanzamientos() {
        return convierteDatos(repository.nuevosLanzamientos());
    }

    public SerieDTO obtenerSeriePorId(Long id) {
        serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return new SerieDTO(
                    s.getId(),
                    s.getTitulo(),
                    s.getTotalTemporadas(),
                    s.getEvaluacion(),
                    s.getPoster(),
                    s.getGenero(),
                    s.getActores(),
                    s.getSinopsis());
        }
        return  null;
    }

    public List<EpisodioDTO> obtenerTodosLosEpisodios(Long id) {
        serie = repository.findById(id);
        if (serie.isPresent()){
            Serie s = serie.get();
            return s.getEpisodios().stream()
                    .map( e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
                    .collect(Collectors.toList());
        }
        return null;
    }

    public List<EpisodioDTO> obtenerEpisodiosPorTemporada(Long id, Integer numero) {
        return repository.obtenerTemporadaPorNumero(id,numero).stream()
                .map( e -> new EpisodioDTO(e.getTemporada(),e.getTitulo(),e.getNumeroEpisodio()))
                .collect(Collectors.toList());

    }

    public List<SerieDTO> obtenerSeriesPorGenero(String nombreGenero) {
        return repository.findByGenero(Categoria.fromName(nombreGenero)).stream()
                .map( s -> new SerieDTO(
                        s.getId(),
                        s.getTitulo(),
                        s.getTotalTemporadas(),
                        s.getEvaluacion(),
                        s.getPoster(),
                        s.getGenero(),
                        s.getActores(),
                        s.getSinopsis()
                ))
                .collect(Collectors.toList());
    }
}
